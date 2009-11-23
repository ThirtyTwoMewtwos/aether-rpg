package com.aether.gbui.bss;

public class BssImageBackground implements BssBackground {
	private String fileLocation;
	private BssBackgroundMode imageMode;
	private int[] insets;

	public BssImageBackground(BssColor backgroundColor) {
	}
	
	public BssImageBackground(String fileLocation, BssBackgroundMode mode, int[] insets) {
		this.fileLocation = fileLocation;
		this.imageMode = mode;
		this.insets = insets;
	}
	
	public String writeBss() {
		return createImageBackground();
	}

	private String createImageBackground() {
		String insetString = "";
		for (int each : insets) {
			insetString += " " + each;
		}
		return String.format("\tbackground: image '%s' %s%s;\n", fileLocation, imageMode.name().toLowerCase(), insetString);
	}

	public void clearBackground() {
	}
}