package com.aether.present.state;

public interface InGameView {
	public static final String ID = "in.game.view";

	void setPresenter(InGamePresenter anyObject);

	void activate();

	void deactivate();

}
