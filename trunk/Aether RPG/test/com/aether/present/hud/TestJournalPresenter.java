package com.aether.present.hud;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.items.GenericItem;
import com.aether.model.quests.BaseEntry;
import com.aether.model.quests.JournalEntry;
import com.aether.model.quests.JournalEntryLocator;
import com.aether.model.quests.KillQuest;


public class TestJournalPresenter {
	private JournalView view;
	private JournalEntryLocator quests;
	private List<JournalEntry> listOfQuests;
	private JournalEntry entryA;
	private JournalEntry entryB;

	@Before
	public void setUp() throws Exception {
		view = EasyMock.createStrictMock(JournalView.class);
		quests = EasyMock.createStrictMock(JournalEntryLocator.class);
		entryA = EasyMock.createStrictMock(JournalEntry.class);
		entryB = EasyMock.createStrictMock(JournalEntry.class);
		
		view.setPresenter((JournalPresenter)EasyMock.anyObject());
	}
	
	@Test
	public void test_Initialize_view_from_presenter() throws Exception {
		EasyMock.replay(view, quests);
		new JournalPresenter(view, quests);
		EasyMock.verify(view, quests);
	}
	
	@Test
	public void test_Activating_loads_the_view() throws Exception {
		listOfQuests = Arrays.asList(entryA, entryB);
		JournalEntry entry = EasyMock.createStrictMock(JournalEntry.class);
		
		EasyMock.expect(quests.isEmpty()).andReturn(false);
		EasyMock.expect(quests.getAllEntries()).andReturn(listOfQuests);
		view.setQuests(listOfQuests);
		String description = "some good guy";
		view.setSelection(entryA);
		EasyMock.expect(entryA.getLevelRequirement()).andReturn(1);
		view.setLevelRequirement("1");
		EasyMock.expect(entryA.getDescription()).andReturn(description);
		view.setDescription(description);
		view.setVisible(true);
		
		EasyMock.replay(view, quests, entry, entryA, entryB);
		JournalPresenter presenter = new JournalPresenter(view, quests);
		presenter.toggleVisibility();
		EasyMock.verify(view, quests, entry, entryA, entryB);
	}
	
	@Test
	public void test_No_quests_to_show_on_toggle_visibility() throws Exception {
		EasyMock.expect(quests.isEmpty()).andReturn(true);
		view.setDescription("No quests to show!");
		view.setVisible(true);
		
		EasyMock.replay(view, quests);
		JournalPresenter presenter = new JournalPresenter(view, quests);
		presenter.toggleVisibility();
		EasyMock.verify(view, quests);
	}
	
	@Test
	public void test_Show_description_for_a_quest() throws Exception {
		String description = "The bugbears have overrun the village,\n" +
							 "and now you must do something about it!";
		view.setSelection(entryA);
		EasyMock.expect(entryA.getLevelRequirement()).andReturn(2);
		view.setLevelRequirement("2");
		EasyMock.expect(entryA.getDescription()).andReturn(description);
		view.setDescription(description);
		
		EasyMock.replay(view, quests, entryA);
		JournalPresenter presenter = new JournalPresenter(view, quests);
		presenter.showQuest(entryA);
		EasyMock.verify(view, quests, entryA);
	}
	
	@Test
	public void test_Abandon_a_quest_removes_it_from_the_journal() throws Exception {
		quests.removeEntry(entryA);
		EasyMock.expect(quests.isEmpty()).andReturn(true);
		view.setDescription("No quests to show!");
		
		EasyMock.replay(view, quests);
		JournalPresenter presenter = new JournalPresenter(view, quests);
		presenter.removeQuest(entryA);
		EasyMock.verify(view, quests);
	}
}
