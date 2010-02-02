package com.aether.present;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.present.state.CharacterCreationView;

public class TestMainMenuWindow {
	private MainMenuPage mainMenuPage;

	@Before
	public void setUp() throws Exception {
		Main.startGame();
		mainMenuPage = new LoginPage().quickLogin();
	}
	
	@Test
	public void test_Can_select_new_campaign_and_enter_create_character() throws Exception {
		mainMenuPage.clickNewCampain();
		BComponentOperatorUtil.windowWithId(CharacterCreationView.ID);
	}
	
	@After
	public void tearDown() {
		Main.shutdown();
	}
}
