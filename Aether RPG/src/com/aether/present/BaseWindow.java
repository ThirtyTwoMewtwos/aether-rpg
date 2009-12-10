package com.aether.present;

/*
 * BaseWindow.java
 *
 * Copyright (c) 2008, Tyler Hoersch
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Wisconsin Oshkosh nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDERS AND CONTRIBUTORS ''AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.jme.input.KeyBindingManager;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.game.state.BasicGameState;

/**
 * Provides support for activate and de-activate of the view. 
 */
public abstract class BaseWindow extends BasicGameState{
    private Map<Object, BWindow> windows;
    private List<String> keyBindings;

	public BaseWindow(String name) {
		super(name);
		windows = new Hashtable<Object, BWindow>();
		keyBindings = new ArrayList<String>();
	}
    
    protected void addWindow(Object named, BWindow window) {
    	windows.put(named, window);
    }

    protected BWindow getWindow(Object named) {
    	return windows.get(named);
    }
    
	public void activate() {
		super.setActive(true);

		// Display the GBUI portion
		getRootNode().attachChild(BuiSystem.getRootNode());
		getRootNode().updateRenderState();

		addWindows();
	}

	private void addWindows() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				for (BWindow each : windows.values()) {
					BuiSystem.addWindow(each);
				}
				return null;
			}
		});
	}

	public void deactivate() {
		super.setActive(false);

		removeWindow();
		getRootNode().detachChild(BuiSystem.getRootNode());
	}

	private void removeWindow() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				for (BWindow each : windows.values()) {
					BuiSystem.removeWindow(each);
				}
				return null;
			}
		});
	}
	
	protected void registerBinding(String name, int keyInput) {
		KeyBindingManager keyboard = KeyBindingManager.getKeyBindingManager();
		keyboard.add(name, keyInput);
		keyBindings.add(name);
	}
	
	@Override
	public void update(float tpf) {
		super.update(tpf);
		
		checkForKeyPress();
	}
	
	private void checkForKeyPress() {
		for (String each : keyBindings) {
			if (isKeyPressed(each)) {
				handleBinding(each);
			}
		}
	}

	private boolean isKeyPressed(String each) {
		return KeyBindingManager.getKeyBindingManager().isValidCommand(each, false);
	}
	
	/**
	 * Override this method to handle key bindings
	 * @param name
	 */
	protected void handleBinding(String name) {
		throw new IllegalStateException("Binding triggered but no handling defined in overriding class '" + name + "'");
	}
}
