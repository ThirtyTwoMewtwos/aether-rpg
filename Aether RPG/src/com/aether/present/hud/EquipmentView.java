package com.aether.present.hud;

import java.util.Collection;

import com.aether.model.items.Item;

public interface EquipmentView {
	String ID = "equipment.view";
	String EQUIPMENT_WEIGHT = "equipment.weight";
	String EQUIPMENT_LIST = "equipment.list";

	void setPresenter(EquipmentPresenter presenter);

	void setWeightCarried(String totalWeight);

	void setItem(int page, int row, int col, Item item1);

	void activate();
	
	void deactivate();
	
	void setVisible(boolean b);
}
