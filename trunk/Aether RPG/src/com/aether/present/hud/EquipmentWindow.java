package com.aether.present.hud;

import java.util.Collection;
import java.util.concurrent.Callable;

import com.aether.present.hud.equipment.HeaderPanel;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BList;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

public class EquipmentWindow implements EquipmentView {
	private BWindow window;
	private HeaderPanel header;
	private BList items;

	public EquipmentWindow() {
		initWindow();
		
		header = new HeaderPanel();
		items = new BList();
		items.setName(EquipmentView.EQUIPMENT_LIST);
		
		window.add(header, new Rectangle(0, 295, 240, 45));
		window.add(items, new Rectangle(0, 100, 240, 180));
	}

	private void initWindow() {
		AbsoluteLayout layout = new AbsoluteLayout();
		window = new BDraggableWindow(BuiSystem.getStyle(), layout);
		window.setName(ID);
		window.setSize(250, 350);
		window.center();
		window.setVisible(false);
	}
	
	@Override
	public void setPresenter(EquipmentPresenter presenter) {
		
	}

	@Override
	public void setWeightCarried(String totalWeight) {
		header.setWeight(totalWeight);
	}
	
	@Override
	public void setItems(Collection<? extends Object> newItems) {
		items.removeAll();
		for (Object each : newItems) {
			items.addValue(each);
		}
	}
	
	@Override
	public void setVisible(boolean visibilityState) {
		window.setVisible(visibilityState);
	}
	
	@Override
	public void activate() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.addWindow(window);
				return null;
			}
		});
	}

	@Override
	public void deactivate() {
		GameTaskQueueManager.getManager().update(new Callable<Object>() {
			public Object call() throws Exception {
				BuiSystem.removeWindow(window);
				return null;
			}
		});
	}
}
