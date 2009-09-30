package com.aether.present;

import java.util.concurrent.Callable;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.present.state.ShutdownService;
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

@Singleton
public class MainWindow extends BasicGameState {
	private BWindow mainWindow;
	
	private BButton startCampaign;
	private BButton exitGame;

	private final ShutdownService shutdownService;

	public MainWindow(final ShutdownService service) {
		super("Main Window");
		this.shutdownService = service;
		
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

		startCampaign = new BButton("Play Campaign");
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
				shutdownService.shutdown();
			}
        });
        
		mainWindow.center();		
	}
	
	public void activate() {
		// activate the main GameState
		super.setActive(true);
		
		// Display the GBUI portion
		getRootNode().attachChild(BuiSystem.getRootNode());
		getRootNode().updateRenderState();
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
		        BuiSystem.addWindow(mainWindow);
				return null;
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
		
		if (KeyBindingManager.getKeyBindingManager().isValidCommand("exit", true)) {
			shutdownService.shutdown();
		}
	}
}
