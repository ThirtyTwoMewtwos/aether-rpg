package com.aether.present.css;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class BssWriter {
	public enum StyleType {window, combobox, menuitem, button, textfield, label}
	public enum StyleState {hover, disabled, selected, down}

    private Map<String, BssStyleClass> stylesClasses = new HashMap<String, BssStyleClass>();

    private BssStyleClass get(String style) {
    	assureContainsStyle(style, new BssStyleClass(style, this));
    	return stylesClasses.get(style);
    }

	private void assureContainsStyle(String style, BssStyleClass styleClass) {
		if (!stylesClasses.containsKey(style)) {
			stylesClasses.put(style, styleClass);
		}
	}

	public Reader asReader() {
		StringBuffer styles = new StringBuffer();
		for (BssStyleClass each : stylesClasses.values()) {
			styles.append(each.writeBss()+ "\n");
		}
		
		styles.deleteCharAt(styles.length() - 1);
		return new StringReader(styles.toString());
	}

	public BssStyleClass createStyle(StyleType widget, StyleState state) {
		return get(widget + ":" + state);
	}

	public BssStyleClass createStyle(StyleType widget) {
		return get(widget.name());
	}
}
