package com.aether.model.parser;

import java.util.Hashtable;
import java.util.Map;

import org.gap.jseed.injection.annotation.Singleton;

import com.aether.model.CommandParser;

@Singleton
class ConsoleCommandParser implements CommandParser {
	private static final String QUERY_AVAILABLE_COMMANDS = "?";
	private Map<String, CommandParser> parsers = new Hashtable<String, CommandParser>();

	public String parse(String request) {
		if (QUERY_AVAILABLE_COMMANDS.equals(request)) {
			return queryAvailableCommands();
		} 
		String[] tokens = request.split(" ");
		if (parsers.containsKey(tokens[0])) {
			return parsers.get(tokens[0]).parse(request);
		}
		return "Unknown command '" + tokens[0] + "'";		
	}

	private String queryAvailableCommands() {
		if(parsers.isEmpty()) {
			return "No available commands\n";
		} else {
			String commands = getAvailableCommands();
			
			return "Available commands:\n" + commands;
		}
	}

	private String getAvailableCommands() {
		String result = "";
		for (String each : parsers.keySet()) {
			result += "  " + each + "\n";
		}
		return result;
	}

	public void add(String command, CommandParser engine) {
		parsers.put(command, engine);
	}

}
