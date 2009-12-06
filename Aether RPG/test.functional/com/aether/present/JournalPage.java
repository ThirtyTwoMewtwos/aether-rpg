package com.aether.present;

import java.awt.event.KeyEvent;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.Condition;
import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.gbui.operators.BListOperator;
import com.aether.gbui.operators.BTextAreaOperator;
import com.aether.present.hud.JournalView;
import com.jmex.bui.BWindow;

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
		BComponentOperatorUtil.delayForUpdate();
		BComponentOperatorUtil.waitFor(new Condition() {
			@Override
			public boolean existing() {
				return window.isVisible() == visibility;
			}
		});
	}

	public void select(final Object item) {
		final BListOperator list = new BListOperator(window, new NameOperatorSearch(JournalView.JOURNAL_ENTRIES_ID));
		list.select(item);
	}

	public int getDescription() {
		BTextAreaOperator textArea = new BTextAreaOperator(window, new NameOperatorSearch(JournalView.ENTRY_DESCRIPTION_ID));
		return textArea.getLineCount();
	}

	public void clearDescription() {
		BTextAreaOperator textArea = new BTextAreaOperator(window, new NameOperatorSearch(JournalView.ENTRY_DESCRIPTION_ID));
		textArea.clearText();
	}

	public Object getLevelRequirement() {
		BLabelOperator fieldOperator = new BLabelOperator(window, new NameOperatorSearch(JournalView.LEVEL_REQUIREMENT_ID));
		return fieldOperator.getText();
	}
}
