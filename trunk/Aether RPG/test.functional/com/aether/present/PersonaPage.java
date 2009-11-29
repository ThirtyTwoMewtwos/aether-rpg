package com.aether.present;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.present.hud.PersonaView;
import com.jmex.bui.BWindow;

public class PersonaPage {
	private BWindow persona;

	public PersonaPage() throws InterruptedException {
		persona = BComponentOperatorUtil.windowWithId(PersonaView.PERSONA_ID);
	}

	public BWindow getWindow() {
		return persona;
	}
	
	public void toggleVisibility() throws AWTException {
		new BKeyboard().typeKey(KeyEvent.VK_P);
	}
}
