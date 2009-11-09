package com.aether.present.state;

import org.junit.After;
import org.junit.Test;

import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.aether.present.Main;


public class TestInGameWindow {

	@Test
	public void test_Can_navigate_to_game_view() throws Exception {
		Main.main(new String[]{});
		CreateCharacterPage createCharacter = new MainMenuPage().clickNewCampain();
		
		createCharacter.setName("John Grisham");
		createCharacter.selectRace(Race.HUMAN);
		createCharacter.selectClass(Classification.Crusader);
		createCharacter.clickFinish();
		
		BComponentOperatorUtil.getWindowWithId(InGameView.ID);
	}
	
	@After
	public void tearDown() {
		Main.shutdown();
	}
}
