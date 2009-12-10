package com.aether.model.items;

import java.util.Collection;

import com.aether.model.Item;

public interface EquipmentContainer {

	int weightCarried();

	boolean isEmpty();

	void addItem(Item itemA);

	Collection<String> getAllItemNames();

}
