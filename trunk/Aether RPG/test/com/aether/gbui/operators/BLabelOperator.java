package com.aether.gbui.operators;

import java.awt.AWTException;
import java.util.concurrent.Callable;

import com.aether.gbui.ComponentSearch;
import com.aether.gbui.LabeledOperatorSearcher;
import com.jmex.bui.BComponent;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.icon.BIcon;

public class BLabelOperator extends BTextComponentOperator {
	private BLabel label;
	
	public BLabelOperator(BWindow window, String text) throws AWTException {
		this(window, new LabeledOperatorSearcher(BLabel.class, text));
	}
	
	public BLabelOperator(BWindow window, ComponentSearch searcher) {
		label = (BLabel)BComponentOperatorUtil.findWidget(window, searcher);
	}
	
	@Override
	protected BComponent getComponent() {
		return label;
	}
	
	public BIcon getIcon() {
		return BComponentOperatorUtil.callInBuiThread(new Callable<BIcon>() {
			@Override
			public BIcon call() throws Exception {
				return label.getIcon();
			}
		});
	}
}
