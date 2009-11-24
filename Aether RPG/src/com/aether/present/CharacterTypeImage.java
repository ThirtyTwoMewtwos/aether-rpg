package com.aether.present;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.aether.model.character.Classification;
import com.util.FileUtil;

public class CharacterTypeImage {
	public static Image getImage(Classification type) {
		String fileName = type.getName().toLowerCase() + ".png";
		try {
			File imageFile = FileUtil.getImageResource(fileName);
			return ImageIO.read(imageFile);
		} catch (Exception e) {
			String message = String.format("Unable to find image for type [%s], searching for file [%s].", type.getName(), fileName);
			throw new RuntimeException(message, e);
		}
	}
}
