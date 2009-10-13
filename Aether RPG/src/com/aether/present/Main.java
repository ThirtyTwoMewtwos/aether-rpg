package com.aether.present;

import com.aether.present.state.*;
import org.gap.jseed.ServiceStore;

import com.jme.app.AbstractGame;
import com.jme.input.MouseInput;
import com.jmex.game.StandardGame;

public class Main {
	
	public static void main(String[] args) {
		StandardGame game = new StandardGame("Aether RPG");
		game.start();
		
		MouseInput.get().setCursorVisible(true);
		ServiceStore store = new ServiceStore();
		
		loadServices(game, store);
		
		new UILookAndFeel().loadBaseStyleSheet();
		bindGameState(store);
	}

	private static void loadServices(StandardGame game, ServiceStore store) {
		store.bind(AbstractGame.class, game);
		store.bind(ShutdownService.class, FinishGameService.class);
		store.bind(StateTransition.class, GameStateTransition.class);
	}

	private static void bindGameState(ServiceStore store) {
		store.bind(MainMenuView.class, MainWindow.class);
		store.bind(MainMenuPresenter.class, MainMenuPresenter.class);
        store.bind(CharacterCreationView.class, CharacterCreationWindow.class);
        store.bind(CharacterCreationPresenter.class, CharacterCreationPresenter.class);

		StateTransition stateTransition = store.get(StateTransition.class);
		
		ActiveState mainMenu = store.get(MainMenuPresenter.class);
        ActiveState createCharacter = store.get(CharacterCreationPresenter.class);
		
		stateTransition.add(mainMenu, MainMenuPresenter.CREATE_CHARACTER_TRANSITION, createCharacter);
        stateTransition.add(createCharacter, CharacterCreationPresenter.MAIN_MENU_TRANSITION, mainMenu);
        stateTransition.setStartState(mainMenu);
	}
}
