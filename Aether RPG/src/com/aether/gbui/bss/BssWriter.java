package com.aether.gbui.bss;

/*
 * BssWriter.java
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

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class BssWriter {
	public enum StyleType {window, combobox, menuitem, button, list_entry, textarea, textfield, label, message, titlemessage, statusmessage, tooltip_label, tooltip_button, tooltip_window}
	public enum StyleState {hover, disabled, selected, down}

    private Map<String, BssStyleClass> stylesClasses = new HashMap<String, BssStyleClass>();

    private BssStyleClass get(String style) {
    	assureContainsStyle(style, new BssStyleClass(style, this));
    	return stylesClasses.get(style);
    }

	private void assureContainsStyle(String style, BssStyleClass styleClass) {
		if (!stylesClasses.containsKey(style)) {
			stylesClasses.put(style, styleClass);
		}
	}

	public Reader asReader() {
		StringBuffer styles = new StringBuffer();
		for (BssStyleClass each : stylesClasses.values()) {
			styles.append(each.writeBss()+ "\n");
		}
		
		styles.deleteCharAt(styles.length() - 1);
		return new StringReader(styles.toString());
	}

	public BssStyleClass createStyle(StyleType widget, StyleState state) {
		return get(widget + ":" + state);
	}
	
	public BssStyleClass createStyle(String widget, StyleState state) {
		return get(widget + ":" + state);
	}

	public BssStyleClass createStyle(StyleType widget) {
		return get(widget.name());
	}

	public BssStyleClass createStyle(String style) {
		return get(style);
	}
}
