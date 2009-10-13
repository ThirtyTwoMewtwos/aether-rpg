package com.aether.present.state;

import com.aether.model.character.Classification;

import java.util.List;

public interface CharacterCreationView {

	void setPresenter(CharacterCreationPresenter anyObject);

    void enableSave();

    void disableSave();

    void setClasses(List<Classification> availableFor);

    void deactivate();

    void activate();
}
