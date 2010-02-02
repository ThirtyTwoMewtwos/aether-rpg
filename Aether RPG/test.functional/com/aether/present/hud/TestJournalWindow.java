package com.aether.present.hud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import com.aether.model.quests.JournalEntryLocator;
import com.aether.model.quests.KillQuest;
import com.aether.present.AetherTestCase;
import com.aether.present.CreateCharacterPage;
import com.aether.present.InGamePage;
import com.aether.present.JournalPage;
import com.aether.present.LoginPage;
import com.aether.present.Main;
import com.aether.present.MainMenuPage;

public class TestJournalWindow extends AetherTestCase {
	private JournalPage journalPage;
	private String title2;
	private String title1;

	@Before
	public void setUp() throws InterruptedException, Exception {
		JournalEntryLocator locator = Main.getServiceStore().get(JournalEntryLocator.class);
		title1 = "Kill them!";
		locator.addEntry(new KillQuest(title1, "get the fabulous dagger, and kill them.", 1, 5, "Bugbear"));
		title2 = "Find billy!";
		locator.addEntry(new KillQuest(title2, "Find billy\n then kill\n him.", 2, 1, "Dwarf"));
		
		CreateCharacterPage createCharacterPage = new LoginPage().clickLogin().clickNewCampain();
		createCharacterPage.loadDummyData();
		InGamePage inGamePage = createCharacterPage.clickFinish();
		journalPage = inGamePage.getJournalPage();
	}
	
	@Test
	public void test_Quests_are_loaded() throws Exception {
		assertFalse(journalPage.isVisible());
		journalPage.setVisible(true);
		assertEquals(2, journalPage.getQuestCount());
	}
	
	@Test
	public void test_Selecting_from_list_updates_the_description() throws Exception {
		journalPage.setVisible(true);
		journalPage.select(title2);
		assertEquals(3, journalPage.getDescription());
		assertEquals("2", journalPage.getLevelRequirement());
		
		journalPage.select(title1);
		assertEquals(1, journalPage.getDescription());
	}
	
	@Test
	public void test_Removing_first_item_updates_the_list() throws Exception {
		journalPage.setVisible(true);
		journalPage.select(title2);
		journalPage.clickRemoveQuest();
		assertEquals(1, journalPage.getQuestCount());
	}
}
