package com.aether.present.state;

import static org.easymock.EasyMock.*;
import org.junit.Test;

public class TestMainMenuPresenter {
	
	@Test
	public void testInitializationAndShutdown() throws Exception {
		MainMenuView view = createStrictMock("MainMenuView", MainMenuView.class);
		StateTransitioner presentationState = createStrictMock("GameState", StateTransitioner.class);
		ShutdownService exitService = createStrictMock("ExitService", ShutdownService.class);
		
		view.setPresenter((MainMenuPresenter)anyObject());
		view.activate();
		presentationState.transition((MainMenuPresenter)anyObject(), matches(MainMenuPresenter.CREATE_CHARACTER_TRANSITION));
		exitService.shutdown();
		view.deactivate();
		
		replay(view, exitService, presentationState);
		MainMenuPresenter presenter = new MainMenuPresenter(view, presentationState, exitService);
		presenter.enter();
		presenter.performCreateCharacter();
		presenter.performExit();
		presenter.exit();
		verify(view, exitService, presentationState);
	}
}
