package com.aether.present;

import java.awt.Image;

import com.aether.gbui.BDraggableChatWindow;
import com.aether.gbui.BMeterBar;
import com.aether.present.game.InGameWorldWindow;
import com.aether.present.state.InGamePresenter;
import com.aether.present.state.InGameView;
import com.jme.image.Texture;
import com.jme.image.Texture.ApplyMode;
import com.jme.image.Texture.CombinerFunctionRGB;
import com.jme.image.Texture.CombinerOperandRGB;
import com.jme.image.Texture.CombinerScale;
import com.jme.image.Texture.CombinerSource;
import com.jme.input.KeyInput;
import com.jme.light.DirectionalLight;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Node;
import com.jme.scene.state.CullState;
import com.jme.scene.state.FogState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.TextureState;
import com.jme.scene.state.FogState.DensityFunction;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jmex.bui.BChatComponent;
import com.jmex.bui.BChatWindow;
import com.jmex.bui.BImage;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.icon.BlankIcon;
import com.jmex.bui.icon.ImageIcon;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.listener.ChatListener;
import com.jmex.bui.util.Rectangle;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;
import com.jmex.terrain.TerrainPage;
import com.jmex.terrain.util.FaultFractalHeightMap;
import com.jmex.terrain.util.ProceduralTextureGenerator;

public class InGameWindow extends BaseWindow implements InGameView {
	private static final String CHAT_WINDOW = "chat.window";
	private static final String STATS_WINDOW = "stats.window";
	private static final String OPTIONS_MENU_KEY_BINDING = "options.menu";
	private static final String PERSONA_VIEW_KEY_BINDING = "persona.view";
    private static final String JOURNAL_VIEW_KEY_BINDING = "journal.view";
	
	private InGamePresenter presenter;
	private BMeterBar healthMeter;
	private BMeterBar manaMeter;
	private BLabel characterIcon;
	private final InGameWorldWindow window;

	public InGameWindow(InGameWorldWindow window) {
		super("Game View");
		this.window = window;
		bindKeysToView();

		BWindow statsWindow = initStatsWindow(); 
		initStatistics(statsWindow);
		
		BChatWindow chatWindow = initChatWindow();
		initChatBox(chatWindow);

		GameStateManager.getInstance().attachChild(this);
	}

	private BWindow initStatsWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow window = new BDraggableWindow(BuiSystem.getStyle(), layout);
		window.setName(STATS_ID);
		window.setSize(34, 100);
		window.setLocation(10, 350);
		addWindow(STATS_WINDOW, window);
		
		return window;
	}

	private void initStatistics(BWindow window) {
		characterIcon = new BLabel(new BlankIcon(24, 24), "");
		characterIcon.setName(STATS_SYMBOL_ID);
		window.add(characterIcon, new Rectangle(0, window.getHeight() - 28, 24, 24));
		
		healthMeter = new BMeterBar(STATS_HEALTH_ID);
		healthMeter.setStyleClass(UILookAndFeel.STATISTICS_HEALTH);
		healthMeter.setTooltipStyleClass(UILookAndFeel.STATISTICS_TOOLTIP_TEXT);
		window.add(healthMeter, new Rectangle(1, 0, 10, window.getHeight() - 30));
		
		manaMeter = new BMeterBar(STATS_MANA_ID);
		manaMeter.setStyleClass(UILookAndFeel.STATISTICS_MANA_POINTS);
		manaMeter.setTooltipStyleClass(UILookAndFeel.STATISTICS_TOOLTIP_TEXT);
		window.add(manaMeter, new Rectangle(14, 0, 10, window.getHeight() - 30));
	}

	private BChatWindow initChatWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BChatWindow window = new BDraggableChatWindow(BuiSystem.getStyle(), layout);
		window.setName(CHAT_ID);
		window.setSize(250, 100);
		window.setLocation(10, 10);
		window.setStyleClass(UILookAndFeel.CHAT_LAYOUT);
		addWindow(CHAT_WINDOW, window);
		return window;
	}

	private void initChatBox(BChatWindow window) {
		BChatComponent chatComponent = new BChatComponent("chat component", new ChatListener(window));
		chatComponent.getInput().setStyleClass(UILookAndFeel.CHAT_INPUT);
		window.add(chatComponent, new Rectangle(0, 0, 230, 100));
	}

	@Override
	public void activate() {
		super.activate();
		window.setActive(true);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		window.setActive(false);
	}

	private void bindKeysToView() {
		registerBinding(OPTIONS_MENU_KEY_BINDING, KeyInput.KEY_ESCAPE);
		registerBinding(PERSONA_VIEW_KEY_BINDING, KeyInput.KEY_P);
        registerBinding(JOURNAL_VIEW_KEY_BINDING, KeyInput.KEY_J);
	}

	@Override
	protected void handleBinding(String name) {
		if (OPTIONS_MENU_KEY_BINDING.equals(name)) {
			presenter.optionsMenu();
		} else if (PERSONA_VIEW_KEY_BINDING.equals(name)) {
			presenter.togglePersona();
		}else if (JOURNAL_VIEW_KEY_BINDING.equals(name)) {
			presenter.toggleQuestJournal();
		}
	}

	public void setPresenter(InGamePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setHealth(int maximum, int current) {
		healthMeter.setMaximum(maximum);
		healthMeter.setValue(current);
	}
	
	@Override
	public void setMana(int maximum, int current) {
		manaMeter.setMaximum(maximum);
		manaMeter.setValue(current);
	}
	
	@Override
	public void setImage(Image image) {
		ImageIcon icon = new ImageIcon(new BImage(image));
		characterIcon.setIcon(icon);
	}
}
