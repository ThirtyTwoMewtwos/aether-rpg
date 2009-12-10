package com.aether.present.hud;

import java.util.Collection;

public interface EquipmentView {
	String ID = "equipment.view";
	String EQUIPMENT_WEIGHT = "equipment.weight";
	String EQUIPMENT_LIST = "equipment.list";

	void setPresenter(EquipmentPresenter presenter);

	void setWeightCarried(String totalWeight);

	void activate();

	void deactivate();

	void setVisible(boolean b);

	void setItems(Collection<? extends Object> items);

}
