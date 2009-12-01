package com.aether.present.hud;

import com.jme.renderer.ColorRGBA;
import com.jmex.bui.BContainer;
import com.jmex.bui.BScrollBar;
import com.jmex.bui.BTextArea;
import com.jmex.bui.BTextField;
import com.jmex.bui.enumeratedConstants.Orientation;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.bui.layout.BorderLayout;

public class BChatComponent extends BContainer {
	private final BTextArea output;
	private final BTextField input;

	public BChatComponent(String name, BTextField input, BTextArea output) {
		super(name);
		this.output = output;
		this.input = input;
		setLayoutManager(new BorderLayout(1, 2));

		add(output, BorderLayout.CENTER);
		add(input, BorderLayout.SOUTH);
		add(new BScrollBar(Orientation.VERTICAL, output.getScrollModel()),
				BorderLayout.EAST);
		add(new BScrollBar(Orientation.HORIZONTAL, 0, 25, 50, 100),
				BorderLayout.NORTH);
	}

	@Override
	protected void wasAdded() {
		super.wasAdded();
		input.addListener(new ActionListener() {
			public void actionPerformed(final ActionEvent event) {
				final String inputText = input.getText();

				if (inputText != null && !inputText.equals("")) {
					output.appendText("You said: ", ColorRGBA.red);
					output.appendText(inputText + "\n");
					input.setText("");
					update(getName(), inputText);
				}
			}
		});
	}

	public BTextField getInput() {
		return input;
	}

	public BTextArea getText() {
		return output;
	}

	public void update(String senderName, String message) {
		if (!getName().equals(senderName)) {
			output.appendText(senderName + ": ", ColorRGBA.blue);
			output.appendText(message + "\n");
		}
	}
}
