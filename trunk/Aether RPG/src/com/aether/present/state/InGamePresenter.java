package com.aether.present.state;

import java.net.MalformedURLException;

import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Statistic;
import com.aether.present.CharacterTypeImage;

public class InGamePresenter implements ActiveState {

	public static final String OPTIONS_MENU_TRANSITION = "options.menu";
	private final StateTransition stateTransition;
	private final InGameView view;
	private CharacterLocator characterLocator;

	public InGamePresenter(InGameView view, StateTransition stateTransition, CharacterLocator characterLocator) {
		this.view = view;
		this.stateTransition = stateTransition;
		view.setPresenter(this);
		this.characterLocator = characterLocator;
	}

	public void optionsMenu() {
		stateTransition.transition(this, OPTIONS_MENU_TRANSITION);
	}
	
	@Override
	public void enter() {
		view.activate();
		CharacterSheet player = characterLocator.getPlayer();
		
		Statistic health = player.getHealth();
		view.setHealth(health.getMax(), health.getValue());
		
		Statistic mana = player.getMana();
		view.setMana(mana.getMax(), mana.getValue());
		
		view.setImage(CharacterTypeImage.getImage(player.getClassification()));
	}

	@Override
	public void exit() {
		view.deactivate();
	}
}
