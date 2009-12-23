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
import com.aether.model.items.Item;

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
		Item item1 = EasyMock.createMock("item1", Item.class);
		Item item2 = EasyMock.createMock("item2", Item.class);
		Item item3 = EasyMock.createMock("item3", Item.class);
		List items = Arrays.asList(item1, item2, item3);
		EasyMock.expect(equipment.getAllItems()).andReturn(items);
		EasyMock.expect(equipment.locationOf(item1)).andReturn(0);
		view.setItem(0, 0, 0, item1);
		EasyMock.expect(equipment.locationOf(item2)).andReturn(21);
		view.setItem(1, 0, 1, item2);
		EasyMock.expect(equipment.locationOf(item3)).andReturn(6);
		view.setItem(0, 1, 1, item3);
		view.setVisible(true);
		view.setVisible(false);
		
		EasyMock.replay(view, locator, character, equipment);
		EquipmentPresenter presenter = new EquipmentPresenter(view, locator);
		presenter.toggleVisibility();
		presenter.toggleVisibility();
		EasyMock.verify(view, locator, character, equipment);
	}
	
	@Test
	public void test_View_requests_a_new_location_for_item() throws Exception {
		Item item1 = EasyMock.createMock(Item.class);
		EasyMock.expect(locator.getPlayer()).andReturn(character);
		EasyMock.expect(character.getEquipmentContainer()).andReturn(equipment);
		equipment.setLocation(21, item1);
		
		EasyMock.replay(view, locator, character, equipment);
		EquipmentPresenter presenter = new EquipmentPresenter(view, locator);
		presenter.setLocation(1, 0, 1, item1);
		EasyMock.verify(view, locator, character, equipment);
	}
}
