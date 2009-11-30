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

public class JournalHeader extends BContainer {
	private BLabel questLogLabel;
	private BLabel questLevelLabel;

	public JournalHeader() {
		AbsoluteLayout layout = new AbsoluteLayout();
		this.setLayoutManager(layout);
		this.setStyleClass(UILookAndFeel.PERSONA_HEADER_PANEL);

		questLogLabel =  new BLabel("Quest Log:");
		questLevelLabel = new BLabel("Level Requirement:");
        questLogLabel.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);
        questLevelLabel.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);

        add(questLogLabel,new Rectangle(0,0,200,40));
        add(questLevelLabel,new Rectangle(200,0,200,40));
	}

}
