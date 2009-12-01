package com.aether.present.game;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.present.PlayerMovementState;

@Singleton
public class PCMovementState implements PlayerMovementState{

	private boolean activeState = true;

	@Override
	public void setActive(boolean activeState) {
		this.activeState = activeState;
	}

	@Override
	public boolean isActive() {
		return activeState;
	}

}
