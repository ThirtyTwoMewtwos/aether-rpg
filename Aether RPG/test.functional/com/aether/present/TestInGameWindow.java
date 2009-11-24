package com.aether.present;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.gbui.operators.BMeterBarOperator;
import com.aether.present.Main;
import com.aether.present.state.InGameView;
import com.aether.present.state.MainMenuView;
import com.jmex.bui.BWindow;
import com.jmex.bui.icon.BlankIcon;


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
	public void test_Esc_navigates_to_options_menu() throws Exception {
		BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
		new BKeyboard().typeKey(KeyEvent.VK_ESCAPE);
		BComponentOperatorUtil.windowWithId(MainMenuView.ID);
	}
	
	@Test
	public void test_Health_is_properly_set_for_new_hero() throws Exception {
		BWindow window = BComponentOperatorUtil.windowWithId(InGameView.STATS_ID);
		BMeterBarOperator health = new BMeterBarOperator(window, InGameView.STATS_HEALTH_ID);
		assertEquals(10, health.getValue());
		assertEquals(10, health.getMaximum());
		assertEquals("(10/10)", health.getTooltip());
	}
	
	@Test
	public void test_Mana_is_properly_set_for_new_hero() throws Exception {
		BWindow window = BComponentOperatorUtil.windowWithId(InGameView.STATS_ID);
		BMeterBarOperator mana = new BMeterBarOperator(window, InGameView.STATS_MANA_ID);
		assertEquals(2, mana.getValue());
		assertEquals(10, mana.getMaximum());
		assertEquals("(2/10)", mana.getTooltip());
	}
	
	@Test
	public void test_Label_image_is_loaded() throws Exception {
		BWindow window = BComponentOperatorUtil.windowWithId(InGameView.STATS_ID);
		BLabelOperator symbol = new BLabelOperator(window, new NameOperatorSearch(InGameView.STATS_SYMBOL_ID));
		assertFalse(symbol.getIcon() instanceof BlankIcon);
	}
	
	@After
	public void tearDown() {
		Main.shutdown();
	}
}
