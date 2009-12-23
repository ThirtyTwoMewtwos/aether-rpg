package com.aether.present.hud;

import com.aether.model.character.CharacterLocator;
import com.aether.model.items.EquipmentContainer;
import com.aether.model.items.Item;

public class EquipmentPresenter implements ViewPresenter {

	private final CharacterLocator locator;
	private final EquipmentView view;
	private boolean isVisible = false;

	public EquipmentPresenter(EquipmentView view, CharacterLocator locator) {
		this.view = view;
		this.locator = locator;
		view.setPresenter(this);
	}

	@Override
	public void activate() {
		view.activate();
	}

	@Override
	public void deactivate() {
		view.deactivate();
	}

	@Override
	public void toggleVisibility() {
		isVisible = !isVisible;
		if (isVisible) {
			EquipmentContainer container = locator.getPlayer().getEquipmentContainer();
			view.setWeightCarried("" + container.weightCarried());
			placeItems(container);
		}
		view.setVisible(isVisible);
	}

	private void placeItems(EquipmentContainer container) {
		for (Item each : container.getAllItems()) {
			int loc = container.locationOf(each);
			int page = loc / 20;
			int row = (loc % 20) / 5;
			int col = (loc % 20) % 5;
			view.setItem(page, row, col, each);
		}
	}

	public void setLocation(int page, int row, int col, Item item) {
		EquipmentContainer container = locator.getPlayer().getEquipmentContainer();
		container.setLocation(page * 20 + row * 5 + col, item);
	}
}
