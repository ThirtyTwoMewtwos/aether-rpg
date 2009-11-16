package com.aether.present.hud;

import java.util.Hashtable;
import java.util.Map;

import org.gap.jseed.injection.annotation.Singleton;

@Singleton
class InGameHUDWindowLocator implements HUDViewLocator {
	private Map<View, ViewPresenter> views;
	
	public InGameHUDWindowLocator() {
		views = new Hashtable<View, ViewPresenter>();
	}
	
	@Override
	public void toggleViewVisibility(View view) {
		if (views.containsKey(view)) {
			views.get(view).toggleVisibility();
		} else {
			throw new IllegalStateException(
					"The given view [" + view + "] has not been bound to the service." +
					"\nUnable to toggle visibility, add the view in the " + HUDLoader.class.getName() + ".class" +
					"\n to make it visible.");
		}
	}

	@Override
	public void activate() {
		for (ViewPresenter each : views.values()) {
			each.activate();
		}
	}

	@Override
	public void deactivate() {
		for (ViewPresenter each : views.values()) {
			each.deactivate();
		}
	}

	public void bind(View view, ViewPresenter presenter) {
		views.put(view, presenter);
	}

}
