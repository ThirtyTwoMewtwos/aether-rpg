package com.aether.model.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.aether.model.character.Classification;
import com.aether.model.character.PCLocator;
import com.aether.model.character.PlayerCharacter;
import com.aether.model.character.Sex;
import com.aether.model.items.EquipmentContainer;

public class TestItemCommandParser {
	private EquipmentContainer backpack;
	private ItemCommandParser parser;

	@Before
	public void setUp() {
		PCLocator locator = new PCLocator();
		locator.setPlayer(new PlayerCharacter("bobo", "", Sex.Female, Classification.Acolyte));
		parser = new ItemCommandParser(locator);
		backpack = locator.getPlayer().getEquipmentContainer();
	}
	
	@Test
	public void test_Create_item() throws Exception {
		String result = parser.parse("items add *");
		assertEquals("added", result);
		assertEquals(1, backpack.itemCount());
	}
	
	@Test
	public void test_Unknown_item_requested() throws Exception {
		assertEquals("unknown item requested.", parser.parse("items add something_unknown"));
	}
	
	@Test
	public void test_Ring_creation() throws Exception {
		assertEquals("added Ring", parser.parse("items add jewelry_1"));
		assertEquals(1, backpack.itemCount());
		assertEquals("jewelry_1", backpack.getAllItems().iterator().next().getItemType());
	}
	
	@Test
	public void test_Requesting_help_on_commands() throws Exception {
		String result = parser.parse("items ?");
		assertNotNull(result);
		assertFalse(result.contains("unknown item requested."));
	}
	
	@Test
	public void test_Passing_in_unknown_command() throws Exception {
		String result = parser.parse("items poo-bear 7");
		assertNotNull(result);
		assertEquals("Unknown items command given.", result);
	}
	
	@Test
	public void test_Command_has_no_parameters() throws Exception {
		String result = parser.parse("items");
		assertEquals("Unknown items command given.", result);
	}
}
