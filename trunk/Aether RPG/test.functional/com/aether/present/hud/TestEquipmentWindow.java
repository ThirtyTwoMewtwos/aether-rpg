package com.aether.present.hud;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.aether.model.character.CharacterLocator;
import com.aether.model.items.EquipmentContainer;
import com.aether.model.items.GenericItem;
import com.aether.present.AetherTestCase;
import com.aether.present.CreateCharacterPage;
import com.aether.present.EquipmentPage;
import com.aether.present.InGamePage;
import com.aether.present.Main;
import com.aether.present.MainMenuPage;


public class TestEquipmentWindow extends AetherTestCase {
	private EquipmentPage equipmentPage;
	private CharacterLocator locator;

	@Before
	public void setUp() throws Exception {
		CreateCharacterPage createCharacterPage = new MainMenuPage().clickNewCampain();
		createCharacterPage.loadDummyData();
		InGamePage inGamePage = createCharacterPage.clickFinish();
		equipmentPage = inGamePage.getEquipmentPage();
		locator = Main.getServiceStore().get(CharacterLocator.class);
	}
	
	@Test
	public void test_Equipment_view_is_visible() throws Exception {
		equipmentPage.setVisible(true);
		assertTrue(equipmentPage.isVisible());
		equipmentPage.setVisible(false);
		assertFalse(equipmentPage.isVisible());
	}
	
	@Test
	public void test_Equipment_weight_load_is_displayed() throws Exception {
		addPocketProtector();
		
		equipmentPage.setVisible(true);
		assertEquals("15 lbs.", equipmentPage.getWeight());
	}

	private void addPocketProtector() {
		EquipmentContainer container = locator.getPlayer().getEquipmentContainer();
		container.addItem(new GenericItem("Pocket protector", "nerd tool", 15));
	}
	
	@Test
	public void test_Equipment_is_listed() throws Exception {
		addPocketProtector();
		
		equipmentPage.setVisible(true);
		assertEquals(1, equipmentPage.getItemsCount());
	}
}
