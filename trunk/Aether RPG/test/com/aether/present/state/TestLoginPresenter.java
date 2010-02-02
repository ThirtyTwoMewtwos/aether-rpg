package com.aether.present.state;

/*
 * TestLoginPresenter.java
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


import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.service.connection.Client;


public class TestLoginPresenter {
	private LoginView view;
	private StateTransition stateTransition;
	private ShutdownService shutdownService;
	private Client client;

	@Before
	public void setUp() {
		view = createStrictMock(LoginView.class);
		stateTransition = createNiceMock(StateTransition.class);
		shutdownService = createStrictMock(ShutdownService.class);
		client = createStrictMock(Client.class);
		
		view.setPresenter((LoginPresenter)anyObject());
	}
	
	@Test
	public void testRequestLogin() throws Exception {
		view.setEnableLogin(true);
		expect(client.login("jsmith", "pdiddy-rules")).andReturn(true);
		stateTransition.transition((LoginPresenter)notNull(), eq(LoginPresenter.MAIN_MENU_TRANSITION));
		
		replay(view, stateTransition, shutdownService, client);
		LoginPresenter presenter = new LoginPresenter(view, stateTransition, shutdownService, client);
		presenter.setUsername("jsmith");
		presenter.setPassword("pdiddy-rules");
		presenter.performLogin();
		verify(view, stateTransition, shutdownService, client);
	}
	
	@Test
	public void testRequestLoginFails() throws Exception {
		view.setEnableLogin(true);
		expect(client.login("jsmith", "pdiddy-rules")).andReturn(false);
		String message = "Username and/or password is incorrect!";
		expect(client.getErrorMessage()).andReturn(message);
		view.setLoginErrorMessage(message);
		
		replay(view, stateTransition, shutdownService, client);
		LoginPresenter presenter = new LoginPresenter(view, stateTransition, shutdownService, client);
		presenter.setUsername("jsmith");
		presenter.setPassword("pdiddy-rules");
		presenter.performLogin();
		verify(view, stateTransition, shutdownService, client);
	}
}
