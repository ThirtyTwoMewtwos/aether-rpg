package com.aether.present.state;

import com.aether.model.character.Classification;

import java.util.List;

public interface CharacterCreationView {
	public static final String ID = "character.creation.view";
	public static final String RACE_SELELECTION_NAME = "set.race";
	public static final String CLASS_SELECTION_NAME = "set.class";

	void setPresenter(CharacterCreationPresenter anyObject);

    void enableSave();

    void disableSave();

    void setClasses(List<Classification> availableFor);

    void deactivate();

    void activate();

	void clearClassifications();

	void setName(String string);

	void setRace(int i);
}
