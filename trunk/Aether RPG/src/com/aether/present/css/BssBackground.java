package com.aether.present.css;

public class BssBackground {
	private BssColor color;
	private String style = "solid";
	private String fileLocation;
	private BssBackgroundMode imageMode;
	private int[] insets;

	public BssBackground(BssColor backgroundColor) {
		this.color = backgroundColor;
	}
	
	public String writeBss() {
		if (fileLocation == null) {
			return String.format("\tbackground: %s %s;\n", style, color);
		} else {
			String insetString = "";
			for (int each : insets) {
				insetString += " " + each;
			}
			return String.format("\tbackground: image '%s' %s%s;\n", fileLocation, imageMode.name().toLowerCase(), insetString);
		}
	}
	
	public void setColor(BssColor color) {
		this.color = color;
		this.style = "solid";
		fileLocation = null;
	}

	public void setImage(String fileLocation, BssBackgroundMode mode, int[] insets) {
		this.fileLocation = fileLocation;
		this.imageMode = mode;
		this.insets = insets;
	}

	public void clearBackground() {
		style = "blank";
		fileLocation = null;
	}
}