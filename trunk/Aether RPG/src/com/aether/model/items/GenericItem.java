package com.aether.model.items;

import com.aether.model.Item;

public class GenericItem implements Item {
	
	private final String name;
	private final String description;
	private final int weight;

	public GenericItem(String name, String description, int weight) {
		this.name = name;
		this.description = description;
		this.weight = weight;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getWeight() {
		return weight;
	}
}
