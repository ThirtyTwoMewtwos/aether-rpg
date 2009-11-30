/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aether.present.hud.questJournal;

import com.aether.present.UILookAndFeel;
import com.aether.present.hud.PersonaView;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

public class JournalDescriptionHeader extends BContainer {
	private BLabel description;

	public JournalDescriptionHeader() {
		AbsoluteLayout layout = new AbsoluteLayout();
		this.setLayoutManager(layout);
		this.setStyleClass(UILookAndFeel.PERSONA_HEADER_PANEL);

		description =  new BLabel("Quest Description:");
        description.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);

        add(description,new Rectangle(0,0,200,40));
	}

}
