package com.aether.present.hud;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;

@Singleton
public class QuestJournalPresenter implements ViewPresenter {
	private boolean isActive;
	private final CharacterLocator locator;
	private final QuestJournalView view;

	public QuestJournalPresenter(QuestJournalView view, CharacterLocator locator) {
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
			view.setQuests(player.getQuestLog());
		}
		view.setVisible(isActive);
	}

}
