package com.aether.present.hud;

public interface HUDViewLocator {
	enum View {PERSONA,JOURNAL, EQUIPMENT, CONSOLE}

	void toggleViewVisibility(View personaView);

	void activate();

	void deactivate();

}
