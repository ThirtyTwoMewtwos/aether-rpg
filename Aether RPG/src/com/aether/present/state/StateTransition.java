package com.aether.present.state;

public interface StateTransition {
	void transition(ActiveState state, Object transition);
	void add(ActiveState from, Object transition, ActiveState to);
	void setStartState(ActiveState stateA);
	ActiveState currentState();
	void reset();
}
