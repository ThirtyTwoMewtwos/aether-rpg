package com.aether.present.game;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Test;

import com.jme.scene.Skybox;


public class TestSkyboxFacingImages {
	@Test
	public void test_Get_facing_image() throws Exception {
		for (Skybox.Face each : Skybox.Face.values()) {
			Image image = SkyboxFacingImages.getImage(each);
			assertNotNull("Unable to load image for type: " + each.name(), image);
		}
	}
}
