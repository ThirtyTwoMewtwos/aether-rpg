package com.aether.present.hud;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.aether.model.quests.KillQuest;


public class TestQuestLabelProvider {
	
	private QuestLabelProvider provider;

	@Before
	public void setUp() {
		provider = new QuestLabelProvider();
	}
	
	@Test
	public void test_Given_a_quest_return_the_title() throws Exception {
		KillQuest killQuest = new KillQuest("Devil Dancing", null, 0, 0, "");
		assertEquals("Devil Dancing", provider.getText(killQuest));
	}
	
	@Test
	public void test_Given_something_other_than_a_quest() throws Exception {
		String x = "definitely not a quest object";
		assertEquals(x, provider.getText(x));
	}
}
