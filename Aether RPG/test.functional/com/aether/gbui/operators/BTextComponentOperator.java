package com.aether.gbui.operators;

import java.util.concurrent.Callable;

import com.jmex.bui.BTextComponent;

public abstract class BTextComponentOperator extends BComponentOperator {
	
	public String getText() {
		return BComponentOperatorUtil.callInBuiThread(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return ((BTextComponent)getComponent()).getText();
			}
		});
	}
}
