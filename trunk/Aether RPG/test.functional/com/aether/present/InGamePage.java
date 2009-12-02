package com.aether.present;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.present.state.InGameView;
import com.jmex.bui.BWindow;

public class InGamePage {
	public InGamePage() throws InterruptedException {
		BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
	}
	
	public PersonaPage getPersonaPage() throws Exception {
		return new PersonaPage();
	}

	public JournalPage getJournalPage() throws Exception {
		return new JournalPage();
	}

	public BWindow getPlayerStats() throws InterruptedException {
		return BComponentOperatorUtil.windowWithId(InGameView.STATS_ID);
	}

	public BWindow getChatWindow() throws InterruptedException {
		return BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
	}
	
	public BTextFieldOperator getChatInputField() throws InterruptedException {
		return new BTextFieldOperator(getChatWindow(), new NameOperatorSearch(InGameView.CHAT_INPUT_ID));
	}


}
