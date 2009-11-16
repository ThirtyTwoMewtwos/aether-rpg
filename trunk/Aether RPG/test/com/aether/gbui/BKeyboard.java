package com.aether.gbui;

import java.awt.AWTException;
import java.awt.Robot;

import com.aether.gbui.operators.BComponentOperatorUtil;

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
		BComponentOperatorUtil.delayForUpdate();
		bot.keyPress(keycode);
		BComponentOperatorUtil.delayForUpdate();
		BComponentOperatorUtil.delayForUpdate();
		bot.keyRelease(keycode);
		BComponentOperatorUtil.delayForUpdate();
	}
}
