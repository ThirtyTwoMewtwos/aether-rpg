package com.aether.model.quests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;


public class TestJournal {
	
	private JournalEntry quest;
	private Journal locator;

	@Before
	public void setUp() {
		quest = EasyMock.createStrictMock("quest", JournalEntry.class);
		locator = new Journal();
	}
	
	@Test
	public void test_Load_empty_journal() throws Exception {
		assertTrue(locator.isEmpty());
		locator.addEntry(quest);
		assertFalse(locator.isEmpty());
	}
	
	@Test
	public void test_Getting_the_first_entry() throws Exception {
		EasyMock.expect(quest.getTitle()).andReturn("goody");
		EasyMock.replay(quest);
		locator.addEntry(quest);
		assertEquals(quest, locator.getEntry("goody"));
		EasyMock.verify(quest);
	}
	
	@Test
	public void test_Get_all_entry_titles() throws Exception {
		JournalEntry quest2 = EasyMock.createStrictMock("quest2", JournalEntry.class);
		EasyMock.expect(quest.getTitle()).andReturn("Kill bugbear!");
		EasyMock.expect(quest2.getTitle()).andReturn("Find billy!");
		
		EasyMock.replay(quest, quest2);
		locator.addEntry(quest);
		locator.addEntry(quest2);
		Collection<String> titles = locator.getEntryTitles();
		assertEquals(2, titles.size());
		
		Iterator<String> iterator = titles.iterator();
		assertEquals("Kill bugbear!", iterator.next());
		assertEquals("Find billy!", iterator.next());
		EasyMock.verify(quest, quest2);
	}
}
