package com.aether.present.css;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class BssWriter {
	private static final String BUTTON_HOVER = "button:hover";
	private static final String BUTTON_DOWN = "button:down";
	private static final String BUTTON_SELECTED = "button:selected";
	private static final String BUTTON_DISABLED = "button:disabled";
	
	private static final String LABEL = "label";
	private static final String BUTTON = "button";
	private Map<String, BssStyleClass> stylesClasses = new HashMap<String, BssStyleClass>();
	
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

	public BssStyleClass buttonHover() {
		assureContainsStyle(BUTTON_HOVER,  new BssStyleClass(BUTTON_HOVER, this));
		return stylesClasses.get(BUTTON_HOVER);
	}

	public BssStyleClass buttonDown() {
		assureContainsStyle(BUTTON_DOWN,  new BssStyleClass(BUTTON_DOWN, this));
		return stylesClasses.get(BUTTON_DOWN);
	}
	
	public BssStyleClass buttonSelected() {
		assureContainsStyle(BUTTON_SELECTED,  new BssStyleClass(BUTTON_SELECTED, this));
		return stylesClasses.get(BUTTON_SELECTED);
	}
	
	public BssStyleClass buttonDisabled() {
		assureContainsStyle(BUTTON_DISABLED,  new BssStyleClass(BUTTON_DISABLED, this));
		return stylesClasses.get(BUTTON_DISABLED);
	}
}
