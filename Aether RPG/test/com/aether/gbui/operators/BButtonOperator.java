package com.aether.gbui.operators;

import java.awt.AWTException;
import java.util.concurrent.Callable;

import com.aether.gbui.LabeledOperatorSearcher;
import com.jmex.bui.BButton;
import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.MouseEvent;

public class BButtonOperator extends BComponentOperator {
	private BButton button;

	public BButtonOperator(final BWindow window, final String label) throws AWTException {
		button = (BButton)BComponentOperatorUtil.findWidget(window, new LabeledOperatorSearcher(BButton.class, label));
	}

	public void click() {
		BComponentOperatorUtil.callInBuiThread(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				button.dispatchEvent(new MouseEvent(this, System.currentTimeMillis(), 0, MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON1, 0, 0));
				button.dispatchEvent(new MouseEvent(this, System.currentTimeMillis(), 0, MouseEvent.MOUSE_RELEASED, MouseEvent.BUTTON1, 0, 0));
				return null;
			}
		});
	}
	
	@Override
	public BComponent getComponent() {
		return button;
	}

	@Override
	public String toString() {
		String toString = BButtonOperator.class.getName() + ": ";
		if (button == null) {
			return toString + null;
		}
		return toString + button.getText();
	}
}
