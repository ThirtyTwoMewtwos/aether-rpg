package com.aether.model.quests;

import java.util.Collection;

public interface JournalEntryLocator {

	Collection<String> getEntryTitles();

	void addEntry(JournalEntry entry);

	JournalEntry getEntry(String title);

	int size();

	boolean isEmpty();

	void removeEntry(String string);
}
