package com.aether.present.hud;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.character.CharacterLocator;
import com.aether.model.quests.JournalEntry;
import com.aether.model.quests.JournalEntryLocator;


public class TestQuestJournalPresenter {
	private JournalView view;
	private CharacterLocator locator;
	private JournalEntryLocator quests;
	private List<String> listOfQuests;

	@Before
	public void setUp() throws Exception {
		view = EasyMock.createStrictMock(JournalView.class);
		locator = EasyMock.createStrictMock(CharacterLocator.class);
		quests = EasyMock.createStrictMock(JournalEntryLocator.class);
		
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
		listOfQuests = Arrays.asList("Kill bugbear", "find food");
		JournalEntry entry = EasyMock.createStrictMock(JournalEntry.class);
		
		EasyMock.expect(quests.isEmpty()).andReturn(false);
		EasyMock.expect(quests.getEntryTitles()).andReturn(listOfQuests);
		view.setQuests(listOfQuests);
		String description = "some good guy";
		EasyMock.expect(quests.getEntry((String)EasyMock.anyObject())).andReturn(entry);
		EasyMock.expect(entry.getDescription()).andReturn(description);
		view.setDescription(description);
		view.setVisible(true);
		
		EasyMock.replay(view, quests, entry);
		JournalPresenter presenter = new JournalPresenter(view, quests);
		presenter.toggleVisibility();
		EasyMock.verify(view, quests, entry);
	}
	
	@Test
	public void test_No_quests_to_show_on_toggle_visibility() throws Exception {
		listOfQuests = Arrays.asList();
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
		JournalEntry entry = EasyMock.createStrictMock(JournalEntry.class);
		EasyMock.expect(quests.getEntry((String)EasyMock.anyObject())).andReturn(entry);
		EasyMock.expect(entry.getDescription()).andReturn(description);
		view.setDescription(description);
		
		EasyMock.replay(view, quests, entry);
		JournalPresenter presenter = new JournalPresenter(view, quests);
		presenter.showDescription("something");
		EasyMock.verify(view, quests, entry);
	}
}
