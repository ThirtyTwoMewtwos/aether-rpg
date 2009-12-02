package com.aether.present.hud;

import org.gap.jseed.ServiceStore;

import com.aether.present.PlayerMovementState;
import com.aether.present.hud.HUDViewLocator.View;

public class HUDLoader {

	public static void initialize(ServiceStore store) {
		bindViews(store);
		bindHUDLocator(store);
	}

	private static void bindViews(ServiceStore store) {
		store.bind(PersonaView.class, new PersonaWindow(store.get(PlayerMovementState.class)));
		store.bind(PersonaPresenter.class, PersonaPresenter.class);

        store.bind(JournalView.class, new JournalWindow());
		store.bind(JournalPresenter.class, JournalPresenter.class);
	}

	private static void bindHUDLocator(ServiceStore store) {
		InGameHUDWindowLocator service = new InGameHUDWindowLocator();

		service.bind(View.PERSONA, store.get(PersonaPresenter.class));
        service.bind(View.QUESTJOURNAl, store.get(JournalPresenter.class));
		
		store.bind(HUDViewLocator.class, service);
	}
}
