package com.aether.model.quests;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.util.FileUtil;

public class QuestTypeImage {
	public static Image getImage(QuestType type) {
		String fileName = type.name().toLowerCase() + "_quest_icon.png";
		try {
			File imageFile = FileUtil.getImageResource(fileName);
			return ImageIO.read(imageFile);
		} catch (Exception e) {
			String message = String.format("Unable to find image for type [%s], searching for file [%s].", type.name(), fileName);
			throw new RuntimeException(message, e);
		}
	}
}
