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

import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Classification;
import com.aether.present.CharacterTypeImage;

public class TestInGamePresenter {
	private InGameView view;
	private StateTransition stateTransition;
	private CharacterLocator characterLocator;

	@Before
	public void setUp() {
		view = createStrictMock(InGameView.class);
		view.setPresenter((InGamePresenter)anyObject());
		stateTransition = EasyMock.createNiceMock("StateTransition", StateTransition.class);
		characterLocator = EasyMock.createNiceMock(CharacterLocator.class);
	}
	
	@Test
	public void test_Show_initialization_of_default_state() throws Exception {
		replay(view);
		new InGamePresenter(view, stateTransition, characterLocator);
		verify(view);
	}
	
	@Test
	public void test_Transition_to_options_menu() throws Exception {
		stateTransition.transition((ActiveState)anyObject(), matches(InGamePresenter.OPTIONS_MENU_TRANSITION));
		
		replay(view, stateTransition);
		InGamePresenter presenter = new InGamePresenter(view, stateTransition, characterLocator);
		presenter.optionsMenu();
		verify(view, stateTransition);
	}
	
    @Test
    public void testEnterActiveAndExitActiveState() throws Exception {
        view.activate();
		CharacterSheet hero = new CharacterSheet("Joe", "other", "male", Classification.Crusader);
		EasyMock.expect(characterLocator.getPlayer()).andReturn(hero);
		view.setHealth(10, 10);
		view.setMana(10, 2);
		view.setImage((Image)EasyMock.anyObject());
        view.deactivate();

        replay(view, stateTransition, characterLocator);
        InGamePresenter presenter = new InGamePresenter(view, stateTransition, characterLocator);
        presenter.enter();
        presenter.exit();
        verify(view, stateTransition, characterLocator);
    }
}
