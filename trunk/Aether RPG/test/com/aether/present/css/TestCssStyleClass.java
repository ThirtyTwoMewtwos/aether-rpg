package com.aether.present.css;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestCssStyleClass {
	
	@Test
	public void testButtonWithValues() throws Exception {
		BssStyleClass buttonStyleClass = new BssStyleClass("button", null);
		buttonStyleClass.setColor(BssColor.RED);
		buttonStyleClass.setBackground(BssColor.GREEN);
		buttonStyleClass.setTextAlign(BssTextAlign.LEFT);
		buttonStyleClass.setVerticalAlign(BssVerticalAlign.BOTTOM);
		buttonStyleClass.setFont("Helvetica", BssFontStyle.PLAIN, 14);
		buttonStyleClass.setTextEffect(BssTextEffect.OUTLINE);
		buttonStyleClass.setPadding(5);
		buttonStyleClass.setEffectColor(BssColor.BLUE);
		
		assertEquals(
				"button {\n" +
				"\tfont: \"Helvetica\" plain 14;\n" + 
				"\tpadding: 5;\n" +
				"\tcolor: #FF0000;\n" +
				"\tbackground: solid #00FF00;\n" +
				"\ttext-align: left;\n" + 
				"\tvertical-align: bottom;\n" +
				"\ttext-effect: outline;\n" + 
				"\teffect-color: #0000FF;\n" +
				"}", buttonStyleClass.writeBss());
	}
}
