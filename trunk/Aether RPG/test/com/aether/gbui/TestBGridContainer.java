package com.aether.gbui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.test.AssertThrows;
import com.aether.test.CodeBlock;
import com.jmex.bui.BLabel;


public class TestBGridContainer {
	private BGridContainer container;

	@Before
	public void setUp() {
		container = new BGridContainer(4, 6);
	}
	
	@Test
	public void test_Create_container() throws Exception {
		assertEquals(4, container.numberOfRows());
		assertEquals(6, container.numberOfColumns());
		assertEquals(0, container.valueCount());
		assertEquals("gridcontainer", container.getStyleClass());
	}
	
	@Test
	public void test_Set_value_in_specific_row_and_column() throws Exception {
		container.setValue(1, 2, "hello");
		assertEquals(1, container.valueCount());
		for (int row = 0;  row < container.numberOfRows(); row++) {
			for (int column = 0; column < container.numberOfColumns(); column++) {
				if (row == 1 && column == 2) {
					assertEquals("hello", container.getValue(row, column));
				} else {
					assertNull(container.getValue(row, column));
				}
			}
		}
	}
	
	@Test
	public void test_Set_value_on_boundary() throws Exception {
		container.setValue(0, 0, "first");
		container.setValue(3, 5, "last");
		for (int row = 0;  row < container.numberOfRows(); row++) {
			for (int column = 0; column < container.numberOfColumns(); column++) {
				if (row == 0 && column == 0) {
					assertEquals("first", container.getValue(row, column));
				} else if (row == 3 && column == 5) {
					assertEquals("last", container.getValue(row, column));
				} else {
					assertNull(container.getValue(row, column));
				}
			}
		}
		assertEquals(2, container.valueCount());
	}
	
	@Test
	public void test_Clearing_a_value() throws Exception {
		container.setValue(3, 2, "value");
		container.removeValue(3, 2);
		assertEquals(0, container.valueCount());
		assertNull(container.getValue(3, 2));
	}
	
	@Test
	public void test_Clearing_a_null_value_does_nothing() throws Exception {
		container.removeValue(2, 3);
		assertEquals(0, container.valueCount());
	}
	
	@Test
	public void test_Adding_to_occupied_location_overwrites() throws Exception {
		container.setValue(1, 2, "hello");
		container.setValue(1, 2, "hello2");
		assertEquals(1, container.valueCount());
		for (int row = 0;  row < container.numberOfRows(); row++) {
			for (int column = 0; column < container.numberOfColumns(); column++) {
				if (row == 1 && column == 2) {
					assertEquals("hello2", container.getValue(row, column));
				} else {
					assertNull(container.getValue(row, column));
				}
			}
		}
	}
	
	@Test
	public void test_Adding_outside_the_boundaries() throws Exception {
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.setValue(-1, 0, "hello");
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.setValue(0, -1, "hello2");
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.setValue(4, 0, "hello2");
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.setValue(3, 6, "hello3");
			}
		});
	}
	
	@Test
	public void test_Get_value_not_in_grid() throws Exception {
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.getValue(-1, 0);
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.getValue(2, -1);
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.getValue(4, 0);
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.getValue(0, 6);
			}
		});
	}
	
	@Test
	public void test_Remove_value_not_in_grid() throws Exception {
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.removeValue(-1, 0);
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.removeValue(2, -1);
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.removeValue(4, 0);
			}
		});
		AssertThrows.assertThrows(ArrayIndexOutOfBoundsException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				container.removeValue(0, 6);
			}
		});
	}
	
	@Test
	public void test_Render_with_cell_renderer() throws Exception {
		BCellRenderer cell = EasyMock.createStrictMock(BCellRenderer.class);
		for (int i = 0; i < 4 * 6; i++) {
			EasyMock.expect(cell.getCellComponent(null, i)).andReturn(new BLabel("bobo" + i));
		}
		
		EasyMock.replay(cell);
		container.setCellRenderer(cell);
		EasyMock.verify(cell);
		assertEquals(24, container.getComponentCount());
	}
	
	
}
