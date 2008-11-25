/*
 * Hero.java
 * 
 * Copyright (c) 2008, Tyler Hoersch
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the University of Wisconsin Oshkosh nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDERS AND CONTRIBUTORS ''AS IS'' AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
import java.awt.Point;
import java.util.Vector;

public class Hero 
{
    private final int ATTRIBUTE_MODIFER = 2;
    private final double EXPIERENCE_MODIFER = 0.05;
    private final double ATTACK_MODIFER = 0.01;
    private final double CHANCE_MODIFER = 0.01;
    private final int MALE = 0;
    private final int FEMALE = 1;
    private final String[] SEXS = { "Male", "Female" };
    
    private int hp, mp, maxHP, maxMP;
    private int strength, dexterity, toughness, intelligence, wisdom, defense;
    private int gold, silver, copper;
    private int level, xp, xpToLevel;
    private double meleeAttack, rangedAttack, magicIntuative, blockChance, dispelChance;
    private double critChance, dodgeChance;
    private String name, bio, sex;
    private Point location;
    private Vector<Item> inventory;
    private Vector<Quest> questLog;
    private Equipment[] equiped;
    
    public Hero(String hName,String hBio, String sex)
    {
        level = 1;
        setName(hName);
        setBio(hBio);
        setSex(sex);
    }
    public void updateStats(Equipment equipment, boolean equip)
    {
        //equip case
        if(equip)
        {
            setStrength(getStrength() + equipment.getStrengthMod());
            setDexterity(getDexterity() + equipment.getDexterityMod());
            setToughness(getToughness() + equipment.getToughnessMod());
            setIntelligence(getIntelligence() + equipment.getIntelligenceMod());
            setWisdom(getWisdom() + equipment.getWisdomMod());
            setDefense(getDefense() + equipment.getDefenseMod());
        }
        //unquip case
        else
        {
            setStrength(getStrength() - equipment.getStrengthMod());
            setDexterity(getDexterity() - equipment.getDexterityMod());
            setToughness(getToughness() - equipment.getToughnessMod());
            setIntelligence(getIntelligence() - equipment.getIntelligenceMod());
            setWisdom(getWisdom() - equipment.getWisdomMod());
            setDefense(getDefense() - equipment.getDefenseMod()); 
        }
    }
    public void unequip(int index)
    {
        if(inventory.size() < inventory.capacity())
        {
            inventory.add(equiped[index]);
            updateStats(equiped[index],false);
            equiped[index] = null;
        }
    }
    public void equip(Equipment equipment)
    {
        if(equipment.getLevelRequirement() <= getLevel())
        {
            dropItem(equipment);
        
            if(equiped[equipment.getSlot()] == null)
                equiped[equipment.getSlot()] = equipment;
            else
            {
                pickUpItem(equiped[equipment.getSlot()]);
                equiped[equipment.getSlot()] = equipment;
            }
            
            updateStats(equipment, true);
        }
    }
    public void addQuest(Quest quest)
    {
        if(quest.getLevelRequirement() <= getLevel())
            if(questLog.size() < questLog.capacity())
                questLog.add(quest);
    }
    public int findQuestIndex(Quest quest)
    {
        int index = -1;
        if(questLog.contains(quest))
            index = questLog.indexOf(quest);
        
        return index;
    }
    public Quest findQuestByIndex(int index)
    {
        return questLog.elementAt(index);
    }
    public void abandonQuest(Quest quest)
    {
        if(questLog.contains(quest))
            questLog.remove(quest);
    }
    public void abandonQuest(int index)
    {
        if(questLog.elementAt(index) != null)
            questLog.remove(index);
    }
    public void pickUpItem(Item item)
    {
        if(inventory.size() < inventory.capacity())
            inventory.add(item);
    }
    public int findItemIndex(Item item)
    {
        int index = -1;
        if(inventory.contains(item))
            index = inventory.indexOf(item);
        
        return index;
    }
    public Item findItemByIndex(int index)
    {
        return inventory.elementAt(index);
    }
    public void dropItem(int index)
    {
        if(inventory.elementAt(index) != null)
            inventory.remove(index);
    }
    public void dropItem(Item item)
    {
        if(inventory.contains(item))
            inventory.remove(item);   
    }
    public String getName()
    {
        return name;
    }
    public void setName(String heroName)
    {
        name = heroName;
    }
    public String getBio()
    {
        return bio;
    }
    public void setBio(String heroBio)
    {
        bio = heroBio;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String s)
    {
        if(s.toLowerCase().equals("male"))
           sex = SEXS[MALE];
        else
           sex = SEXS[FEMALE];
    }
    public int getDefense()
    {
        return defense;
    }
    public void setDefense(int def)
    {
        defense = def;
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
    public int getGold()
    {
        return gold;
    }
    public void addGold(int g)
    {
        gold += g;
    }
    public int getSilver()
    {
        return silver;
    }
    public void addSilver(int s)
    {
        silver += s;
        
        if(silver >= 100)
        {
            addGold(1);
            silver = silver - 100;
            addSilver(silver);
        }
    }
    public int getCopper()
    {
        return copper;
    }
    public void addCopper(int c)
    {
        copper += c;
        
        if(copper >= 100)
        {
            addSilver(1);
            copper = copper - 100;
            addCopper(copper);
        }
    }
    public int getLevel()
    {
        return level;
    }
    public void levelUp()
    {
        level++;
        
        setStrength(getStrength() + ATTRIBUTE_MODIFER);
        setDexterity(getDexterity()  + ATTRIBUTE_MODIFER);
        setToughness(getToughness() + ATTRIBUTE_MODIFER);
        setIntelligence(getIntelligence() + ATTRIBUTE_MODIFER);
        setWisdom(getWisdom() + ATTRIBUTE_MODIFER);
        
        setMeleeAttack(getMeleeAttack() + ATTACK_MODIFER);
        setRangedAttack(getRangedAttack() + ATTACK_MODIFER);
        setMagicIntuative(getMagicIntuative() + ATTACK_MODIFER);
        
        setBlockChance(getBlockChance() + CHANCE_MODIFER);
        setDispelChance(getDispelChance() + CHANCE_MODIFER);
        setCritChance(getCritChance() + CHANCE_MODIFER);
        setDodgeChance(getDodgeChance() + CHANCE_MODIFER);
        
        setXPToLevel((int)(getXPToLevel() + EXPIERENCE_MODIFER));
        setXP(0);
    }
    public int getXPToLevel()
    {
        return xpToLevel;
    }
    public void setXPToLevel(int exp)
    {
        xpToLevel = exp;
    }
    public int getXP()
    {
        return xp;
    }
    public void setXP(int exp)
    {
        xp = exp;
    }
}
