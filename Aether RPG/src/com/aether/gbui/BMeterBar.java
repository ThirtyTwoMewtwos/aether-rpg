package com.aether.gbui;

import org.lwjgl.opengl.GL11;

import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jmex.bui.BComponent;

public class BMeterBar extends BComponent {
	private String tooltipStyleClass;
	private int maximum;
	private int value;

	public BMeterBar(String name) {
		super(name);
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
		float height = ((float)getHeight()) * ((float)value / (float)maximum);
		GL11.glColor4f(color.r, color.g, color.b, color.a);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(getWidth(), 0);
		GL11.glVertex2f(getWidth(), height);
		GL11.glVertex2f(0, height);
		GL11.glEnd();
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
