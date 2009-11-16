package com.aether.present.hud;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;

@Singleton
public class PersonaPresenter implements ViewPresenter {
	private boolean isActive;
	private final CharacterLocator locator;
	private final PersonaView view;

	public PersonaPresenter(PersonaView view, CharacterLocator locator) {
		this.view = view;
		this.locator = locator;
		view.setPresenter(this);
	}

	@Override
	public void activate() {
		view.activate();
		isActive = false;
	}

	@Override
	public void deactivate() {
		view.deactivate();
	}

	@Override
	public void toggleVisibility() {
		isActive = !isActive;
		if (isActive) {
			CharacterSheet player = locator.getPlayer();
			view.setStrength(player.getStrength());
			view.setDexterity(player.getDexterity());
			view.setInteligence(player.getIntelligence());
			view.setWisdom(player.getWisdom());
		}
		view.setVisible(isActive);
	}

}
