package com.aether.gbui;

import java.awt.AWTException;

import javax.swing.JComponent;

import com.jmex.bui.BButton;
import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.MouseEvent;

public class BButtonOperator {
	private BButton button;

	public BButtonOperator(final BWindow window, final String label) throws AWTException {
		button = (BButton)BComponentOperatorUtil.findWidget(window, new LabeledOperatorSearcher(BButton.class, label));
	}

	public void click() {
		button.dispatchEvent(new MouseEvent(this, System.currentTimeMillis(), 0, MouseEvent.MOUSE_PRESSED, 0, 0, 0));
		BComponentOperatorUtil.performPause();
		button.dispatchEvent(new MouseEvent(this, System.currentTimeMillis(), 0, MouseEvent.MOUSE_RELEASED, 0, 0, 0));
		
		System.out.println("Mouse clicked button [" + button.getText() + "]");
	}

	public boolean isEnabled() {
		return button.isEnabled();
	}
}
