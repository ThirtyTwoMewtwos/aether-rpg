package com.aether.present;

import org.gap.jseed.ServiceStore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.aether.present.state.StateTransition;


public abstract class AetherTestCase {

	@BeforeClass
	public static void startUp() throws Exception {
		Main.startGame();
	}
	
	@After	
	public void tearDown() {
		ServiceStore serviceStore = Main.getServiceStore();
		StateTransition stateTransition = serviceStore.get(StateTransition.class);
		stateTransition.reset();
	}
	
	@AfterClass
	public static void shutDown() {
		Main.shutdown();
	}
}
