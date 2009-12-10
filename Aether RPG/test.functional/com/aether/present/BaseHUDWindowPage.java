package com.aether.present;

import java.awt.event.KeyEvent;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.Condition;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.present.hud.JournalView;
import com.jmex.bui.BWindow;

public abstract class BaseHUDWindowPage {
	protected BWindow window;
	
	public BaseHUDWindowPage(String windowID) throws InterruptedException {
		window = BComponentOperatorUtil.windowWithId(windowID);
	}

	public boolean isVisible() {
		return window.isVisible();
	}

	public void setVisible(final boolean visibility) throws Exception {
		if (window.isVisible() != visibility) {
			new BKeyboard().typeKey(getVisibilityKeyEvent());
		}
		BComponentOperatorUtil.delayForUpdate();
		BComponentOperatorUtil.waitFor(new Condition() {
			@Override
			public boolean existing() {
				return window.isVisible() == visibility;
			}
		});
	}
	
	protected abstract int getVisibilityKeyEvent();
	
	public BWindow getWindow() {
		return window;
	}
}
