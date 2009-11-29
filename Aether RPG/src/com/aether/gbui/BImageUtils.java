package com.aether.gbui;

import java.awt.Image;

import com.aether.present.hud.persona.CombatPanelStatistics;
import com.aether.present.hud.persona.PersonaImages;
import com.jmex.bui.BImage;
import com.jmex.bui.BLabel;
import com.jmex.bui.background.ImageBackground;
import com.jmex.bui.enumeratedConstants.ImageBackgroundMode;

public class BImageUtils {
	public static void loadBackgroundImageFor(BLabel label, Image image) {
		BImage bImage = new BImage(image);
		ImageBackground background = new ImageBackground(ImageBackgroundMode.CENTER_XY, bImage);
		setBackgroundImage(label, background);
	}

	private static void setBackgroundImage(BLabel label, ImageBackground background) {
		label.setBackground(0, background);
		label.setBackground(1, background);
		label.setBackground(2, background);
	}
}
