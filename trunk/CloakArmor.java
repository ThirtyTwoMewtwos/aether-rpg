public class CloakArmor extends Equipment
{
    public CloakArmor(String arName,int arLevelRequirement)
    {
        this(arName,arLevelRequirement,0,0,0,0,0,0);
    }
    public CloakArmor(String arName,int arLevelRequirement,int str, int dex,int tough,int intel,int wis, int def)
    {
        setName(arName);
        setLevelRequirement(arLevelRequirement);
        setStrengthMod(str);
        setDexterityMod(dex);
        setToughnessMod(tough);
        setIntelligenceMod(intel);
        setWisdomMod(wis);
        setDefenseMod(def);
    }
    public int getSlot()
    {
        return Equipment.CLOAK;
    }
}