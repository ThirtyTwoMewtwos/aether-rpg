package com.aether.present;

import java.awt.event.KeyEvent;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BTextFieldOperator;
import com.aether.present.hud.PersonaView;

public class PersonaPage extends BaseHUDWindowPage {
	public PersonaPage() throws InterruptedException {
		super(PersonaView.PERSONA_ID);
	}
	
	@Override
	protected int getVisibilityKeyEvent() {
		return KeyEvent.VK_P;
	}
	
	public void setBio(String value) {
		if (!window.isVisible()) {
			throw new IllegalStateException("Persona is not visible!");
		}
		BTextFieldOperator bio = new BTextFieldOperator(getWindow(), new NameOperatorSearch(PersonaView.BIO_FIELD));
		bio.setText(value);
	}

	public void releaseFocus() {
		BTextFieldOperator bio = new BTextFieldOperator(getWindow(), new NameOperatorSearch(PersonaView.BIO_FIELD));
		bio.releaseFocus();
	}
}
