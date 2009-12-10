package com.aether.gbui.bss;

/*
 * BssStyleClass.java
 * 
 * Copyright (c) 2008, Tyler Hoersch
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Wisconsin Oshkosh nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDERS AND CONTRIBUTORS ''AS IS'' AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

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
