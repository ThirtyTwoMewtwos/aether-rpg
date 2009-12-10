package com.aether.present.hud;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.present.hud.HUDViewLocator.View;
import com.aether.test.AssertThrows;
import com.aether.test.CodeBlock;

public class TestInGameHUDWindowLocator {
	private ViewPresenter view;
	private InGameHUDWindowLocator locator;

	@Before
	public void setUp() {
		view = EasyMock.createStrictMock("ViewPresenter", ViewPresenter.class);
		locator = new InGameHUDWindowLocator();
	}
	
	@Test
	public void test_Loading_of_views_can_be_toggled() throws Exception {
		view.toggleVisibility();
		
		EasyMock.replay(view);
		locator.attach(View.PERSONA, view);
		locator.toggleViewVisibility(View.PERSONA);
		EasyMock.verify(view);
	}
	
	@Test
	public void test_View_is_not_bound_throw_exception() throws Exception {
		AssertThrows.assertThrows(IllegalStateException.class, new CodeBlock() {
			@Override
			public void execute() throws Exception {
				locator.toggleViewVisibility(View.PERSONA);
			}
		});
	}
	
	@Test
	public void test_Activate_locator_activates_all_windows_bound() throws Exception {
		view.activate();
		view.deactivate();
		
		EasyMock.replay(view);
		locator.attach(View.PERSONA, view);
		locator.activate();
		locator.deactivate();
		EasyMock.verify(view);
	}
}
