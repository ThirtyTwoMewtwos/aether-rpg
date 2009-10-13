package com.aether.present.state;

import org.gap.jseed.injection.annotation.Singleton;

/** 
 * This looks too simple, but give it time, this should handle
 * loading of game state, evaluating whether a saved game exists, informing
 * the view to enable or disable the login button, etc.  And of course
 * manages shutting down of the UI.
 *  
 * @author gpelcha
 */
@Singleton
public class MainMenuPresenter implements ActiveState {
	public static final String CREATE_CHARACTER_TRANSITION = "create.character.view";
	
	private final ShutdownService exitService;
	private final MainMenuView view;
	private final StateTransition presentationState;

	public MainMenuPresenter(MainMenuView view, StateTransition presentationState, ShutdownService exitService) {
		this.view = view;
		this.presentationState = presentationState;
		this.exitService = exitService;
		view.setPresenter(this);
	}

	public void performExit() {
		exitService.shutdown();
	}

	public void enter() {
		view.activate();
	}

	public void performCreateCharacter() {
		presentationState.transition(this, CREATE_CHARACTER_TRANSITION);
	}

	@Override
	public void exit() {
		view.deactivate();
	}
}
