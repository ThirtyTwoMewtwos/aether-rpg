package com.aether.gbui.bss;


public class BssStyleClass {
	private static final int TOP = 0;
	private static final int RIGHT = 1;
	private static final int BOTTOM = 2;
	private static final int LEFT = 3;

	private final BssWriter cssWriter;
	private final String name;

	private Integer[] padding = {3, 5, null, null};
	private BssColor color = BssColor.RED;
	private BssBackground background = new BssColoredBackground(BssColor.BLUE);
	private BssText text = new BssText();
	private int borderThickness = 0;
	private BssColor effectColor = BssColor.RED;
	private BssColor borderColor;
	
	/*package*/ BssStyleClass(String name, BssWriter cssWriter) {
		this.name = name;
		this.cssWriter = cssWriter;
		text = new BssText();
	}

	public BssWriter end() {
		return cssWriter;
	}

	public BssStyleClass setColor(BssColor newColor) {
		color = newColor;
		return this;
	}

	public BssStyleClass setBackground(BssColor newBackground) {
		background = new BssColoredBackground(newBackground);
		return this;
	}

	/**
	 * 
	 * @param fileLocation the URL used to find the image.
	 * @param mode the mode to use.
	 * @param insets inset values excepted are for top right bottom left.
	 * @return
	 */
	public BssStyleClass setBackground(String fileLocation, BssBackgroundMode mode, int...insets) {
		background = new BssImageBackground(fileLocation, mode, insets);
		return this;
	}

	public BssStyleClass setTextAlign(BssTextAlign newTextAlign) {
		text.setTextAlign(newTextAlign);
		return this;
	}
	
	public BssStyleClass setVerticalAlign(BssVerticalAlign newVerticalAlign) {
		text.setVerticalAlign(newVerticalAlign);
		return this;
	}

	public BssStyleClass setTextEffect(BssTextEffect newTextEffect) {
		text.setTextEffect(newTextEffect);
		return this;
	}
	
	public BssStyleClass setFont(String family, BssFontStyle style, int size) {
		text.setFont(family, style, size);
		return this;
	}
	
	public BssStyleClass setPadding(int allSides) {
		padding[TOP] = allSides;
		padding[RIGHT] = padding[BOTTOM] = padding[LEFT] = null;
		return this;
	}

	public BssStyleClass setPadding(int topAndBottom, int leftAndRight) {
		padding[TOP] = topAndBottom;
		padding[RIGHT] = leftAndRight;
		padding[BOTTOM] = padding[LEFT] = null;
		return this;
	}
	
	public BssStyleClass setPadding(int top, int right, int bottom, int left) {
		padding[TOP] = top;
		padding[RIGHT] = right;
		padding[BOTTOM] = bottom;
		padding[LEFT] = left;
		
		return this;
	}

	public BssStyleClass setBorder(int thickness, BssColor color) {
		borderThickness = thickness;
		borderColor = color;
		return this;
	}

	public BssStyleClass clearBackground() {
		background = new BssClearBackground();
		return this;
	}

	public BssStyleClass setEffectColor(BssColor newEffectColor) {
		this.effectColor  = newEffectColor;
		return this;		
	}
	
	public String writeBss() {
		return name + " {\n" + 
				writePaddingAction() +
			   	writeAction("color", color) +
			   	background.writeBss() + 
			   	text.writeBss() +
			   	writeAction("effect-color", effectColor) +
			   	writeBorderAction() + 
			   "}";
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
	
	private String writeBorderAction() {
		if (borderThickness == 0) {
			return "";
		}
		return String.format("\tborder: %s solid %s;\n", borderThickness, borderColor);
	}

}
