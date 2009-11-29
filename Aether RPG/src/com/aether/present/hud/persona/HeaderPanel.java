package com.aether.present.hud.persona;

import com.aether.present.UILookAndFeel;
import com.aether.present.hud.PersonaView;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

public class HeaderPanel extends BContainer {
	private BLabel name;
	private BLabel classification;
	private BLabel raceAndSex;

	public HeaderPanel() {
		AbsoluteLayout layout = new AbsoluteLayout();
		this.setLayoutManager(layout);
		this.setStyleClass(UILookAndFeel.PERSONA_HEADER_PANEL);
		
		name = createHeader(PersonaView.NAME_FIELD, 0, 17, 260);
		classification = createHeader(PersonaView.CLASS_FIELD, 0, 0, 150);
		raceAndSex = createHeader(PersonaView.RACE_SEX_FIELD, 100, 0, 100);
	}
	
	private BLabel createHeader(String header, int x, int y, int width) {
		BLabel stat = new BLabel(header);
		stat.setStyleClass(UILookAndFeel.PERSONA_STATISTICS);
		int statNameWidth = header.length() * 12;
		add(stat, new Rectangle(x, y, statNameWidth, 35));

		BLabel statValue = new BLabel("dummy value");
		statValue.setStyleClass(UILookAndFeel.PERSONA_HEADER_VALUES);
		statValue.setName(header);
		add(statValue, new Rectangle(x + (int)(statNameWidth * 0.75), y, width, 35));
		
		return statValue;
	}

	public void setName(String newName) {
		this.name.setText(newName);
	}

	public void setClassification(String newClassification) {
		this.classification.setText(newClassification);
	}

	public void setRaceAndSex(String newRaceAndSex) {
		this.raceAndSex.setText(newRaceAndSex);
	}
}
