package com.aether.present.css;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestBssStyleClass {
	
	@Test
	public void testButtonWithValues() throws Exception {
		BssStyleClass style = new BssStyleClass("button", null);
		style.setColor(BssColor.RED);
		style.setBackground(BssColor.GREEN);
		style.setTextAlign(BssTextAlign.LEFT);
		style.setVerticalAlign(BssVerticalAlign.BOTTOM);
		style.setFont("Helvetica", BssFontStyle.PLAIN, 14);
		style.setTextEffect(BssTextEffect.OUTLINE);
		style.setPadding(5);
		style.setBorder(2, BssColor.GRAY);
		style.setEffectColor(BssColor.BLUE);
		
		assertEquals(
				"button {\n" +
				"\tfont: \"Helvetica\" plain 14;\n" + 
				"\tpadding: 5;\n" +
				"\tcolor: #FF0000;\n" +
				"\tbackground: solid #008000;\n" +
				"\ttext-align: left;\n" + 
				"\tvertical-align: bottom;\n" +
				"\ttext-effect: outline;\n" + 
				"\teffect-color: #0000FF;\n" +
				"\tborder: 2 solid " + BssColor.GRAY.toString() + ";\n" + 
				"}", style.writeBss());
	}
	
	@Test
	public void testBackgroundImage() throws Exception {
		BssStyleClass style = new BssStyleClass("something", null);
		style.setBackground("test/test_image.png", BssBackgroundMode.FRAMEXY, 2, 2, 2, 2);
		
		assertContains("\tbackground: image 'test/test_image.png' framexy 2 2 2 2;\n", style.writeBss());
	}

	private void assertContains(String containedText, String actualText) {
		assertTrue("Expected the text, '" + containedText + "' to be contained, but was '" + actualText, actualText.contains(containedText));
	}
}
