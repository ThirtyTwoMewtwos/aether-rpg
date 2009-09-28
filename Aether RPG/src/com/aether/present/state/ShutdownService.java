package com.aether.present.state;

import com.jme.app.AbstractGame;

public class ShutdownService {

	private final AbstractGame game;

	public ShutdownService(AbstractGame game) {
		this.game = game;
	}

	public void shutdown() {
		game.finish();
	}

}
