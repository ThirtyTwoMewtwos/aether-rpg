package com.aether.present.hud;

import java.util.Collection;

import com.aether.model.quests.JournalEntry;

public interface JournalView {
	public static final String JOURNAL_ID = "hud.journal.view";
	public static final String JOURNAL_ENTRIES_ID = "journal.entries.list";
	public static final String ENTRY_DESCRIPTION_ID = "entry.description.field";
	public static final String LEVEL_REQUIREMENT_ID = "journal.level.requirement.id";

	void setPresenter(JournalPresenter presenter);

	void activate();

	void deactivate();

    void setVisible(boolean b);
    
	void setQuests(Collection<JournalEntry> listOfQuests);

	void setDescription(String description);

	void setSelection(JournalEntry string);

	void setLevelRequirement(String string);
}
