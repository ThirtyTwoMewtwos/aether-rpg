package com.aether.gbui.components;

import java.util.LinkedList;
import java.util.List;

import com.aether.gbui.event.FocusListener;
import com.jmex.bui.BTextField;
import com.jmex.bui.event.FocusEvent;

public class BExtendedTextField extends BTextField {
	
	private FocusListener listener;
	private List<FocusListener> listeners;

	public BExtendedTextField() {
		super();
	}
	
	public BExtendedTextField(String text) {
		super(text);
	}
	
	public BExtendedTextField(int maxLength) {
		super(maxLength);
	}
	
	public BExtendedTextField(String text, int maxLength) {
		super(text, maxLength);
	}
	
	public void addFocusListener(FocusListener listener) {
		if (listeners == null) {
			listeners = new LinkedList<FocusListener>();
		}
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void removeFocusListener(FocusListener listener) {
		listeners.remove(listener);
	}
	
	@Override
	protected void gainedFocus() {
		super.gainedFocus();
		FocusEvent event = new FocusEvent(this, System.currentTimeMillis(), FocusEvent.FOCUS_GAINED);
		for (FocusListener each : listeners) {
			each.focusGained(event);
		}
	}
	
	@Override
	protected void lostFocus() {
		super.lostFocus();
		FocusEvent event = new FocusEvent(this, System.currentTimeMillis(), FocusEvent.FOCUS_LOST);
		for (FocusListener each : listeners) {
			each.focusLost(event);
		}
	}
}
