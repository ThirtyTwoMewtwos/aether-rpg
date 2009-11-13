package com.aether.model.character;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.CharacterSheet;

@Singleton
public class PCLocator implements CharacterLocator {

	private CharacterSheet hero;

	@Override
	public void setPlayer(CharacterSheet hero) {
		this.hero = hero;
	}

	@Override
	public CharacterSheet getPlayer() {
		return hero;
	}

}
