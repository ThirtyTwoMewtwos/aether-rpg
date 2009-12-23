package com.aether.present.hud;

import org.gap.jseed.ServiceStore;

import com.aether.present.PlayerMovementState;
import com.aether.present.hud.HUDViewLocator.View;
import com.aether.present.hud.equipment.EquipmentWindow;
import com.aether.present.hud.journal.JournalWindow;
import com.aether.present.hud.persona.PersonaWindow;

public class HUDLoader {

	public static void initialize(ServiceStore store) {
		bindViews(store);
		attachViewsToHUDLocator(store);
	}

	private static void bindViews(ServiceStore store) {
		store.bind(PersonaView.class, PersonaWindow.class);
		store.bind(PersonaPresenter.class, PersonaPresenter.class);

        store.bind(JournalView.class, JournalWindow.class);
		store.bind(JournalPresenter.class, JournalPresenter.class);
		
		store.bind(EquipmentView.class, EquipmentWindow.class);
		store.bind(EquipmentPresenter.class, EquipmentPresenter.class);

		store.bind(ConsoleView.class, ConsoleWindow.class);
		store.bind(ConsolePresenter.class, ConsolePresenter.class);
	}

	private static void attachViewsToHUDLocator(ServiceStore store) {
		InGameHUDWindowLocator service = new InGameHUDWindowLocator();

		service.attach(View.PERSONA, store.get(PersonaPresenter.class));
        service.attach(View.JOURNAL, store.get(JournalPresenter.class));
        service.attach(View.EQUIPMENT, store.get(EquipmentPresenter.class));
        service.attach(View.CONSOLE, store.get(ConsolePresenter.class));
		
		store.bind(HUDViewLocator.class, service);
	}
}
