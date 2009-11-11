package com.aether.present;

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
		keyBindings = new ArrayList<String>();
		windows = new Hashtable<Object, BWindow>();
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

		addWindow();
	}

	private void addWindow() {
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

		getRootNode().detachChild(BuiSystem.getRootNode());

		removeWindow();
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
			if (KeyBindingManager.getKeyBindingManager().isValidCommand(each, true)) {
				handleBinding(each);
			}
		}
	}
	
	/**
	 * Override this method to handle key bindings
	 * @param name
	 */
	protected void handleBinding(String name) {
		throw new IllegalStateException("Binding triggered but no handling defined in overriding class '" + name + "'");
	}
}
