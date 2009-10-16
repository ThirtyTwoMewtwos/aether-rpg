package com.aether.gbui;

import java.util.concurrent.Callable;

import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BComponent;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;

public class BComponentOperatorUtil {
	private static final long DEFAULT_SEARCH_TIMOUT = 10 * 1000; 
	public static final String WAIT_TIME_PROPERTY = "wait.time";
	public static final String SEARCH_FOR_COMPONENT_TIMEOUT_PROPERTY = "search.for.component.timeout";

	public static BWindow getWindowWithId(String id) throws InterruptedException {
		waitForRootNodeSet();
		BWindow result = null;
		long startTime = System.currentTimeMillis();
		while (result == null) {
			if (System.currentTimeMillis() - startTime > DEFAULT_SEARCH_TIMOUT) {
				throw new IllegalStateException("Unable to find window with id [" + id + "]");
			}
			result = BuiSystem.getWindow(id);
			waitForShowComponent();
		}
		return result;
	}

	private static void waitForRootNodeSet() throws InterruptedException {
		while (BuiSystem.getRootNode() == null) {
			Thread.sleep(150);
		}
	}

	public static void performPause() {
		try {
			String property = System.getProperty(WAIT_TIME_PROPERTY);
			if (property == null) {
				return;
			}
			Thread.sleep(Integer.getInteger(property));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static BComponent findWidget(BWindow window, ComponentSearch searcher) {
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;
		while (elapsedTime < DEFAULT_SEARCH_TIMOUT) {
			for (int i = 0; i < window.getComponentCount(); i++) {
				BComponent component = window.getComponent(i);
				if (searcher.isMatch(component)) {
					return component;
				}
			}
			System.out.println("searching for widget!" );

			waitForShowComponent();
			elapsedTime= System.currentTimeMillis() - startTime;
		}
		throw new IllegalStateException("Unable to find button 'widget'");
	}
	
	private static void waitForShowComponent() {
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
		}
	}
	
	public static void callInBuiThread(Callable<Object> callable) {
		GameTaskQueueManager.getManager().update(callable);
	}
	
	public static void waitFor(Condition condition) {
		while (condition.existing()) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
