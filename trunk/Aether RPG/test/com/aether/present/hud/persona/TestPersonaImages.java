package com.aether.present.hud.persona;

import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Image;

public class TestPersonaImages {
	@Test
	public void test_Load_block_image() throws Exception {
		for (CombatPanelStatistics each : CombatPanelStatistics.values()) {
			Image image = PersonaImages.getImage(each);
			assertNotNull("Unable to load image for type: " + each.name(), image);
		}
	}
}
