package com.aether.model.character;

public class Statistic {
	private int maximumValue;
	private int value;

	public Statistic(int maxValue) {
		setMax(maxValue);
	}

	public void setMax(int newValue) {
		this.maximumValue = newValue;
		this.value = newValue;
	}

	public int getMax() {
		return maximumValue;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int newValue) {
		this.value = newValue;
	}

	public int asPercentage() {
		float maxValue = (float)maximumValue;
		float currentValue = (float)value;
		return (int)((currentValue / maxValue) * 100f);
	}
}
