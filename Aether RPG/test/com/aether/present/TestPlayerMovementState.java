package com.aether.present;

import static org.junit.Assert.*;
import org.junit.Test;

import com.aether.present.game.PCMovementState;


public class TestPlayerMovementState {
	@Test
	public void test_When_state_is_true_returns_active() throws Exception {
		PCMovementState handler = new PCMovementState();
		handler.setActive(true);
		assertTrue(handler.isActive());
	}
	
	@Test
	public void test_When_state_is_false_returns_inactive() throws Exception {
		PCMovementState handler = new PCMovementState();
		handler.setActive(false);
		assertFalse(handler.isActive());
	}
}
