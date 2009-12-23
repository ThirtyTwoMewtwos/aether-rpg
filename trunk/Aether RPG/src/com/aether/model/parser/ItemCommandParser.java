package com.aether.model.parser;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.aether.model.CommandParser;
import com.aether.model.character.CharacterLocator;
import com.aether.model.items.EquipmentContainer;
import com.aether.model.items.GenericItem;

class ItemCommandParser implements CommandParser {

	private final CharacterLocator locator;

	public ItemCommandParser(CharacterLocator locator) {
		this.locator = locator;
	}

	@Override
	public String parse(String request) {
		String[] split = request.trim().split(" ");
		if (split[1].equals("add")) {
			if (split[2].equals("*")) {
				GenericItem item = new GenericItem("Sack", "A light soft-leather bag", "?", 1);
				addItemToPlayersBackpack(item);
				return "added";
			} else if (split[2].equals("jewelry_1")){
				// We should not be HARD code all the items in here.  We should create a factory to do this.
				GenericItem item = new GenericItem("Ring", "Simple, iron ring", "jewelry_1", 1);
				addItemToPlayersBackpack(item);
				return "added " + item.getName();
			} else {
				return "unknown item requested.";
			}
		}
		return null;
	}

	private void addItemToPlayersBackpack(GenericItem item) {
		EquipmentContainer backpack = locator.getPlayer().getEquipmentContainer();
		backpack.addItem(item);
	}

	@Override
	public void add(String command, CommandParser engine) {
		throw new NotImplementedException();
	}

}
