package com.aether.gbui.operators;

import com.aether.gbui.ComponentSearch;
import com.jmex.bui.BComponent;
import com.jmex.bui.BList;
import com.jmex.bui.BToggleButton;
import com.jmex.bui.BWindow;

public class BListOperator extends BComponentOperator {
	private BList list;

	public BListOperator(BWindow window, ComponentSearch searcher) {
		list = (BList)BComponentOperatorUtil.findWidget(window, searcher);
	}
	
	@Override
	protected BComponent getComponent() {
		return list;
	}

	public void select(String value) {
		list.setSelectedValue(value);
	}

}
