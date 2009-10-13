package com.aether.present.css;

import static org.junit.Assert.assertEquals;

import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

public class TestCssWriter {
	
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
		BssStyleClass buttonWriter = bssWriter.button();
		BssWriter writer = buttonWriter.end();
		
		assertEquals(bssWriter, writer);
		
		compareReaders(
				"button {\n" +
				buttonDefaultBody + 
				"}", writer.asReader());
	}

	private void compareReaders(String expected, Reader reader) throws Exception {
		StringBuffer actual = new StringBuffer();
		for (int i = 0; i < expected.length(); i++) {
			actual.append((char)reader.read());
		}
		assertEquals(expected, actual.toString());
	}
	
	@Test
	public void testButtonHover() throws Exception {
		bssWriter.buttonHover();
		
		compareReaders(
				"button:hover {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonDown() throws Exception {
		bssWriter.buttonDown();
		
		compareReaders(
				"button:down {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonSelected() throws Exception {
		bssWriter.buttonSelected();
		
		compareReaders(
				"button:selected {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
	
	@Test
	public void testButtonDisabled() throws Exception {
		bssWriter.buttonDisabled();
		
		compareReaders(
				"button:disabled {\n" +
				buttonDefaultBody + 
				"}", bssWriter.asReader());
	}
	
	
	@Test
	public void testLabel() throws Exception {
		BssStyleClass labelWriter = bssWriter.label();
		BssWriter writer = labelWriter.end();
		
		compareReaders(
				"label {\n" +
				"\tfont: \"Dialog\" bold 12;\n" + 
				  "\tpadding: 3 5;\n" +
				  "\tcolor: #FF0000;\n" +
				  "\tbackground: blank #0000FF;\n" +
				  "\ttext-align: center;\n" + 
				  "\tvertical-align: center;\n" + 
				  "\ttext-effect: none;\n" + 
				  "\teffect-color: #FF0000;\n" +		
				"}", writer.asReader());
	}

    @Test
    public void testTextField() throws Exception {
        BssStyleClass textField = bssWriter.textField();
		BssWriter writer = textField.end();

		compareReaders(
				"textfield {\n" +
				"\tfont: \"Dialog\" bold 12;\n" +
				  "\tpadding: 5;\n" +
				  "\tcolor: #000088;\n" +
				  "\tbackground: solid #0000FF;\n" +
				  "\ttext-align: center;\n" +
				  "\tvertical-align: center;\n" +
				  "\ttext-effect: none;\n" +
				  "\teffect-color: #FF0000;\n" +
				"}", writer.asReader());
    }
    
    @Test
    public void testComboBox() throws Exception {
        BssStyleClass comboBox = bssWriter.comboBox();
		BssWriter writer = comboBox.end();

		compareReaders(
				"combobox {\n" +
				"\tfont: \"Dialog\" bold 12;\n" +
				  "\tpadding: 3 5;\n" +
				  "\tcolor: #FF0000;\n" +
				  "\tbackground: solid #0000FF;\n" +
				  "\ttext-align: center;\n" +
				  "\tvertical-align: center;\n" +
				  "\ttext-effect: none;\n" +
				  "\teffect-color: #FF0000;\n" +
				"}\n" + 
				"menuitem {\n" +
				"\tfont: \"Dialog\" bold 12;\n" +
				  "\tpadding: 3 5;\n" +
				  "\tcolor: #FF0000;\n" +
				  "\tbackground: solid #0000FF;\n" +
				  "\ttext-align: center;\n" +
				  "\tvertical-align: center;\n" +
				  "\ttext-effect: none;\n" +
				  "\teffect-color: #FF0000;\n" +
				"}", writer.asReader());
    }
	
    @Test
    public void testMenuItem() throws Exception {
        BssStyleClass menuItem = bssWriter.menuItem();
		BssWriter writer = menuItem.end();

		compareReaders(
				"menuitem {\n" +
				"\tfont: \"Dialog\" bold 12;\n" +
				  "\tpadding: 3 5;\n" +
				  "\tcolor: #FF0000;\n" +
				  "\tbackground: solid #0000FF;\n" +
				  "\ttext-align: center;\n" +
				  "\tvertical-align: center;\n" +
				  "\ttext-effect: none;\n" +
				  "\teffect-color: #FF0000;\n" +
				"}", writer.asReader());
    }
}
