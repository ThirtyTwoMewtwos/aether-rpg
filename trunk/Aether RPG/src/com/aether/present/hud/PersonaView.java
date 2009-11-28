package com.aether.present.hud;

public interface PersonaView {
	public static final String PERSONA_ID = "hud.persona.view";
    

	public static final String NAME_FIELD = "Name";
	public static final String CLASS_FIELD = "Class";
	public static final String RACE_SEX_FIELD = "Race/Sex";

	public static final String LEVEL_STATISTIC = "LEVEL";
    public static final String EXP_STATISTIC = "EXP";
    public static final String NEXTLEVEL_STATISTIC = "NEXT";
    public static final String HP_STATISTIC = "HP";
    public static final String MP_STATISTIC = "MP";

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
    public static final String DISPELL_STATISTIC ="DSPELL";
    public static final String CRITICAL_STATISTIC = "CRIT";
    public static final String DODGE_STATISTIC = "DODGE";

	void setPresenter(PersonaPresenter anyObject);

	void activate();
	
	void deactivate();

    void setName(String n);

    void setRaceAndSex(String r);

    void setClassification(String c);

    void setSex(String s);
    
    void setLevel(int i);

    void setXP(int i);

    void setNextLevel(int i);

    void setHP(String s);

    void setMP(String s);

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

    void setCrit(double d);

    void setDodge(double d);

    void setDispell(double d);
}
