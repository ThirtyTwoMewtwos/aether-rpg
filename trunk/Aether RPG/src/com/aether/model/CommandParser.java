package com.aether.model;

public interface CommandParser {
	String parse(String request);
	void add(String command, CommandParser engine);
}
