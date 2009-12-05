package com.aether.present.hud;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Statistic;

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

	public void setBio(String newBio) {
		locator.getPlayer().setBio(newBio);
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

	// TODO there is something wrong here.  character sheet is turning into 
	// a giant bag of attributes, that we extract data from and push around.
	// we should see what we can do to help this out.
	@Override
	public void toggleVisibility() {
		isActive = !isActive;
		if (isActive) {
			CharacterSheet player = locator.getPlayer();
            view.setName(player.getName());
            view.setBlock(player.getBlockChance());
            view.setClassification(player.getClassification().getName());
            view.setCritical(player.getCritChance());
            view.setDefense(player.getDefense());
            view.setDispell(player.getDispelChance());
            view.setDodge(player.getDodgeChance());
            view.setXP(player.getXP(), player.getXPToLevel());
            Statistic health = player.getHealth();
			view.setHealth(health.getValue(), health.getMax());
            Statistic mana = player.getMana();
			view.setMana(mana.getValue(), mana.getMax());
            view.setMagic(player.getMagicIntuative());
            view.setMelee(player.getMeleeAttack());
            view.setLevel(player.getLevel());
            view.setRange(player.getRangedAttack());
            view.setRaceAndSex(player.getRace().name() + "/" + player.getSex());
            view.setToughness(player.getToughness());
			view.setStrength(player.getStrength());
			view.setDexterity(player.getDexterity());
			view.setInteligence(player.getIntelligence());
			view.setWisdom(player.getWisdom());
			view.setBio(player.getBio());
		}
		view.setVisible(isActive);
	}
}