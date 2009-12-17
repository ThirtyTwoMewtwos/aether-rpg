package com.aether.gbui.operators;

import java.util.concurrent.Callable;

import com.aether.gbui.BGridContainer;
import com.aether.gbui.ComponentSearch;
import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;

public class BGridContainerOperator extends BComponentOperator {
	private BGridContainer list;

	public BGridContainerOperator(BWindow window, ComponentSearch searcher) {
		list = (BGridContainer)BComponentOperatorUtil.findWidget(window, searcher);
	}
	
	@Override
	protected BComponent getComponent() {
		return list;
	}

	public int size() {
		return BComponentOperatorUtil.callInBuiThread(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return list.valueCount();
			}
		});
	}
}
