package com.aether.model.quests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.gap.jseed.injection.annotation.Singleton;

@Singleton
class Journal implements JournalEntryLocator {
	private Map<String, JournalEntry> entries = new HashMap<String, JournalEntry>();

	@Override
	public Collection<String> getEntryTitles() {
		return entries.keySet();
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public int size() {
		return 0;
	}

	public void addEntry(JournalEntry entry) {
		entries.put(entry.getTitle(), entry);
	}

	public JournalEntry getEntry(String title) {
		return entries.get(title);
	}
}
