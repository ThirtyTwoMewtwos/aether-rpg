package com.aether.present.hud;

public interface PersonaView {
	public static final String PERSONA_ID = "hud.persona.view";
    
	public static final String NAME_FIELD = "Name";
	public static final String CLASS_FIELD = "Class";
	public static final String RACE_SEX_FIELD = "Race/Sex";
	public static final String EXPERIENCE_LEVEL = "Level";

    public static final String HEALTH_STATISTIC = "HP";
    public static final String MANA_STATISTIC = "MP";

	public static final String STRENGTH_STATISTIC = "STR";
	public static final String DEXTERITY_STATISTIC = "DEX";
	public static final String INTELLIGENCE_STATISTIC = "INT";
    public static final String TOUGHNESS_STATISTIC = "CON";
	public static final String WISDOM_STATISTIC = "WIS";

    public static final String DEFENSE_STATISTIC = "DEF";
    public static final String MELEE_STATISTIC = "MELEE";
    public static final String RANGE_STATISTIC = "RANGE";
    public static final String MAGIC_STATISTIC = "MAGIC";
    public static final String BLOCK_STATISTIC = "BLOCK";
    public static final String DISPELL_STATISTIC ="DISPELL";
    public static final String CRITICAL_STATISTIC = "CRIT";
    public static final String DODGE_STATISTIC = "DODGE";
    public static final String BIO_FIELD = "BIO";
	public static final String CURRENT_EXPERIENCE = "CURRENT_XP";

	void setPresenter(PersonaPresenter anyObject);

	void activate();
	
	void deactivate();

    void setName(String n);

    void setRaceAndSex(String r);

    void setClassification(String c);

    void setLevel(int level);

    void setXP(int currentXp, int nextLevel);

    void setHealth(int value, int max);

    void setMana(int value, int max);

    void setToughness(int i);

	void setStrength(int i);

	void setDexterity(int i);

	void setInteligence(int i);

	void setWisdom(int i);

	void setVisible(boolean b);

    void setDefense(int i);

    void setMelee(double d);

    void setRange(double d);

    void setMagic(double d);

    void setBlock(double d);

    void setCritical(double d);

    void setDodge(double d);

    void setDispell(double d);

	void setBio(String bio);
}
