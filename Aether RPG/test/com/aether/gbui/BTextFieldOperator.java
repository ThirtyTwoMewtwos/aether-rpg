package com.aether.gbui;

import java.util.concurrent.Callable;

import com.jme.system.DisplaySystem;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.FocusEvent;
import com.jmex.game.state.GameStateManager;

public class BTextFieldOperator {

	private BTextField textField;

	public BTextFieldOperator(BWindow window, String label) {
		textField = (BTextField)BComponentOperatorUtil.findWidget(window, new LabeledOperatorSearcher(BTextField.class, label));
	}

	public void setText(final String text) {
		BComponentOperatorUtil.callInBuiThread(new Callable<Object>() {
			public Object call() throws Exception {
				textField.dispatchEvent(new FocusEvent(this, System.currentTimeMillis(), FocusEvent.FOCUS_GAINED));
				textField.setText(text);
				return null;
			}
		});
	}
}
