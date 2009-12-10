package com.aether.present.state;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.matches;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.awt.Image;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.character.CharacterLocator;
import com.aether.model.character.CharacterSheet;
import com.aether.model.character.Classification;
import com.aether.model.character.PlayerCharacter;
import com.aether.model.character.Sex;
import com.aether.present.hud.HUDViewLocator;

public class TestInGamePresenter {
	private InGameView view;
	private StateTransition stateTransition;
	private CharacterLocator characterLocator;
	private HUDViewLocator hudLocator;

	@Before
	public void setUp() {
		view = createStrictMock(InGameView.class);
		stateTransition = EasyMock.createNiceMock("StateTransition", StateTransition.class);
		characterLocator = EasyMock.createNiceMock(CharacterLocator.class);
		hudLocator = EasyMock.createStrictMock(HUDViewLocator.class);

		view.setPresenter((InGamePresenter)anyObject());
	}
	
	@Test
	public void test_Show_initialization_of_default_state() throws Exception {
		replay(view);
		new InGamePresenter(view, stateTransition, characterLocator, hudLocator);
		verify(view);
	}
	
	@Test
	public void test_Transition_to_options_menu() throws Exception {
		stateTransition.transition((ActiveState)anyObject(), matches(InGamePresenter.OPTIONS_MENU_TRANSITION));
		
		replay(view, stateTransition, characterLocator, hudLocator);
		InGamePresenter presenter = new InGamePresenter(view, stateTransition, characterLocator, hudLocator);
		presenter.optionsMenu();
		verify(view, stateTransition, characterLocator, hudLocator);
	}
	
    @Test
    public void test_Enter_active_and_exit_active_state() throws Exception {
        view.activate();
        hudLocator.activate();
		CharacterSheet hero = new PlayerCharacter("Joe", "other", Sex.Male, Classification.Crusader);
		EasyMock.expect(characterLocator.getPlayer()).andReturn(hero);
		view.setHealth(10, 10);
		view.setMana(10, 2);
		view.setImage((Image)EasyMock.anyObject());
        view.deactivate();
        hudLocator.deactivate();

        replay(view, stateTransition, characterLocator, hudLocator);
        InGamePresenter presenter = new InGamePresenter(view, stateTransition, characterLocator, hudLocator);
        presenter.enter();
        presenter.exit();
        verify(view, stateTransition, characterLocator, hudLocator);
    }
    
    @Test
	public void test_Trigger_visibility_of_persona_window() throws Exception {
    	hudLocator.toggleViewVisibility(HUDViewLocator.View.PERSONA);
    	
        replay(view, stateTransition, characterLocator, hudLocator);
        InGamePresenter presenter = new InGamePresenter(view, stateTransition, characterLocator, hudLocator);
        presenter.togglePersona();
        verify(view, stateTransition, characterLocator, hudLocator);
	}
}
