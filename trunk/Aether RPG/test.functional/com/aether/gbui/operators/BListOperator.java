package com.aether.gbui.operators;

import java.util.concurrent.Callable;

import com.aether.gbui.ComponentSearch;
import com.aether.gbui.Condition;
import com.jmex.bui.BComponent;
import com.jmex.bui.BList;
import com.jmex.bui.BToggleButton;
import com.jmex.bui.BWindow;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.MouseEvent;

public class BListOperator extends BComponentOperator {
	private BList list;

	public BListOperator(BWindow window, ComponentSearch searcher) {
		list = (BList)BComponentOperatorUtil.findWidget(window, searcher);
	}
	
	@Override
	protected BComponent getComponent() {
		return list;
	}

	public void select(final Object value) {
		final BToggleButton button = BComponentOperatorUtil.callInBuiThread(new Callable<BToggleButton>() {
			@Override
			public BToggleButton call() throws Exception {
				for (int i = 0; i < list.getComponentCount(); i++) {
					BComponent component = list.getComponent(i);
					BToggleButton toggle = (BToggleButton) component;
					if (value.equals(toggle.getText())) {
						return toggle;
					}
				}
				return null;
			}
		});
		if (button == null){
			return;
		}
		BComponentOperatorUtil.callInBuiThread(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				button.dispatchEvent(new MouseEvent(list, System.currentTimeMillis(), 0, MouseEvent.MOUSE_PRESSED, MouseEvent.BUTTON1, 0, 0));
				button.dispatchEvent(new MouseEvent(list, System.currentTimeMillis(), 0, MouseEvent.MOUSE_RELEASED, 0, 0));
				return null;
			}
		});
	}

	public Object getSelection() {
		return list.getSelectedValue();
	}

	public int size() {
		return BComponentOperatorUtil.callInBuiThread(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return list.getComponentCount();
			}
		});
	}

}
