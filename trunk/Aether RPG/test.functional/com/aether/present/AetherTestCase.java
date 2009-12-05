package com.aether.present;

import org.gap.jseed.ServiceStore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.aether.present.state.StateTransition;
import com.jme.system.DisplaySystem;


public abstract class AetherTestCase {

	@BeforeClass
	public static void startUp() throws Exception {
		Main.startGame();
		Thread.sleep(1000);
	}
	
	@After	
	public void tearDown() throws Exception {
		ServiceStore serviceStore = Main.getServiceStore();
		StateTransition stateTransition = serviceStore.get(StateTransition.class);
		stateTransition.reset();
	}
	
	@AfterClass
	public static void shutDown() throws Exception {
		Main.shutdown();
		Thread.sleep(1000);
	}
}
