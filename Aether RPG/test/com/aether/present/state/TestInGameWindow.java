package com.aether.present.state;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BMeterBarOperator;
import com.aether.present.Main;
import com.jmex.bui.BWindow;


public class TestInGameWindow {

	@Before
	public void setUp() throws Exception {
		Main.startGame();
		CreateCharacterPage createCharacter = new MainMenuPage().clickNewCampain();
		createCharacter.loadDummyData();
		createCharacter.clickFinish();
	}
	
	@Test
	public void test_Can_navigate_to_game_view() throws Exception {
		BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
	}
	
	@Test
	public void test_F12_navigates_to_options_menu() throws Exception {
		BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
		new BKeyboard().typeKey(KeyEvent.VK_F12);
		BComponentOperatorUtil.windowWithId(MainMenuView.ID);
	}
	
	@Test
	public void test_Changing_the_hit_points_reflects_the_meter() throws Exception {
		BWindow window = BComponentOperatorUtil.windowWithId(InGameView.STATS_ID);
		BMeterBarOperator hitPoints = new BMeterBarOperator(window, InGameView.STATS_HEALTH_ID);
		assertEquals(10, hitPoints.getValue());
	}
	
	@After
	public void tearDown() {
		Main.shutdown();
	}
}
