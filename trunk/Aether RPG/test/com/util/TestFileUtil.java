package com.util;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.aether.test.AssertThrows;
import com.aether.test.CodeBlock;

public class TestFileUtil {
	
	@Test
	public void test_Get_path_to_image_directory() throws Exception {
		File file = FileUtil.getImageResource("test.jpg");
		assertTrue("Expected full path to file, but was [" + file.getAbsolutePath() + "]", file.exists());
	}
	
	@Test
	public void test_Unable_to_find_file_throws_exception() throws Exception {
		AssertThrows.assertThrows(FileNotFoundException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				FileUtil.getImageResource("Some file that never should exist.goobley gob");
			}
		});
	}
}
