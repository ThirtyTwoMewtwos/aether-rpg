package com.aether.present.css;

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
