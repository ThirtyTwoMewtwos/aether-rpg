package com.aether.model.quests;

import java.util.Collection;
import java.util.HashMap;
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
		return entries.size();
	}

	public void addEntry(JournalEntry entry) {
		entries.put(entry.getTitle(), entry);
	}

	public JournalEntry getEntry(String title) {
		return entries.get(title);
	}
	
	@Override
	public void removeEntry(String title) {
		entries.remove(title);
	}
}
