package com.aether.present;

import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.present.state.InGameView;

public class InGamePage {
	public InGamePage() throws InterruptedException {
		BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
	}
	
	public PersonaPage getPersonaPage() throws Exception {
		return new PersonaPage();
	}

}
