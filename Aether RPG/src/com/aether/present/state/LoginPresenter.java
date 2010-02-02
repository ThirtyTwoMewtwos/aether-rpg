package com.aether.present.state;

/*
 * LoginPresenter.java
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

import com.aether.service.connection.Client;

@Singleton
public class LoginPresenter implements ActiveState {
	public static final String MAIN_MENU_TRANSITION = "main.menu.view";

	private final ShutdownService exitService;
	private final LoginView view;
	private final StateTransition transitionState;

	private String user;

	private String password;

	private final Client client;

	public LoginPresenter(LoginView view, StateTransition stateTransition, ShutdownService exitService, Client client) {
		this.view = view;
		this.transitionState = stateTransition;
		this.exitService = exitService;
		this.client = client;
		view.setPresenter(this);
	}

	public void performExit() {
		exitService.shutdown();
	}

	public void enter() {
		view.activate();
	}

	@Override
	public void exit() {
		view.deactivate();
	}

	@NotNull
	public void setUsername(String user) {
		this.user = user;
		checkEnableLogin();
	}

	private void checkEnableLogin() {
		if (password != null && user != null) {
			view.setEnableLogin(true);
		}
	}

	@NotNull
	public void setPassword(String password) {
		this.password = password;
		checkEnableLogin();
	}

	public void performLogin() {
		if (client.login(user, password)) {
			transitionState.transition(this, MAIN_MENU_TRANSITION);
		} else {
			view.setLoginErrorMessage(client.getErrorMessage());
		}
	}
}
