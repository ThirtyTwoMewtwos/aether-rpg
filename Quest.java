
public abstract class Quest 
{
    private String name, description;
    private int levelRequirement;
    
    public String getName()
    {
        return name;
    }
    public void setName(String questName)
    {
        name = questName;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String desc)
    {
        description = desc;
    }
    public int getLevelRequirement()
    {
        return levelRequirement;
    }
    public void setLevelRequirement(int req)
    {
        levelRequirement = req;
    }
}
