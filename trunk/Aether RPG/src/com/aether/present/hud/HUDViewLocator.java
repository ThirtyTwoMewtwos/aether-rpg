package com.aether.present.hud;

public interface HUDViewLocator {
	enum View {PERSONA,QUESTJOURNAl}

	void toggleViewVisibility(View personaView);
    

	void activate();

	void deactivate();

}
