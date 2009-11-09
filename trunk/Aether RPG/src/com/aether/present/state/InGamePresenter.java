package com.aether.present.state;

public class InGamePresenter implements ActiveState {

	public static final String OPTIONS_MENU_TRANSITION = "options.menu";
	private final StateTransition stateTransition;
	private final InGameView view;

	public InGamePresenter(InGameView view, StateTransition stateTransition) {
		this.view = view;
		this.stateTransition = stateTransition;
		view.setPresenter(this);
	}

	public void optionsMenu() {
		stateTransition.transition(this, OPTIONS_MENU_TRANSITION);
	}
	
	@Override
	public void enter() {
		view.activate();
	}

	@Override
	public void exit() {
		view.deactivate();
	}
}
