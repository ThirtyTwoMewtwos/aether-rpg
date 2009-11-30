package com.aether.gbui.operators;

import java.util.concurrent.Callable;

import com.aether.gbui.ComponentSearch;
import com.aether.gbui.LabeledOperatorSearcher;
import com.jmex.bui.BComponent;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.FocusEvent;

public class BTextFieldOperator extends BComponentOperator {
	private BTextField textField;

	public BTextFieldOperator(BWindow window, String label) {
		this(window, new LabeledOperatorSearcher(BTextField.class, label));
	}
	
	public BTextFieldOperator(BWindow window, ComponentSearch searcher) {
		System.out.println("Creating textfield " + searcher);
		textField = (BTextField)BComponentOperatorUtil.findWidget(window, searcher);
	}

	public void setText(final String text) {
		BComponentOperatorUtil.callInBuiThread(new Callable<Void>() {
			public Void call() throws Exception {
				textField.dispatchEvent(new FocusEvent(this, System.currentTimeMillis(), FocusEvent.FOCUS_GAINED));
				textField.setText(text);
				return null;
			}
		});
	}
	
	public String getText() {
		return BComponentOperatorUtil.callInBuiThread(new Callable<String>() {
			@Override
			public String call() throws Exception {
				textField.dispatchEvent(new FocusEvent(this, System.currentTimeMillis(), FocusEvent.FOCUS_GAINED));
				return textField.getText();
			}
		});
	}
	
	@Override
	public BComponent getComponent() {
		return textField;
	}
}
