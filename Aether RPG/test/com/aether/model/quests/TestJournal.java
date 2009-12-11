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
	
	@Test
	public void test_Removing_an_entry_from_the_journal() throws Exception {
		EasyMock.expect(quest.getTitle()).andReturn("Kill bugbear!").atLeastOnce();
		
		EasyMock.replay(quest);
		locator.addEntry(quest);
		
		assertEquals(1, locator.size());
		assertFalse(locator.isEmpty());
		
		locator.removeEntry(quest);
		
		assertEquals(0, locator.size());
		assertTrue(locator.isEmpty());
		EasyMock.verify(quest);
	}
}
