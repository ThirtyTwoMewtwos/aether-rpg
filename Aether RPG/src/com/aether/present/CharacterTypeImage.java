package com.aether.present;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aether.model.character.Classification;

public class CharacterTypeImage {
	public static Image getImage(Classification type) {
		String pathname = createPathToImage(type);
		try {
			return loadBufferedImage(pathname);
		} catch (Exception e) {
			String message = String.format("Unable to image for type [%s], searching for file [%s].", type.getName(), pathname);
			throw new RuntimeException(message, e);
		}
	}

	private static Image loadBufferedImage(String pathname)	throws IOException {
		File file = new File(pathname);
		return ImageIO.read(file);
	}

	private static String createPathToImage(Classification type) {
		String property = System.getProperty("user.dir");
		String path = property.replace('\\', '/');
		String pathname = path + "/images/" + type.getName().toLowerCase() + ".png";
		System.out.println(pathname);
		return pathname;
	}

}
