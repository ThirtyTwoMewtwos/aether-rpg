package com.aether.model.parser;

import org.gap.jseed.ServiceStore;

import com.aether.model.CommandParser;

public class ConsoleParserLoader {
	public ConsoleParserLoader(ServiceStore store) {
		store.bind(CommandParser.class, ConsoleCommandParser.class);
		store.bind(ItemCommandParser.class, ItemCommandParser.class);
		
		store.get(CommandParser.class).add("items", store.get(ItemCommandParser.class));
	}
}
