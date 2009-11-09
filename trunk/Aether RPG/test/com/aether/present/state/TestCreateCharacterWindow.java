package com.aether.present.state;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComboBoxOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.model.character.Classification;
import com.aether.model.character.Race;
import com.aether.present.CharacterCreationWindow;
import com.aether.present.Main;
import com.jmex.bui.BWindow;

public class TestCreateCharacterWindow {
	private BWindow window;
	private BButtonOperator finishButton;
	private BComboBoxOperator raceSelection;
	private BComboBoxOperator classSelection;
	@Before
	public void setUp() throws Exception {
		Main.main(new String[]{});

		BWindow mainWindow = BComponentOperatorUtil.getWindowWithId(MainMenuView.ID);
		new BButtonOperator(mainWindow, "New Campaign").click();
		
		window = BComponentOperatorUtil.getWindowWithId(CharacterCreationView.ID);

		raceSelection = new BComboBoxOperator(window, new NameOperatorSearch(CharacterCreationView.RACE_SELELECTION_NAME));
		classSelection = new BComboBoxOperator(window, new NameOperatorSearch(CharacterCreationView.CLASS_SELECTION_NAME));
		finishButton = new BButtonOperator(window, "Finish");
	}

	@Test
	public void test_Navigate_to_create_character_then_back() throws Exception {
		Assert.assertFalse(finishButton.isEnabled());
		new BButtonOperator(window, "Back").click();
		BComponentOperatorUtil.getWindowWithId(MainMenuView.ID);
	}
	
	@Test
	public void test_Create_character_is_not_allowed_until_all_fields_entered() throws Exception {
		assertFalse(finishButton.isEnabled());
		new BTextFieldOperator(window, "").setText("Joe the Invinceable");
		Assert.assertFalse(finishButton.isEnabled());
		assertFalse(classSelection.isEnabled());
		raceSelection.select(Race.HUMAN);
		assertTrue(classSelection.isEnabled());
		classSelection.select(Classification.Crusader);
		assertTrue(finishButton.isEnabled());
		finishButton.click();
	}
	
	@Test
	public void test_Classification_field_unavailable_until_a_race_is_selected() throws Exception {
		assertFalse(classSelection.isEnabled());
		raceSelection.select(Race.HUMAN);
		assertTrue(classSelection.isEnabled());
		raceSelection.select(0);
		assertFalse(classSelection.isEnabled());
	}

	@After
	public void tearDown() throws Exception {
		Main.shutdown();
	}
}
