package com.aether.present.css;

import java.awt.Color;

public class BssColor {
//	#FFFFFF PZ bgcolor, default #FFFFFF white
//	#CC0000 PZ errorText, default #CC0000 old red
//	#999999 PZ lightestSubText, default #999999 medium gray
//	#666666 PZ lightSubText, default #666666 dark gray
//	#FF6600 PZ specialText, default #FF6600 orange red
//	#0D447F PZ tableBack, default #0D447F deep blue
//	#D4E5FA PZ tableBody1, default #D4E5FA light blue
//	#EEF3F9 PZ tableBody2, default #EEF3F9 pale blue
//	#000000 PZ text, default #000000 true black
//	#003366 PZ link, default #003366 ultra dark blue
//	#CC0000 PZ alink, default #CC0000 old red
//	#003366 PZ vlink, default #003366 ultra dark blue
//	#6699CC PZ DarkSubtextColor, default #6699CC medium dark blue
//	#FAF4CD PZ HiLiteColor, default #FAF4CD pale yellow
//	#33CC33 PZ ButtonColor, default #33CC33 bright green
	
	public static final BssColor RED = new BssColor("FF0000"), 
								 GREEN = new BssColor("00FF00"), 
								 BLUE = new BssColor("0000FF"), 
								 WHITE = new BssColor("FFFFFF"), 
								 BLACK = new BssColor("000000");
	
	private final String hashColor;

	public BssColor(String color) {
		if (color.length() == 3) {
			char[] cs = color.toCharArray();
			this.hashColor = "#" + cs[0] + cs[0] + cs[1] + cs[1] + cs[2] + cs[2]; 
		} else {
			this.hashColor = "#" + color;
		}
	}
	
	public BssColor(Color color) {
		String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());
		if (color.getAlpha() == 255) {
			this.hashColor = "#" + getHexValue(red) + getHexValue(green) + getHexValue(blue);
		} else {
			String alpha = Integer.toHexString(color.getAlpha());
			this.hashColor = "#" + getHexValue(red) + getHexValue(green) + getHexValue(blue) + getHexValue(alpha);
		}
	}

	private String getHexValue(String hex) {
		return (hex.length() == 2? hex: "0" + hex).toUpperCase();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BssColor) {
			BssColor otherBssColor = (BssColor)obj;
			return hashColor.equals(otherBssColor.hashColor);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return hashColor;
	}
}
