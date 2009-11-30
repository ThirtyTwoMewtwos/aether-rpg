package com.aether.present.hud.persona;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.util.FileUtil;

public class PersonaImages {
	private static Map<CombatPanelStatistics, Image> images = new Hashtable<CombatPanelStatistics, Image>();
	
	public static Image getImage(CombatPanelStatistics stat) {
		if (images.containsKey(stat)) {
			return images.get(stat);
		}
		String fileName = stat.name().toLowerCase() + ".png";
		try {
			File imageFile = FileUtil.getImageResource("persona", fileName);
			BufferedImage image = ImageIO.read(imageFile);
			images.put(stat, image);
			return image;
		} catch (Exception e) {
			String message = String.format("Unable to find image for statistic [%s], searching for file [%s].", stat.name(), fileName);
			throw new RuntimeException(message, e);
		}
	}
}
