package com.aether.gbui.bss;

class BssColoredBackground implements BssBackground {
	private BssColor color;
	private String style = "solid";

	public BssColoredBackground(BssColor backgroundColor) {
		this.color = backgroundColor;
	}
	
	public String writeBss() {
		return String.format("\tbackground: %s %s;\n", style, color);
	}
}
