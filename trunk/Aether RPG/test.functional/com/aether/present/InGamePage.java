package com.aether.present;

import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.present.hud.PersonaView;
import com.aether.present.state.InGameView;
import com.jmex.bui.BWindow;

public class InGamePage {

	private BWindow chatWindow;

	public InGamePage() throws InterruptedException {
		chatWindow = BComponentOperatorUtil.windowWithId(InGameView.CHAT_ID);
	}
	
	public PersonaPage getPersonaPage() throws Exception {
		return new PersonaPage();
	}

}
