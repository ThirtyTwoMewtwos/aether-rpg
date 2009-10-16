package com.aether.present;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.present.state.MainMenuPresenter;
import com.aether.present.state.MainMenuView;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.system.DisplaySystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Point;
import com.jmex.game.state.GameStateManager;

@Singleton
public class MainWindow extends BaseWindow implements MainMenuView {
	private static final int MENU_ITEMS = 2;
	private static final int BUTTON_HEIGHT = 40;
	private static final int BUTTON_WIDTH = 250;
	
	private BButton startCampaign;
	private BButton exitGame;

	private MainMenuPresenter presenter;
	private int startPosY;
	private int counter = 0;

    public MainWindow() {
		super(ID);

        defineControls();

		DisplaySystem display = DisplaySystem.getDisplaySystem();
		BWindow mainWindow = new BWindow(BuiSystem.getStyle(), new AbsoluteLayout());
		mainWindow.setName(ID);
		setWindow(mainWindow);
        mainWindow.setSize(BUTTON_WIDTH + 150, BUTTON_HEIGHT * MENU_ITEMS + 220);
                
		startPosY = mainWindow.getHeight() - 100;
		int stepPosY = (int)(BUTTON_HEIGHT * 1.5f);
		int posX = (mainWindow.getWidth() - BUTTON_WIDTH) / 2;
		
		BLabel titleLabel = new BLabel("Welcome to Æther");
		titleLabel.setPreferredSize(BUTTON_WIDTH + 80, BUTTON_HEIGHT);
		mainWindow.add(titleLabel, new Point(posX - 40, startPosY - stepPosY * counter++));

		initStartCampaignButton(stepPosY, posX);
        initExitGameButton(stepPosY, posX);
        
		mainWindow.center();		
		
		GameStateManager.getInstance().attachChild(this);
	}

	private void initStartCampaignButton(int stepPosY,  int posX) {
		startCampaign = new BButton("New Campaign");
        startCampaign.setPreferredSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		getWindow().add(startCampaign, new Point(posX, startPosY - stepPosY * counter++));
		startCampaign.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
                presenter.performCreateCharacter();
			}
		});
	}

	private void initExitGameButton(int stepPosY, int posX) {
		exitGame = new BButton("Exit");
		exitGame.setPreferredSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		getWindow().add(exitGame, new Point(posX, startPosY - stepPosY * counter++));
		exitGame.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.performExit();
			}
		});
	}

	private void defineControls() {
		KeyBindingManager keyboard = KeyBindingManager.getKeyBindingManager();
		keyboard.add("exit", KeyInput.KEY_ESCAPE);
	}

	@Override
	public void update(float tpf) {
		super.update(tpf);
		
		handleBindings();
	}

	private void handleBindings() {
		if (KeyBindingManager.getKeyBindingManager().isValidCommand("exit", true)) {
			presenter.performExit();
		}
	}

	@Override
	public void setPresenter(MainMenuPresenter presenter) {
		this.presenter = presenter;
	}
	
}
