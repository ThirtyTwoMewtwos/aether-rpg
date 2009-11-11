package com.aether.present.state;

import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.jmex.bui.BWindow;

public class MainMenuPage {
	private BWindow mainWindow;

	public MainMenuPage() throws InterruptedException {
		mainWindow = BComponentOperatorUtil.windowWithId(MainMenuView.ID);
	}
	
	public CreateCharacterPage clickNewCampain() throws Exception {
		new BButtonOperator(mainWindow, "New Campaign").click();
		return new CreateCharacterPage();
	}

	public void clickExit() throws Exception {
		new BButtonOperator(mainWindow, "Exit").click();
	}
}
