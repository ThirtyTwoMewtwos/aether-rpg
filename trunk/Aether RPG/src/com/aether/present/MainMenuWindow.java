package com.aether.present;

/*
 * MainMenuWindow.java
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

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.present.state.MainMenuPresenter;
import com.aether.present.state.MainMenuView;
import com.jme.input.KeyInput;
import com.jme.system.DisplaySystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Point;
import com.jmex.game.state.GameStateManager;

@Singleton
public class MainMenuWindow extends BaseWindow implements MainMenuView {
	private static final Object DEFAULT_WINDOW = "base.window";
	private static final String EXIT_KEY_BINDING = "exit";
	private static final int MENU_ITEMS = 2;
	private static final int BUTTON_HEIGHT = 40;
	private static final int BUTTON_WIDTH = 250;
	
	private BButton createCharacter;
	private BButton logout;

	private MainMenuPresenter presenter;
	private int startPosY;
	private int counter = 0;

    public MainMenuWindow() {
		super(ID);

        bindKeysToView();

		DisplaySystem.getDisplaySystem();
		BWindow mainWindow = new BWindow(BuiSystem.getStyle(), new AbsoluteLayout());
		mainWindow.setName(ID);
		addWindow(DEFAULT_WINDOW, mainWindow);
        mainWindow.setSize(BUTTON_WIDTH + 150, BUTTON_HEIGHT * MENU_ITEMS + 220);
                
		startPosY = mainWindow.getHeight() - 100;
		int stepPosY = (int)(BUTTON_HEIGHT * 1.5f);
		int posX = (mainWindow.getWidth() - BUTTON_WIDTH) / 2;
		
		BLabel titleLabel = new BLabel("Login in successful.");
		titleLabel.setPreferredSize(BUTTON_WIDTH + 80, BUTTON_HEIGHT);
		mainWindow.add(titleLabel, new Point(posX - 40, startPosY - stepPosY * counter++));
		mainWindow.center();		

		initStartCampaignButton(stepPosY, posX);
        initlogoutButton(stepPosY, posX);
		
		GameStateManager.getInstance().attachChild(this);
	}

	private void initStartCampaignButton(int stepPosY,  int posX) {
		createCharacter = new BButton("Create Character");
        createCharacter.setPreferredSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		getWindow(DEFAULT_WINDOW).add(createCharacter, new Point(posX, startPosY - stepPosY * counter++));
		createCharacter.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
                presenter.performCreateCharacter();
			}
		});
	}

	private void initlogoutButton(int stepPosY, int posX) {
		logout = new BButton("Logout");
		logout.setPreferredSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		getWindow(DEFAULT_WINDOW).add(logout, new Point(posX, startPosY - stepPosY * counter++));
		logout.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.performLogout();
			}
		});
	}

	private void bindKeysToView() {
		registerBinding(EXIT_KEY_BINDING, KeyInput.KEY_ESCAPE);
	}

	@Override
	protected void handleBinding(String name) {
		if (EXIT_KEY_BINDING.equals(name)) {
			presenter.performExit();
		}
	}

	@Override
	public void setPresenter(MainMenuPresenter presenter) {
		this.presenter = presenter;
	}
}