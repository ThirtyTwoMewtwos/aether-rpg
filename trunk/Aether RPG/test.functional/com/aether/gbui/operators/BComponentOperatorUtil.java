package com.aether.gbui.operators;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.aether.gbui.ComponentSearch;
import com.aether.gbui.Condition;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BComponent;
import com.jmex.bui.BContainer;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;

public class BComponentOperatorUtil {
	private static final int TIME_TO_WAIT_FOR_EVENT = 20;
	private static final long DEFAULT_SEARCH_TIMOUT = 5 * 1000; 
	
	public static final String WAIT_TIME_PROPERTY = "wait.time";
	public static final String SEARCH_FOR_COMPONENT_TIMEOUT_PROPERTY = "search.for.component.timeout";

	public static BWindow windowWithId(String id) throws InterruptedException {
		waitForRootNodeSet();
		BWindow result = null;
		long startTime = System.currentTimeMillis();
		while (result == null) {
			if (System.currentTimeMillis() - startTime > DEFAULT_SEARCH_TIMOUT) {
				throw new IllegalStateException("Unable to find window with id [" + id + "]");
			}
			try {
				result = BuiSystem.getWindow(id);
			} catch (Exception e) {}
			performWaitOnThread();
		}
		return result;
	}

	private static void waitForRootNodeSet() throws InterruptedException {
		while (BuiSystem.getRootNode() == null) {
			performWaitOnThread();
		}
	}

	public static BComponent findWidget(BWindow window, ComponentSearch searcher) {
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;
		while (elapsedTime < DEFAULT_SEARCH_TIMOUT) {
			BComponent component = searchForWidget(window, searcher);
			if (component != null) {
				return component;
			}
			System.out.println("searching for widget! " + searcher);

			performWaitOnThread();
			elapsedTime= System.currentTimeMillis() - startTime;
		}
		throw new IllegalStateException("Unable to find 'widget': " + searcher);
	}

	private static BComponent searchForWidget(BContainer container, ComponentSearch searcher) {
		for (int i = 0; i < container.getComponentCount(); i++) {
			BComponent component = container.getComponent(i);
			if (!component.isShowing()) {
				continue;
			}
			if (searcher.isMatch(component)) {
				return component;
			}
			if (component instanceof BContainer) {
				System.out.println(component.getName());
				BComponent found = searchForWidget((BContainer)component, searcher);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}
	
	private static void performWaitOnThread() {
		try {
			Thread.sleep(TIME_TO_WAIT_FOR_EVENT);
		} catch (InterruptedException e) {
		}
	}
	
	public static <T> T callInBuiThread(Callable<T> callable) {
		Future<T> update = GameTaskQueueManager.getManager().update(callable);
		try {
			return update.get();
		} catch (InterruptedException e) {
			throw new IllegalStateException("Call in Bui thread with thread interruption: ", e);
		} catch (ExecutionException e) {
			throw new IllegalStateException("Call in Bui thread failed: ", e);
		}
	}
	
    public static void delayForUpdate() {
        callInBuiThread(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return null;
			}
        });
    }
	
	public static void waitFor(Condition condition) {
		long startTime = System.currentTimeMillis();
		while (!condition.existing()) {
			performWaitOnThread();
			long elapsedTime = System.currentTimeMillis() - startTime;
			if (elapsedTime > DEFAULT_SEARCH_TIMOUT) {
				break;
			}
		}
	}
}
