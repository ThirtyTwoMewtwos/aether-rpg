package com.aether.present.hud.equipment;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.awt.Image;

import org.junit.Test;

public class TestItemTypeImage {

	@Test
	public void test_Able_to_load_default_image() throws Exception {
		Image image = ItemTypeImage.getImage("a non existant type");
		assertNotNull("Unable to load a default image", image);
		Image sameImage = ItemTypeImage.getImage("a non existant type");
		assertSame(image, sameImage);
	}
	
	@Test
	public void test_Load_food_items() throws Exception {
		Image defaultImage = ItemTypeImage.getImage("a non existant type");
		for (int i = 0; i < 24; i++) {
			Image image = ItemTypeImage.getImage("food_" + i);
			assertNotSame("expected food_" + i + " to be in list:", defaultImage, image);
		}
	}
	
	@Test
	public void test_Load_jewelry_items() throws Exception {
		Image defaultImage = ItemTypeImage.getImage("a non existant type");
		for (int i = 0; i < 8 * 5; i++) {
			Image image = ItemTypeImage.getImage("jewelry_" + i);
			assertNotSame("expected jewelry_" + i + " to be in list:", defaultImage, image);
		}
	}
	
	@Test
	public void test_Load_weaponry_items() throws Exception {
		Image defaultImage = ItemTypeImage.getImage("a non existant type");
		for (int i = 0; i < 10 * 10; i++) {
			Image image = ItemTypeImage.getImage("weapons_" + i);
			assertNotSame("expected weapons_" + i + " to be in list:", defaultImage, image);
		}
	}
	
	@Test
	public void test_Load_potions_items() throws Exception {
		Image defaultImage = ItemTypeImage.getImage("a non existant type");
		for (int i = 0; i < 6 * 10; i++) {
			Image image = ItemTypeImage.getImage("potions_" + i);
			assertNotSame("expected potions_" + i + " to be in list:", defaultImage, image);
		}
	}
}
