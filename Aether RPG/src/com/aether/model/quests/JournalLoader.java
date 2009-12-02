package com.aether.model.quests;

import org.gap.jseed.ServiceStore;

public class JournalLoader {

	public JournalLoader(ServiceStore store) {
		JournalEntryLocator locator = new Journal();
		locator.addEntry(new KillQuest("Kill them!", "get the fabulous dagger, and kill them.", 1, 5, "Bugbear"));
		locator.addEntry(new KillQuest("Find billy!", "Find billy\n then kill\n him.", 1, 1, "Dwarf"));
		
		store.bind(JournalEntryLocator.class, locator);
	}

}
