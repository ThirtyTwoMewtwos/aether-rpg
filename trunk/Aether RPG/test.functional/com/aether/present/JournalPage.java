package com.aether.present;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.Condition;
import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BButtonOperator;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.gbui.operators.BListOperator;
import com.aether.gbui.operators.BTextAreaOperator;
import com.aether.present.hud.JournalView;
import com.jmex.bui.BWindow;

public class JournalPage extends BaseHUDWindowPage {
	private BListOperator questList;

	public JournalPage() throws InterruptedException {
		super(JournalView.JOURNAL_ID);
	}
	
	@Override
	protected int getVisibilityKeyEvent() {
		return KeyEvent.VK_J;
	}

	public void select(final Object item) {
		getQuestList().select(item);
	}
	
	public BListOperator getQuestList() {
		if (questList == null) {
			questList = new BListOperator(window, new NameOperatorSearch(JournalView.JOURNAL_ENTRIES_ID));
		}
		return questList;
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

	public void clickRemoveQuest() throws AWTException {
		new BButtonOperator(window, "Abandon").click();
	}

	public int getQuestCount() {
		return getQuestList().size();
	}
}
