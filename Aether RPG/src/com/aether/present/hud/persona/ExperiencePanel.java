package com.aether.present.hud.persona;

import com.aether.gbui.BMeterBar;
import com.aether.present.UILookAndFeel;
import com.aether.present.hud.PersonaView;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

public class ExperiencePanel extends BContainer {
	
	private BMeterBar xpPoints;
	private BLabel experienceLabel;

	public ExperiencePanel() {
		setLayoutManager(new AbsoluteLayout());
		
		createTitle();
		createExperienceMeter();
	}

	private void createExperienceMeter() {
		xpPoints = new BMeterBar("experience.points", false);
		add(xpPoints, new Rectangle(0, 0, 270, 12));
		xpPoints.setStyleClass(UILookAndFeel.STATISTICS_MANA_POINTS);
		xpPoints.setName(PersonaView.CURRENT_EXPERIENCE);
		
		experienceLabel = new BLabel("text");
		experienceLabel.setStyleClass(UILookAndFeel.PERSONA_STATISTICS_VALUES);
		add(experienceLabel, new Rectangle(0, 0, 270, 12));
	}

	private void createTitle() {
		BLabel title = new BLabel("XP");
		title.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);
		add(title, new Rectangle(0, 15, 50, 12));
	}

	public void setXP(int current, int needed) {
		xpPoints.setValue(current);
		xpPoints.setMaximum(needed);
		experienceLabel.setText("current " + current + " / needed " + needed);
	}
}
