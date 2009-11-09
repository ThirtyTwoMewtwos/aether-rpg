package com.aether.present.state;

import static org.junit.Assert.*;

import static org.easymock.EasyMock.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;


public class TestInGamePresenter {

	private InGameView view;
	private StateTransition stateTransition;

	@Before
	public void setUp() {
		view = createStrictMock(InGameView.class);
		view.setPresenter((InGamePresenter)anyObject());
		stateTransition = EasyMock.createNiceMock("StateTransition", StateTransition.class);
	}
	
	@Test
	public void test_Show_initialization_of_default_state() throws Exception {
		replay(view);
		new InGamePresenter(view, stateTransition);
		verify(view);
	}
	
	@Test
	public void test_Transition_to_options_menu() throws Exception {
		stateTransition.transition((ActiveState)anyObject(), matches(InGamePresenter.OPTIONS_MENU_TRANSITION));
		replay(view, stateTransition);
		InGamePresenter presenter = new InGamePresenter(view, stateTransition);
		presenter.optionsMenu();
		verify(view, stateTransition);
	}
	
    @Test
    public void testEnterActiveAndExitActiveState() throws Exception {
        view.activate();
        view.deactivate();

        replay(view, stateTransition);
        InGamePresenter presenter = new InGamePresenter(view, stateTransition);
        presenter.enter();
        presenter.exit();
        verify(view, stateTransition);
    }
}
