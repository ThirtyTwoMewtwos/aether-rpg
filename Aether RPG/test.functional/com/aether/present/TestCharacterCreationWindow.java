package com.aether.present;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComboBoxOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.aether.model.character.Sex;
import com.aether.present.state.InGameView;
import com.aether.present.state.MainMenuView;

public class TestCharacterCreationWindow extends AetherTestCase {
	private CreateCharacterPage createCharacterPage;
	private BComboBoxOperator classSelection;
	private CharacterLocator characterLocator;

	@Before
	public void setUp() throws Exception {
		characterLocator = Main.getServiceStore().get(CharacterLocator.class);
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
		String name = "Joe the Invinceable";
		createCharacterPage.setName(name);
		createCharacterPage.setSex(Sex.Male);
		Assert.assertFalse(finishButton.isEnabled());
		assertFalse(classSelection.isEnabled());
		createCharacterPage.selectRace(Race.Human);
		assertTrue(classSelection.isEnabled());
		classSelection.select(Classification.Crusader);
		assertTrue(finishButton.isEnabled());
		
		finishButton.click();
		BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
		
		CharacterSheet player = characterLocator.getPlayer();
		assertEquals(name, player.getName());
		assertEquals(Sex.Male, player.getSex());
	}
	
	@Test
	public void test_Classification_field_unavailable_until_a_race_is_selected() throws Exception {
		assertFalse(classSelection.isEnabled());
		createCharacterPage.selectRace(Race.Human);
		assertTrue(classSelection.isEnabled());
		createCharacterPage.clearRace();
		assertFalse(classSelection.isEnabled());
	}
}
