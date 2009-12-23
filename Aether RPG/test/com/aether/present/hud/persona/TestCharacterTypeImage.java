package com.aether.present.hud.persona;

import static org.junit.Assert.assertNotNull;

import java.awt.Image;

import org.junit.Test;

import com.aether.model.character.Classification;


public class TestCharacterTypeImage {
	@Test
	public void test_Able_to_load_all_Classification_types() throws Exception {
		for (Classification each : Classification.values()) {
			Image image = CharacterTypeImage.getImage(each);
			assertNotNull("Unable to load image for type: " + each.getName(), image);
		}
	}
}
