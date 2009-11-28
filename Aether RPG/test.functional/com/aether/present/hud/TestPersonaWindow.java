package com.aether.present.hud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.present.CreateCharacterPage;
import com.aether.present.InGamePage;
import com.aether.present.Main;
import com.aether.present.MainMenuPage;
import com.aether.present.hud.PersonaView;
import com.jmex.bui.BWindow;

public class TestPersonaWindow {
	
	private BWindow personaWindow;

	@Before
	public void setUp() throws Exception {
		Main.startGame();
		CreateCharacterPage newCampain = new MainMenuPage().clickNewCampain();
		newCampain.loadDummyData();
		InGamePage gamePage = newCampain.clickFinish();
		
		personaWindow = gamePage.getPersonaWindow();
	}
	
	@Test
	public void test_Window_is_available() throws Exception {
		togglePersonaView();
		assertTrue(personaWindow.isVisible());
		togglePersonaView();
		assertFalse(personaWindow.isVisible());
	}

	private void togglePersonaView() throws AWTException {
		new BKeyboard().typeKey(KeyEvent.VK_P);
	}
	
	@Test
	public void test_Statistics_have_been_set_from_character_sheet() throws Exception {
		togglePersonaView();
		BLabelOperator strength = getStatisticLabel(PersonaView.STRENGTH_STATISTIC);
		assertEquals("10", strength.getText());
		BLabelOperator dexterity = getStatisticLabel(PersonaView.DEXTERITY_STATISTIC);
		assertEquals("10", dexterity.getText());
		BLabelOperator intelligence = getStatisticLabel(PersonaView.INTELLIGENCE_STATISTIC);
		assertEquals("10", intelligence.getText());
		BLabelOperator wisdom = getStatisticLabel(PersonaView.WISDOM_STATISTIC);
		assertEquals("10", wisdom.getText());
	}

	private BLabelOperator getStatisticLabel(String stat) {
		return new BLabelOperator(personaWindow, new NameOperatorSearch(stat));
	}

	@After
	public void tearDown() {
		Main.shutdown();
	}
}
