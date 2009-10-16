package com.aether.gbui;

import java.util.concurrent.Callable;

import com.aether.model.character.Race;
import com.jmex.bui.BComboBox;
import com.jmex.bui.BWindow;
import com.jmex.bui.BComboBox.Item;
import com.jmex.bui.event.FocusEvent;

public class BComboBoxOperator {

	private BComboBox comboBox;

	public BComboBoxOperator(BWindow window, ComponentSearch componentSearch) {
		comboBox = (BComboBox)BComponentOperatorUtil.findWidget(window, componentSearch);
	}

	public void select(final Object value) {
		comboBox.selectItem(value);
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			Object item = comboBox.getItem(i);
			Object currentItem = item;
			if (item instanceof Item) {
				currentItem = ((Item)item).value;
			}
			if (value.equals(currentItem)) {
				select(i);
				BComponentOperatorUtil.waitFor(new Condition() {
					@Override
					public boolean existing() {
						return comboBox.getSelectedValue() != value;
					}
				});
				return;
			} 
		}
		throw new IllegalArgumentException("The given value '" + value + "' is not in the comboBox");
	}

	private void select(final int index) {
		BComponentOperatorUtil.callInBuiThread(new Callable<Object>() {
			public Object call() throws Exception {
				comboBox.selectItem(index);
				return null;
			}
		});
	}

	public boolean isEnabled() {
		return comboBox.isEnabled();
	}
	
}
