package com.aether.present.state;

import com.aether.model.character.CharacterLocator;
import com.aether.model.character.CharacterSheet;
import com.aether.model.character.Statistic;
import com.aether.present.hud.HUDViewLocator;
import com.aether.present.hud.HUDViewLocator.View;
import com.aether.present.hud.persona.CharacterTypeImage;

public class InGamePresenter implements ActiveState {
	public static final String OPTIONS_MENU_TRANSITION = "options.menu";
	private final StateTransition stateTransition;
	private final InGameView view;
	private final CharacterLocator characterLocator;
	private HUDViewLocator hudLocator;

	public InGamePresenter(InGameView view, StateTransition stateTransition, CharacterLocator characterLocator, HUDViewLocator hudLocator) {
		this.view = view;
		this.stateTransition = stateTransition;
		this.characterLocator = characterLocator;
		this.hudLocator = hudLocator;

		view.setPresenter(this);
	}

	public void optionsMenu() {
		stateTransition.transition(this, OPTIONS_MENU_TRANSITION);
	}
	
	@Override
	public void enter() {
		view.activate();
		hudLocator.activate();
		CharacterSheet player = characterLocator.getPlayer();
		
		Statistic health = player.getHealth();
		view.setHealth(health.getMax(), health.getValue());
		
		Statistic mana = player.getMana();
		view.setMana(mana.getMax(), mana.getValue());

                int xp = player.getXP();
                int xpToLevel = player.getXPToLevel();
                view.setXP(xp, xpToLevel);
		
		view.setImage(CharacterTypeImage.getImage(player.getClassification()));
	}

	@Override
	public void exit() {
		view.deactivate();
		hudLocator.deactivate();
	}

	public void togglePersona() {
		hudLocator.toggleViewVisibility(View.PERSONA);
	}
        public void toggleQuestJournal(){
        hudLocator.toggleViewVisibility(View.JOURNAL);
        }

	public void toggleEquipment() {
		hudLocator.toggleViewVisibility(View.EQUIPMENT);
	}

	public void toggleConsole() {
		hudLocator.toggleViewVisibility(View.CONSOLE);
	}
}
