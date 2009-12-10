package com.aether.present.hud;

public interface HUDViewLocator {
	enum View {PERSONA,JOURNAL, EQUIPMENT}

	void toggleViewVisibility(View personaView);

	void activate();

	void deactivate();

}
