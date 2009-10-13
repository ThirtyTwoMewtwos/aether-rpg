package com.aether.present;

import java.awt.Color;

import com.aether.present.css.BssColor;
import com.aether.present.css.BssFontStyle;
import com.aether.present.css.BssStyleClass;
import com.aether.present.css.BssTextAlign;
import com.aether.present.css.BssTextEffect;
import com.aether.present.css.BssWriter;
import com.jme.input.InputHandler;
import com.jme.util.Timer;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.PolledRootNode;
import com.jmex.bui.bss.BStyleSheetUtil;

public class UILookAndFeel {
	private static final int BUTTON_FONT_SIZE = 24;
	private static final String FONT_FAMILY = "Helvetica";
	BssWriter writer = new BssWriter();

	public void loadBaseStyleSheet() {
		setupButtons();
		setupLabels();
        setupTextField();
        setupComboBox();
        setupMenuItem();
        
		setupDefaultStyleSheet();
	}

	private void setupButtons() {
		BssStyleClass button = writer.button();
		button.setFont(FONT_FAMILY, BssFontStyle.BOLD, BUTTON_FONT_SIZE);
		button.setColor(BssColor.RED);
		button.setBackground(BssColor.BLUE);

		BssStyleClass buttonHover = writer.buttonHover();
		buttonHover.setFont(FONT_FAMILY, BssFontStyle.BOLD, BUTTON_FONT_SIZE);
		buttonHover.setTextEffect(BssTextEffect.OUTLINE);
		buttonHover.setEffectColor(new BssColor(new Color(255, 0, 0, 50)));
	}

    private void setupLabels() {
		BssStyleClass label = writer.label();
		label.setFont(FONT_FAMILY, BssFontStyle.ITALIC, 30);
		label.setColor(BssColor.GREEN);
		label.clearBackground();
	}

    private void setupTextField() {
        BssStyleClass textField = writer.textField();
        textField.setFont(FONT_FAMILY, BssFontStyle.PLAIN, 30);
        textField.setBackground(BssColor.RED);
    }
    
    private void setupComboBox() {
    	BssStyleClass comboBox = writer.comboBox();
    	comboBox.setFont(FONT_FAMILY, BssFontStyle.BOLD, BUTTON_FONT_SIZE);
    	comboBox.setPadding(8);
    	comboBox.setColor(BssColor.RED);
    	comboBox.setBackground(BssColor.BLUE);
    }
    
    private void setupMenuItem() {
    	BssStyleClass menuItem = writer.menuItem();
        menuItem.setFont(FONT_FAMILY, BssFontStyle.PLAIN, BUTTON_FONT_SIZE);
        menuItem.setTextAlign(BssTextAlign.LEFT);
        menuItem.setBackground(BssColor.BLUE);
    }

    private void setupDefaultStyleSheet() {
		BStyleSheet styleSheet = BStyleSheetUtil.getStyleSheet(writer.asReader());
		BuiSystem.init(new PolledRootNode(Timer.getTimer(), new InputHandler()), styleSheet);
	}
}
