package com.aether.gbui;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import com.jmex.bui.BComponent;
import com.jmex.bui.BStyleSheet;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.dragndrop.BDragNDrop;
import com.jmex.bui.event.MouseEvent;
import com.jmex.bui.layout.BLayoutManager;
import com.jmex.bui.layout.BorderLayout;

public class TestBGridContainerDragNDrop {
	private final MouseEvent MOUSE_ENTERED = new MouseEvent(this, -1, 0, MouseEvent.MOUSE_ENTERED, 1, 1);
	private final MouseEvent MOUSE_ENTERED_DRAGGING = new MouseEvent(this, -1, 0, MouseEvent.MOUSE_ENTERED, MouseEvent.BUTTON1, 1, 1);
	private final MouseEvent MOUSE_PRESSED = new MouseEvent(this, -1, 0, MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON1, 1, 1);
	private final MouseEvent MOUSE_RELEASED = new MouseEvent(this, -1, 0, MouseEvent.MOUSE_RELEASED, MouseEvent.BUTTON1, 1, 1);
	private BGridContainer container;
	private BDragNDrop dragNDrop;

	@Before
	public void setUp() {
		BuiSystem.init(new BDummyRootNode(), "");
		container = new BGridContainer(3, 3);
		BWindow window = new BWindow(new BStyleSheet(new StringReader(""), null), new BorderLayout());
		window.setSize(500, 500);
		BuiSystem.addWindow(window);
		container.setDragEnabled(true);
		window.add(container, BorderLayout.CENTER);
		
		dragNDrop = BDragNDrop.instance();
	}
	
	@Test
	public void test_Able_to_select() throws Exception {
		container.setValue(0, 0, "hello");
		
		assertFalse(dragNDrop.isDragging());
		performDrag();
		assertTrue(dragNDrop.isDragging());
	}

	private void performDrag() {
		container.getComponent(0).dispatchEvent(MOUSE_ENTERED_DRAGGING);
		dragNDrop.eventDispatched(MOUSE_ENTERED_DRAGGING);
		container.getComponent(0).dispatchEvent(MOUSE_PRESSED);
		dragNDrop.eventDispatched(MOUSE_PRESSED);
	}
//	
//	@Test
//	public void test_Dropped_item_in_new_location() throws Exception {
//		container.setValue(0, 0, "hello");
//		
//		performDrag();
//		performDrop(4);
//		
//		assertNull(container.getValue(0, 0));
//		assertEquals("hello", container.getValue(1, 1));
//	}

	private void performDrop(int index) {
		BComponent component = container.getComponent(index);
		MouseEvent mouseEvent = new MouseEvent(this, -1, 0, MouseEvent.MOUSE_RELEASED, MouseEvent.BUTTON1, component.getX(), component.getY());
		component.dispatchEvent(MOUSE_RELEASED);
		dragNDrop.eventDispatched(MOUSE_RELEASED);
	}
}
