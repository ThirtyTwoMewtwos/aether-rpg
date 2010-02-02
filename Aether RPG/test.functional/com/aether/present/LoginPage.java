package com.aether.present;

import java.awt.AWTException;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.present.state.LoginView;
import com.jmex.bui.BWindow;

public class LoginPage {
	private BWindow loginWindow;
	private BButtonOperator loginButton;

	public LoginPage() throws InterruptedException {
		loginWindow = BComponentOperatorUtil.windowWithId(LoginView.ID);
	}

	public MainMenuPage clickLogin() throws InterruptedException, AWTException {
		getLoginButton().click();
		return new MainMenuPage();
	}

	public BButtonOperator getLoginButton() throws AWTException {
		if (loginButton == null) {
			loginButton = new BButtonOperator(loginWindow, "Login");
		}
		return loginButton;
	}

	public void setUsername(String newUsername) {
		BTextFieldOperator username = new BTextFieldOperator(loginWindow, new NameOperatorSearch(LoginView.USERNAME_ID));
		username.setText(newUsername);
	}

	public void setPassword(String newPassword) {
		BTextFieldOperator password = new BTextFieldOperator(loginWindow, new NameOperatorSearch(LoginView.PASSWORD_ID));
		password.setText(newPassword);
	}

	public boolean isLoginEnabled() throws AWTException {
		return getLoginButton().isEnabled();
	}
	
	public MainMenuPage quickLogin() throws AWTException, InterruptedException {
		setUsername("joe");
		setPassword("some password");
		return clickLogin();
	}
}
