package com.aether.gbui;

import com.jmex.bui.BComponent;
import com.jmex.bui.BTextComponent;

public class LabeledOperatorSearcher implements ComponentSearch {

	private final Class<? extends BTextComponent> widgetType;
	private final String label;

	public LabeledOperatorSearcher(Class<? extends BTextComponent> type, String label) {
		this.widgetType = type;
		this.label = label;
	}

	@Override
	public boolean isMatch(BComponent component) {
		if (widgetType.isInstance(component)) {
			BTextComponent text = (BTextComponent)component;
			return (text.getText().equals(label));
		}
		return false;
	}

}
