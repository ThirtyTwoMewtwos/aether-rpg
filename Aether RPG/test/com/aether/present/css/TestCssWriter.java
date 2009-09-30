package com.aether.present.css;

import static org.junit.Assert.*;

import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

import com.jmex.bui.bss.BStyleSheetUtil;

public class TestCssWriter {
	
	private BssWriter cssWriter;

	@Before
	public void setUp() {
		cssWriter = new BssWriter();
	}
	
	//  padding: 3 5;
	//  font: "Dialog" bold 24;
	//  text-align: center;
	//  text-effect: outline;
	//  effect-color: #442288;
	@Test
	public void testDefaultButton() throws Exception {
		BssStyleClass buttonWriter = cssWriter.button();
		BssWriter writer = buttonWriter.end();
		
		assertEquals(cssWriter, writer);
		compareReaders(
				"button {\n" +
				"\tfont: Dialog bold 12;\n" + 
				"\tpadding: 3 5;\n" +
				"\tcolor: #FF0000;\n" +
				"\tbackground: solid #0000FF;\n" +
				"\ttext-align: center;\n" + 
				"\tvertical-align: center;\n" + 
				"\ttext-effect: none;\n" + 
				"}", writer.asReader());
	}
	
	@Test
	public void testButtonWithValues() throws Exception {
		BssStyleClass buttonWriter = cssWriter.button();
		
		buttonWriter.setColor(BssColor.RED);
		buttonWriter.setBackground(BssColor.GREEN);
		buttonWriter.setTextAlign(BssTextAlign.LEFT);
		buttonWriter.setVerticalAlign(BssVerticalAlign.BOTTOM);
		buttonWriter.setFont("Helvetica", BssFontStyle.PLAIN, 14);
		buttonWriter.setTextEffect(BssTextEffect.OUTLINE);
		buttonWriter.setPadding(5);
		
		compareReaders(
				"button {\n" +
				"\tfont: Helvetica plain 14;\n" + 
				"\tpadding: 5;\n" +
				"\tcolor: #FF0000;\n" +
				"\tbackground: solid #00FF00;\n" +
				"\ttext-align: left;\n" + 
				"\tvertical-align: bottom;\n" +
				"\ttext-effect: outline;\n" + 
				"}", cssWriter.asReader());
	}
	
	@Test
	public void testDefaultLabel() throws Exception {
		BssStyleClass labelWriter = cssWriter.label();
		BssWriter writer = labelWriter.end();
		
		assertEquals(cssWriter, writer);
		compareReaders(
				"label {\n" +
				"\tfont: Dialog bold 12;\n" + 
				"\tpadding: 3 5;\n" +
				"\tcolor: #FF0000;\n" +
				"\tbackground: blank #0000FF;\n" +
				"\ttext-align: center;\n" + 
				"\tvertical-align: center;\n" + 
				"\ttext-effect: none;\n" + 
				"}", writer.asReader());
	}
	
	private void compareReaders(String expected, Reader actual) throws Exception {
		for (int i = 0; i < expected.length(); i++) {
			assertEquals(expected.charAt(i), (char)actual.read());
		}
	}
}
