package com.aether.present.hud;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.aether.model.character.CharacterLocator;
import com.aether.model.character.CharacterSheet;
import com.aether.model.items.EquipmentContainer;


public class TestEquipmentPresenter {
	private EquipmentView view;
	private EquipmentContainer equipment;
	private CharacterLocator locator;
	private CharacterSheet character;

	@Before
	public void setUp() {
		view = EasyMock.createStrictMock(EquipmentView.class);
		locator = EasyMock.createStrictMock(CharacterLocator.class);
		character = EasyMock.createStrictMock(CharacterSheet.class);
		equipment = EasyMock.createStrictMock(EquipmentContainer.class);
		view.setPresenter((EquipmentPresenter)EasyMock.anyObject());
	}
	
	@Test
	public void test_Initialize_equipment_view() throws Exception {
		EasyMock.replay(view, locator);
		new EquipmentPresenter(view, locator);
		EasyMock.verify(view, locator);
	}
	
	@Test
	public void test_Show_equipment_carried() throws Exception {
		EasyMock.expect(locator.getPlayer()).andReturn(character);
		EasyMock.expect(character.getEquipmentContainer()).andReturn(equipment);
		EasyMock.expect(equipment.weightCarried()).andReturn(45);
		view.setWeightCarried("45");
		List<String> items = Arrays.asList("Rocket", "Knife", "Glue");
		EasyMock.expect(equipment.getAllItemNames()).andReturn(items);
		view.setItems(items);
		view.setVisible(true);
		view.setVisible(false);
		
		EasyMock.replay(view, locator, character, equipment);
		EquipmentPresenter presenter = new EquipmentPresenter(view, locator);
		presenter.toggleVisibility();
		presenter.toggleVisibility();
		EasyMock.verify(view, locator, character, equipment);
	}
}
