package com.aether.present.state;

public interface MainMenuView {
	void setPresenter(MainMenuPresenter anyObject);

	void activate();

	void deactivate();
}
