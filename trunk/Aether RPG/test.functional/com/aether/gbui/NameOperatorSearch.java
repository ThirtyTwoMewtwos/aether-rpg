package com.aether.gbui;

import com.jmex.bui.BComponent;

public class NameOperatorSearch implements ComponentSearch {

	
	private final String name;

	public NameOperatorSearch(String name) {
		this.name = name;
	}

	@Override
	public boolean isMatch(BComponent component) {
		return name.equals(component.getName());
	}
	
	@Override
	public String toString() {
		return "named " + name;
	}
}
