package com.aether.present.hud;

import java.util.concurrent.Callable;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.present.PlayerMovementState;
import com.jme.renderer.ColorRGBA;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BTextField;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.event.FocusEvent;
import com.jmex.bui.event.FocusListener;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.BorderLayout;

@Singleton
public class ConsoleWindow implements ConsoleView {
	private BWindow window;
	private ConsolePresenter presenter;
	private BTextArea result;
	private BTextField input;
	private final PlayerMovementState state;

	public ConsoleWindow(PlayerMovementState state) {
		this.state = state;
		window = initWindow();
		setupDisplay(window);
	}
	
	private BWindow initWindow() {
		BorderLayout layout = new BorderLayout();
		BWindow result = new BDraggableWindow(BuiSystem.getStyle(), layout);
		result.setName(CONSOLE_ID);
		result.setSize(300, 140);
		result.center();
		result.setVisible(false);
		return result;
	}

	private void setupDisplay(BWindow window) {
		result = new BTextArea();
		result.appendText(
				"Game console:  Here you can give commands directly to the engine.  Type [?] " +
				"to see what commands are available, and you can type [<command> ?] to get " +
				"help on the commands available.\n", ColorRGBA.yellow);
		result.setName(RESULT_ID);
		
		input = new BTextField();
		input.setSize(300, 20);
		input.setName(INPUT_ID);
		input.addListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String command = input.getText();
				input.setText("");
				presenter.processCommand(command);
			}
			
		});
		input.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {
				state.setActive(false);
			}

			@Override
			public void focusLost(FocusEvent event) {
				state.setActive(true);
			}
			
		});
		
		window.add(result, BorderLayout.CENTER);
		window.add(input, BorderLayout.SOUTH);
	}
	
	@Override
	public void setPresenter(ConsolePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setResponse(String value) {
		result.appendText(value);
	}

	@Override
	public void activate() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.addWindow(window);
				return null;
			}
		});
	}

	@Override
	public void deactivate() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.removeWindow(window);
				return null;
			}
		});
	}

	@Override
	public void setVisible(boolean b) {
		window.setVisible(b);
	}
}
