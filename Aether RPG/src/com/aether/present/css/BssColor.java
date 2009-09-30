package com.aether.present.css;

public enum BssColor {
	RED("#FF0000"), 
	GREEN("#00FF00"), 
	BLUE("#0000FF");

	private final String hashValue;

	BssColor(String hash) {
		this.hashValue = hash;
	}
	
	@Override
	public String toString() {
		return hashValue;
	}
}
