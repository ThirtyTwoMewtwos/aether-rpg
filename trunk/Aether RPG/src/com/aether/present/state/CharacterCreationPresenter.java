package com.aether.present.state;

import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Classification;
import com.aether.model.character.PlayerCharacter;
import com.aether.model.character.Race;
import com.aether.model.character.Sex;
import com.util.StringUtil;

public class CharacterCreationPresenter implements ActiveState {
	public static final String CANCEL_CREATE_CHARACTER_TRANSITION = "back.main.menu";
	public static final String GAME_WINDOW_TRANSITION = "game.window";

	private CharacterCreationView view;
    private StateTransition state;
    
    private String name;
    private Sex sex;
    private Race race;
    private Classification classification;
	private final CharacterLocator characterManager;

    public CharacterCreationPresenter(CharacterCreationView view, StateTransition state, CharacterLocator characterManager) {
        this.view = view;
        this.state = state;
		this.characterManager = characterManager;
        view.setPresenter(this);
        view.disableSave();
	}

    public void setName(String newName) {
        this.name = newName;
        evaluateCharacter();
    }

    private void evaluateCharacter() {
        boolean setEnabled = (!StringUtil.isEmptyOrNull(name) &&
                             (race != null)) &&
                             (classification != null);

        if (setEnabled) {
            view.enableSave();
        } else {
            view.disableSave();
        }
    }

    public void setSex(Sex newSex) {
		this.sex = newSex;
    }

    public void setRace(Race newRace) {
        race = newRace;
        
        if (race == null) {
        	view.clearClassifications();
        } else {
        	view.setClasses(Classification.getAvailableFor(newRace));
        }
        
        classification = null;
        
        evaluateCharacter();
    }

    public void setClassification(Classification newClass) {
        classification = newClass;
        evaluateCharacter();
    }

    public void backToMainMenu() {
    	state.transition(this, CANCEL_CREATE_CHARACTER_TRANSITION);
    }

    public void finish() {
    	characterManager.setPlayer(new PlayerCharacter(name, "", sex, classification));
        state.transition(this, GAME_WINDOW_TRANSITION);
    }

    @Override
    public void enter() {
    	classification = null;
    	sex = Sex.Female;
    	race = null;
    	name = null;
    	view.setName("");
    	view.setSex(Sex.Female);
    	view.setRace(0);
    	view.clearClassifications();
        view.activate();
    }

    @Override
    public void exit() {
        view.deactivate();
    }

}
