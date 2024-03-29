package com.aether.gbui.bss;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import com.aether.gbui.bss.BssColor;


public class TestBssColor {
	
	@Test
	public void testDefaultColors() throws Exception {
		assertEquals("#FF0000", BssColor.RED.toString());
		assertEquals("#008000", BssColor.GREEN.toString());
		assertEquals("#0000FF", BssColor.BLUE.toString());
	}
	
	@Test
	public void testCreateColors() throws Exception {
		assertEquals(BssColor.BLACK, new BssColor("000000"));
		assertEquals(BssColor.WHITE, new BssColor("FFF"));
		assertEquals(BssColor.BLUE, new BssColor(Color.blue));
	}
	
	@Test
	public void testSettingAlpha() throws Exception {
		Color redWithAlpha = new Color(255, 0, 0, 170);
		assertEquals("#FF0000AA", new BssColor(redWithAlpha).toString());
	}
}
