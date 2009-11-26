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
            view.setName(player.getName());
            view.setBlock(player.getBlockChance());
            view.setClassification(player.getClassification().getName());
            view.setCrit(player.getCritChance());
            view.setDefense(player.getDefense());
            view.setDispell(player.getDispelChance());
            view.setDodge(player.getDodgeChance());
            view.setExp(player.getXP());
            view.setNextLevel(player.getXPToLevel());
            view.setHP(player.getHealth().getValue() + "/" + player.getHealth().getMax());
            view.setMP(player.getMana().getValue() + "/" + player.getMana().getMax());
            view.setMagic(player.getMagicIntuative());
            view.setMelee(player.getMeleeAttack());
            view.setLevel(player.getLevel());
            view.setRange(player.getRangedAttack());
            view.setRace(player.getRace().name());
            view.setToughness(player.getToughness());
			view.setStrength(player.getStrength());
			view.setDexterity(player.getDexterity());
			view.setInteligence(player.getIntelligence());
			view.setWisdom(player.getWisdom());
		}
		view.setVisible(isActive);
	}

}
