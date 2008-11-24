
public abstract class Equipment extends Item
{
    public final int HEAD = 0;
    public final int SHOULDER = 1;
    public final int HANDS = 2;
    public final int CHEST = 3;
    public final int GREAVES = 4;
    public final int BOOTS = 5;
    public final int CLOAK = 6;
    public final int BELT = 7;
    public final int TRINKET = 8;
    public final int NECKLACE = 9;
    public final int WEAPON = 10;
    public final int OFFHAND = 11;
    public final int TOME = 12;
    
    private int levelRequirement;
    
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
