package com.aether.gbui;

import java.awt.AWTException;
import java.awt.Robot;

public class BKeyboard {
	private Robot bot;
	
	public BKeyboard() throws AWTException {
		bot = new Robot();
	}
	
	/**
	 * TODO Seems escape key does not work sometimes.
	 * @param keycode
	 */
	public void typeKey(int keycode) {
		bot.keyPress(keycode);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		bot.keyRelease(keycode);
	}
}
