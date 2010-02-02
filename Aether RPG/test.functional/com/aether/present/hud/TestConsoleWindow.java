package com.aether.present.hud;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.aether.present.AetherTestCase;
import com.aether.present.ConsolePage;
import com.aether.present.CreateCharacterPage;
import com.aether.present.InGamePage;
import com.aether.present.LoginPage;
import com.aether.present.MainMenuPage;

public class TestConsoleWindow extends AetherTestCase {
	private ConsolePage console;

	@Before
	public void setUp() throws Exception {
		CreateCharacterPage createCharacterPage = new LoginPage().quickLogin().clickNewCampain();
		createCharacterPage.loadDummyData();
		InGamePage inGamePage = createCharacterPage.clickFinish();
		console = inGamePage.getConsolePage();
	}
	
	@Test
	public void test_Console_is_visible() throws Exception {
		console.setVisible(true);
		assertTrue(console.isVisible());
	}
	
	@Test
	public void test_Parse_simple_command() throws Exception {
		console.setVisible(true);
		console.setCommand("items add *");
		assertTrue("actual text contained [" + console.getResult() + "]", console.getResult().contains("added"));
	}
}
