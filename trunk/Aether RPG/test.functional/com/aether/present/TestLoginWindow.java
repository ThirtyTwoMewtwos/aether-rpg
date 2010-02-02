package com.aether.present;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLoginWindow {
	@Test
	public void testLoginWithUser() throws Exception {
		Main.startGame();
		LoginPage loginPage = new LoginPage();
		assertFalse(loginPage.isLoginEnabled());
		loginPage.setUsername("offline user");
		loginPage.setPassword("offline user password");
		assertTrue(loginPage.isLoginEnabled());
		loginPage.clickLogin();
		Main.shutdown();
	}
}
