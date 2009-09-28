package com.aether.present.state;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jme.app.AbstractGame;
import com.jme.system.GameSettings;
import com.jmex.game.StandardGame;
import com.jmex.game.StandardGame.GameType;


public class TestShutdownService {
	@Test
	public void testGameShutdownIsCalled() throws Exception {
		DummyGame standardGame = new DummyGame();
		ShutdownService shutdownService = new ShutdownService(standardGame);
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
