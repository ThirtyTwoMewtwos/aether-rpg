package com.aether.gbui;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.jmex.bui.BComponent;
import com.jmex.bui.BContainer;
import com.jmex.bui.BLabel;
import com.jmex.bui.layout.TableLayout;

public class BGridContainer extends BContainer {
	private Object[] values;
	private final int numberOfRows;
	private final int numberOfColumns;
	private BCellRenderer cellRenderer = new BCellRenderer() {
		@Override
		public BComponent getCellComponent(Object value, int i) {
			String text = (value == null? "": value.toString());
			BLabel label = new BLabel(text);
			return label;
		}
	};

	public BGridContainer(int rows, int columns) {
		this.numberOfRows = rows;
		this.numberOfColumns = columns;
		values = new Object[rows * columns];
		TableLayout layout = new TableLayout(numberOfColumns, 2, 2);
		layout.setHorizontalAlignment(TableLayout.CENTER);
		layout.setVerticalAlignment(TableLayout.CENTER);
		super.setLayoutManager(layout);
		setStyleClass("gridcontainer");
		initializeComponents();
	}

	public int numberOfRows() {
		return numberOfRows;
	}

	public int numberOfColumns() {
		return numberOfColumns;
	}

	public void setValue(int row, int column, Object value) {
		values[getIndex(row, column)] = value;
		initializeComponents();
	}

	private int getIndex(int row, int column) {
		validate(row, column);
		return (row * numberOfColumns) + column;
	}

	private void validate(int row, int column) {
		if (row >= numberOfRows || row < 0) {
			throw new ArrayIndexOutOfBoundsException("Valid row value is [0, " + (numberOfRows - 1) + "], but was " + row);
		}
		if (column >= numberOfColumns || column < 0) {
			throw new ArrayIndexOutOfBoundsException("Valid column value is [0, " + (numberOfColumns - 1) + "], but was " + column);
		}
	}

	public Object getValue(int row, int column) {
		return values[getIndex(row, column)];
	}

	public int valueCount() {
		int count = 0;
		for (Object each : values) {
			if (each != null)
				count++;
		}
		return count;
	}

	public void removeValue(int row, int column) {
		if (values[getIndex(row, column)] == null) {
			return;
		}
		values[getIndex(row, column)] = null;
	}

	public void setCellRenderer(BCellRenderer cellRenderer) {
		this.cellRenderer = cellRenderer;
		initializeComponents();
	}

	private void initializeComponents() {
		for (int index = 0; index < values.length; index++) {
			if (getComponentCount() > index)
				super.remove(index);
			BComponent child = cellRenderer.getCellComponent(values[index], index);
			child.setStyleClass("gridcell");
			super.add(index, child, null);
		}
	}
	
	@Override
	public void add(BComponent child) {
		throw new NotImplementedException();
	}
	
	@Override
	public void add(BComponent child, Object constraints) {
		throw new NotImplementedException();
	}
	
	@Override
	public void add(int index, BComponent child) {
		throw new NotImplementedException();
	}
	
	@Override
	public void add(int index, BComponent child, Object constraints) {
		throw new NotImplementedException();
	}
	
	@Override
	public void remove(BComponent child) {
		throw new NotImplementedException();
	}
	
	@Override
	public void remove(int index) {
		throw new NotImplementedException();
	}
	
	@Override
	public void removeAll() {
		throw new NotImplementedException();
	}
}
