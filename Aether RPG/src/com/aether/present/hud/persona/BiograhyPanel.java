package com.aether.present.hud.persona;

import com.aether.gbui.components.BExtendedTextField;
import com.aether.gbui.event.FocusListener;
import com.aether.present.PlayerMovementState;
import com.aether.present.UILookAndFeel;
import com.aether.present.hud.PersonaPresenter;
import com.aether.present.hud.PersonaView;
import com.jme.renderer.Renderer;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.BTextField;
import com.jmex.bui.event.FocusEvent;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.text.Document;
import com.jmex.bui.text.Document.Listener;
import com.jmex.bui.util.Rectangle;

public class BiograhyPanel extends BContainer {
	private BExtendedTextField bio;
	private TextChangedListener listener;
	private BLabel label;

	public BiograhyPanel(final PlayerMovementState state) {
		setLayoutManager(new AbsoluteLayout());
		label = new BLabel("Biography");
		label.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);
		
		bio = new BExtendedTextField("");
		bio.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {
				state.setActive(false);
			}

			@Override
			public void focusLost(FocusEvent event) {
				state.setActive(true);
			}
			
		});
		bio.setName(PersonaView.BIO_FIELD);
		bio.setStyleClass(UILookAndFeel.CHAT_INPUT);
		
		add(label, new Rectangle(0, 0, 500, 15));
		add(bio, new Rectangle(0, 0, 500, 15));
	}
	
	@Override
	public void render(Renderer renderer) {
		label.setSize(getWidth(), 12);
		label.setLocation(0, getHeight() - 14);
		bio.setSize(getWidth(), getHeight() - 14);
		bio.setLocation(0, 0);
		super.render(renderer);
	}
	
	public void setBio(String newBio) {
		bio.setText(newBio);
	}

	public void setPresenter(final PersonaPresenter presenter) {
		listener = new TextChangedListener(presenter);
		bio.getDocument().addListener(listener);
	}

	private final class TextChangedListener implements Listener {
		private final PersonaPresenter presenter;

		private TextChangedListener(PersonaPresenter presenter) {
			this.presenter = presenter;
		}

		@Override
		public void textInserted(Document document, int offset, int length) {
			presenter.setBio(bio.getText());
		}

		@Override
		public void textRemoved(Document document, int offset, int length) {
			presenter.setBio(bio.getText());
		}
	}
}
