package com.aether.present;

import com.jme.input.MouseInput;
import com.jmex.game.StandardGame;
import com.jmex.game.state.GameStateManager;

public class Main {
	public static void main(String[] args) {
		StandardGame game = new StandardGame("ATechnique");
		game.start();
		
		MouseInput.get().setCursorVisible(true);
		MainWindow state = new MainWindow(game);
		GameStateManager.getInstance().attachChild(state);
		state.activate();
	}
}
