package com.aether.present;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BTextAreaOperator;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.present.hud.ConsoleView;

public class ConsolePage extends BaseHUDWindowPage {

	public ConsolePage() throws InterruptedException {
		super(ConsoleView.CONSOLE_ID);
	}
	
	@Override
	protected int getVisibilityKeyEvent() {
		return KeyEvent.VK_F1;
	}

	public void setCommand(String command) throws AWTException {
		BTextFieldOperator input = new BTextFieldOperator(getWindow(), new NameOperatorSearch(ConsoleView.INPUT_ID));
		input.setText(command);
		input.pressEnter();
	}

	public String getResult() {
		BTextAreaOperator result = new BTextAreaOperator(getWindow(), new NameOperatorSearch(ConsoleView.RESULT_ID));
		return result.getText();
	}
}
