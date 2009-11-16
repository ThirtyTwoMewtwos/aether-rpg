package com.aether.present.hud;

import org.gap.jseed.ServiceStore;

import com.aether.present.hud.HUDViewLocator.View;

public class HUDLoader {

	public static void initialize(ServiceStore store) {
		bindViews(store);
		bindHUDLocator(store);
	}

	private static void bindViews(ServiceStore store) {
		store.bind(PersonaView.class, new PersonaWindow());
		store.bind(PersonaPresenter.class, PersonaPresenter.class);
	}

	private static void bindHUDLocator(ServiceStore store) {
		InGameHUDWindowLocator service = new InGameHUDWindowLocator();

		service.bind(View.PERSONA, store.get(PersonaPresenter.class));
		
		store.bind(HUDViewLocator.class, service);
	}
}
