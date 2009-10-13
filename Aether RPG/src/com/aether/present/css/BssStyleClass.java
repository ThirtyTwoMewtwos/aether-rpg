package com.aether.present.css;

import org.gap.jseed.contract.Contract;

import com.jmex.bui.property.FontProperty;

public class BssStyleClass {
	private static final int TOP = 0;
	private static final int RIGHT = 1;
	private static final int BOTTOM = 2;
	private static final int LEFT = 3;

	private final BssWriter cssWriter;
	private final String name;

	private Integer[] padding = {3, 5, null, null};
	private BssColor color = BssColor.RED;
	private BssColor backgroundColor = BssColor.BLUE;
	private String backgroundStyle = "solid";
	private BssTextAlign textAlign = BssTextAlign.CENTER;
	private BssVerticalAlign verticalAlign = BssVerticalAlign.CENTER;
	private BssTextEffect textEffect = BssTextEffect.NONE;
	private FontProperty font = new FontProperty();
	private BssColor effectColor = BssColor.RED;
	
	/*package*/ BssStyleClass(String name, BssWriter cssWriter) {
		this.name = name;
		this.cssWriter = cssWriter;
		font.family = "Dialog";
		font.style = BssFontStyle.BOLD.toString();
		font.size = 12;
	}

	public BssWriter end() {
		return cssWriter;
	}

	public void setColor(BssColor newColor) {
		color = newColor;
	}

	public void setBackground(BssColor newBackground) {
		this.backgroundStyle = "solid";
		this.backgroundColor  = newBackground;
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
	
	public void setPadding(int allSides) {
		padding[TOP] = allSides;
		padding[RIGHT] = padding[BOTTOM] = padding[LEFT] = null;
	}

	public void setPadding(int topAndBottom, int leftAndRight) {
		padding[TOP] = topAndBottom;
		padding[RIGHT] = leftAndRight;
		padding[BOTTOM] = padding[LEFT] = null;
	}
	
	public void setPadding(int top, int right, int bottom, int left) {
		padding[TOP] = top;
		padding[RIGHT] = right;
		padding[BOTTOM] = bottom;
		padding[LEFT] = left;
	}

	public void clearBackground() {
		backgroundStyle = "blank";
	}

	public void setEffectColor(BssColor newEffectColor) {
		this.effectColor  = newEffectColor;
		
	}
	
	public String writeBss() {
		return name + " {\n" + 
				writeFont() + 
				writePaddingAction() +
			   	writeAction("color", color) +
			   	writeBackground() + 
			   	writeAction("text-align", textAlign) + 
			   	writeAction("vertical-align", verticalAlign) +
			   	writeAction("text-effect", textEffect) +
			   	writeAction("effect-color", effectColor) +
			   "}";
	}

	private String writeFont() {
		return String.format("\tfont: \"%s\" %s %s;\n", font.family, font.style, font.size);
	}

	private String writePaddingAction() {
		String result = "\tpadding:";
		for (Integer each : padding) {
			if (each != null) {
				result += " " + each;
			} else {
				break;
			}
		}
		
		return result + ";\n";
	}

	private String writeAction(String name, Object value) {
		if (value == null) {
			return "";
		}
		return String.format("\t%s: %s;\n", name, value);
	}

	private String writeBackground() {
		return String.format("\tbackground: %s %s;\n", backgroundStyle, backgroundColor);
	}
}
