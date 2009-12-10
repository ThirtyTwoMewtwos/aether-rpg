package com.aether.gbui;

/*
 * BImageUtils.java
 *
 * Copyright (c) 2008, Tyler Hoersch
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Wisconsin Oshkosh nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDERS AND CONTRIBUTORS ''AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.aether.present.Main;
import com.aether.present.ShutdownHook;
import com.jmex.bui.BImage;
import com.jmex.bui.BLabel;
import com.jmex.bui.background.ImageBackground;
import com.jmex.bui.enumeratedConstants.ImageBackgroundMode;

public class BImageUtils {
	private static List<BImage> images = new ArrayList<BImage>();
	static {
		Main.addShutdownHook(new ShutdownHook() {

			@Override
			public void doShutdown() {
				for (BImage each : images) {
					each.clearBuffers();
					each.clearTextureBuffers();
				}
				images.clear();
			}
			
		});
	}
	
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
