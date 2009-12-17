package com.aether.present.hud;

import java.awt.Image;
import java.util.Collection;
import java.util.concurrent.Callable;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.gbui.BCellRenderer;
import com.aether.gbui.BGridContainer;
import com.aether.model.quests.QuestType;
import com.aether.model.quests.QuestTypeImage;
import com.aether.present.hud.equipment.HeaderPanel;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BComponent;
import com.jmex.bui.BImage;
import com.jmex.bui.BLabel;
import com.jmex.bui.BWindow;
import com.jmex.bui.BuiSystem;
import com.jmex.bui.headlessWindows.BDraggableWindow;
import com.jmex.bui.icon.BlankIcon;
import com.jmex.bui.icon.ImageIcon;
import com.jmex.bui.layout.AbsoluteLayout;
import com.jmex.bui.util.Rectangle;

@Singleton
class EquipmentWindow implements EquipmentView {
	private BWindow window;
	private HeaderPanel header;
	private BGridContainer items;

	public EquipmentWindow() {
		initWindow();

		header = new HeaderPanel();
		items = new BGridContainer(4, 6);
		items.setName(EquipmentView.EQUIPMENT_LIST);
		items.setCellRenderer(new BCellRenderer() {
			@Override
			public BComponent getCellComponent(Object value, int i) {
				String text = (value == null ? "" : value.toString());
				BLabel label = null;
				if (value == null) {
					label = new BLabel(new BlankIcon(24, 24));
				} else {
					Image image = QuestTypeImage.getImage(QuestType.Hunt);
					BImage bImage = new BImage(image);
					label = new BLabel(new ImageIcon(bImage));
					label.setTooltipText(text);
					label.setTooltipRelativeToMouse(true);
				}
				return label;
			}
		});

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
		int column = 0;
		for (Object each : newItems) {
			items.setValue(0, column++, each);
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
