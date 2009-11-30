package com.aether.present;

public interface ShutdownHook {
	/**
	 * Called when the main gameloop is ending.
	 */
	void doShutdown();
}
