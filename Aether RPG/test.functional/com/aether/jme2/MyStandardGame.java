package com.aether.jme2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import jmetest.flagrushtut.Lesson3;
import jmetest.flagrushtut.lesson9.ForceFieldFence;

import com.aether.present.game.MyInputHandler;
import com.jme.bounding.BoundingBox;
import com.jme.image.Texture;
import com.jme.input.ChaseCamera;
import com.jme.input.thirdperson.ThirdPersonMouseLook;
import com.jme.math.FastMath;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.shape.Box;
import com.jme.scene.state.CullState;
import com.jme.scene.state.TextureState;
import com.jme.scene.state.CullState.Face;
import com.jme.system.DisplaySystem;
import com.jme.system.PropertiesGameSettings;
import com.jme.util.TextureManager;
import com.jme.util.Timer;
import com.jmex.editors.swing.settings.GameSettingsPanel;
import com.jmex.game.StandardGame;
import com.jmex.game.StandardGame.GameType;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;
import com.jmex.terrain.TerrainBlock;
import com.jmex.terrain.util.MidPointHeightMap;
import com.jmex.terrain.util.ProceduralTextureGenerator;

public class MyStandardGame {
	private static Node player;
	private static TerrainBlock tb;
	private static ChaseCamera chaser;
	private static Timer timer;
	private static MyInputHandler inputHandler;
	
	public static void main(String[] args) throws Exception {
		PropertiesGameSettings settings = loadSettings();
		StandardGame game = new StandardGame("TestGame", GameType.GRAPHICAL, settings);
		game.start();
		timer = Timer.getTimer();

		BasicGameState gameState = new BasicGameState("Go Team");

		GameStateManager.getInstance().attachChild(gameState);
		gameState.setActive(true);

		setupTerrain(gameState);
		buildPlayer(gameState);
		buildChaseCamera(game);
		buildForceField(gameState);
		buildCullState(gameState);
		buildUpdateNode(gameState);

		gameState.getRootNode().updateRenderState();
	}

	private static PropertiesGameSettings loadSettings()
			throws InterruptedException, IOException {
		PropertiesGameSettings settings = new PropertiesGameSettings(
				"game.properties");
		if (!settings.load()) {
			if (!GameSettingsPanel.prompt(settings, "Settings for you")) {
				return settings;
			}
			settings.save();
		}
		return settings;
	}

	private static void buildUpdateNode(BasicGameState gameState) {
		gameState.getRootNode().attachChild(new Node() {
			private static final long serialVersionUID = 1L;

			@Override
			public void updateGeometricState(float time, boolean initiator) {
				super.updateGeometricState(time, initiator);
				float characterMinHeight = tb.getHeight(player
						.getLocalTranslation())
						+ ((BoundingBox) player.getWorldBound()).yExtent;
				if (!Float.isInfinite(characterMinHeight)
						&& !Float.isNaN(characterMinHeight)) {
					player.getLocalTranslation().y = characterMinHeight;
				}
				
				timer.update();
		        float interpolation = timer.getTimePerFrame();
		        inputHandler.update(interpolation);
		        chaser.update(interpolation);
			}
		});
	}

	private static void buildCullState(BasicGameState gameState) {
		CullState cs = DisplaySystem.getDisplaySystem().getRenderer()
				.createCullState();
		cs.setCullFace(Face.Back);
		gameState.getRootNode().setRenderState(cs);
	}

	private static void buildForceField(BasicGameState gameState) {
		ForceFieldFence fence = new ForceFieldFence("the fence");
		inputHandler = new MyInputHandler(player);
		gameState.getRootNode().attachChild(fence);
	}

	private static void buildChaseCamera(StandardGame game) {
		Vector3f targetOffset = new Vector3f();
		targetOffset.y = ((BoundingBox) player.getWorldBound()).yExtent * 1f;
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(ThirdPersonMouseLook.PROP_MAXROLLOUT, "10");
		props.put(ThirdPersonMouseLook.PROP_MINROLLOUT, "2");
		props.put(ChaseCamera.PROP_TARGETOFFSET, targetOffset);
		props.put(ThirdPersonMouseLook.PROP_MAXASCENT, "" + 20 * FastMath.DEG_TO_RAD);
		props.put(ChaseCamera.PROP_INITIALSPHERECOORDS, new Vector3f(5, 0, 0 * FastMath.DEG_TO_RAD));
		props.put(ChaseCamera.PROP_TARGETOFFSET, targetOffset);
		Camera cam = game.getCamera();
		chaser = new ChaseCamera(cam, (Spatial) player, props);
		chaser.setMaxDistance(8);
		chaser.setMinDistance(7);
		chaser.setLooking(true);
		chaser.setStayBehindTarget(true);
	}

	private static void buildPlayer(BasicGameState gameState) {
		// box stand in
		Box b = new Box("box", new Vector3f(), 0.35f, 0.25f, 0.5f);
		b.setModelBound(new BoundingBox());
		b.updateModelBound();

		player = new Node("Player Node");
		player.setLocalTranslation(new Vector3f(100, 0, 100));
		gameState.getRootNode().attachChild(player);
		player.attachChild(b);
		player.updateWorldBound();
	}

	private static void setupTerrain(BasicGameState gameState) {
		MidPointHeightMap heightMap = new MidPointHeightMap(64, 0.1f);
		Vector3f terrainScale = new Vector3f(20, 0.01f, 20);
		tb = new TerrainBlock("Terrain", heightMap.getSize(), terrainScale,
				heightMap.getHeightMap(), new Vector3f(0, 0, 0));

		tb.setModelBound(new BoundingBox());
		tb.updateModelBound();
		ProceduralTextureGenerator pt = new ProceduralTextureGenerator(
				heightMap);
		pt.addTexture(new ImageIcon(Lesson3.class.getClassLoader().getResource(
				"jmetest/data/texture/grassb.png")), -128, 0, 128);
		pt.addTexture(new ImageIcon(Lesson3.class.getClassLoader().getResource(
				"jmetest/data/texture/dirt.jpg")), 0, 128, 255);
		pt.addTexture(new ImageIcon(Lesson3.class.getClassLoader().getResource(
				"jmetest/data/texture/highest.jpg")), 128, 255, 384);
		pt.createTexture(32);
		tb.updateRenderState();
		gameState.getRootNode().attachChild(tb);

		TextureState ts = DisplaySystem.getDisplaySystem().getRenderer()
				.createTextureState();
		Texture t1 = TextureManager.loadTexture(pt.getImageIcon().getImage(),
				Texture.MinificationFilter.Trilinear,
				Texture.MagnificationFilter.Bilinear, true);
		ts.setTexture(t1, 0);
		tb.setRenderState(ts);
	}
}
