package com.aether.present.state;

import org.gap.jseed.injection.annotation.Singleton;

import java.util.Hashtable;
import java.util.Map;

@Singleton
public class GameStateTransition implements StateTransition {
	private Map<ActiveState, Map<Object, ActiveState>> transitions = new Hashtable<ActiveState, Map<Object, ActiveState>>();
	private ActiveState currentState;
	
	@Override
	public void transition(ActiveState state, Object transition) {
		if (state != currentState) {
			String message = "The state given is not the active state!  Tried to transition from " + state + " with transition " + transition + " when active state was " + currentState;
			throw new IllegalStateException(message);
		}
		if (!transitions.get(currentState).containsKey(transition)) {
			String message = "The transition requested is unavailable for the given state! Tried to transition from " + state + " with non-existing transition " + transition;
			throw new IllegalStateException(message);
		}
		currentState.exit();
		currentState = transitions.get(currentState).get(transition);
		currentState.enter();
	}

	@Override
	public void add(ActiveState from, Object transition, ActiveState to) {
		if (!transitions.containsKey(from)) {
			transitions.put(from, new Hashtable<Object, ActiveState>());
		}
		transitions.get(from).put(transition, to);
	}

	@Override
	public void setStartState(ActiveState startState) {
		if (!transitions.containsKey(startState)) {
			String message = "The state given is not a registered state: " + startState;
			throw new IllegalArgumentException(message);
		}
		startState.enter();
		currentState = startState;
	}

	public ActiveState currentState() {
		return currentState;
	}
}
