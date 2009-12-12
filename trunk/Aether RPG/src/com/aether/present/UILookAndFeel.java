package com.aether.present;

/*
 * UILookAndFeel.java
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

        public static final String LOGIN_LOGO_LOOK = "login_logo_look";
        public static final String LOGIN_TEXTFIELD_LOOK = "login_textfield_look";
	
	private static final int BUTTON_FONT_SIZE = 24;
	private static final String FONT_FAMILY = "Helvetica";

	private BssColor fadedBlack = new BssColor("00000088");
	private BssWriter writer = new BssWriter();

	public void loadBaseStyleSheet() {
		try {
                        setupLoginLookAndFeel();
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

        private void setupLoginLookAndFeel()
        {
            writer.createStyle(LOGIN_LOGO_LOOK)
                                .setBackground("images/logoPlaceHolder.png", BssBackgroundMode.FRAMEXY);

            writer.createStyle(LOGIN_TEXTFIELD_LOOK)
                                .setBackground("images/clearTextField.png", BssBackgroundMode.SCALEX)
                                .setPadding(3, 30)
                                .setColor(BssColor.BLACK)
                                .setFont("serif", BssFontStyle.PLAIN, 15);
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
