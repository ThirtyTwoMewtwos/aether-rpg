package com.aether.present;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.Condition;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.present.Main;
import com.aether.present.state.CharacterCreationView;
import com.jmex.game.StandardGame;

public class TestMainMenuWindow {
	
	private MainMenuPage mainMenuPage;

	@Before
	public void setUp() throws Exception {
		Main.startGame();
		mainMenuPage = new MainMenuPage();
	}
	
	@Test
	public void test_Pressing_exit_button_exits_the_game() throws Exception {
		mainMenuPage.clickExit();
		final StandardGame game = Main.getGame();
		BComponentOperatorUtil.waitFor(new Condition() {

			@Override
			public boolean existing() {
				return !game.isStarted();
			}
			
		});
		assertFalse(game.isStarted());
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
