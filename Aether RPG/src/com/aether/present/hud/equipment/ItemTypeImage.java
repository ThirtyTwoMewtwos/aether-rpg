package com.aether.present.hud.equipment;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.util.FileUtil;

public class ItemTypeImage {
	private static final String DEFAULT_ICON = "default_icon.png";
	private static final int TYPE = 0;
	private static final int NUMBER = 1;
	private static BufferedImage defaultImage;
	private static Map<String, List<Image>> images = new Hashtable<String, List<Image>>();

	public static Image getImage(String itemType) {
		String[] name = itemType.split("_");
		if (!images.containsKey(name[TYPE])) {
			try {
				loadImageTiles(name[TYPE]);
			} catch (FileNotFoundException e) {
				return tryDefaultImage(itemType, e);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return images.get(name[TYPE]).get(Integer.parseInt(name[NUMBER]));
	}

	private static void loadImageTiles(String name) throws FileNotFoundException, IOException {
		String fileName = name.toLowerCase() + ".png";
		File imageFile = FileUtil.getImageResource("items", fileName);
		BufferedImage tiledImage = ImageIO.read(imageFile);
		ArrayList<Image> list = splitImageIntoTiles(tiledImage);
		images.put(name, list);
	}

	private static ArrayList<Image> splitImageIntoTiles(BufferedImage tiledImage) {
		Image[] tiledImages = splitImage(tiledImage, tiledImage.getWidth(null) / 32, tiledImage.getHeight(null) / 32);
		ArrayList<Image> list = new ArrayList<Image>();
		list.addAll(Arrays.asList(tiledImages));
		return list;
	}

	private static Image tryDefaultImage(String itemType, Exception e) {
		if (defaultImage == null) {
			tryLoadingDefaultImage(itemType, e);
		}
		return defaultImage;
	}

	private static void tryLoadingDefaultImage(String itemType, Exception e) {
		try {
			File defaultFile = FileUtil.getImageResource("items", DEFAULT_ICON);
			defaultImage = ImageIO.read(defaultFile);
		} catch (Exception ee) {
			String message = String.format("Unable to find image for type [%s], searching for file [%s].", itemType, DEFAULT_ICON);
			throw new RuntimeException(message, e);
		}
	}

	private static Image[] splitImage(BufferedImage img, int cols, int rows) {
		int w = img.getWidth() / cols;
		int h = img.getHeight() / rows;
		int num = 0;
		BufferedImage imgs[] = new BufferedImage[w * h];
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				imgs[num] = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				// Tell the graphics to draw only one block of the image
				Graphics2D g = imgs[num].createGraphics();
				g.drawImage(img, 0, 0, w, h, w * x, h * y, w * x + w, h * y + h, null);
				g.dispose();
				num++;
			}
		}
		return imgs;
	}
}
