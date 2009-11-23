package com.aether.gbui.bss;

public enum BssTextEffect {
	NONE,
	OUTLINE,
	SHADOW,
	GLOW;

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
