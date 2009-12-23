package com.aether.present.hud.journal;

import static org.junit.Assert.assertNotNull;

import java.awt.Image;

import org.junit.Test;

import com.aether.model.quests.QuestType;
import com.aether.present.hud.journal.QuestTypeImage;

public class TestQuestTypeImage {
	@Test
	public void testname() throws Exception {
		for (QuestType each : QuestType.values()) {
			Image image = QuestTypeImage.getImage(each);
			assertNotNull("Unable to load image for type: " + each.name(), image);
		}
	}
}
