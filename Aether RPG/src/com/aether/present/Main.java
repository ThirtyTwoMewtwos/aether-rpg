package com.aether.present;

import org.gap.jseed.ServiceStore;

import com.aether.present.state.ShutdownService;
import com.jme.app.AbstractGame;
import com.jme.input.MouseInput;
import com.jmex.game.StandardGame;
import com.jmex.game.state.GameStateManager;

public class Main {
	public static void main(String[] args) {
		StandardGame game = new StandardGame("ATechnique");
		game.start();
		
		MouseInput.get().setCursorVisible(true);
		ServiceStore store = new ServiceStore();
		store.bind(AbstractGame.class, game);
		store.bind(ShutdownService.class, ShutdownService.class);
		store.bind(MainWindow.class, MainWindow.class);

		bindGameState(store);
	}

	private static void bindGameState(ServiceStore store) {
		MainWindow mainWindow = store.get(MainWindow.class);
		GameStateManager.getInstance().attachChild(mainWindow);
		mainWindow.activate();
	}
}
