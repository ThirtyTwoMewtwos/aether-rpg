package com.aether.model.items;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.aether.test.AssertCollection;

public class TestCharacterBackpack {
	private CharacterBackpack backpack;
	private Item itemA;
	private Item itemB;
	private Item itemC;

	@Before
	public void setUp() throws Exception {
		backpack = new CharacterBackpack();
		itemA = new GenericItem("itemA", null, null, 5);
		itemB = new GenericItem("itemB", null, null, 3);
		itemC = new GenericItem("itemC", null, null, 7);
	}
	
	@Test
	public void test_Create_empty_backpack() throws Exception {
		assertEquals(0, backpack.weightCarried());
		assertTrue(backpack.isEmpty());
	}
	
	@Test
	public void test_Add_item_to_backpack() throws Exception {
		backpack.addItem(itemA);
		assertEquals(5, backpack.weightCarried());
		assertFalse(backpack.isEmpty());
	}
	
	@Test
	public void test_Adding_many_items_accumulates_weight() throws Exception {
		backpack.addItem(itemA);
		backpack.addItem(itemB);
		backpack.addItem(itemC);
		
		assertEquals(15, backpack.weightCarried());
		assertEquals(3, backpack.itemCount());
	}
	
	@Test
	public void test_Dropping_an_item_reduces_the_weight_accumulates_weight() throws Exception {
		backpack.addItem(itemA);
		backpack.addItem(itemB);
		backpack.addItem(itemC);
		backpack.removeItem(itemB);
		assertEquals(12, backpack.weightCarried());
		assertEquals(2, backpack.itemCount());
	}
	
	@Test
	public void test_Only_remove_items_that_are_contained() throws Exception {
		backpack.addItem(itemA);
		backpack.addItem(itemB);
		backpack.removeItem(itemC);
		assertEquals(8, backpack.weightCarried());
		assertEquals(2, backpack.itemCount());
	}
	
	@Test
	public void test_Only_add_unique_items() throws Exception {
		backpack.addItem(itemA);
		backpack.addItem(itemA);
		assertEquals(5, backpack.weightCarried());
		assertEquals(1, backpack.itemCount());
	}
	
	@Test
	public void test_Get_all_items() throws Exception {
		backpack.addItem(itemA);
		backpack.addItem(itemB);
		backpack.addItem(itemC);
		
		Collection<? extends Object> items = backpack.getAllItems();
		
		AssertCollection.assertContains(itemA, items);
		AssertCollection.assertContains(itemB, items);
		AssertCollection.assertContains(itemC, items);
	}
	
	@Test
	public void test_Add_multiple_items_places_in_sequential_order() throws Exception {
		backpack.addItem(itemA);
		backpack.addItem(itemB);
		backpack.addItem(itemC);
		assertEquals(0, backpack.locationOf(itemA));
		assertEquals(1, backpack.locationOf(itemB));
		assertEquals(2, backpack.locationOf(itemC));
	}
	
	@Test
	public void test_Move_added_item_to_new_location() throws Exception {
		backpack.addItem(itemA);
		
		backpack.setLocation(21, itemA);
		assertEquals(21, backpack.locationOf(itemA));
	}
}
