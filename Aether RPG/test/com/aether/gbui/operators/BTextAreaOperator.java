package com.aether.gbui.operators;

import java.util.concurrent.Callable;

import com.aether.gbui.ComponentSearch;
import com.jmex.bui.BComponent;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.FocusEvent;

public class BTextAreaOperator extends BComponentOperator {
	private BTextArea textArea;

	public BTextAreaOperator(BWindow window, ComponentSearch searcher) {
		textArea = (BTextArea)BComponentOperatorUtil.findWidget(window, searcher);
	}

	@Override
	protected BComponent getComponent() {
		return textArea;
	}
	
	public void setText(final String text) {
		BComponentOperatorUtil.callInBuiThread(new Callable<Object>() {
			public Object call() throws Exception {
				textArea.dispatchEvent(new FocusEvent(this, System.currentTimeMillis(), FocusEvent.FOCUS_GAINED));
				textArea.setText(text);
				return null;
			}
		});
	}

	public int getLineCount() {
		return textArea.getLineCount();
	}

	public void clearText() {
		BComponentOperatorUtil.callInBuiThread(new Callable<Object>() {
			public Object call() throws Exception {
				textArea.clearText();
				return null;
			}
		});
	}
}
