/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aether.present.hud.journal;

import com.aether.present.UILookAndFeel;
import com.aether.present.hud.JournalView;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

public class JournalHeader extends BContainer {
	private BLabel questLevel;

	public JournalHeader() {
		AbsoluteLayout layout = new AbsoluteLayout();
		this.setLayoutManager(layout);
		this.setStyleClass(UILookAndFeel.HUD_HEADER_PANEL);

		BLabel questLogLabel =  new BLabel("Entries");
		questLogLabel.setStyleClass(UILookAndFeel.HUD_STATISTICS_LABELS);

		BLabel questLevelLabel = new BLabel("Level Requirement:");
        questLevelLabel.setStyleClass(UILookAndFeel.HUD_STATISTICS_LABELS);

        questLevel = new BLabel("A");
        questLevel.setName(JournalView.LEVEL_REQUIREMENT_ID);
        questLevel.setStyleClass(UILookAndFeel.HUD_STATISTICS_VALUES);
        
        add(questLogLabel,new Rectangle(0,0,200,40));
        add(questLevelLabel,new Rectangle(135,0,200,40));
        add(questLevel, new Rectangle(235, 0, 50, 40));
	}

	public void setLevelRequirement(String level) {
		questLevel.setText(level);
	}

}
