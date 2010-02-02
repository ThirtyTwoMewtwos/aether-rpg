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
		if (split.length > 1) {
			return parseItemsCommand(split);
		}
		return "Unknown items command given.";
	}

	private String parseItemsCommand(String[] split) {
		if (split[1].equals("add")) {
			return createItem(split);
		} else if (split[1].equals("?")) {
			return "\nUsage: items <subcommands> <item type>\n" +
			       "  \n" + 
			       "Available subcommands\n" +
			       "  add   Used to add an item to the player backpack\n" +
			       "  \n" +
			       "Available Item types\n" +
			       "  *           A random Item\n" + 
			       "  jewlery_1   An iron ring\n" + 
			       "  \n" + 
			       "Typing 'items ?' will always return this help for the item commands";
		} else {
			return "Unknown items command given.";
		}
	}

	private String createItem(String[] split) {
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

	private void addItemToPlayersBackpack(GenericItem item) {
		EquipmentContainer backpack = locator.getPlayer().getEquipmentContainer();
		backpack.addItem(item);
	}

	@Override
	public void add(String command, CommandParser engine) {
		throw new NotImplementedException();
	}

}
