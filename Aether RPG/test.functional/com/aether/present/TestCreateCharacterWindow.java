package com.aether.present;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComboBoxOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.aether.present.Main;
import com.aether.present.state.InGameView;
import com.aether.present.state.MainMenuView;

public class TestCreateCharacterWindow {
	private CreateCharacterPage createCharacterPage;
	private BComboBoxOperator classSelection;
	
	@Before
	public void setUp() throws Exception {
		Main.startGame();
		createCharacterPage = new MainMenuPage().clickNewCampain();
		classSelection = createCharacterPage.getClassSelection();
	}

	@Test
	public void test_Cancel_create_character_view_back_to_main_menu() throws Exception {
		Assert.assertFalse(createCharacterPage.getFinishButton().isEnabled());
		createCharacterPage.clickBack();
		BComponentOperatorUtil.windowWithId(MainMenuView.ID);
	}
	
	@Test
	public void test_Create_character_is_not_allowed_until_all_fields_entered() throws Exception {
		BButtonOperator finishButton = createCharacterPage.getFinishButton();
		assertFalse(finishButton.isEnabled());
		createCharacterPage.setName("Joe the Invinceable");
		Assert.assertFalse(finishButton.isEnabled());
		assertFalse(classSelection.isEnabled());
		createCharacterPage.selectRace(Race.Human);
		assertTrue(classSelection.isEnabled());
		classSelection.select(Classification.Crusader);
		assertTrue(finishButton.isEnabled());
		
		finishButton.click();
		BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
	}
	
	@Test
	public void test_Classification_field_unavailable_until_a_race_is_selected() throws Exception {
		assertFalse(classSelection.isEnabled());
		createCharacterPage.selectRace(Race.Human);
		assertTrue(classSelection.isEnabled());
		createCharacterPage.clearRace();
		assertFalse(classSelection.isEnabled());
	}

	@After
	public void tearDown() throws Exception {
		Main.shutdown();
	}
}
