package com.aether.present.hud.persona;

import com.aether.gbui.BMeterBar;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.VGroupLayout;

public class ExperiencePanel extends BContainer {
	
	public ExperiencePanel() {
		setLayoutManager(new VGroupLayout());
		add(new BLabel("XP"));
		BMeterBar xpPoints = new BMeterBar("experience.points", false);
		add(xpPoints);
		xpPoints.setMaximum(2000);
		xpPoints.setValue(150);
	}
}
