package com.aether.present;

import static com.aether.present.css.BssWriter.StyleState.disabled;
import static com.aether.present.css.BssWriter.StyleState.hover;
import static com.aether.present.css.BssWriter.StyleType.button;
import static com.aether.present.css.BssWriter.StyleType.combobox;
import static com.aether.present.css.BssWriter.StyleType.label;
import static com.aether.present.css.BssWriter.StyleType.menuitem;
import static com.aether.present.css.BssWriter.StyleType.textfield;
import static com.aether.present.css.BssWriter.StyleType.window;

import java.awt.Color;
import java.io.IOException;

import com.aether.present.css.BssBackgroundMode;
import com.aether.present.css.BssColor;
import com.aether.present.css.BssFontStyle;
import com.aether.present.css.BssStyleClass;
import com.aether.present.css.BssTextAlign;
import com.aether.present.css.BssTextEffect;
import com.aether.present.css.BssWriter;
import com.aether.present.css.BssWriter.StyleType;
import com.jme.input.InputHandler;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.bss.BStyleSheetUtil;
import com.util.StringUtil;

public class UILookAndFeel {
	private static final int BUTTON_FONT_SIZE = 24;
	private static final String FONT_FAMILY = "Helvetica";
	BssWriter writer = new BssWriter();

	public void loadBaseStyleSheet() {
		try {
			setupWindow();
			setupButtons();
			setupLabels();
	        setupTextField();
	        setupComboBox();
	        setupMenuItem();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		setupDefaultStyleSheet();
	}

	private void setupWindow() {
		BssColor fadedBlack = new BssColor("00000088");
		writer.createStyle(window)
				.setBackground(fadedBlack)
				.setEffectColor(BssColor.WHITE);
	}

	private void setupButtons() throws IOException {
		writer.createStyle(button)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, BUTTON_FONT_SIZE)
				.setColor(BssColor.WHITE)
				.setBackground("images/button_up.png", BssBackgroundMode.FRAMEXY, 4, 4, 4, 4);

		writer.createStyle(button, hover)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, BUTTON_FONT_SIZE)
				.setTextEffect(BssTextEffect.OUTLINE)
				.setBackground("images/button_up.png", BssBackgroundMode.FRAMEXY, 4, 4, 4, 4)
				.setEffectColor(new BssColor(new Color(255, 0, 0, 50)));
		
		makeDisabledButton(button);
	}

    private void setupLabels() {
		writer.createStyle(label)
				.setFont(FONT_FAMILY, BssFontStyle.ITALIC, 20)
				.setTextAlign(BssTextAlign.LEFT)
				.setColor(BssColor.GREEN)
				.clearBackground();
	}

    private void setupTextField() {
        writer.createStyle(textfield)
        		.setFont(FONT_FAMILY, BssFontStyle.PLAIN, 16)
        		.setBackground("images/textfield.png", BssBackgroundMode.FRAMEXY)
        		.setColor(BssColor.BLACK);
    }
    
    private void setupComboBox() throws IOException {
    	writer.createStyle(combobox)
    			.setFont(FONT_FAMILY, BssFontStyle.BOLD, 16)
    			.setPadding(8)
    			.setColor(BssColor.WHITE)
    			.setBackground("images/combobox_up.png", BssBackgroundMode.FRAMEX);
    	
    	writer.createStyle(combobox, disabled)
				.setFont(FONT_FAMILY, BssFontStyle.PLAIN, 16)
				.setPadding(8)
				.setColor(BssColor.DARK_GRAY)
				.setBackground("images/button_disabled.png", BssBackgroundMode.FRAMEXY);
    }

	private BssStyleClass makeDisabledButton(StyleType widget) throws IOException {
		return writer.createStyle(widget, disabled)
    			.setFont(FONT_FAMILY, BssFontStyle.PLAIN, BUTTON_FONT_SIZE)
    			.setPadding(8)
    			.setColor(BssColor.DARK_GRAY)
    			.setBackground("images/button_disabled.png", BssBackgroundMode.FRAMEXY);
	}
    
    private void setupMenuItem() {
    	writer.createStyle(menuitem)
        		.setFont(FONT_FAMILY, BssFontStyle.PLAIN, 16)
        		.setTextAlign(BssTextAlign.LEFT)
        		.setBackground(BssColor.IVORY)
        		.setBorder(1, BssColor.LIGHT_GRAY);
    }

    private void setupDefaultStyleSheet() {
		BStyleSheet styleSheet = BStyleSheetUtil.getStyleSheet(writer.asReader());
		// printOutBssToConsole();
		BuiSystem.init(new PolledRootNode(Timer.getTimer(), new InputHandler()), styleSheet);
	}

	private void printOutBssToConsole() {
		try {
			int lineNo = 1;
			for (String each : StringUtil.toString(writer.asReader()).split("\n")) {
				System.out.println(lineNo + ": " + each);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
