package com.aether.present.css;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import static com.aether.present.css.BssWriter.StyleState.*;
import static com.aether.present.css.BssWriter.StyleType.*;

import org.junit.Before;
import org.junit.Test;

import com.util.StringUtil;

public class TestBssWriter {
	
	private BssWriter bssWriter;
	private String buttonDefaultBody;

	@Before
	public void setUp() {
		bssWriter = new BssWriter();
		
		buttonDefaultBody = "\tfont: \"Dialog\" bold 12;\n" + 
						"\tpadding: 3 5;\n" +
						"\tcolor: #FF0000;\n" +
						"\tbackground: solid #0000FF;\n" +
						"\ttext-align: center;\n" + 
						"\tvertical-align: center;\n" + 
						"\ttext-effect: none;\n" + 
						"\teffect-color: #FF0000;\n";
	}

	@Test
	public void testButton() throws Exception {
		BssStyleClass buttonWriter = bssWriter.createStyle(button);
		BssWriter writer = buttonWriter.end();
		
		assertEquals(bssWriter, writer);
		
		compareReaders(
				"button {\n" +
				buttonDefaultBody + 
				"}", writer.asReader());
	}

	private void compareReaders(String expected, Reader reader) throws Exception {
		String actual = StringUtil.toString(reader);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testButtonHover() throws Exception {
		bssWriter.createStyle(button, hover);
		
		compareReaders(
				"button:hover {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonDown() throws Exception {
		bssWriter.createStyle(button, down);
		
		compareReaders(
				"button:down {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonSelected() throws Exception {
		bssWriter.createStyle(button,selected);
		
		compareReaders(
				"button:selected {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonDisabled() throws Exception {
		bssWriter.createStyle(button, disabled);
		
		compareReaders(
				"button:disabled {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
}
