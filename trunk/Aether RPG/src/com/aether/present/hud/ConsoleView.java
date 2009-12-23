package com.aether.present.hud;

public interface ConsoleView {
	public static final String CONSOLE_ID = "console.id";
	public static final String INPUT_ID = "console.input.id";
	public static final String RESULT_ID = "console.result.id";

	void setPresenter(ConsolePresenter anyObject);

	void setResponse(String value);

	void activate();

	void deactivate();

    void setVisible(boolean b);
}
