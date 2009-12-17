package com.aether.present;

/*
 * Main.java
 *
 * Copyright (c) 2008, Tyler Hoersch
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Wisconsin Oshkosh nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDERS AND CONTRIBUTORS ''AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.gap.jseed.ServiceStore;

import com.aether.model.character.CharacterLocator;
import com.aether.model.character.PCLocator;
import com.aether.model.quests.JournalLoader;
import com.aether.present.game.InGameWorldWindow;
import com.aether.present.game.PCMovementState;
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
import com.aether.present.state.LoginPresenter;
import com.aether.present.state.LoginView;
import com.aether.present.state.ShutdownService;
import com.aether.present.state.StateTransition;
import com.jme.app.AbstractGame;
import com.jme.input.MouseInput;
import com.jme.renderer.Camera;
import com.jme.renderer.ColorRGBA;
import com.jmex.bui.BuiSystem;
import com.jmex.game.StandardGame;

public class Main {
	private static StandardGame game;
	private static List<ShutdownHook> shutdownHooks = new LinkedList<ShutdownHook>();
	private static ServiceStore store;

	public static void startGame() {
		try {
			Thread.sleep(1000);
			Main.main(new String[] {});
			try {
				while (!game.isStarted()) {
					Thread.sleep(20);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new IllegalStateException("Unable to start game: ", e);
		}
	}

	public static void main(String[] args) throws IOException {
		game = new StandardGame(" Aether Online ");
		game.setBackgroundColor(ColorRGBA.darkGray);
		game.start();

		MouseInput.get().setCursorVisible(true);
		store = new ServiceStore();

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
		new JournalLoader(store);
		store.bind(PlayerMovementState.class, PCMovementState.class);
	}

	private static void bindGameViewStates(ServiceStore store) {
		HUDLoader.initialize(store);

		store.bind(InGameWorldWindow.class, InGameWorldWindow.class);
                store.bind(LoginView.class, LoginWindow.class);
		store.bind(LoginPresenter.class, LoginPresenter.class);
		store.bind(MainMenuView.class, MainMenuWindow.class);
		store.bind(MainMenuPresenter.class, MainMenuPresenter.class);
		store.bind(CharacterCreationView.class, CharacterCreationWindow.class);
		store.bind(CharacterCreationPresenter.class, CharacterCreationPresenter.class);
		store.bind(InGameView.class, InGameWindow.class);
		store.bind(InGamePresenter.class, InGamePresenter.class);

		StateTransition stateTransition = store.get(StateTransition.class);

		ActiveState mainMenuScreen = store.get(MainMenuPresenter.class);
		ActiveState createCharacterScreen = store.get(CharacterCreationPresenter.class);
		InGamePresenter inGame = store.get(InGamePresenter.class);

//		stateTransition.add(loginScreen,
//				LoginPresenter.LOGIN_TRANSITION, mainMenuScreen);
//		stateTransition.add(mainMenuScreen,
//				MainMenuPresenter.LOGOUT_TRANSITION,
//				loginScreen);
		stateTransition.add(mainMenuScreen,
				MainMenuPresenter.CREATE_CHARACTER_TRANSITION, createCharacterScreen);
                stateTransition.add(createCharacterScreen, CharacterCreationPresenter.CANCEL_CREATE_CHARACTER_TRANSITION, mainMenuScreen);
                stateTransition.add(createCharacterScreen,CharacterCreationPresenter.GAME_WINDOW_TRANSITION,inGame);
		stateTransition.add(inGame, InGamePresenter.OPTIONS_MENU_TRANSITION,
				mainMenuScreen);

		stateTransition.setStartState(mainMenuScreen);
	}

	public static void shutdown() {
		callShutdownHooks();
		callShutdown();
		
		assureGameIsShutdown();
	}

	private static void callShutdown() {
		BuiSystem.getRootNode().clearBuffers();

		game.shutdown();
	}

	private static void callShutdownHooks() {
		for (ShutdownHook each : shutdownHooks) {
			each.doShutdown();
		}
	}

	public static void assureGameIsShutdown() {
		try {
			while (game.isStarted()) {
				Thread.sleep(20);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static StandardGame getGame() {
		return game;
	}

	public static ServiceStore getServiceStore() {
		return store;
	}

	public static void addShutdownHook(ShutdownHook shutdownHook) {
		shutdownHooks.add(shutdownHook);
	}
}
