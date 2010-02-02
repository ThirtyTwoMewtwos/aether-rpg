package com.aether.present;

/*
 * LoginWindow.java
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

import org.gap.jseed.contract.annotation.NotNull;
import org.gap.jseed.injection.annotation.Singleton;

import com.aether.present.state.LoginPresenter;
import com.aether.present.state.LoginView;
import com.jme.input.KeyInput;
import com.jme.system.DisplaySystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BLabel;
import com.jmex.bui.BPasswordField;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.text.Document;
import com.jmex.bui.text.Document.Listener;
import com.jmex.bui.util.Point;
import com.jmex.game.state.GameStateManager;

@Singleton
public class LoginWindow extends BaseWindow implements LoginView {
	private static final String DEFAULT_WINDOW = "base.window";
	private static final String EXIT_KEY_BINDING = "Exit";
	private static final String LOGIN_KEY_BINDING = "Login";

	private BTextField username;
	private BPasswordField password;
	private BButton login;
	private BButton exitGame;

	private LoginPresenter presenter;
	private int startPosY;

	public LoginWindow() {
		super(ID);

		bindKeysToView();

		DisplaySystem.getDisplaySystem();
		BWindow mainWindow = new BWindow(BuiSystem.getStyle(), new AbsoluteLayout());
		mainWindow.setName(ID);
		addWindow(DEFAULT_WINDOW, mainWindow);
		mainWindow.setSize(450, 450);

		startPosY = mainWindow.getHeight() - 100;
		int posX = mainWindow.getWidth() / 2;

		initWindowTitle(mainWindow, posX);
		initUsernameInput(mainWindow, posX);
		initPasswordInput(mainWindow, posX);
		initLoginButton(posX);
		initExitGameButton(posX);

		mainWindow.center();

		GameStateManager.getInstance().attachChild(this);
	}

	private void initWindowTitle(BWindow mainWindow, int posX) {
		BLabel welcome = new BLabel("AEther Online");
		welcome.setPreferredSize(200, 20);
		mainWindow.add(welcome, new Point((posX / 2), startPosY));
	}

	private void initUsernameInput(BWindow mainWindow, int posX) {
		BLabel userLabel = new BLabel("Username:");
		userLabel.setPreferredSize(200, 20);
		mainWindow.add(userLabel, new Point((posX / 2) - 70, startPosY - 110));

		username = new BTextField(30);
		username.setName(USERNAME_ID);
		username.setStyleClass(UILookAndFeel.LOGIN_TEXTFIELD_LOOK);
		username.getDocument().addListener(new Listener() {
			@Override
			public void textInserted(Document document, int offset, int length) {
				presenter.setUsername(username.getText());
			}

			@Override
			public void textRemoved(Document document, int offset, int length) {
				presenter.setUsername(username.getText());
			}
		});
		mainWindow.add(username, new Point((posX / 2) - 70, startPosY - 140));
	}

	private void initPasswordInput(BWindow mainWindow, int posX) {
		BLabel pswdLabel = new BLabel("Password:");
		pswdLabel.setPreferredSize(200, 20);
		mainWindow.add(pswdLabel, new Point((posX / 2) - 70, startPosY - 165));

		password = new BPasswordField(30);
		password.setName(PASSWORD_ID);
		password.setStyleClass(UILookAndFeel.LOGIN_TEXTFIELD_LOOK);
		password.getDocument().addListener(new Listener() {
			@Override
			public void textInserted(Document document, int offset, int length) {
				presenter.setPassword(password.getText());
			}

			@Override
			public void textRemoved(Document document, int offset, int length) {
				presenter.setPassword(password.getText());
			}
		});
		mainWindow.add(password, new Point((posX / 2) - 70, startPosY - 195));
	}

	private void initLoginButton(int posX) {
		login = new BButton(LOGIN_KEY_BINDING);
		login.setPreferredSize(50, 20);
		login.setEnabled(false);
		getWindow(DEFAULT_WINDOW).add(login, new Point((posX / 2) - 70, startPosY - 240));
		login.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.performLogin();
			}
		});
	}

	private void initExitGameButton(int posX) {
		exitGame = new BButton(EXIT_KEY_BINDING);
		exitGame.setPreferredSize(50, 20);
		getWindow(DEFAULT_WINDOW).add(exitGame, new Point((posX / 2) + 100, startPosY - 240));
		exitGame.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.performExit();
			}
		});
	}

	private void bindKeysToView() {
		registerBinding(EXIT_KEY_BINDING, KeyInput.KEY_ESCAPE);
		registerBinding(LOGIN_KEY_BINDING, KeyInput.KEY_RETURN);
	}

	@Override
	protected void handleBinding(String name) {
		if (EXIT_KEY_BINDING.equals(name)) {
			presenter.performExit();
		} else if (LOGIN_KEY_BINDING.equals(name)) {
			presenter.performLogin();
		}
	}

	@NotNull
	@Override
	public void setPresenter(LoginPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setEnableLogin(boolean isEnabled) {
		login.setEnabled(isEnabled);
	}

	@Override
	public void setLoginErrorMessage(String string) {
		
	}
}
