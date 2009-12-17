package com.aether.present.hud;

import java.util.Collection;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.quests.JournalEntry;
import com.aether.model.quests.JournalEntryLocator;

@Singleton
class JournalPresenter implements ViewPresenter {
	private final JournalView view;
	private final JournalEntryLocator quests;
	private boolean visible = false;

	public JournalPresenter(JournalView view, JournalEntryLocator quests) {
		this.view = view;
		this.quests = quests;
		view.setPresenter(this);
	}

	@Override
	public void activate() {
		view.activate();
	}

	@Override
	public void deactivate() {
		view.deactivate();
	}

	@Override
	public void toggleVisibility() {
		if (!visible ) {
			showAvailableQuests();
		}
		visible = !visible;
		view.setVisible(visible);
	}

	private void showAvailableQuests() {
		if (quests.isEmpty()) {
			view.setDescription("No quests to show!");
		} else {
			Collection<JournalEntry> titles = quests.getAllEntries();
			view.setQuests(titles);
			showQuest(titles.iterator().next());
		}
	}

	public void showQuest(JournalEntry entry) {
		view.setSelection(entry);
		view.setLevelRequirement("" + entry.getLevelRequirement());
		view.setDescription(entry.getDescription());
	}

	public void removeQuest(JournalEntry entry) {
		quests.removeEntry(entry);
		showAvailableQuests();
	}

}
