package com.aether.present;

import static com.aether.gbui.bss.BssWriter.StyleState.*;
import static com.aether.gbui.bss.BssWriter.StyleType.*;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;

import com.aether.gbui.bss.BssBackgroundMode;
import com.aether.gbui.bss.BssColor;
import com.aether.gbui.bss.BssFontStyle;
import com.aether.gbui.bss.BssStyleClass;
import com.aether.gbui.bss.BssTextAlign;
import com.aether.gbui.bss.BssTextEffect;
import com.aether.gbui.bss.BssWriter;
import com.aether.gbui.bss.BssWriter.StyleType;
import com.jme.input.InputHandler;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.bss.BStyleSheetUtil;

public class UILookAndFeel {
	public static final String CHAT_LAYOUT = "chatlayout";
	public static final String CHAT_INPUT = "chattext";

	public static final String STATISTICS_HEALTH = "statistics_hitpoints";
	public static final String STATISTICS_MANA_POINTS = "statistics_manapoints";
	public static final String STATISTICS_TOOLTIP_TEXT = "tooltip_text";
	
	public static final String PERSONA_STATISTICS = "persona_statistics";
	public static final String PERSONA_STATISTICS_VALUES = "persona_statistics_values";
	
	private static final int BUTTON_FONT_SIZE = 24;
	private static final String FONT_FAMILY = "Helvetica";

	private BssColor fadedBlack = new BssColor("00000088");
	private BssWriter writer = new BssWriter();

	public void loadBaseStyleSheet() {
		try {
			setupInGameStatistics();
			setupInGameChatBox();
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

	private void setupInGameStatistics() {
		writer.createStyle(STATISTICS_TOOLTIP_TEXT)
				.setBackground(BssColor.black)
				.setColor(BssColor.WHITE)
				.setFont(FONT_FAMILY, BssFontStyle.ITALIC, 11);
		
		writer.createStyle(STATISTICS_HEALTH)
				.setBackground(BssColor.black)
				.setColor(BssColor.red)
				.setBorder(1, BssColor.GRAY);
		
		writer.createStyle(STATISTICS_MANA_POINTS)
				.setBackground(BssColor.black)
				.setColor(BssColor.blue)
				.setBorder(1, BssColor.GRAY);
		
		writer.createStyle(PERSONA_STATISTICS)
				.clearBackground()
				.setTextAlign(BssTextAlign.LEFT)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setColor(BssColor.WHITE);
		
		writer.createStyle(PERSONA_STATISTICS_VALUES)
				.clearBackground()
				.setTextAlign(BssTextAlign.CENTER)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setColor(BssColor.WHITE);
	}

	private void setupInGameChatBox() {
		writer.createStyle(CHAT_LAYOUT)
				.setBackground(fadedBlack)
				.setTextAlign(BssTextAlign.LEFT)
				.setColor(BssColor.yellow);
		
		writer.createStyle(CHAT_INPUT)
				.setBackground(fadedBlack)
				.setTextAlign(BssTextAlign.LEFT)
				.setBorder(1, BssColor.white)
				.setColor(BssColor.white);
	}

	private void setupWindow() {
		writer.createStyle(window)
				.setBackground(fadedBlack)
				.setEffectColor(BssColor.WHITE);
	}

	private void setupButtons() throws IOException {
		writer.createStyle(button)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, BUTTON_FONT_SIZE)
				.setColor(BssColor.WHITE)
				.setBackground("images/button_up.png", BssBackgroundMode.FRAMEXY);

		writer.createStyle(button, hover)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, BUTTON_FONT_SIZE)
				.setTextEffect(BssTextEffect.OUTLINE)
				.setBackground("images/button_up.png", BssBackgroundMode.FRAMEXY)
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
        		.setBackground("images/textfield.png", BssBackgroundMode.SCALEX)
        		.setColor(BssColor.BLACK);
    }
    
    private void setupComboBox() throws IOException {
    	writer.createStyle(combobox)
    			.setFont(FONT_FAMILY, BssFontStyle.BOLD, 16)
    			.setPadding(8)
    			.setColor(BssColor.WHITE)
    			.setBackground("images/combobox_up.png", BssBackgroundMode.SCALEXY);
    	
    	writer.createStyle(combobox, hover)
		.setFont(FONT_FAMILY, BssFontStyle.BOLD, 16)
		.setPadding(8)
		.setBackground("images/combobox_up.png", BssBackgroundMode.SCALEXY);
    	
    	writer.createStyle(combobox, disabled)
				.setFont(FONT_FAMILY, BssFontStyle.PLAIN, 16)
				.setPadding(8)
				.setColor(BssColor.DARK_GRAY)
				.setBackground("images/button_disabled.png", BssBackgroundMode.SCALEXY);
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
		BuiSystem.init(new PolledRootNode(Timer.getTimer(), new InputHandler()), styleSheet);
	}
}