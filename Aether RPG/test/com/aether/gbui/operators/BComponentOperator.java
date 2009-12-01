package com.aether.gbui.operators;

import java.util.concurrent.Callable;

import com.jmex.bui.BComponent;
import com.jmex.bui.event.FocusEvent;

public abstract class BComponentOperator {
	
	protected abstract BComponent getComponent();
	
	public boolean isEnabled() {
		Callable<Boolean> callable = new Callable<Boolean>() {
			public Boolean call() throws Exception {
				return getComponent().isEnabled();
			}
		};
		return BComponentOperatorUtil.callInBuiThread(callable);
	}
	
	public void setEnabled(final boolean enabled) {
		Callable<Object> callable = new Callable<Object>() {
			public Object call() throws Exception {
				getComponent().setEnabled(enabled);
				return null;
			}
		};
		BComponentOperatorUtil.callInBuiThread(callable);
	}
	
	public boolean isVisible() {
		Callable<Boolean> callable = new Callable<Boolean>() {
			public Boolean call() throws Exception {
				return getComponent().isVisible();
			}
		};
		return BComponentOperatorUtil.callInBuiThread(callable);
	}
	
	public void setVisible(final boolean visible) {
		Callable<Object> callable = new Callable<Object>() {
			public Object call() throws Exception {
				getComponent().setVisible(visible);
				return null;
			}
		};
		BComponentOperatorUtil.callInBuiThread(callable);
	}
	
	public String getTooltip() {
		Callable<String> callable = new Callable<String>() {
			public String call() throws Exception {
				return getComponent().getTooltipText();
			}
		};
		return BComponentOperatorUtil.callInBuiThread(callable);
	}
	
	public void requestFocus() {
		Callable<Void> callable = new Callable<Void>() {
			public Void call() throws Exception {
				getComponent().requestFocus();
				getComponent().dispatchEvent(new FocusEvent(this, System.currentTimeMillis(), FocusEvent.FOCUS_GAINED));
				return null;
			}
		};
		BComponentOperatorUtil.callInBuiThread(callable);
	}
}
