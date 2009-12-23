package com.aether.present.hud.equipment;

import java.awt.Image;
import java.util.Collection;
import java.util.concurrent.Callable;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.gbui.BCellRenderer;
import com.aether.gbui.BGridContainer;
import com.aether.gbui.bss.BssWriter;
import com.aether.model.items.Item;
import com.aether.present.hud.EquipmentPresenter;
import com.aether.present.hud.EquipmentView;
import com.jme.util.GameTaskQueueManager;
import com.jmex.bui.BButton;
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
public class EquipmentWindow implements EquipmentView {
	private BWindow window;
	private HeaderPanel header;
	private BGridContainer items;
	private EquipmentPresenter presenter;

	public EquipmentWindow() {
		initWindow();

		header = new HeaderPanel();
		items = new BGridContainer(4, 5);
		items.setName(EquipmentView.EQUIPMENT_LIST);
		items.setCellRenderer(new ItemCellRenderer());
		items.setDragEnabled(true);
			
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
		this.presenter = presenter;
	}

	@Override
	public void setWeightCarried(String totalWeight) {
		header.setWeight(totalWeight);
	}
	
	@Override
	public void setItem(int page, int row, int col, Item item) {
		items.setValue(row, col, item);
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
	
	private final class ItemCellRenderer implements BCellRenderer {
		@Override
		public BComponent getCellComponent(Object value, int i) {
			Item item = (Item)value;
			String text = (value == null ? "" : item.getName());
			if (value == null) {
				return new BLabel(new BlankIcon(32, 32));
			} else {
				return createItemLabel(item, text);
			}
		}

		private BLabel createItemLabel(Item item, String text) {
			BLabel label;
			Image image = ItemTypeImage.getImage(item.getItemType());
			BImage bImage = new BImage(image);
			label = new BButton(new ImageIcon(bImage), "") {
				@Override
				protected BComponent createTooltipComponent(String tiptext) {
					return new BLabel(tiptext, BssWriter.StyleType.tooltip_label.name());
				}
			};
			label.setTooltipText(text);
			label.setTooltipRelativeToMouse(true);
			return label;
		}
	}
}
