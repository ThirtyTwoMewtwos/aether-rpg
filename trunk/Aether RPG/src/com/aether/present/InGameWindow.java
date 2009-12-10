package com.aether.present;

/*
 * InGameWindow.java
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

import java.awt.Image;

import com.aether.gbui.BDraggableChatWindow;
import com.aether.gbui.components.BMeterBar;
import com.aether.present.game.InGameWorldWindow;
import com.aether.present.hud.BChatComponent;
import com.aether.present.state.InGamePresenter;
import com.aether.present.state.InGameView;
import com.jme.input.KeyInput;
import com.jmex.bui.BChatWindow;
import com.jmex.bui.BImage;
import com.jmex.bui.BLabel;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.FocusEvent;
import com.jmex.bui.event.FocusListener;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.icon.BlankIcon;
import com.jmex.bui.icon.ImageIcon;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;
import com.jmex.game.state.GameStateManager;

public class InGameWindow extends BaseWindow implements InGameView {
	private static final String CHAT_WINDOW = "chat.window";
	private static final String STATS_WINDOW = "stats.window";
	private static final String OPTIONS_MENU_KEY_BINDING = "options.menu";
	private static final String PERSONA_VIEW_KEY_BINDING = "persona.view";
	private static final String JOURNAL_VIEW_KEY_BINDING = "journal.view";
	private static final String EQUIPMENT_VIEW_KEY_BINDING = "equipment.view";

	private InGamePresenter presenter;
	private BMeterBar healthMeter;
	private BMeterBar manaMeter;
	private BLabel characterIcon;
	private final InGameWorldWindow window;
	private final PlayerMovementState state;

	public InGameWindow(InGameWorldWindow window, PlayerMovementState state) {
		super("Game View");
		this.window = window;
		this.state = state;
		bindKeysToView();

		BWindow statsWindow = initStatsWindow();
		initStatistics(statsWindow);

		BChatWindow chatWindow = initChatWindow();
		initChatBox(chatWindow, state);

		GameStateManager.getInstance().attachChild(this);
	}

	private BWindow initStatsWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BWindow window = new BDraggableWindow(BuiSystem.getStyle(), layout);
		window.setName(STATS_ID);
		window.setSize(34, 100);
		window.setLocation(10, 350);
		addWindow(STATS_WINDOW, window);

		return window;
	}

	private void initStatistics(BWindow window) {
		characterIcon = new BLabel(new BlankIcon(24, 24), "");
		characterIcon.setName(STATS_SYMBOL_ID);
		window.add(characterIcon, new Rectangle(0, window.getHeight() - 28, 24,
				24));

		healthMeter = new BMeterBar(STATS_HEALTH_ID);
		healthMeter.setStyleClass(UILookAndFeel.STATISTICS_HEALTH);
		healthMeter.setTooltipStyleClass(UILookAndFeel.STATISTICS_TOOLTIP_TEXT);
		window.add(healthMeter,
				new Rectangle(1, 0, 10, window.getHeight() - 30));

		manaMeter = new BMeterBar(STATS_MANA_ID);
		manaMeter.setStyleClass(UILookAndFeel.STATISTICS_MANA_POINTS);
		manaMeter.setTooltipStyleClass(UILookAndFeel.STATISTICS_TOOLTIP_TEXT);
		window
				.add(manaMeter, new Rectangle(14, 0, 10,
						window.getHeight() - 30));
	}

	private BChatWindow initChatWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		BChatWindow window = new BDraggableChatWindow(BuiSystem.getStyle(),
				layout);
		window.setName(CHAT_ID);
		window.setSize(250, 100);
		window.setLocation(10, 10);
		window.setStyleClass(UILookAndFeel.CHAT_LAYOUT);
		addWindow(CHAT_WINDOW, window);
		return window;
	}

	private void initChatBox(BChatWindow window, final PlayerMovementState state) {
		BTextField input = new BTextField();
		input.setName(CHAT_INPUT_ID);
		input.setStyleClass(UILookAndFeel.CHAT_INPUT);
		input.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent event) {
				state.setActive(false);
			}

			@Override
			public void focusLost(FocusEvent event) {
				state.setActive(true);
			}

		});
		BChatComponent chatComponent = new BChatComponent("chat component",
				input, new BTextArea());
		window.add(chatComponent, new Rectangle(0, 0, 230, 100));
	}

	@Override
	public void activate() {
		super.activate();
		window.setActive(true);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		window.setActive(false);
	}

	private void bindKeysToView() {
		registerBinding(OPTIONS_MENU_KEY_BINDING, KeyInput.KEY_ESCAPE);
		registerBinding(PERSONA_VIEW_KEY_BINDING, KeyInput.KEY_P);
		registerBinding(JOURNAL_VIEW_KEY_BINDING, KeyInput.KEY_J);
		registerBinding(EQUIPMENT_VIEW_KEY_BINDING, KeyInput.KEY_I);
	}

	@Override
	protected void handleBinding(String name) {
		if (state.isActive()) {
			if (OPTIONS_MENU_KEY_BINDING.equals(name)) {
				presenter.optionsMenu();
			} else if (PERSONA_VIEW_KEY_BINDING.equals(name)) {
				presenter.togglePersona();
			} else if (JOURNAL_VIEW_KEY_BINDING.equals(name)) {
				presenter.toggleQuestJournal();
			} else if (EQUIPMENT_VIEW_KEY_BINDING.equals(name)) {
				presenter.toggleEquipment();
			}
		}
	}

	public void setPresenter(InGamePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setHealth(int maximum, int current) {
		healthMeter.setMaximum(maximum);
		healthMeter.setValue(current);
	}

	@Override
	public void setMana(int maximum, int current) {
		manaMeter.setMaximum(maximum);
		manaMeter.setValue(current);
	}

	@Override
	public void setImage(Image image) {
		ImageIcon icon = new ImageIcon(new BImage(image));
		characterIcon.setIcon(icon);
	}
}
