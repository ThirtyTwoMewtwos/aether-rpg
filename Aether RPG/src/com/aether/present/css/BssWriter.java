package com.aether.present.css;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class BssWriter {
    private static final String MENU_ITEM = "menuitem";
	private static final String COMBOBOX = "combobox";
	private static final String BUTTON = "button";
    private static final String BUTTON_HOVER = "button:hover";
    private static final String BUTTON_DOWN = "button:down";
    private static final String BUTTON_SELECTED = "button:selected";
    private static final String BUTTON_DISABLED = "button:disabled";

    private static final String TEXTFIELD = "textfield";
    private static final String LABEL = "label";

    private Map<String, BssStyleClass> stylesClasses = new HashMap<String, BssStyleClass>();

    public BssStyleClass button() {
    	return get(BUTTON);
	}

    private BssStyleClass get(String style) {
    	assureContainsStyle(style, new BssStyleClass(style, this));
    	return stylesClasses.get(style);
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
			styles.append(each.writeBss()+ "\n");
		}
		return new StringReader(styles.toString());
	}

	public BssStyleClass buttonHover() {
		return get(BUTTON_HOVER);
	}

	public BssStyleClass buttonDown() {
		return get(BUTTON_DOWN);
	}
	
	public BssStyleClass buttonSelected() {
		return get(BUTTON_SELECTED);
	}
	
	public BssStyleClass buttonDisabled() {
		return get(BUTTON_DISABLED);
	}

    public BssStyleClass textField() {
        BssStyleClass bssStyleClass = new BssStyleClass(TEXTFIELD, this);
        bssStyleClass.setColor(new BssColor("008"));
        bssStyleClass.setPadding(5);
        assureContainsStyle(TEXTFIELD, bssStyleClass);

        return stylesClasses.get(TEXTFIELD);
    }

	public BssStyleClass comboBox() {
		BssStyleClass result = get(COMBOBOX);
		get(MENU_ITEM);
		return result;
	}

	public BssStyleClass menuItem() {
		return get(MENU_ITEM);
	}
}
