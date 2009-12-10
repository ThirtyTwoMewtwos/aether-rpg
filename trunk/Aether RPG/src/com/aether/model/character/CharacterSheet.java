package com.aether.model.character;

import com.aether.model.items.EquipmentContainer;

public interface CharacterSheet {
	String getName();

	String getBio();

	void setBio(String heroBio);

	Sex getSex();

	Race getRace();

	Classification getClassification();

	int getDefense();

	double getCritChance();

	double getDodgeChance();

	double getMeleeAttack();

	double getRangedAttack();

	double getMagicIntuative();

	double getBlockChance();

	double getDispelChance();

	int getStrength();

	int getDexterity();

	int getToughness();

	int getIntelligence();

	int getWisdom();

	Statistic getHealth();

	Statistic getMana();

	int getXP();
	
	int getXPToLevel();
	
	int getLevel();
	
	EquipmentContainer getEquipmentContainer();
}
