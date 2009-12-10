package com.aether.present.hud;

import com.aether.model.character.CharacterLocator;
import com.aether.model.items.EquipmentContainer;

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
			view.setItems(container.getAllItemNames());
		}
		view.setVisible(isVisible);
	}

}
