package com.aether.present.state;

import com.aether.model.Hero;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Statistic;

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
		Hero player = characterLocator.getPlayer();
		Statistic hitPoints = player.getHealth();
		view.setHealth(hitPoints.getMax(), hitPoints.getValue());
	}

	@Override
	public void exit() {
		view.deactivate();
	}
}
