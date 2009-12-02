package com.aether.present;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.concurrent.Callable;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.Condition;
import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BListOperator;
import com.aether.gbui.operators.BTextAreaOperator;
import com.aether.present.hud.JournalView;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.FocusEvent;

public class JournalPage {
	private BWindow window;

	public JournalPage() throws InterruptedException {
		window = BComponentOperatorUtil.windowWithId(JournalView.JOURNAL_ID);
	}

	public boolean isVisible() {
		return window.isVisible();
	}

	public void setVisible(final boolean visibility) throws Exception {
		if (window.isVisible() != visibility) {
			new BKeyboard().typeKey(KeyEvent.VK_J);
		}
		BComponentOperatorUtil.waitFor(new Condition() {
			@Override
			public boolean existing() {
				return window.isVisible() == visibility;
			}
		});
	}

	public void select(final String index) {
		final BListOperator list = new BListOperator(window, new NameOperatorSearch(JournalView.JOURNAL_ENTRIES_ID));
		BComponentOperatorUtil.callInBuiThread(new Callable<Object>() {
			public Object call() throws Exception {
				list.select(index);
				return null;
			}
		});
	}

	public int getDescription() {
		BTextAreaOperator textArea = new BTextAreaOperator(window, new NameOperatorSearch(JournalView.ENTRY_DESCRIPTION_ID));
		return textArea.getLineCount();
	}

	public void clearDescription() {
		BTextAreaOperator textArea = new BTextAreaOperator(window, new NameOperatorSearch(JournalView.ENTRY_DESCRIPTION_ID));
		textArea.clearText();
	}
}