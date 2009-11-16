package com.aether.present.hud;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.CharacterSheet;
import com.aether.model.character.CharacterLocator;
import com.aether.model.character.Classification;


public class TestPersonaPresenter {
	private PersonaView view;
	private CharacterLocator locator;

	@Before
	public void setUp() {
		view = EasyMock.createStrictMock(PersonaView.class);
		locator = EasyMock.createStrictMock(CharacterLocator.class);
		
		view.setPresenter((PersonaPresenter)EasyMock.anyObject());
	}
	
	@Test
	public void test_Initialize_the_view() throws Exception {
		EasyMock.replay(view, locator);
		new PersonaPresenter(view, locator);
		EasyMock.verify(view, locator);
	}
	
	@Test
	public void test_Toggle_view_shows_character_sheet_data() throws Exception {
		CharacterSheet characterSheet = new CharacterSheet("Joe the big guy", "something cool", "male", Classification.Acolyte);
		EasyMock.expect(locator.getPlayer()).andReturn(characterSheet);
		view.setStrength(10);
		view.setDexterity(10);
		view.setInteligence(10);
		view.setWisdom(10);
		view.setVisible(true);
		view.setVisible(false);
		
		EasyMock.replay(view, locator);
		PersonaPresenter presenter = new PersonaPresenter(view, locator);
		presenter.toggleVisibility();
		presenter.toggleVisibility();
		EasyMock.verify(view, locator);
	}
	
	@Test
	public void test_Activating_the_presenter_activates_the_view() throws Exception {
		view.activate();
		view.deactivate();
		
		EasyMock.replay(view, locator);
		PersonaPresenter presenter = new PersonaPresenter(view, locator);
		presenter.activate();
		presenter.deactivate();
		EasyMock.verify(view, locator);
	}
}
