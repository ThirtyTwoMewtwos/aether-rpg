package com.aether.present.css;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestBssStyleClass {
	
	private BssStyleClass style;
	
	@Before
	public void setUp() {
		style = new BssStyleClass("button", null);
	}
	
	@Test
	public void test_Create_new_button_style() throws Exception {
		assertTrue(style.writeBss().startsWith("button {\n"));
		assertTrue(style.writeBss().endsWith("\n}"));
	}

	@Test
	public void test_Set_border_effect() throws Exception {
		style.setBorder(2, BssColor.GRAY);
		
		assertContains("\tborder: 2 solid " + BssColor.GRAY.toString() + ";\n", style.writeBss());
	}
	
	@Test
	public void test_Set_text_effect() throws Exception {
		style.setTextEffect(BssTextEffect.OUTLINE);
		
		assertContains("\ttext-effect: outline;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_text_effect_color() throws Exception {
		style.setEffectColor(BssColor.BLUE);
		
		assertContains("\teffect-color: #0000FF;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_text_alignment() throws Exception {
		style.setTextAlign(BssTextAlign.LEFT);
		
		assertContains("\ttext-align: left;\n", style.writeBss());
	}

	@Test
	public void test_Set_padding_for_all_sides() throws Exception {
		style.setPadding(5);
		
		assertContains("\tpadding: 5;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_padding_for_horizontal_and_vertical_sides() throws Exception {
		style.setPadding(5, 4);
		
		assertContains("\tpadding: 5 4;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_padding_for_top_and_bottom_and_left_and_right_sides_directly() throws Exception {
		style.setPadding(5, 4, 3, 2);
		
		assertContains("\tpadding: 5 4 3 2;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_font() throws Exception {
		style.setFont("Helvetica", BssFontStyle.PLAIN, 14);
		
		assertContains("\tfont: \"Helvetica\" plain 14;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_foreground_color() throws Exception {
		style.setBackground(BssColor.GREEN);
		
		assertContains("\tcolor: #FF0000;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_background_color() throws Exception {
		style.setBackground(BssColor.GREEN);
		
		assertContains("\tbackground: solid #008000;\n", style.writeBss());
	}
	
	@Test
	public void test_Set_image_background() throws Exception {
		style.setBackground("test/test_image.png", BssBackgroundMode.FRAMEXY, 2, 2, 2, 2);
		
		assertContains("\tbackground: image 'test/test_image.png' framexy 2 2 2 2;\n", style.writeBss());
	}

	private void assertContains(String containedText, String actualText) {
		assertTrue("Expected the text, '" + containedText + "' to be contained, but was '" + actualText, actualText.contains(containedText));
	}
}
