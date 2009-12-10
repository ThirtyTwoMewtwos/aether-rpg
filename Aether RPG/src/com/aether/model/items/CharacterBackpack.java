package com.aether.model.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aether.model.Item;

public class CharacterBackpack implements EquipmentContainer {
	private int weightCarried = 0;
	private Map<String, Item> items = new HashMap<String, Item>();
	
	@Override
	public int weightCarried() {
		return weightCarried;
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	@Override
	public void addItem(Item toAdd) {
		if (!items.containsKey(toAdd.getName())) {
			weightCarried += toAdd.getWeight();
			items.put(toAdd.getName(), toAdd);
		}
	}

	public void removeItem(Item toRemove) {
		if (items.containsKey(toRemove.getName())) {
			weightCarried -= toRemove.getWeight();
			items.remove(toRemove.getName());
		}
	}

	public int itemCount() {
		return items.size();
	}

	@Override
	public Collection<String> getAllItemNames() {
		return items.keySet();
	}
}
