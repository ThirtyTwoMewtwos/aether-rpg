package com.aether.present.hud;

import java.util.Collection;

public interface JournalView {
	public static final String JOURNAL_ID = "hud.journal.view";
	public static final String JOURNAL_ENTRIES_ID = "journal.entries.list";
	public static final String ENTRY_DESCRIPTION_ID = "entry.description.field";

	void setPresenter(JournalPresenter presenter);

	void activate();

	void deactivate();

    void setVisible(boolean b);
    
	void setQuests(Collection<String> listOfQuests);

	void setDescription(String description);
}
