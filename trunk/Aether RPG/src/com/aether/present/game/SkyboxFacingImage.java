package com.aether.present.game;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.jme.scene.Skybox.Face;
import com.util.FileUtil;

public class SkyboxFacingImage {

	public static Image getImage(Face type) {
		String fileName = "dg_" + type.name().toLowerCase() + ".png";
		try {
			File imageFile = FileUtil.getImageResource("sky", fileName);
			return ImageIO.read(imageFile);
		} catch (Exception e) {
			String message = String.format("Unable to find image for type [%s], searching for file [%s].", type.name(), fileName);
			throw new RuntimeException(message, e);
		}
	}

}
