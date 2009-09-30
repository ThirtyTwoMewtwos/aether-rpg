package com.aether.present.state;

import com.jme.app.AbstractGame;

public class FinishGameService implements ShutdownService {

	private final AbstractGame game;

	public FinishGameService(AbstractGame game) {
		this.game = game;
	}

	public void shutdown() {
		game.finish();
	}

}
