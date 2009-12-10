package com.aether.present;

/*
 * CharacterCreationWindow.java
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

import java.util.List;

import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.aether.model.character.Sex;
import com.aether.present.state.CharacterCreationPresenter;
import com.aether.present.state.CharacterCreationView;
import com.jme.input.KeyInput;
import com.jme.system.DisplaySystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BComboBox;
import com.jmex.bui.BGroupContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.BTextField;
import com.jmex.bui.BToggleButton;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.Spacer;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.SelectionListener;
import com.jmex.bui.event.StateChangedEvent;
import com.jmex.bui.event.StateChangedEvent.SelectionState;
import com.jmex.bui.layout.HGroupLayout;
import com.jmex.bui.layout.TableLayout;
import com.jmex.bui.text.Document;
import com.jmex.bui.text.Document.Listener;
import com.jmex.game.state.GameStateManager;

public class CharacterCreationWindow extends BaseWindow implements CharacterCreationView {
    private static final String DEFAULT_WINDOW = "base";
	private static final String HIDDEN_CREATE_CHARACTER = "hidden.create.character";
	private static final Item PLEASE_SELECT_ITEM = new BComboBox.Item(null, "<Select>");
	private static final int BUTTON_HEIGHT = 40;
	private static final int BUTTON_WIDTH = 250;
	
    private CharacterCreationPresenter presenter;
    
    private BTextField nameField;
	private BComboBox raceSelection;
	private BComboBox classSelection;

	private BButton saveCharacter;
	private BButton backButton;

    public CharacterCreationWindow() {
		super("Character Creation Window");

		bindHiddenKeyFeature();
		
		BWindow window = initWindow();

		initNameField();
		initSexSelection();
        initRaceSelection();
        initClassSelection();
        
        initFillRow();
        
        initBackToMainMenu();
        initSaveCharacter();

		window.center();

		GameStateManager.getInstance().attachChild(this);
    }

	private BWindow initWindow() {
		DisplaySystem display = DisplaySystem.getDisplaySystem();
        TableLayout tableLayout = new TableLayout(2, 8, 8);
        tableLayout.setHorizontalAlignment(TableLayout.LEFT);
        tableLayout.setVerticalAlignment(TableLayout.CENTER);
        BWindow window = new BWindow(BuiSystem.getStyle(), tableLayout);
        window.setName(ID);
        window.setSize(display.getWidth() - 80, display.getHeight() - 100);
        addWindow(DEFAULT_WINDOW, window);
		return window;
	}


	private void bindHiddenKeyFeature() {
		registerBinding(HIDDEN_CREATE_CHARACTER, KeyInput.KEY_F4);
	}
	
	@Override
	protected void handleBinding(String name) {
		if (HIDDEN_CREATE_CHARACTER.equals(name)) {
			presenter.setName("Joe the berzerker");
			presenter.setRace(Race.Human);
			presenter.setClassification(Classification.Acolyte);
			presenter.finish();
		}
	}

	private void initNameField() {
		getWindow(DEFAULT_WINDOW).add(new BLabel("Name: "));
        nameField = new BTextField("");
        nameField.setPreferredSize(BUTTON_WIDTH, 25);
        getWindow(DEFAULT_WINDOW).add(nameField);
        nameField.setName("set.name");
        nameField.getDocument().addListener(new Listener() {
			@Override
			public void textInserted(Document document, int offset, int length) {
				  presenter.setName(nameField.getText());
			}
			@Override
			public void textRemoved(Document document, int offset, int length) {
				  presenter.setName(nameField.getText());
			}
        });
	}
	
	private void initSexSelection() {
		getWindow(DEFAULT_WINDOW).add(new BLabel("Sex: "));
		BGroupContainer container = new BGroupContainer();
		container.setPreferredSize(BUTTON_WIDTH, 25);
		container.setLayoutManager(new HGroupLayout());
		createSexButton(container, Sex.Male);
		createSexButton(container, Sex.Female);
		getWindow(DEFAULT_WINDOW).add(container);
	}

	private void createSexButton(BGroupContainer container, final Sex sex) {
		BToggleButton selection = new BToggleButton(sex.name());
		selection.setPreferredSize(BUTTON_WIDTH / 2, 25);
		selection.setStyleClass(UILookAndFeel.RADIO_BUTTON);
		selection.addSelectionListener(new SelectionListener() {
			@Override
			public void stateChanged(StateChangedEvent event) {
				if (event.getType() == SelectionState.Selected) {
					presenter.setSex(sex);
				}
			}
		});
		container.add(selection);
	}

	private void initRaceSelection() {
		getWindow(DEFAULT_WINDOW).add(new BLabel("Race: "));
        raceSelection = new BComboBox();
        raceSelection.setPreferredSize(BUTTON_WIDTH, 25);
        raceSelection.addItem(PLEASE_SELECT_ITEM);
        raceSelection.setName(RACE_SELELECTION_NAME);
        for (Race each : Race.values()) {
			Item item = new BComboBox.Item(each, each.name());
			raceSelection.addItem(item);
		}
        raceSelection.selectItem(0);
        raceSelection.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Object selectedItem = raceSelection.getSelectedValue();
				presenter.setRace((Race)selectedItem);
			}
        });
        getWindow(DEFAULT_WINDOW).add(raceSelection);
	}

	private void initClassSelection() {
		getWindow(DEFAULT_WINDOW).add(createLabel("Class: "));
		classSelection = new BComboBox();
		classSelection.setPreferredSize(BUTTON_WIDTH, 25);
		classSelection.setName(CLASS_SELECTION_NAME);
		
		classSelection.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Object selectedItem = classSelection.getSelectedValue();
				presenter.setClassification((Classification)selectedItem);
			}
		});
		
		clearClassifications();
		getWindow(DEFAULT_WINDOW).add(classSelection);
	}

	private BLabel createLabel(String text) {
		return new BLabel(text);
	}
	
	private void initFillRow() {
		getWindow(DEFAULT_WINDOW).add(new Spacer(-1, 25));
		getWindow(DEFAULT_WINDOW).add(new Spacer(-1, 25));
	}

	private void initBackToMainMenu() {
		backButton = new BButton("Back");
		getWindow(DEFAULT_WINDOW).add(backButton);
		backButton.setPreferredSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.backToMainMenu();
			}
		});
	}
	
	private void initSaveCharacter() {
		saveCharacter = new BButton("Finish");
		getWindow(DEFAULT_WINDOW).add(saveCharacter);
		saveCharacter.setPreferredSize(200, 40);
		saveCharacter.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				presenter.finish();
			}
		});
	}
	
    @Override
    public void setPresenter(CharacterCreationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void enableSave() {
    	saveCharacter.setEnabled(true);
    }

    @Override
    public void disableSave() {
    	saveCharacter.setEnabled(false);
    }
    
    @Override
    public void setName(String name) {
    	nameField.setText(name);
    }
    
    @Override
    public void setSex(Sex sex) {
    	
    }
    
    @Override
    public void setRace(int i) {
    	raceSelection.selectItem(0);
    }

    @Override
    public void setClasses(List<Classification> availableFor) {
    	clearClassifications();
    	for (Classification each : availableFor) {
    		Item item = new BComboBox.Item(each, each.getName());
    		classSelection.addItem(item);
		}
    	classSelection.setEnabled(true);
    }

	@Override
	public void clearClassifications() {
		classSelection.clearItems();
		classSelection.addItem(PLEASE_SELECT_ITEM);
		classSelection.selectItem(PLEASE_SELECT_ITEM);
		classSelection.setEnabled(false);
	}
}
