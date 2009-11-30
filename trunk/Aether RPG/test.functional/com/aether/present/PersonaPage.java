package com.aether.present;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import com.aether.gbui.BKeyboard;
import com.aether.gbui.Condition;
import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BComponentOperatorUtil;
import com.aether.gbui.operators.BTextFieldOperator;
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
	
	public void setVisibility(final boolean visibility) throws AWTException {
		if (persona.isVisible() != visibility) {
			new BKeyboard().typeKey(KeyEvent.VK_P);
		}
		BComponentOperatorUtil.waitFor(new Condition() {
			@Override
			public boolean existing() {
				return persona.isVisible() == visibility;
			}
		});
	}

	public void setBio(String value) {
		if (!persona.isVisible()) {
			throw new IllegalStateException("Persona is not visible!");
		}
		BTextFieldOperator bio = new BTextFieldOperator(getWindow(), "");
		bio.setText(value);
	}
}
