package com.aether.model.parser;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.CommandParser;

public class TestConsoleCommandParser {
	private ConsoleCommandParser commands;

	@Before
	public void setUp() {
		commands = new ConsoleCommandParser();
	}
	
	@Test
	public void test_Default_no_parsers() throws Exception {
		assertEquals("No available commands\n", commands.parse("?"));
	}
	
	@Test
	public void test_Get_available_commands() throws Exception {
		CommandParser itemsParser = EasyMock.createStrictMock(CommandParser.class);
		commands.add("items", itemsParser);
		assertEquals("Available commands:\n  items\n", commands.parse("?"));
	}
	
	@Test
	public void test_Using_non_existant_command() throws Exception {
		assertEquals("Unknown command 'items'", commands.parse("items"));
	}
	
	@Test
	public void test_Process_known_command() throws Exception {
		CommandParser itemsParser = EasyMock.createStrictMock(CommandParser.class);
		commands.add("items", itemsParser);
		EasyMock.expect(itemsParser.parse("items something")).andReturn("value");
		
		EasyMock.replay(itemsParser);
		assertEquals("value", commands.parse("items something"));
		EasyMock.verify(itemsParser);
	}
}
