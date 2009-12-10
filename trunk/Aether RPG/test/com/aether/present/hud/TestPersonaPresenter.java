package com.aether.present.hud;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.aether.model.character.CharacterLocator;
import com.aether.model.character.CharacterSheet;
import com.aether.model.character.Classification;
import com.aether.model.character.PlayerCharacter;
import com.aether.model.character.Sex;
import com.aether.model.character.Statistic;


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
		CharacterSheet characterSheet = new PlayerCharacter("Joe the big guy", "something cool", Sex.Male, Classification.Acolyte);
		EasyMock.expect(locator.getPlayer()).andReturn(characterSheet);
		view.setName("Joe the big guy");
		view.setBlock(characterSheet.getBlockChance());
		view.setClassification(characterSheet.getClassification().getName());
		view.setCritical(characterSheet.getCritChance());
		view.setDefense(characterSheet.getDefense());
		view.setDispell(characterSheet.getDispelChance());
		view.setDodge(characterSheet.getDodgeChance());
		view.setXP(characterSheet.getXP(), characterSheet.getXPToLevel());
		Statistic health = characterSheet.getHealth();
		view.setHealth(health.getValue(), health.getMax());
		Statistic mana = characterSheet.getMana();
		view.setMana(mana.getValue(), mana.getMax());
		view.setMagic(characterSheet.getMagicIntuative());
		view.setMelee(characterSheet.getMeleeAttack());
		view.setLevel(characterSheet.getLevel());
		view.setRange(characterSheet.getRangedAttack());
		view.setRaceAndSex(characterSheet.getRace().name() + "/" + characterSheet.getSex());
		view.setToughness(characterSheet.getToughness());
		view.setStrength(characterSheet.getStrength());
		view.setDexterity(characterSheet.getDexterity());
		view.setInteligence(10);
		view.setWisdom(10);
		view.setBio(characterSheet.getBio());
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
	
	@Test
	public void test_Changing_the_bio_is_saved_to_the_character_sheet() throws Exception {
		CharacterSheet characterSheet = new PlayerCharacter("Joe the big guy", "something cool", Sex.Male, Classification.Acolyte);
		EasyMock.expect(locator.getPlayer()).andReturn(characterSheet);
		
		EasyMock.replay(view, locator);
		PersonaPresenter presenter = new PersonaPresenter(view, locator);
		presenter.setBio("New bio");
		assertEquals("New bio", characterSheet.getBio());
		EasyMock.verify(view, locator);
	}
}
