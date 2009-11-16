package com.aether.present.hud;

public interface PersonaView {
	public static final String PERSONA_ID = "hud.persona.view";
	public static final String STRENGTH_STATISTIC = "STR";
	public static final String DEXTERITY_STATISTIC = "DEX";
	public static final String INTELLIGENCE_STATISTIC = "INT";
	public static final String WISDOM_STATISTIC = "WIS";

	void setPresenter(PersonaPresenter anyObject);

	void activate();
	
	void deactivate();

	void setStrength(int i);

	void setDexterity(int i);

	void setInteligence(int i);

	void setWisdom(int i);

	void setVisible(boolean b);
}
