package com.aether.present.hud;

import org.gap.jseed.ServiceStore;

import com.aether.present.PlayerMovementState;
import com.aether.present.hud.HUDViewLocator.View;

public class HUDLoader {

	public static void initialize(ServiceStore store) {
		bindViews(store);
		attachViewsToHUDLocator(store);
	}

	private static void bindViews(ServiceStore store) {
		PlayerMovementState playerMovementState = store.get(PlayerMovementState.class);
		store.bind(PersonaView.class, new PersonaWindow(playerMovementState));
		store.bind(PersonaPresenter.class, PersonaPresenter.class);

        store.bind(JournalView.class, new JournalWindow());
		store.bind(JournalPresenter.class, JournalPresenter.class);
		
		store.bind(EquipmentView.class, new EquipmentWindow());
		store.bind(EquipmentPresenter.class, EquipmentPresenter.class);
	}

	private static void attachViewsToHUDLocator(ServiceStore store) {
		InGameHUDWindowLocator service = new InGameHUDWindowLocator();

		service.attach(View.PERSONA, store.get(PersonaPresenter.class));
        service.attach(View.JOURNAL, store.get(JournalPresenter.class));
        service.attach(View.EQUIPMENT, store.get(EquipmentPresenter.class));
		
		store.bind(HUDViewLocator.class, service);
	}
}
