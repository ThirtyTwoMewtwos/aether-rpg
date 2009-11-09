package com.aether.present;

import java.util.List;

import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.aether.present.state.CharacterCreationPresenter;
import com.aether.present.state.CharacterCreationView;
import com.jme.system.DisplaySystem;
import com.jmex.bui.BButton;
import com.jmex.bui.BComboBox;
import com.jmex.bui.BLabel;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.TableLayout;
import com.jmex.bui.text.Document;
import com.jmex.bui.text.Document.Listener;
import com.jmex.game.state.GameStateManager;

public class CharacterCreationWindow extends BaseWindow implements CharacterCreationView {
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

		DisplaySystem display = DisplaySystem.getDisplaySystem();
        TableLayout tableLayout = new TableLayout(2, 8, 8);
        tableLayout.setHorizontalAlignment(TableLayout.LEFT);
        tableLayout.setVerticalAlignment(TableLayout.CENTER);
        BWindow window = new BWindow(BuiSystem.getStyle(), tableLayout);
        window.setName(ID);
        window.setSize(display.getWidth() - 80, display.getHeight() - 100);
        setWindow(window);

		initNameField();
        initRaceSelection();
        initClassSelection();
        
        initBackToMainMenu();
        initSaveCharacter();

		window.center();

		GameStateManager.getInstance().attachChild(this);
    }


	private void initNameField() {
		getWindow().add(new BLabel("Name: "));
        nameField = new BTextField("");
        nameField.setPreferredSize(BUTTON_WIDTH, 25);
        getWindow().add(nameField);
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

	private void initRaceSelection() {
		getWindow().add(new BLabel("Race: "));
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
        getWindow().add(raceSelection);
	}

	private void initClassSelection() {
		getWindow().add(createLabel("Class: "));
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
		getWindow().add(classSelection);
	}

	private BLabel createLabel(String text) {
		return new BLabel(text);
	}

	private void initBackToMainMenu() {
		backButton = new BButton("Back");
		getWindow().add(backButton);
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
		getWindow().add(saveCharacter);
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
