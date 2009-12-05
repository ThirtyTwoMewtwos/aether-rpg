package com.aether.gbui.operators;

import java.awt.AWTException;
import java.util.concurrent.Callable;

import com.aether.gbui.NameOperatorSearch;
import com.aether.gbui.components.BMeterBar;
import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;

public class BMeterBarOperator extends BComponentOperator {
	private BMeterBar meterBar;

	public BMeterBarOperator(BWindow window, String name) throws AWTException {
		meterBar = (BMeterBar)BComponentOperatorUtil.findWidget(window, new NameOperatorSearch(name));
	}
	
	@Override
	public BComponent getComponent() {
		return meterBar;
	}

	public int getValue() {
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				int percentage = meterBar.getValue();
				return new Integer(percentage);
			}
		};
		return BComponentOperatorUtil.callInBuiThread(callable).intValue();
	}

	public Object getMaximum() {
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				int percentage = meterBar.getMaximum();
				return new Integer(percentage);
			}
		};
		return BComponentOperatorUtil.callInBuiThread(callable).intValue();
	}
}
