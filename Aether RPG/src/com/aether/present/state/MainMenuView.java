package com.aether.present.state;

public interface MainMenuView {
	public static final String ID = "main.menu.view";
	void setPresenter(MainMenuPresenter anyObject);

	void activate();

	void deactivate();
}
