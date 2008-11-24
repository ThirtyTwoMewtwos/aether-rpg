
public abstract class Equipment extends Item
{
    public final static int HEAD = 0;
    public final static int SHOULDER = 1;
    public final static int HANDS = 2;
    public final static int CHEST = 3;
    public final static int GREAVES = 4;
    public final static int BOOTS = 5;
    public final static int CLOAK = 6;
    public final static int BELT = 7;
    public final static int TRINKET = 8;
    public final static int NECKLACE = 9;
    public final static int WEAPON = 10;
    public final static int OFFHAND = 11;
    public final static int TOME = 12;
    
    private int strength, dexterity, toughness, intelligence, wisdom, defense;
    private int levelRequirement;
    
    public int getStrengthMod()
    {
        return strength;
    }
    public void setStrengthMod(int str)
    {
        strength = str;
    }
    public int getDexterityMod()
    {
        return dexterity;
    }
    public void setDexterityMod(int dex)
    {
        dexterity = dex;
    }
    public int getToughnessMod()
    {
        return toughness;
    }
    public void setToughnessMod(int tough)
    {
        toughness = tough;
    }
    public int getIntelligenceMod()
    {
        return intelligence;
    }
    public void setIntelligenceMod(int intel)
    {
        intelligence = intel;
    }
    public int getWisdomMod()
    {
        return wisdom;
    }
    public void setWisdomMod(int wis)
    {
        wisdom = wis;
    }
    public int getDefenseMod()
    {
        return defense;
    }
    public void setDefenseMod(int def)
    {
        defense = def;
    }
    public int getLevelRequirement()
    {
        return levelRequirement;
    }
    public void setLevelRequirement(int req)
    {
        levelRequirement = req;
    }
    public abstract int getSlot();
}
