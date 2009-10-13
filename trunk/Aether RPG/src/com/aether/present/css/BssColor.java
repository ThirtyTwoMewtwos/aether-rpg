package com.aether.present.css;

import java.awt.Color;

public class BssColor {
//	#CC0000 PZ errorText, default #CC0000 old red
//	#999999 PZ lightestSubText, default #999999 medium gray
//	#666666 PZ lightSubText, default #666666 dark gray
//	#FF6600 PZ specialText, default #FF6600 orange red
//	#0D447F PZ tableBack, default #0D447F deep blue
//	#D4E5FA PZ tableBody1, default #D4E5FA light blue
//	#EEF3F9 PZ tableBody2, default #EEF3F9 pale blue
//	#003366 PZ link, default #003366 ultra dark blue
//	#CC0000 PZ alink, default #CC0000 old red
//	#003366 PZ vlink, default #003366 ultra dark blue
//	#6699CC PZ DarkSubtextColor, default #6699CC medium dark blue
//	#FAF4CD PZ HiLiteColor, default #FAF4CD pale yellow
//	#33CC33 PZ ButtonColor, default #33CC33 bright green
	
	public static final BssColor RED = new BssColor("F00"), 
								 BLUE = new BssColor("00F"), 
								 DARK_BLUE = new BssColor("44F"),
								 WHITE = new BssColor("FFF"), 
								 BLACK = new BssColor("000"),
								 LIGHT_GRAY = new BssColor("CCC"),
								 AQUA = new BssColor("00ffff"),
								 BEIGE = new BssColor("f5f5dc"),
								 BROWN = new BssColor("a52a2a"),
								 CYAN = new BssColor("00ffff"),
								 DARK_CYAN = new BssColor("008b8b"),
								 DARK_GRAY	= new BssColor("a9a9a9"),
								 DARK_GREEN	= new BssColor("006400"),
								 DARK_RED	= new BssColor("8b0000"),
								 DARK_VIOLET	= new BssColor("9400d3"),
								 DEEP_PINK	= new BssColor("ff1493"),
								 DEEP_SKY_BLUE	= new BssColor("00bfff"),
								 FIRE_BRICK	= new BssColor("b22222"),
								 FOREST_GREEN	= new BssColor("228b22"),
								 GHOST_WHITE	= new BssColor("f8f8ff"),
								 GOLD	= new BssColor("ffd700"),
								 GOLDEN_ROD	= new BssColor("daa520"),
								 GREEN	= new BssColor("008000"),
								 GREEN_YELLOW	= new BssColor("adff2f"),
								 GRAY	= new BssColor("808080"),
								 HOT_PINK	= new BssColor("ff69b4"),
								 INDIAN_RED	= new BssColor("cd5c5c"),
								 IVORY	= new BssColor("fffff0"),
								 KHAKI	= new BssColor("f0e68c"),
								 LAVENDER	= new BssColor("e6e6fa"),
								 LIGHT_BLUE	= new BssColor("add8e6"),
								 lightcoral	= new BssColor("f08080"),
								 lightcyan	= new BssColor("e0ffff"),
								 lightgoldenrodyellow	= new BssColor("fafad2"),
								 lightgray	= new BssColor("d3d3d3"),
								 lightgreen	= new BssColor("90ee90"),
								 lightgrey	= new BssColor("d3d3d3"),
								 lightpink	= new BssColor("ffb6c1"),
								 lightsalmon	= new BssColor("ffa07a"),
								 lightseagreen	= new BssColor("20b2aa"),
								 lightskyblue	= new BssColor("87cefa"),
								 lightslategray	= new BssColor("778899"),
								 lightslategrey	= new BssColor("778899"),
								 lightsteelblue	= new BssColor("b0c4de"),
								 lightyellow	= new BssColor("ffffe0"),
								 lime	= new BssColor("00ff00"),
								 limegreen	= new BssColor("32cd32"),
								 linen	= new BssColor("faf0e6"),
								 magenta	= new BssColor("ff00ff"),
								 maroon	= new BssColor("800000"),
								 mediumaquamarine	= new BssColor("66cdaa"),
								 mediumblue	= new BssColor("0000cd"),
								 mediumorchid	= new BssColor("ba55d3"),
								 mediumpurple	= new BssColor("9370db"),
								 mediumseagreen	= new BssColor("3cb371"),
								 mediumslateblue	= new BssColor("7b68ee"),
								 mediumspringgreen	= new BssColor("00fa9a"),
								 mediumturquoise	= new BssColor("48d1cc"),
								 mediumvioletred	= new BssColor("c71585"),
								 midnightblue	= new BssColor("191970"),
								 mintcream	= new BssColor("f5fffa"),
								 mistyrose	= new BssColor("ffe4e1"),
								 moccasin	= new BssColor("ffe4b5"),
								 navajowhite	= new BssColor("ffdead"),
								 navy	= new BssColor("000080"),
								 oldlace	= new BssColor("fdf5e6"),
								 olive	= new BssColor("808000"),
								 olivedrab	= new BssColor("6b8e23"),
								 orange	= new BssColor("ffa500"),
								 orangered	= new BssColor("ff4500"),
								 orchid	= new BssColor("da70d6"),
								 palegoldenrod	= new BssColor("eee8aa"),
								 palegreen	= new BssColor("98fb98"),
								 paleturquoise	= new BssColor("afeeee"),
								 palevioletred	= new BssColor("db7093"),
								 papayawhip	= new BssColor("ffefd5"),
								 peachpuff	= new BssColor("ffdab9"),
								 peru	= new BssColor("cd853f"),
								 pink	= new BssColor("ffc0cb"),
								 plum	= new BssColor("dda0dd"),
								 powderblue	= new BssColor("b0e0e6"),
								 purple	= new BssColor("800080"),
								 red	= new BssColor("ff0000"),
								 rosybrown	= new BssColor("bc8f8f"),
								 royalblue	= new BssColor("4169e1"),
								 saddlebrown	= new BssColor("8b4513"),
								 salmon	= new BssColor("fa8072"),
								 sandybrown	= new BssColor("f4a460"),
								 seagreen	= new BssColor("2e8b57"),
								 seashell	= new BssColor("fff5ee"),
								 sienna	= new BssColor("a0522d"),
								 silver	= new BssColor("c0c0c0"),
								 skyblue	= new BssColor("87ceeb"),
								 slateblue	= new BssColor("6a5acd"),
								 slategray	= new BssColor("708090"),
								 slategrey	= new BssColor("708090"),
								 snow	= new BssColor("fffafa"),
								 springgreen	= new BssColor("00ff7f"),
								 steelblue	= new BssColor("4682b4"),
								 tan	= new BssColor("d2b48c"),
								 teal	= new BssColor("008080"),
								 thistle	= new BssColor("d8bfd8"),
								 tomato	= new BssColor("ff6347"),
								 turquoise	= new BssColor("40e0d0"),
								 violet	= new BssColor("ee82ee"),
								 wheat	= new BssColor("f5deb3"),
								 white	= new BssColor("ffffff"),
								 whitesmoke	= new BssColor("f5f5f5"),
								 yellow	= new BssColor("ffff00"),
								 yellowgreen = new BssColor("9acd32");

	
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
        String tmpColor;
        String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());
        tmpColor = "#" + getHexValue(red) + getHexValue(green) + getHexValue(blue);
        if (color.getAlpha() != 255) {
			String alpha = Integer.toHexString(color.getAlpha());
			tmpColor += getHexValue(alpha);
		}
        hashColor = tmpColor;
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
