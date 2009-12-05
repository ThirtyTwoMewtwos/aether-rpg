package com.aether.present;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.gbui.operators.BMeterBarOperator;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.present.state.InGameView;
import com.aether.present.state.MainMenuView;
import com.jmex.bui.BWindow;
import com.jmex.bui.icon.BlankIcon;


public class TestInGameWindow extends AetherTestCase {
	private InGamePage inGamePage;

	@Before
	public void setUp() throws Exception {
		CreateCharacterPage createCharacter = new MainMenuPage().clickNewCampain();
		createCharacter.loadDummyData();
		inGamePage = createCharacter.clickFinish();
	}
	
	@Test
	public void test_Esc_navigates_to_options_menu() throws Exception {
		new BKeyboard().typeKey(KeyEvent.VK_ESCAPE);
		BComponentOperatorUtil.windowWithId(MainMenuView.ID);
	}
	
	@Test
	public void test_Health_is_properly_set_for_new_hero() throws Exception {
		BWindow window = inGamePage.getPlayerStats();
		BMeterBarOperator health = new BMeterBarOperator(window, InGameView.STATS_HEALTH_ID);
		assertEquals(10, health.getValue());
		assertEquals(10, health.getMaximum());
		assertEquals("(10/10)", health.getTooltip());
	}
	
	@Test
	public void test_Mana_is_properly_set_for_new_hero() throws Exception {
		BWindow window = inGamePage.getPlayerStats();
		BMeterBarOperator mana = new BMeterBarOperator(window, InGameView.STATS_MANA_ID);
		assertEquals(2, mana.getValue());
		assertEquals(10, mana.getMaximum());
		assertEquals("(2/10)", mana.getTooltip());
	}
	
	@Test
	public void test_Label_image_is_loaded() throws Exception {
		BWindow window = inGamePage.getPlayerStats();
		BLabelOperator symbol = new BLabelOperator(window, new NameOperatorSearch(InGameView.STATS_SYMBOL_ID));
		assertFalse(symbol.getIcon() instanceof BlankIcon);
	}
	
	@Test
	public void test_When_chatting_player_movement_is_inactive() throws Exception {
		PlayerMovementState state = Main.getServiceStore().get(PlayerMovementState.class);
		assertTrue(state.isActive());
		BTextFieldOperator input = inGamePage.getChatInputField();
		input.setText("say hello");
		assertFalse(state.isActive());
	}
	
	@Test
	public void test_When_entering_text_into_chat_no_other_windows_open_or_close() throws Exception {
		BTextFieldOperator input = inGamePage.getChatInputField();
		input.requestFocus();
		new BKeyboard().typeKey(KeyEvent.VK_P);
		assertFalse(inGamePage.getPersonaPage().isVisible());
	}
	
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		if (!Main.getGame().isStarted()) {
			Main.startGame();
		}
	}
}
