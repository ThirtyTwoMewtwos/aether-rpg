package com.aether.present.hud.equipment;

import com.aether.present.UILookAndFeel;
import com.aether.present.hud.EquipmentView;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.TableLayout;

public class HeaderPanel extends BContainer {
	private BLabel weight;

	public HeaderPanel() {
		TableLayout layout = new TableLayout(2);
		setLayoutManager(layout);
		setStyleClass(UILookAndFeel.HUD_HEADER_PANEL);
		
		BLabel weightLabel = new BLabel("Total Weight");
		weightLabel.setStyleClass(UILookAndFeel.HUD_STATISTICS_LABELS);
		weight = new BLabel("");
		weight.setName(EquipmentView.EQUIPMENT_WEIGHT);
		weight.setStyleClass(UILookAndFeel.HUD_STATISTICS_VALUES);
		
		add(weightLabel);
		add(weight);
	}
	
	@Override
	protected void wasAdded() {
		super.wasAdded();
		setSize(getParent().getWidth(), -1);
	}

	public void setWeight(String newWeight) {
		weight.setText(newWeight + " lbs.");
	}
	
}
