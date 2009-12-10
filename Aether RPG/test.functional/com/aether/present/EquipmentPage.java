package com.aether.present;

import java.awt.event.KeyEvent;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.operators.BLabelOperator;
import com.aether.gbui.operators.BListOperator;
import com.aether.present.hud.EquipmentView;

public class EquipmentPage extends BaseHUDWindowPage {

	public EquipmentPage() throws InterruptedException {
		super(EquipmentView.ID);
	}
	
	@Override
	protected int getVisibilityKeyEvent() {
		return KeyEvent.VK_I;
	}

	public String getWeight() {
		BLabelOperator weight = new BLabelOperator(window, new NameOperatorSearch(EquipmentView.EQUIPMENT_WEIGHT));
		return weight.getText();
	}

	public Object getItemsCount() {
		BListOperator items = new BListOperator(window, new NameOperatorSearch(EquipmentView.EQUIPMENT_LIST));
		return items.size();
	}
}
