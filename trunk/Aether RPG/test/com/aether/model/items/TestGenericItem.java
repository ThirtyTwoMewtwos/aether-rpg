package com.aether.model.items;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestGenericItem {
	@Test
	public void test_Init_item() throws Exception {
		GenericItem item = new GenericItem("trash", "this is a piece of trash", 1);
		
		assertEquals("trash", item.getName());
		assertEquals("this is a piece of trash", item.getDescription());
		assertEquals(1, item.getWeight());
	}
}
