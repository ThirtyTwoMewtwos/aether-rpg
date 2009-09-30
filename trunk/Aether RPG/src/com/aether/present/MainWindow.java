package com.aether.present;

import java.util.concurrent.Callable;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.present.state.FinishGameService;
import com.aether.present.state.MainMenuPresenter;
import com.aether.present.state.MainMenuView;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BButton;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Point;
import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;

@Singleton
public class MainWindow extends BasicGameState implements MainMenuView {
	private BWindow mainWindow;
	
	private BButton startCampaign;
	private BButton exitGame;

	private MainMenuPresenter presenter;

	public MainWindow() {
		super("Main Window");
		
		defineControls();

		DisplaySystem display = DisplaySystem.getDisplaySystem();
		mainWindow = new BWindow(BuiSystem.getStyle(), new AbsoluteLayout());
        mainWindow.setSize((int)(0.8f * 1.2f * display.getWidth() / 2), 
        		(int)(1.4f * (display.getHeight() / 2)));
                
		// x,y of component are the lower-left corner
        int startPosY = mainWindow.getHeight() - 100;
        int buttonCount = 4;
        int buttonHeight = (int)(startPosY / (1.5f * buttonCount));
		int stepPosY = (int)(buttonHeight * 1.5f);
        int buttonWidth = (int)(mainWindow.getWidth() * 0.6f);
		int posX = (mainWindow.getWidth() - buttonWidth) / 2;
		int counter = 0;
		
		BLabel titleLabel = new BLabel("Welcome to Æther");
		titleLabel.setPreferredSize(buttonWidth + 80, buttonHeight);
		mainWindow.add(titleLabel, new Point(posX - 40, startPosY - stepPosY * counter++));

		startCampaign = new BButton("Create Campaign");
        startCampaign.setPreferredSize(buttonWidth, buttonHeight);
		mainWindow.add(startCampaign, new Point(posX, startPosY - stepPosY * counter++));
		startCampaign.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("and we are off!");
			}
		});

        exitGame = new BButton("Exit");
        exitGame.setPreferredSize(buttonWidth, buttonHeight);
        mainWindow.add(exitGame, new Point(posX, startPosY - stepPosY * counter++));
        exitGame.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.performExit();
			}
        });
        
		mainWindow.center();		
		
		GameStateManager.getInstance().attachChild(this);
	}

	private void defineControls() {
		KeyBindingManager keyboard = KeyBindingManager.getKeyBindingManager();
		keyboard.add("exit", KeyInput.KEY_ESCAPE);
	}
	
	public void activate() {
		// activate the main GameState
		super.setActive(true);
		
		// Display the GBUI portion
		getRootNode().attachChild(BuiSystem.getRootNode());
		getRootNode().updateRenderState();
		
		addWindow();
	}
	
	private void addWindow() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.addWindow(mainWindow);
				return null;
			}
		});
	}
	
	@Override
	public void deactivate() {
		super.setActive(false);
		
		getRootNode().detachChild(BuiSystem.getRootNode());
		
		removeWindow();
	}
	
	private void removeWindow() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.removeWindow(mainWindow);
				return null;
			}
		});
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
