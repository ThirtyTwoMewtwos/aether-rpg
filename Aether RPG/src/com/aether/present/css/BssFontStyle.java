package com.aether.present.css;

public enum BssFontStyle {
	PLAIN,
	BOLD,
	ITALIC,
	BOLDITALIC;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
