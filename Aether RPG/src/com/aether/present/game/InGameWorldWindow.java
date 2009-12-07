package com.aether.present.game;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import jmetest.flagrushtut.Lesson3;
import jmetest.renderer.loader.TestColladaLoading;

import com.aether.present.PlayerMovementState;
import com.jme.animation.AnimationController;
import com.jme.animation.Bone;
import com.jme.animation.BoneAnimation;
import com.jme.animation.SkinNode;
import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.image.Texture.ApplyMode;
import com.jme.image.Texture.CombinerFunctionRGB;
import com.jme.image.Texture.CombinerOperandRGB;
import com.jme.image.Texture.CombinerScale;
import com.jme.image.Texture.CombinerSource;
import com.jme.input.ChaseCamera;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.thirdperson.ThirdPersonMouseLook;
import com.jme.light.DirectionalLight;
import com.jme.light.PointLight;
import com.jme.math.FastMath;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Controller;
import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.scene.Spatial;
import com.jme.scene.Skybox.Face;
import com.jme.scene.shape.Box;
import com.jme.scene.state.CullState;
import com.jme.scene.state.FogState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.scene.state.ZBufferState;
import com.jme.scene.state.FogState.DensityFunction;
import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueue;
import com.jme.util.GameTaskQueueManager;
import com.jme.util.TextureManager;
import com.jme.util.Timer;
import com.jme.util.resource.ResourceLocatorTool;
import com.jme.util.resource.SimpleResourceLocator;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;
import com.jmex.model.collada.ColladaImporter;
import com.jmex.terrain.TerrainPage;
import com.jmex.terrain.util.FaultFractalHeightMap;
import com.jmex.terrain.util.ProceduralTextureGenerator;

public class InGameWorldWindow extends BasicGameState {
	private static final Logger logger = Logger.getLogger(InGameWorldWindow.class.toString());
	private Node player;
	List<Texture> textures = new ArrayList<Texture>();
	
	private Vector3f normal = new Vector3f();
	private AnimationController ac;
	private Node startNode;
	private TerrainPage terrain;
	private LightState lightState;

	private boolean _isPaused;
	private Camera camera;
	private ChaseCamera chaser;
	private Timer timer;
	private MyInputHandler inputHandler;
	private Skybox skybox;
	private ProceduralTextureGenerator proceduralTextureGenerator;
	private TextureState textureState;

	public InGameWorldWindow(Camera theCamera, PlayerMovementState state) {
		super("InGame: mainState");
		this.camera = theCamera;

		DisplaySystem displaySystem = DisplaySystem.getDisplaySystem();
		setupCamera(displaySystem, theCamera);
		setupZBufferState(displaySystem);
		PointLight light = setupBasicLight();
		attachLightToLightState(displaySystem, light);
		buildEnvironment(state);

		GameStateManager.getInstance().attachChild(this);
	}

	private void buildEnvironment(final PlayerMovementState state) {
		GameTaskQueueManager.getManager().getQueue(GameTaskQueue.UPDATE).enqueue(new Callable<Void>() {
			public Void call() throws Exception {
				setupTerrain();
				initSkybox();
				buildPlayer();
				buildChaseCamera();

				rootNode.updateRenderState();
				inputHandler = new MyInputHandler(player, state);

				return null;
			}
		});
		KeyBindingManager.getKeyBindingManager().add("mouse_trigger", KeyInput.KEY_LCONTROL);
	}

	private void setupCamera(DisplaySystem displaySystem, Camera theCamera) {
		theCamera.setFrustumPerspective(60.0f, (float) displaySystem.getWidth() / (float) displaySystem.getHeight(), 1, 1500);
		theCamera.setParallelProjection(false);
		theCamera.update();
	}

	private void setupZBufferState(DisplaySystem displaySystem) {
		ZBufferState buf = displaySystem.getRenderer().createZBufferState();
		buf.setEnabled(true);
		buf.setFunction(ZBufferState.TestFunction.LessThanOrEqualTo);
		rootNode.setRenderState(buf);
	}

	private void attachLightToLightState(DisplaySystem displaySystem, PointLight light) {
		lightState = displaySystem.getRenderer().createLightState();
		lightState.setEnabled(true);
		lightState.attach(light);
		rootNode.setRenderState(lightState);
		timer = Timer.getTimer();
		setupStats();
	}

	private PointLight setupBasicLight() {
		PointLight light = new PointLight();
		light.setDiffuse(new ColorRGBA(0.75f, 0.75f, 0.75f, 0.75f));
		light.setAmbient(new ColorRGBA(0.5f, 0.5f, 0.5f, 1.0f));
		light.setLocation(new Vector3f(100, 100, 100));
		light.setEnabled(true);
		return light;
	}

	private void initSkybox() {
		Skybox skybox = getSkybox(Face.North, Face.South, Face.East, Face.West, Face.Up, Face.Down);
		rootNode.attachChild(skybox);
	}

	private Skybox getSkybox(Face... facings) {
		skybox = new Skybox("skybox", 500, 500, 500);
		for (Face each : facings) {
			java.awt.Image image = SkyboxFacingImage.getImage(each);
			Texture loadTexture = TextureManager.loadTexture(image, Texture.MinificationFilter.BilinearNearestMipMap, Texture.MagnificationFilter.Bilinear, 1, true);
			textures.add(loadTexture);
			skybox.setTexture(each, loadTexture);
		}
		return skybox;
	}

	private void buildPlayer() {
		ClassLoader classLoader = TestColladaLoading.class.getClassLoader();
		try {
			ResourceLocatorTool.addResourceLocator(ResourceLocatorTool.TYPE_TEXTURE, new SimpleResourceLocator(classLoader.getResource("jmetest/data/model/collada/")));
		} catch (URISyntaxException e1) {
			logger.warning("Unable to add texture directory to RLT: " + e1.toString());
		}

		player = new Node("Player Node");
		player.setLocalTranslation(new Vector3f(100, 0, 100));
		getRootNode().attachChild(player);
		player.updateWorldBound();

		// this stream points to the model itself.
		InputStream mobboss = classLoader.getResourceAsStream("jmetest/data/model/collada/man.dae");
		// this stream points to the animation file. Note: You don't necessarily
		// have to split animations out into seperate files, this just helps.
		InputStream animation = classLoader.getResourceAsStream("jmetest/data/model/collada/man_walk.dae");
		if (mobboss == null) {
			logger.info("Unable to find file, did you include jme-test.jar in classpath?");
			System.exit(0);
		}
		// tell the importer to load the mob boss
		ColladaImporter.load(mobboss, "model");
		// we can then retrieve the skin from the importer as well as the
		// skeleton
		SkinNode sn = ColladaImporter.getSkinNode(ColladaImporter.getSkinNodeNames().get(0));
		Bone skel = ColladaImporter.getSkeleton(ColladaImporter.getSkeletonNames().get(0));
		// clean up the importer as we are about to use it again.
		ColladaImporter.cleanUp();

		// load the animation file.
		ColladaImporter.load(animation, "anim");
		// this file might contain multiple animations, (in our case it's one)
		ArrayList<String> animations = ColladaImporter.getControllerNames();
		if (animations != null) {
			logger.info("Number of animations: " + animations.size());
			for (int i = 0; i < animations.size(); i++) {
				logger.info(animations.get(i));
			}
			// Obtain the animation from the file by name
			BoneAnimation anim1 = ColladaImporter.getAnimationController(animations.get(0));

			// set up a new animation controller with our BoneAnimation
			ac = new AnimationController();
			ac.addAnimation(anim1);
			ac.setRepeatType(Controller.RT_WRAP);
			ac.setActive(false);
			ac.setActiveAnimation(anim1);

			// assign the animation controller to our skeleton
			skel.addController(ac);
		}
		player.setLocalScale(0.15f);
		Quaternion pitch90 = new Quaternion();
		pitch90.fromAngleAxis(FastMath.PI / 2, new Vector3f(-1, 0, 0));
		sn.setLocalRotation(pitch90);
		sn.setLocalTranslation(new Vector3f(0, 0, 0));
		sn.setModelBound(new BoundingBox());
		sn.updateModelBound();
		// attach the skeleton and the skin to the rootnode. Skeletons could
		// possibly
		// be used to update multiple skins, so they are seperate objects.
		player.attachChild(sn);
		player.attachChild(skel);

		player.updateGeometricState(0, true);
		// all done clean up.
		ColladaImporter.cleanUp();
	}

	private void buildChaseCamera() {
		Vector3f targetOffset = new Vector3f();
		targetOffset.y = ((BoundingBox) player.getWorldBound()).yExtent * 1f;
		chaser = new ChaseCamera(camera, (Spatial) player);
		chaser.setMaxDistance(8);
		chaser.setMinDistance(7);
		chaser.setEnabled(true);
		chaser.setTargetOffset(targetOffset);
		chaser.setIdealSphereCoords(new Vector3f(5, 0, 20*FastMath.DEG_TO_RAD));
		chaser.getMouseLook().setMinRollOut(10);
		chaser.getMouseLook().setMaxRollOut(40);
		chaser.getMouseLook().setMaxAscent(40 * FastMath.DEG_TO_RAD);
		chaser.setLooking(true);
		chaser.setEnableSpring(true);
		chaser.setEnabledOfAttachedHandlers(false);
		chaser.setStayBehindTarget(true);
	}

	private void setupStats() {
		startNode = new Node("Stats node");
		startNode.setCullHint(Spatial.CullHint.Never);
		startNode.setRenderQueueMode(Renderer.QUEUE_ORTHO);
		rootNode.attachChild(startNode);

		startNode.updateGeometricState(0.0f, true);
		startNode.updateRenderState();
	}

	public void setupTerrain() {
		final Node terrainNode = new Node();
		rootNode.attachChild(terrainNode);

		// this piece is pretty much copied from the jme terrain test
		FaultFractalHeightMap heightMap = new FaultFractalHeightMap(257, 32, 0, 255, 0.75f);
		Vector3f terrainScale = new Vector3f(10, 1, 10);
		heightMap.setHeightScale(0.001f);
		terrain = new TerrainPage("Terrain", 33, heightMap.getSize(), terrainScale, heightMap.getHeightMap());

		terrain.setDetailTexture(1, 16);
		terrainNode.attachChild(terrain);

		proceduralTextureGenerator = new ProceduralTextureGenerator(heightMap);
		proceduralTextureGenerator.addTexture(new ImageIcon(Lesson3.class.getClassLoader().getResource("jmetest/data/texture/grassb.png")), -128, 0, 128);
		proceduralTextureGenerator.addTexture(new ImageIcon(Lesson3.class.getClassLoader().getResource("jmetest/data/texture/dirt.jpg")), 0, 128, 255);
		proceduralTextureGenerator.addTexture(new ImageIcon(Lesson3.class.getClassLoader().getResource("jmetest/data/texture/highest.jpg")), 128, 255, 384);
		proceduralTextureGenerator.createTexture(512);

		textureState = DisplaySystem.getDisplaySystem().getRenderer().createTextureState();
		textureState.setEnabled(true);
		Texture texture1 = TextureManager.loadTexture(proceduralTextureGenerator.getImageIcon().getImage(), Texture.MinificationFilter.Trilinear, Texture.MagnificationFilter.Bilinear, true);
		textureState.setTexture(texture1, 0);

		Texture texture2 = TextureManager.loadTexture(InGameWorldWindow.class.getClassLoader().getResource("jmetest/data/texture/Detail.jpg"), Texture.MinificationFilter.Trilinear,
				Texture.MagnificationFilter.Bilinear);

		textures.add(texture1);
		textures.add(texture2);

		textureState.setTexture(texture2, 1);
		texture2.setWrap(Texture.WrapMode.Repeat);

		texture1.setApply(ApplyMode.Combine);
		texture1.setCombineFuncRGB(CombinerFunctionRGB.Modulate);
		texture1.setCombineSrc0RGB(CombinerSource.CurrentTexture);
		texture1.setCombineOp0RGB(CombinerOperandRGB.SourceColor);
		texture1.setCombineSrc1RGB(CombinerSource.PrimaryColor);
		texture1.setCombineOp1RGB(CombinerOperandRGB.SourceColor);
		texture1.setCombineScaleRGB(CombinerScale.One);

		texture2.setApply(ApplyMode.Combine);
		texture2.setCombineFuncRGB(CombinerFunctionRGB.AddSigned);
		texture2.setCombineSrc0RGB(CombinerSource.CurrentTexture);
		texture2.setCombineOp0RGB(CombinerOperandRGB.SourceColor);
		texture2.setCombineSrc1RGB(CombinerSource.Previous);
		texture2.setCombineOp1RGB(CombinerOperandRGB.SourceColor);
		texture2.setCombineScaleRGB(CombinerScale.One);
		terrainNode.setRenderState(textureState);

		FogState fs = DisplaySystem.getDisplaySystem().getRenderer().createFogState();
		fs.setDensity(0.0015f);
		fs.setEnabled(true);
		fs.setColor(new ColorRGBA(0.5f, 0.55f, 0.5f, 0.5f));
		fs.setDensityFunction(DensityFunction.Exponential);
		fs.setQuality(FogState.Quality.PerVertex);
		terrainNode.setRenderState(fs);

		terrainNode.lock();
		terrainNode.lockBranch();

		CullState cs = DisplaySystem.getDisplaySystem().getRenderer().createCullState();
		cs.setCullFace(CullState.Face.Back);
		cs.setEnabled(true);
		terrainNode.setRenderState(cs);

		lightState.detachAll();

		DirectionalLight dl = new DirectionalLight();
		dl.setDiffuse(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
		dl.setDirection(new Vector3f(1, -0.5f, 1));
		dl.setEnabled(true);
		lightState.attach(dl);

		DirectionalLight dr = new DirectionalLight();
		dr.setEnabled(true);
		dr.setDiffuse(new ColorRGBA(1.0f, 1.0f, 1.0f, 1.0f));
		dr.setAmbient(new ColorRGBA(0.5f, 0.5f, 0.5f, 1.0f));
		dr.setDirection(new Vector3f(0.5f, -0.5f, 0).normalizeLocal());
		lightState.attach(dr);
	}

	@Override
	public void update(float tpf) {
		if (!_isPaused) {
			super.update(tpf);

			float characterMinHeight = terrain.getHeight(player.getLocalTranslation()) + ((BoundingBox) player.getWorldBound()).yExtent;
			if (!Float.isInfinite(characterMinHeight) && !Float.isNaN(characterMinHeight)) {
				player.getLocalTranslation().y = characterMinHeight;
			}

			timer.update();
			float interpolation = timer.getTimePerFrame();
			inputHandler.update(interpolation);
			if (KeyBindingManager.getKeyBindingManager().isValidCommand("mouse_trigger", true)) {
				chaser.getMouseLook().setEnabled(true);
			} else {
				chaser.getMouseLook().setEnabled(false);
			}

			chaser.update(interpolation);
		}
	}

	@Override
	public void render(float tpf) {
		super.render(tpf);

		DisplaySystem.getDisplaySystem().getRenderer().draw(startNode);
	}

	@Override
	public void cleanup() {
		super.cleanup();
		if (textureState == null) {
			return;
		}
		textureState.clearTextures();
		textureState.deleteAll();
		textureState = null;

		lightState.detachAll();
		lightState = null;

		proceduralTextureGenerator.clearTextures();
		proceduralTextureGenerator = null;

		for (Texture each : textures) {
			TextureManager.releaseTexture(each);
		}
		clearNode(skybox);
		skybox = null;

		clearNode(terrain);
		terrain = null;

		clearNode(rootNode);
		rootNode = null;

		TextureManager.doTextureCleanup();
		TextureManager.clearCache();
	}

	private void clearNode(Node node) {
		node.detachAllChildren();
		node.clearControllers();
		node.removeFromParent();
	}
}
