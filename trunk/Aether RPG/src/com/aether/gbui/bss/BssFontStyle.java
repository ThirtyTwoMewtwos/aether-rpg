package com.aether.gbui.bss;

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
