package com.aether.gbui;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

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

	public void select(Object value) {
		comboBox.selectItem(value);
		for (int i = 0; i < comboBox.getItemCount(); i++) {
			Object item = comboBox.getItem(i);
			Object currentItem = item;
			if (item instanceof Item) {
				currentItem = ((Item)item).value;
			}
			if (value.equals(currentItem)) {
				select(i);
				return;
			} 
		}
		throw new IllegalArgumentException("The given value '" + value + "' is not in the comboBox");
	}

	public void select(final int index) {
		performSelection(index);
		waitForSelection(index);
	}


	private void performSelection(final int index) {
		BComponentOperatorUtil.callInBuiThread(new Callable<Object>() {
			public Object call() throws Exception {
				comboBox.selectItem(index);
				return null;
			}
		});
	}

	private void waitForSelection(final int index) {
		System.out.println("Selecting from comboBox index [" + index + "]");
		BComponentOperatorUtil.waitFor(new Condition() {
			@Override
			public boolean existing() {
				return comboBox.getSelectedIndex() != index;
			}
		});
	}

	public boolean isEnabled() {
		Callable<Boolean> callable = new Callable<Boolean>() {
			public Boolean call() throws Exception {
				return comboBox.isEnabled();
			}
		};
		return BComponentOperatorUtil.callInBuiThread(callable);
	}
}
