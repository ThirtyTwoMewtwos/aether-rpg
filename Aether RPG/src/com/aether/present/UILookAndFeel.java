package com.aether.present;

import static com.aether.gbui.bss.BssWriter.StyleState.disabled;
import static com.aether.gbui.bss.BssWriter.StyleState.hover;
import static com.aether.gbui.bss.BssWriter.StyleState.selected;
import static com.aether.gbui.bss.BssWriter.StyleType.button;
import static com.aether.gbui.bss.BssWriter.StyleType.list_entry;
import static com.aether.gbui.bss.BssWriter.StyleType.textarea;
import static com.aether.gbui.bss.BssWriter.StyleType.combobox;
import static com.aether.gbui.bss.BssWriter.StyleType.label;
import static com.aether.gbui.bss.BssWriter.StyleType.menuitem;
import static com.aether.gbui.bss.BssWriter.StyleType.textfield;
import static com.aether.gbui.bss.BssWriter.StyleType.window;

import java.awt.Color;
import java.io.IOException;

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
	public static final String RADIO_BUTTON = "radio_button";

	public static final String STATISTICS_HEALTH = "statistics_hitpoints";
	public static final String STATISTICS_MANA_POINTS = "statistics_manapoints";
	public static final String STATISTICS_TOOLTIP_TEXT = "tooltip_text";
	
	public static final String HUD_STATISTICS_LABELS = "persona_statistics";
	public static final String HUD_STATISTICS_VALUES = "persona_statistics_values";
	public static final String PERSONA_HEADER_VALUES = "persona_header_values";
	public static final String HUD_HEADER_PANEL = "persona_header_panel";
	public static final String PERSONA_ATTRIBUTES_PANEL = "persona_attributes_panel";
	public static final String HUD_LIST = "hud_list";
	
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
		
		writer.createStyle(HUD_STATISTICS_LABELS)
				.clearBackground()
				.setTextAlign(BssTextAlign.LEFT)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setColor(BssColor.WHITE);
		
		writer.createStyle(HUD_STATISTICS_VALUES)
				.clearBackground()
				.setTextAlign(BssTextAlign.CENTER)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setColor(BssColor.WHITE);
		
		writer.createStyle(PERSONA_HEADER_VALUES)
				.clearBackground()
				.setTextAlign(BssTextAlign.LEFT)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setColor(BssColor.LIGHT_GRAY);
		
		writer.createStyle(HUD_HEADER_PANEL)
				.setBackground("images/button_up.png", BssBackgroundMode.FRAMEXY);
		
		writer.createStyle(PERSONA_ATTRIBUTES_PANEL)
				.clearBackground()
				.setBorder(2, BssColor.DARK_GRAY);
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
		
		writer.createStyle(list_entry)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setColor(BssColor.white)
				.setTextAlign(BssTextAlign.LEFT)
				.clearBackground();
		
		writer.createStyle(list_entry, selected)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setColor(BssColor.white)
				.setTextAlign(BssTextAlign.LEFT)
				.setBackground(BssColor.DARK_GRAY);
		
		writer.createStyle(textarea)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 12)
				.setTextAlign(BssTextAlign.LEFT)
				.setColor(BssColor.white)
				.clearBackground();

		makeDisabledButton(button);
		
		writer.createStyle(RADIO_BUTTON)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 15)
				.setColor(BssColor.white)
				.setBackground("images/radio_button_unselected.png", BssBackgroundMode.SCALEXY);
		
		writer.createStyle(RADIO_BUTTON, selected)
				.setFont(FONT_FAMILY, BssFontStyle.BOLD, 15)
				.setColor(BssColor.white)
				.setBackground("images/radio_button_selected.png", BssBackgroundMode.SCALEXY);
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
