package com.aether.gbui.components;

/*
 * BMeterBar.java
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

import org.lwjgl.opengl.GL11;

import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jmex.bui.BComponent;

public class BMeterBar extends BComponent {
	private String tooltipStyleClass;
	private int maximum;
	private int value;
	private final boolean isVertical;

	public BMeterBar(String name) {
		this(name, true);
	}
	
	public BMeterBar(String name, boolean verticalState) {
		super(name);
		this.isVertical = verticalState;
		updateTooltipText();
		setTooltipRelativeToMouse(true);
	}

	@Override
	protected void renderComponent(Renderer renderer) {
		super.renderComponent(renderer);
		ColorRGBA color = getColor();
		
		if (maximum != 0) {
			drawMeter(color);
		}
	}

	private void drawMeter(ColorRGBA color) {
		if (isVertical) {
			float height = ((float)getHeight()) * ((float)value / (float)maximum);
			GL11.glColor4f(color.r, color.g, color.b, color.a);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(0, 0);
			GL11.glVertex2f(getWidth(), 0);
			GL11.glVertex2f(getWidth(), height);
			GL11.glVertex2f(0, height);
			GL11.glEnd();
		} else {
			float width = ((float)getWidth()) * ((float)value / (float)maximum);
			GL11.glColor4f(color.r, color.g, color.b, color.a);
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(0, 0);
			GL11.glVertex2f(width, 0);
			GL11.glVertex2f(width, getHeight());
			GL11.glVertex2f(0, getHeight());
			GL11.glEnd();
		}
	}

	@Override
	protected BComponent createTooltipComponent(String tiptext) {
		BComponent tooltipComponent = super.createTooltipComponent(tiptext);
		tooltipComponent.setStyleClass(tooltipStyleClass);
		return tooltipComponent;
	}

	public void setTooltipStyleClass(String tooltipStyleClass) {
		this.tooltipStyleClass = tooltipStyleClass;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
		updateTooltipText();
	}

	private void updateTooltipText() {
		String text = String.format("(%s/%s)", getValue(), getMaximum());
		setTooltipText(text);
	}
	
	public int getMaximum() {
		return maximum;
	}

	public void setValue(int value) {
		this.value = value;
		updateTooltipText();
	}

	public int getValue() {
		return value;
	}
}
