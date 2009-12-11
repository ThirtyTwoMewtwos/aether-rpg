package com.aether.model.quests;

import static org.junit.Assert.assertNotNull;

import java.awt.Image;

import org.junit.Test;

public class TestQuestTypeImage {
	@Test
	public void testname() throws Exception {
		for (QuestType each : QuestType.values()) {
			Image image = QuestTypeImage.getImage(each);
			assertNotNull("Unable to load image for type: " + each.name(), image);
		}
	}
}
