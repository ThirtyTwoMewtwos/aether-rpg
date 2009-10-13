package com.aether.present.state;

import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.util.StringUtil;

public class CharacterCreationPresenter implements ActiveState {
    private CharacterCreationView view;
    private StateTransition state;
    private String name;
    private Race race;
    private Classification classification;
    public static final String MAIN_MENU_TRANSITION = "back.main.menu";

    public CharacterCreationPresenter(CharacterCreationView view, StateTransition state) {
        this.view = view;
        this.state = state;
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

    public void setRace(Race newRace) {
        race = newRace;
        view.setClasses(Classification.getAvailableFor(newRace));
        evaluateCharacter();
    }

    public void setClassification(Classification newClass) {
        classification = newClass;
        evaluateCharacter();
    }

    public void finish() {
        state.transition(this, MAIN_MENU_TRANSITION);
    }

    @Override
    public void enter() {
        view.activate();
    }

    @Override
    public void exit() {
        view.deactivate();
    }
}
