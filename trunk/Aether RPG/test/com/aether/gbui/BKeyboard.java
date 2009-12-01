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
	 * java.awt.event.KeyEvent is used.
	 * @param keycode
	 */
	public void typeKey(int keycode) {
		bot.keyPress(keycode);
		BComponentOperatorUtil.delayForUpdate();
		BComponentOperatorUtil.delayForUpdate();
		bot.keyRelease(keycode);
		BComponentOperatorUtil.delayForUpdate();
		BComponentOperatorUtil.delayForUpdate();
	}
}
