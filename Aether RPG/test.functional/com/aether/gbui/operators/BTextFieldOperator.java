package com.aether.gbui.operators;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.concurrent.Callable;

import com.aether.gbui.BKeyboard;
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
		requestFocus();
		BComponentOperatorUtil.callInBuiThread(new Callable<Void>() {
			public Void call() throws Exception {
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

	public void pressEnter() throws AWTException {
		new BKeyboard().typeKey(KeyEvent.VK_ENTER);
//		BComponentOperatorUtil.callInBuiThread(new Callable<Void>() {
//			@Override
//			public Void call() throws Exception {
//				textField.dispatchEvent(new KeyEvent(textField, System.currentTimeMillis(), 0, KeyEvent.KEY_PRESSED, (char)28, java.awt.event.KeyEvent.VK_ENTER));
//				textField.dispatchEvent(new KeyEvent(textField, System.currentTimeMillis(), 0, KeyEvent.KEY_RELEASED, (char)28, java.awt.event.KeyEvent.VK_ENTER));
//				return null;
//			}
//		});
	}
}
