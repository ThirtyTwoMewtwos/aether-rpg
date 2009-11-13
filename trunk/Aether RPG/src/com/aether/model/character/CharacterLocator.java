package com.aether.model.character;

import com.aether.model.CharacterSheet;

public interface CharacterLocator {

	void setPlayer(CharacterSheet anyObject);

	CharacterSheet getPlayer();

}
