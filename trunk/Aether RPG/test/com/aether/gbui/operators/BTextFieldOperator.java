package com.aether.gbui.operators;

import java.util.concurrent.Callable;

import com.aether.gbui.LabeledOperatorSearcher;
import com.jmex.bui.BComponent;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.FocusEvent;

public class BTextFieldOperator extends BComponentOperator {
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
	
	@Override
	public BComponent getComponent() {
		return textField;
	}
}
