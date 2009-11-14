package com.aether.present;

import java.awt.Image;

import com.aether.gbui.BDraggableChatWindow;
import com.aether.gbui.BMeterBar;
import com.aether.present.state.InGamePresenter;
import com.aether.present.state.InGameView;
import com.jme.input.KeyInput;
import com.jme.system.DisplaySystem;
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
import com.jmex.game.state.GameStateManager;

public class InGameWindow extends BaseWindow implements InGameView {
	private static final String CHAT_WINDOW = "chat.window";
	private static final String STATS_WINDOW = "stats.window";
	private static final String OPTIONS_MENU_KEY_BINDING = "options.menu";
	
	private InGamePresenter presenter;
	private BMeterBar healthMeter;
	private BMeterBar manaMeter;
	private BLabel characterIcon;

	public InGameWindow() {
		super("Game View");
		DisplaySystem.getDisplaySystem();
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
		window.setSize(35, 100);
		window.setLocation(10, 350);
		addWindow(STATS_WINDOW, window);
		return window;
	}

	private void initStatistics(BWindow window) {
		characterIcon = new BLabel(new BlankIcon(24, 24), "");
		characterIcon.setName(STATS_SYMBOL_ID);
		window.add(characterIcon, new Rectangle(0, window.getHeight() - 65, 24, window.getHeight() - 4));
		
		healthMeter = new BMeterBar(STATS_HEALTH_ID);
		healthMeter.setStyleClass(UILookAndFeel.STATISTICS_HEALTH);
		healthMeter.setTooltipStyleClass(UILookAndFeel.STATISTICS_TOOLTIP_TEXT);
		window.add(healthMeter, new Rectangle(0, 0, 10, window.getHeight() - 30));
		
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
	}

	private void bindKeysToView() {
		registerBinding(OPTIONS_MENU_KEY_BINDING, KeyInput.KEY_F12);
	}

	@Override
	protected void handleBinding(String name) {
		if (OPTIONS_MENU_KEY_BINDING.equals(name)) {
			presenter.optionsMenu();
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
