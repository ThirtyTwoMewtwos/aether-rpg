package com.aether.present.hud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.present.CreateCharacterPage;
import com.aether.present.InGamePage;
import com.aether.present.Main;
import com.aether.present.MainMenuPage;
import com.aether.present.PersonaPage;

public class TestPersonaWindow {
	
	private PersonaPage persona;

	@Before
	public void setUp() throws Exception {
		Main.startGame();
		CreateCharacterPage newCampain = new MainMenuPage().clickNewCampain();
		newCampain.loadDummyData();
		InGamePage gamePage = newCampain.clickFinish();
		
		persona = gamePage.getPersonaPage();
	}
	
	@Test
	public void test_Window_is_available() throws Exception {
		assertFalse(persona.getWindow().isVisible());
		persona.toggleVisibility();
		assertTrue(persona.getWindow().isVisible());
		persona.toggleVisibility();
		assertFalse(persona.getWindow().isVisible());
	}
	
	@Test
	public void test_Statistics_have_been_set_from_character_sheet() throws Exception {
		persona.toggleVisibility();
		
		assertLabelText("John Grisham", PersonaView.NAME_FIELD);
		assertLabelText("Crusader", PersonaView.CLASS_FIELD);
		assertLabelText("Human/Female", PersonaView.RACE_SEX_FIELD);
		
		assertLabelText("10", PersonaView.STRENGTH_STATISTIC);
		assertLabelText("10", PersonaView.DEXTERITY_STATISTIC);
		assertLabelText("10", PersonaView.INTELLIGENCE_STATISTIC);
		assertLabelText("10", PersonaView.WISDOM_STATISTIC);
		
		assertLabelText("5.0", PersonaView.BLOCK_STATISTIC);
		assertLabelText("5.0", PersonaView.DODGE_STATISTIC);
		assertLabelText("7.0", PersonaView.RANGE_STATISTIC);
		assertLabelText("7.0", PersonaView.MELEE_STATISTIC);
		assertLabelText("7.0", PersonaView.MAGIC_STATISTIC);
		assertLabelText("9.6", PersonaView.DISPELL_STATISTIC);
		assertLabelText("5.2", PersonaView.CRITICAL_STATISTIC);
		assertLabelText("100", PersonaView.DEFENSE_STATISTIC);
		
		assertLabelText("10/10", PersonaView.HEALTH_STATISTIC);
		assertLabelText("2/10", PersonaView.MANA_STATISTIC);
	}

	private void assertLabelText(String expected, String nameField) {
		BLabelOperator name = new BLabelOperator(persona.getWindow(), new NameOperatorSearch(nameField));
		assertEquals(expected, name.getText());
	}

	@After
	public void tearDown() {
		Main.shutdown();
	}
}
