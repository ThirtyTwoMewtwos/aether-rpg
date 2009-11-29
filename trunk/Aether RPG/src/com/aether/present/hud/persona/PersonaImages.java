package com.aether.present.hud.persona;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.util.FileUtil;

public class PersonaImages {

	public static Image getImage(CombatPanelStatistics stat) {
		String fileName = stat.name().toLowerCase() + ".png";
		try {
			File imageFile = FileUtil.getImageResource("persona", fileName);
			return ImageIO.read(imageFile);
		} catch (Exception e) {
			String message = String.format("Unable to find image for statistic [%s], searching for file [%s].", stat.name(), fileName);
			throw new RuntimeException(message, e);
		}
	}

}
