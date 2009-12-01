package com.aether.present.game;

import org.easymock.EasyMock;
import org.junit.Test;

import com.aether.present.PlayerMovementState;
import com.jme.scene.Node;


public class TestMyInputHandler {
	@Test
	public void test_Active_when_movement_state_is_active() throws Exception {
		PlayerMovementState state = EasyMock.createStrictMock(PlayerMovementState.class);
		EasyMock.expect(state.isActive()).andReturn(false);
		MyInputHandler handler = new MyInputHandler(new Node(), state);
		
		EasyMock.replay(state);
		handler.update(50000.0000f);
		EasyMock.verify(state);
	}
}
