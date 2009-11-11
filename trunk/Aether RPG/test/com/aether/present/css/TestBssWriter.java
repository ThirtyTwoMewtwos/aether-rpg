package com.aether.present.css;

import static com.aether.present.css.BssWriter.StyleState.disabled;
import static com.aether.present.css.BssWriter.StyleState.down;
import static com.aether.present.css.BssWriter.StyleState.hover;
import static com.aether.present.css.BssWriter.StyleState.selected;
import static com.aether.present.css.BssWriter.StyleType.button;
import static org.junit.Assert.assertEquals;

import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

import com.util.StringUtil;

public class TestBssWriter {
	
	private BssWriter bssWriter;
	private String defaultBody;

	@Before
	public void setUp() {
		bssWriter = new BssWriter();
		
		defaultBody = "\tfont: \"Dialog\" bold 12;\n" + 
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
				defaultBody + 
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
				defaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonDown() throws Exception {
		bssWriter.createStyle(button, down);
		
		compareReaders(
				"button:down {\n" +
				defaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonSelected() throws Exception {
		bssWriter.createStyle(button,selected);
		
		compareReaders(
				"button:selected {\n" +
				defaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonDisabled() throws Exception {
		bssWriter.createStyle(button, disabled);
		
		compareReaders(
				"button:disabled {\n" +
				defaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testStyleClassDefinedByString() throws Exception {
		bssWriter.createStyle("wordup");
		
		compareReaders(
				"wordup {\n" +
				defaultBody + 
				"}", bssWriter.asReader());
	}
}
