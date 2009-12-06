package com.aether.present.state;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jme.app.AbstractGame;
import com.jme.system.GameSettings;

public class TestShutdownService {
	@Test
	public void testGameShutdownIsCalled() throws Exception {
		DummyGame standardGame = new DummyGame();
		FinishGameService shutdownService = new FinishGameService(standardGame);
		shutdownService.shutdown();
		assertTrue("finish called: " + standardGame, standardGame.isFinished());
	}
	
	private class DummyGame extends AbstractGame {
		public DummyGame() {
			finished = false;
		}
		
		public boolean isFinished() {
			return finished;
		}
		@Override
		protected void cleanup() {
		}

		@Override
		protected GameSettings getNewSettings() {
			return null;
		}

		@Override
		protected void initGame() {
		}

		@Override
		protected void initSystem() {
		}

		@Override
		protected void quit() {
		}

		@Override
		protected void reinit() {
		}

		@Override
		protected void render(float interpolation) {
		}

		@Override
		public void start() {
		}

		@Override
		protected void update(float interpolation) {
		}
		
	}
}