package com.aether.model.character;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.Hero;

@Singleton
public class PCLocator implements CharacterLocator {

	private Hero hero;

	@Override
	public void setPlayer(Hero hero) {
		this.hero = hero;
	}

	@Override
	public Hero getPlayer() {
		return hero;
	}

}
