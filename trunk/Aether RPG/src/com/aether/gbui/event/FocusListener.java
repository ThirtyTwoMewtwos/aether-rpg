package com.aether.gbui.event;

import com.jmex.bui.event.FocusEvent;

public interface FocusListener {
	
	void focusGained(FocusEvent event);
	
	void focusLost(FocusEvent event);
}
