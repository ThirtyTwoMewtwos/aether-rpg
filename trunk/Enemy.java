import java.awt.Point;
import java.util.*;

public class Enemy 
{
    private final int RESPAWN_TIMER = 1 * 60 * 1000;
    private int hp, mp, maxHP, maxMP;
    private int strength, dexterity, toughness, intelligence, wisdom, defense;
    private int level, expWorth;
    private double meleeAttack, rangedAttack, magicIntuative, blockChance, dispelChance;
    private double critChance, dodgeChance;
    private String name;
    private Point location;
    private Timer timer;
    
    public Enemy(String name,int enHP,int enMP, int str, int dex, int tough, int intel, int wis, int def, int lvl, int xpWorth, Point loc)
    {
        setName(name);
        setMaxHP(enHP);
        setMaxMP(enMP);
        setHP(enHP);
        setMP(enMP);
        setStrength(str);
        setDexterity(dex);
        setToughness(tough);
        setIntelligence(intel);
        setWisdom(wis);
        setDefense(def);
        setLevel(lvl);
        setExpWorth(xpWorth);
        setLocation(loc);
    }
    public Enemy(Enemy en)
    {
        setMaxHP(en.getMaxHP());
        setMaxMP(en.getMaxMP());
        setHP(en.getMaxHP());
        setMP(en.getMP());
        setStrength(en.getStrength());
        setDexterity(en.getDexterity());
        setToughness(en.getToughness());
        setIntelligence(en.getIntelligence());
        setWisdom(en.getWisdom());
        setDefense(en.getDefense());
        setLevel(en.getLevel());
        setExpWorth(en.getExpWorth());
        setMeleeAttack(en.getMeleeAttack());
        setRangedAttack(en.getRangedAttack());
        setMagicIntuative(en.getMagicIntuative());
        setBlockChance(en.getBlockChance());
        setDispelChance(en.getDispelChance());
        setCritChance(en.getCritChance());
        setDodgeChance(en.getDodgeChance());
        setName(en.getName());
        setLocation(en.getLocation());
    }
    public void Death()
    {
        timer = new Timer();
        timer.schedule(new Respawn(this), RESPAWN_TIMER);    
    }
    class Respawn extends TimerTask  
    {
        Enemy enemy;
        public Respawn(Enemy en)
        {
            run(en);
        }
        public void run(Enemy en)
        {
            enemy = en;
            run();
        }
        public void run ()   
        {
            new Enemy(enemy);
            timer.cancel ();
        }
    }
    public int getDefense()
    {
        return defense;
    }
    public void setDefense(int def)
    {
        defense = def;
    }
    public int getExpWorth()
    {
        return expWorth;
    }
    public void setExpWorth(int xp)
    {
        expWorth = xp;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String heroName)
    {
        name = heroName;
    }
    public double getCritChance()
    {
        return critChance;
    }
    public void setCritChance(double crit)
    {
        critChance = crit;
    }
    public double getDodgeChance()
    {
        return dodgeChance;
    }
    public void setDodgeChance(double dodge)
    {
        dodgeChance = dodge;
    }
    public double getMeleeAttack()
    {
        return meleeAttack;
    }
    public void setMeleeAttack(double melee)
    {
        meleeAttack = melee;
    }
    public double getRangedAttack()
    {
        return rangedAttack;
    }
    public void setRangedAttack(double ranged)
    {
        rangedAttack = ranged;
    }
    public double getMagicIntuative()
    {
        return magicIntuative;
    }
    public void setMagicIntuative(double magic)
    {
        magicIntuative = magic;
    }
    public double getBlockChance()
    {
        return blockChance;
    }
    public void setBlockChance(double block)
    {
        blockChance = block;
    }
    public double getDispelChance()
    {
        return dispelChance;
    }
    public void setDispelChance(double dispel)
    {
        dispelChance = dispel;
    }
    public int getStrength()
    {
        return strength;
    }
    public void setStrength(int str)
    {
        strength = str;
    }
    public int getDexterity()
    {
        return dexterity;
    }
    public void setDexterity(int dex)
    {
        dexterity = dex;
    }
    public int getToughness()
    {
        return toughness;
    }
    public void setToughness(int tough)
    {
        toughness = tough;
    }
    public int getIntelligence()
    {
        return intelligence;
    }
    public void setIntelligence(int intel)
    {
        intelligence = intel;
    }
    public int getWisdom()
    {
        return wisdom;
    }
    public void setWisdom(int wis)
    {
        wisdom = wis;
    }
    public int getHP()
    {
        return hp;
    }
    public void setHP(int heroHP)
    {
        hp = heroHP;
    }
    public int getMP()
    {
        return mp;
    }
    public void setMP(int heroMP)
    {
        mp = heroMP;
    }
    public int getMaxHP()
    {
        return maxHP;
    }
    public void setMaxHP(int heroMax)
    {
        maxHP = heroMax;
    }
    public int getMaxMP()
    {
        return maxMP;
    }
    public void setMaxMP(int heroMax)
    {
        maxMP = heroMax;
    }
    public Point getLocation()
    {
        return location;
    }
    public void setLocation(Point newLoc)
    {
        location = newLoc;
    }
    public int getLevel()
    {
        return level;
    }
    public void setLevel(int lvl)
    {
        level = lvl;
    }
}
