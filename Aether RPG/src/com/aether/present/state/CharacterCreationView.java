package com.aether.present.state;

import com.aether.model.character.Classification;

import java.util.List;

public interface CharacterCreationView {
	String ID = "character.creation.view";

	void setPresenter(CharacterCreationPresenter anyObject);

    void enableSave();

    void disableSave();

    void setClasses(List<Classification> availableFor);

    void deactivate();

    void activate();

	void clearClassifications();
}
