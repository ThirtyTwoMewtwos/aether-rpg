package com.aether.present.state;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.matches;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.Hero;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Classification;

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
		Hero hero = new Hero("Joe", "other", "male", Classification.Acolyte);
		EasyMock.expect(characterLocator.getPlayer()).andReturn(hero);
		view.setHealth(10, 10);
        view.deactivate();

        replay(view, stateTransition, characterLocator);
        InGamePresenter presenter = new InGamePresenter(view, stateTransition, characterLocator);
        presenter.enter();
        presenter.exit();
        verify(view, stateTransition, characterLocator);
    }
}