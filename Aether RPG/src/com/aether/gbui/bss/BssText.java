package com.aether.gbui.bss;

import org.gap.jseed.contract.Contract;

import com.jmex.bui.property.FontProperty;

class BssText {
	private BssTextAlign textAlign = BssTextAlign.CENTER;
	private BssVerticalAlign verticalAlign = BssVerticalAlign.CENTER;
	private BssTextEffect textEffect = BssTextEffect.NONE;
	private FontProperty font = new FontProperty();
	
	public BssText() {
		font.family = "Dialog";
		font.style = BssFontStyle.BOLD.toString();
		font.size = 12;
	}
	
	public void setTextAlign(BssTextAlign newTextAlign) {
		textAlign = newTextAlign;
	}
	
	public void setVerticalAlign(BssVerticalAlign newVerticalAlign) {
		verticalAlign = newVerticalAlign;
	}

	public void setTextEffect(BssTextEffect newTextEffect) {
		textEffect = newTextEffect;
	}

	public void setFont(String family, BssFontStyle style, int size) {
		Contract.argumentNotNull(family, "Family font must be specified");
		Contract.argumentNotNull(style, "Style font must be specified");
		font.family = family;
		font.style = style.toString();
		font.size = size;		
	}

	public String writeBss() {
		return 	writeFont() + 
				writeAction("text-align", textAlign) + 
	   			writeAction("vertical-align", verticalAlign) +	
	   			writeAction("text-effect", textEffect);
	}

	private String writeFont() {
		return String.format("\tfont: \"%s\" %s %s;\n", font.family, font.style, font.size);
	}
	
	private String writeAction(String name, Object value) {
		if (value == null) {
			return "";
		}
		return String.format("\t%s: %s;\n", name, value);
	}
}
