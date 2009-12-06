package com.aether.present.hud;

import java.util.Collection;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.quests.JournalEntry;
import com.aether.model.quests.JournalEntryLocator;

@Singleton
public class JournalPresenter implements ViewPresenter {
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
			showAllQuests();
		}
		visible = !visible;
		view.setVisible(visible);
	}

	private void showAllQuests() {
		if (!quests.isEmpty()) {
			Collection<String> titles = quests.getEntryTitles();
			view.setQuests(titles);
			showQuest(titles.iterator().next());
		} else {
			view.setDescription("No quests to show!");
		}
	}

	public void showQuest(String title) {
		JournalEntry entry = quests.getEntry(title);
		view.setSelection(title);
		view.setLevelRequirement("" + entry.getLevelRequirement());
		view.setDescription(entry.getDescription());
	}

}
