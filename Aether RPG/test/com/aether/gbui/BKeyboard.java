package com.aether.gbui;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.Callable;

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
	public void typeKey(final int keycode) {
		BComponentOperatorUtil.callInBuiThread(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				bot.keyPress(keycode);
				return null;
			}
		});
		BComponentOperatorUtil.callInBuiThread(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				bot.keyRelease(keycode);
				return null;
			}
		});
	}
}
