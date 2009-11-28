package com.aether.present;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.gap.jseed.ServiceStore;

import com.aether.model.character.CharacterLocator;
import com.aether.model.character.PCLocator;
import com.aether.present.game.InGameWorldWindow;
import com.aether.present.hud.HUDLoader;
import com.aether.present.state.ActiveState;
import com.aether.present.state.CharacterCreationPresenter;
import com.aether.present.state.CharacterCreationView;
import com.aether.present.state.FinishGameService;
import com.aether.present.state.GameStateTransition;
import com.aether.present.state.InGamePresenter;
import com.aether.present.state.InGameView;
import com.aether.present.state.MainMenuPresenter;
import com.aether.present.state.MainMenuView;
import com.aether.present.state.ShutdownService;
import com.aether.present.state.StateTransition;
import com.jme.app.AbstractGame;
import com.jme.input.MouseInput;
import com.jme.renderer.Camera;
import com.jme.renderer.ColorRGBA;
import com.jme.util.Debug;
import com.jme.util.TextureManager;
import com.jme.util.geom.BufferUtils;
import com.jmex.game.StandardGame;

public class Main {

	private static StandardGame game;

	public static void startGame() {
		try {
			Main.main(new String[] {});
		} catch (IOException e) {
			throw new IllegalStateException("Unable to start game: ", e);
		}
	}
	
	public static void main(String[] args) throws IOException {
		game = new StandardGame("Aether RPG");
		game.setBackgroundColor(ColorRGBA.darkGray);
		game.start();

		MouseInput.get().setCursorVisible(true);
		ServiceStore store = new ServiceStore();
		
		store.bind(Camera.class, game.getCamera());
		
		loadServices(game, store);

		new UILookAndFeel().loadBaseStyleSheet();
		bindGameViewStates(store);
	}

	private static void loadServices(StandardGame game, ServiceStore store) {
		store.bind(AbstractGame.class, game);
		store.bind(ShutdownService.class, FinishGameService.class);
		store.bind(StateTransition.class, GameStateTransition.class);
		store.bind(CharacterLocator.class, PCLocator.class);
	}

	private static void bindGameViewStates(ServiceStore store) {
		HUDLoader.initialize(store);
		
		store.bind(InGameWorldWindow.class, InGameWorldWindow.class);
		
		store.bind(MainMenuView.class, MainMenuWindow.class);
		store.bind(MainMenuPresenter.class, MainMenuPresenter.class);
		store.bind(CharacterCreationView.class, CharacterCreationWindow.class);
		store.bind(CharacterCreationPresenter.class, CharacterCreationPresenter.class);
		store.bind(InGameView.class, InGameWindow.class);
		store.bind(InGamePresenter.class, InGamePresenter.class);

		StateTransition stateTransition = store.get(StateTransition.class);

		ActiveState mainMenu = store.get(MainMenuPresenter.class);
		ActiveState createCharacter = store.get(CharacterCreationPresenter.class);
		InGamePresenter inGame = store.get(InGamePresenter.class);

		stateTransition.add(mainMenu, MainMenuPresenter.CREATE_CHARACTER_TRANSITION, createCharacter);
		stateTransition.add(createCharacter, CharacterCreationPresenter.CANCEL_CREATE_CHARACTER_TRANSITION, mainMenu);
		stateTransition.add(createCharacter, CharacterCreationPresenter.GAME_WINDOW_TRANSITION, inGame);
		stateTransition.add(inGame, InGamePresenter.OPTIONS_MENU_TRANSITION, mainMenu);
		
		stateTransition.setStartState(mainMenu);
	}

	public static void shutdown() {
		game.shutdown();
		assureGameIsShutdown();
	}

	public static void assureGameIsShutdown() {
		while (game.isStarted()) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static StandardGame getGame() {
		return game;
	}
}