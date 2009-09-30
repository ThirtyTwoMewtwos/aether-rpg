package com.aether.present.css;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class BssWriter {
	private static final String LABEL = "label";
	private static final String BUTTON = "button";
	Map<String, BssStyleClass> stylesClasses = new HashMap<String, BssStyleClass>();
	BssStyleClass button;
	BssStyleClass label;
	
	public BssStyleClass button() {
		assureContainsStyle(BUTTON, new BssStyleClass(BUTTON, this));
		return stylesClasses.get(BUTTON);
	}

	private void assureContainsStyle(String style, BssStyleClass styleClass) {
		if (!stylesClasses.containsKey(style)) {
			stylesClasses.put(style, styleClass);
		}
	}

	public BssStyleClass label() {
		BssStyleClass styleClass = new BssStyleClass(LABEL, this);
		styleClass.clearBackground();
		assureContainsStyle(LABEL, styleClass);
		return stylesClasses.get(LABEL);
	}

	public Reader asReader() {
		StringBuffer styles = new StringBuffer();
		for (BssStyleClass each : stylesClasses.values()) {
			styles.append(each.writeBss());
		}
		return new StringReader(styles.toString());
	}
}
