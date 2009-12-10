package com.aether.gbui.bss;

/*
 * BssText.java
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

import org.gap.jseed.contract.Contract;

import com.jmex.bui.property.FontProperty;

class BssText {
	private BssTextAlign textAlign = BssTextAlign.CENTER;
	private BssVerticalAlign verticalAlign = BssVerticalAlign.CENTER;
	private BssTextEffect textEffect = BssTextEffect.NONE;
	private FontProperty font = new FontProperty();
	
	public BssText() {
		font.family = "Dialog";
		font.style = BssFontStyle.BOLD.toString();
		font.size = 12;
	}
	
	public void setTextAlign(BssTextAlign newTextAlign) {
		textAlign = newTextAlign;
	}
	
	public void setVerticalAlign(BssVerticalAlign newVerticalAlign) {
		verticalAlign = newVerticalAlign;
	}

	public void setTextEffect(BssTextEffect newTextEffect) {
		textEffect = newTextEffect;
	}

	public void setFont(String family, BssFontStyle style, int size) {
		Contract.argumentNotNull(family, "Family font must be specified");
		Contract.argumentNotNull(style, "Style font must be specified");
		font.family = family;
		font.style = style.toString();
		font.size = size;		
	}

	public String writeBss() {
		return 	writeFont() + 
				writeAction("text-align", textAlign) + 
	   			writeAction("vertical-align", verticalAlign) +	
	   			writeAction("text-effect", textEffect);
	}

	private String writeFont() {
		return String.format("\tfont: \"%s\" %s %s;\n", font.family, font.style, font.size);
	}
	
	private String writeAction(String name, Object value) {
		if (value == null) {
			return "";
		}
		return String.format("\t%s: %s;\n", name, value);
	}
}
