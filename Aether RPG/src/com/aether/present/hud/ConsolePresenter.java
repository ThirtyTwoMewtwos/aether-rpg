package com.aether.present.hud;

import com.aether.model.CommandParser;


public class ConsolePresenter implements ViewPresenter {
	private boolean isVisible = false;
	private final CommandParser parser;
	private final ConsoleView view;

	public ConsolePresenter(ConsoleView view, CommandParser parser) {
		this.view = view;
		this.parser = parser;
		view.setPresenter(this);
	}

	@Override
	public void activate() {
		view.activate();
	}

	@Override
	public void deactivate() {
		view.deactivate();
	}

	@Override
	public void toggleVisibility() {
		isVisible = !isVisible;
		view.setVisible(isVisible);
	}

	public void processCommand(String command) {
		String result = parser.parse(command);
		view.setResponse(result);
	}
}
