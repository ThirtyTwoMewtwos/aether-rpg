package com.util;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtil {
	public static File getImageResource(String ... imagePathToFile) throws FileNotFoundException {
		String appDirectory = System.getProperty("user.dir");
		String path = appDirectory.replace('\\', '/') + "/images";
		
		for (String each : imagePathToFile) {
			path += "/" + each;
		}
		
		File file = new File(path);
		if (file.exists()) {
			return file;
		} 
		throw new FileNotFoundException("Unable to locate file, " + file.getAbsolutePath());
	}
}
