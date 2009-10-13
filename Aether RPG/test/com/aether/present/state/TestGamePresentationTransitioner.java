package com.aether.present.state;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.gap.jseed.injection.annotation.Singleton;
import org.junit.Before;
import org.junit.Test;

@Singleton
public class TestGamePresentationTransitioner {
	
	private static final String C_TO_A = "C-to-A";
	private static final String B_TO_C = "B-to-C";
	private static final String A_TO_C = "A-to-C";
	private static final String A_TO_B = "A-to-B";
	private ActiveState stateA;
	private ActiveState stateB;
	private ActiveState stateC;
	private GameStateTransition transition;

	@Before
	public void setUp() {
		transition = new GameStateTransition();

		stateA = createMock("ActiveStateA", ActiveState.class);
		stateB = createMock("ActiveStateB", ActiveState.class);
		stateC = createMock("ActiveStateC", ActiveState.class);
	}
	
	@Test
	public void testTransitions() throws Exception {
		transition.add(stateA, A_TO_B, stateB);
		transition.add(stateA, A_TO_C, stateC);
		transition.add(stateB, B_TO_C, stateC);
		transition.add(stateC, C_TO_A, stateA);
		transition.setStartState(stateA);
		
		assertEquals(stateA, transition.currentState());
		transition.transition(stateA, A_TO_B);
		assertEquals(stateB, transition.currentState());
		transition.transition(stateB, B_TO_C);
		assertEquals(stateC, transition.currentState());
		transition.transition(stateC, C_TO_A);
		assertEquals(stateA, transition.currentState());
		transition.transition(stateA, A_TO_C);
		assertEquals(stateC, transition.currentState());
	}
	
	@Test
	public void testTransitionToBadState() throws Exception {
		transition.add(stateA, A_TO_B, stateB);
		try {
			transition.setStartState(stateC);
			fail("Expected IllegalArgumentException stating the state given is not a registered state.");
		} catch (IllegalArgumentException iae) {
		}
		
		transition.setStartState(stateA);
		try {
			transition.transition(stateB, B_TO_C);
			fail("Expected IllegalStateException when transitioning from an inactive state");
		} catch (IllegalStateException ise) {}
		
		try {
			transition.transition(stateA, A_TO_C);
			fail("Expected IllegalStateException on attempt to transition that is not set for the active state");
		} catch (IllegalStateException ise) {}
	}
	
	@Test
	public void testActiveStateCalledProperly() throws Exception {
		stateA.enter();
		stateA.exit();
		stateB.enter();

		transition.add(stateA, A_TO_B, stateB);
		replay(stateA, stateB);
		transition.setStartState(stateA);
		transition.transition(stateA, A_TO_B);
		verify(stateA, stateB);
	}
}
