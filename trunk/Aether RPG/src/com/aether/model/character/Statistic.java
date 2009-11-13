package com.aether.model.character;

public class Statistic {
	private int maximum;
	private int value;

	public Statistic(int value) {
		setMax(value);
		setValue(value);
	}

	public void setMax(int newValue) {
		this.maximum = newValue;
		if (maximum < value) {
			this.value = newValue;
		}
	}

	public int getMax() {
		return maximum;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int newValue) {
		if (maximum < newValue) {
			this.value = maximum;
		} else {
			this.value = newValue;
		}
	}
}
