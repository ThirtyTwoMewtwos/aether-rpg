package com.aether.present;

import java.awt.AWTException;

import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.present.state.LoginView;
import com.jmex.bui.BWindow;

public class LoginPage {
	private BWindow loginWindow;

	public LoginPage() throws InterruptedException {
		loginWindow = BComponentOperatorUtil.windowWithId(LoginView.ID);
	}

	public MainMenuPage clickLogin() throws InterruptedException, AWTException {
		new BButtonOperator(loginWindow, "Login").click();
		return new MainMenuPage();
	}
}
