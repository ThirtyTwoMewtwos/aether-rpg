package com.aether.present.hud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;

import org.gap.jseed.ServiceStore;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.gbui.operators.BMeterBarOperator;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.CharacterSheet;
import com.aether.present.AetherTestCase;
import com.aether.present.CreateCharacterPage;
import com.aether.present.InGamePage;
import com.aether.present.LoginPage;
import com.aether.present.Main;
import com.aether.present.MainMenuPage;
import com.aether.present.PersonaPage;
import com.aether.present.PlayerMovementState;

public class TestPersonaWindow extends AetherTestCase {

	private static PersonaPage persona;
	private static ServiceStore serviceStore;

	@Before
	public void setUp() throws Exception {
		serviceStore = Main.getServiceStore();
		CreateCharacterPage newCampain = new LoginPage().clickLogin().clickNewCampain();
		newCampain.loadDummyData();
		InGamePage gamePage = newCampain.clickFinish();

		persona = gamePage.getPersonaPage();
	}

	@Test
	public void test_Window_is_available() throws Exception {
		assertFalse(persona.getWindow().isVisible());
		persona.setVisible(true);
		assertTrue(persona.getWindow().isVisible());
		persona.setVisible(false);
		assertFalse(persona.getWindow().isVisible());
	}

	@Test
	public void test_Statistics_have_been_set_from_character_sheet()
			throws Exception {
		persona.setVisible(true);

		assertLabelText("John Grisham", PersonaView.NAME_FIELD);
		assertLabelText("Crusader", PersonaView.CLASS_FIELD);
		assertLabelText("Human/Female", PersonaView.RACE_SEX_FIELD);
		assertLabelText("1", PersonaView.EXPERIENCE_LEVEL);

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
		BMeterBarOperator experience = new BMeterBarOperator(persona
				.getWindow(), PersonaView.CURRENT_EXPERIENCE);
		assertEquals(0, experience.getValue());
		assertEquals(100, experience.getMaximum());
	}

	private void assertLabelText(String expected, String nameField) {
		BLabelOperator name = new BLabelOperator(persona.getWindow(),
				new NameOperatorSearch(nameField));
		assertEquals(expected, name.getText());
	}

	@Test
	public void test_Changing_bio_sets_character_bio() throws Exception {
		persona.setVisible(true);
		persona.setBio("Some new bio");
		CharacterSheet player = serviceStore.get(CharacterLocator.class)
				.getPlayer();
		assertEquals("Some new bio", player.getBio());
	}

	@Test
	public void test_Bio_gains_focus_player_cannot_move() throws Exception {
		persona.setVisible(true);
		persona.setBio("setting bio");
		assertFalse(serviceStore.get(PlayerMovementState.class).isActive());
	}

	@Override
	public void tearDown() throws Exception {
		if (persona.isVisible()) {
			persona.releaseFocus();
			persona.setVisible(false);
		}
		super.tearDown();
	}
}
