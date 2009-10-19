package com.aether.gbui;

import java.awt.AWTException;
import java.util.concurrent.Callable;

import javax.swing.JComponent;

import com.jmex.bui.BButton;
import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.MouseEvent;

public class BButtonOperator {
	private BButton button;

	public BButtonOperator(final BWindow window, final String label) throws AWTException {
		button = (BButton)BComponentOperatorUtil.findWidget(window, new LabeledOperatorSearcher(BButton.class, label));
	}

	public void click() {
		button.dispatchEvent(new MouseEvent(this, System.currentTimeMillis(), 0, MouseEvent.MOUSE_PRESSED, 0, 0, 0));
		button.dispatchEvent(new MouseEvent(this, System.currentTimeMillis(), 0, MouseEvent.MOUSE_RELEASED, 0, 0, 0));
		final boolean[] clicked = new boolean[1];
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				clicked[0] = true;
			}
			
		};
		button.addListener(listener);
		System.out.println("Mouse clicked button [" + button.getText() + "]");
		BComponentOperatorUtil.delayForUpdate();
		button.removeListener(listener);
	}

	public boolean isEnabled() {
		Callable<Boolean> callable = new Callable<Boolean>() {
			public Boolean call() throws Exception {
				System.out.println("Check button enabled [" + button.getText() + "]");
				return button.isEnabled();
			}
		};
		return BComponentOperatorUtil.callInBuiThread(callable);
	}
}
