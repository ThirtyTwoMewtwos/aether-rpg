package com.aether.gbui.bss;

class BssClearBackground implements BssBackground {
	public String writeBss() {
		return String.format("\tbackground: blank;\n");
	}
}
