package com.aether.present.hud;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.CommandParser;


public class TestConsolePresenter {
	private CommandParser parser;
	private ConsoleView view;

	@Before
	public void setUp() {
		parser = EasyMock.createStrictMock(CommandParser.class);
		view = EasyMock.createStrictMock(ConsoleView.class);
		view.setPresenter((ConsolePresenter)EasyMock.anyObject());
	}
	
	@Test
	public void test_Initialization_of_view() throws Exception {
		EasyMock.replay(view, parser);
		new ConsolePresenter(view, parser);
		EasyMock.verify(view, parser);
	}
	
	@Test
	public void test_Send_command_to_parser() throws Exception {
		EasyMock.expect(parser.parse("something to do")).andReturn("the result");
		view.setResponse("the result");
		
		EasyMock.replay(view, parser);
		ConsolePresenter presenter = new ConsolePresenter(view, parser);
		presenter.processCommand("something to do");
		EasyMock.verify(view, parser);
	}
	
	@Test
	public void test_Toggle_visibility() throws Exception {
		view.setVisible(true);
		view.setVisible(false);
		
		EasyMock.replay(view, parser);
		ConsolePresenter presenter = new ConsolePresenter(view, parser);
		presenter.toggleVisibility();
		presenter.toggleVisibility();
		EasyMock.verify(view, parser);
	}
	
	@Test
	public void test_Activate_and_deactivate() throws Exception {
		view.activate();
		view.deactivate();
		
		EasyMock.replay(view, parser);
		ConsolePresenter presenter = new ConsolePresenter(view, parser);
		presenter.activate();
		presenter.deactivate();
		EasyMock.verify(view, parser);
	}
}
