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
import java.awt.Color;
import java.util.Vector;
import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

public class Hero implements Serializable
{
    private final int ATTRIBUTE_MODIFER = 2;
    private final double EXPIERENCE_MODIFER = 0.05;
    private final double ATTACK_MODIFER = 0.01;
    private final double CHANCE_MODIFER = 0.01;
    private final double LARGE_STAT_MODIFIER = 0.05;
    private final double SMALL_STAT_MODIFIER = 0.02;
    private final static int MALE = 0;
    private final static int FEMALE = 1;
    private final static int REVENANT = 0;
    private final static int HUMAN = 1;
    private final static String[] SEXS = { "Male", "Female" };
    private final static String[] RACES = { "Revenant", "Human" };

    private int hp;
    private int mp;
    private int maxHP;
    private int maxMP;
    private int strength;
    private int dexterity;
    private int toughness;
    private int intelligence;
    private int wisdom;
    private int defense;
    private int gold;
    private int silver;
    private int copper;
    private int level;
    private int xp;
    private int xpToLevel;

    private double meleeAttack;
    private double rangedAttack;
    private double magicIntuative;
    private double blockChance;
    private double dispelChance;
    private double critChance;
    private double dodgeChance;

    private String name;
    private String bio;
    private String sex;

    private Point location;
    private Vector<Item> inventory;
    private Vector<Quest> questLog;
    private Equipment[] equiped;
    private Class role;
    
    public enum Class
    {
        HERETIC("Heretic", "Healer", RACES[REVENANT]),
        DARKGUARD("Dark Guard", "Tank", RACES[REVENANT]),
        NECROMANCER("Necromancer","Magic DPS", RACES[REVENANT]),
        BERSERKER("Berserker","Melee DPS", RACES[REVENANT]),
        SAGE("Sage","Healer", RACES[HUMAN]),
        HOLYKNIGHT("Holy Knight","Tank", RACES[HUMAN]),
        ACOLYTE("Acolyte","Magic DPS", RACES[HUMAN]),
        CRUSADER("Crusader","Melee DPS", RACES[HUMAN]);
        
        private String className;
        private String role;
        private String race;
        
        private Class(String strClass, String strRole, String strRace)
        {
            className = strClass;
            role = strRole;
            race = strRace;
        }
        public String getName()
        {
            return className;
        }
        public String getRole()
        {
            return role;
        }
        public String getRace()
        {
            return race;
        }
    }
    public Hero(String hName,String hBio, String sex,Class hClass)
    {
        level = 1;
        setXPToLevel(100);


        setStrength(10);
        setDexterity(10);
        setToughness(10);
        setIntelligence(10);
        setWisdom(10);
        setDefense(100);
        
        setCombatValues();
        
        setName(hName);
        setBio(hBio);
        setSex(sex);
        setHeroClass(hClass);
    }   
    private void updateStats(Equipment equipment, boolean equip)
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
        
        setCombatValues(); 
    }
    private void setCombatValues()
    {
        double melee = (getStrength() * (getStrength() * LARGE_STAT_MODIFIER)) + (getDexterity() * (getDexterity() * SMALL_STAT_MODIFIER));
        double magic = (getIntelligence() * (getIntelligence() * LARGE_STAT_MODIFIER)) + (getWisdom() * (getWisdom() * SMALL_STAT_MODIFIER));
        double ranged = (getDexterity() * (getDexterity() * LARGE_STAT_MODIFIER)) + (getStrength() * (getStrength() * SMALL_STAT_MODIFIER));
        double crit = (getDexterity() * (getDexterity() * LARGE_STAT_MODIFIER)) + (getStrength() * SMALL_STAT_MODIFIER);
        double dodge = (getToughness() * (getToughness() * LARGE_STAT_MODIFIER)) - (getDexterity()* (getDexterity() * SMALL_STAT_MODIFIER)) + (getStrength() * (getStrength() * SMALL_STAT_MODIFIER));
        double block = (getDexterity() * (getDexterity() * LARGE_STAT_MODIFIER)) - (getToughness()* (getToughness() * SMALL_STAT_MODIFIER)) + (getStrength() * (getStrength() * SMALL_STAT_MODIFIER));
        double dispel = (((getWisdom() + getIntelligence()) * LARGE_STAT_MODIFIER) * getWisdom()) - (getDexterity() * SMALL_STAT_MODIFIER) - (getStrength() * SMALL_STAT_MODIFIER);

        setMeleeAttack(melee);
        setMagicIntuative(magic);
        setRangedAttack(ranged);
        setCritChance(crit);
        setDodgeChance(dodge);
        setBlockChance(block);
        setDispelChance(dispel);
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
    public void setHeroClass(Hero.Class hClass)
    {
        role = hClass;
    }
    public Hero.Class getHeroClass()
    {
        return role;
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
        
        setCombatValues();
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

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.getName() + "\n");
        buffer.append(this.getLevel() + "\n");
        buffer.append(this.getSex() + "\n");
        buffer.append(this.getHeroClass() + "\n");
        buffer.append(this.getBio() + "\n");
        buffer.append(this.getHP() + "\n");
        buffer.append(this.getMaxHP() + "\n");
        buffer.append(this.getMP() + "\n");
        buffer.append(this.getMaxMP() + "\n");
        buffer.append(this.getStrength() + "\n");
        buffer.append(this.getDexterity() + "\n");
        buffer.append(this.getIntelligence() + "\n");
        buffer.append(this.getToughness() + "\n");
        buffer.append(this.getWisdom() + "\n");
        buffer.append(this.getDefense() + "\n");
        buffer.append(this.getBlockChance() + "\n");
        buffer.append(this.getCritChance() + "\n");
        buffer.append(this.getDispelChance() + "\n");
        buffer.append(this.getDodgeChance() + "\n");
        buffer.append(this.getMagicIntuative() + "\n");
        buffer.append(this.getMeleeAttack() + "\n");
        buffer.append(this.getRangedAttack() + "\n");
        buffer.append(this.getXP() + "\n");
        buffer.append(this.getXPToLevel() + "\n");
        buffer.append(this.getLocation() + "\n");
        buffer.append(this.getCopper() + "\n");
        buffer.append(this.getSilver() + "\n");
        buffer.append(this.getGold() + "\n");

        return buffer.toString();
    }

    public void exportCharToPDF()
    {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("heroes/"+ getName() +".pdf"));

            document.open();

            Paragraph title = new Paragraph(this.getName(), FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 255)));

            Chapter chapter = new Chapter(title, 1);
            chapter.setNumberDepth(0);

            Paragraph subTitle = new Paragraph("Level " + this.getLevel() + " " + this.getSex() + " " + this.getHeroClass(), FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
            Section section = chapter.addSection(subTitle);

            Paragraph content = new Paragraph("Biography: " + this.getBio() + "\n"
                                                + "HP: " + this.getHP() + "/" + this.getMaxHP() + "\n"
                                                + "MP: " + this.getMP() + "/" + this.getMaxMP() + "\n"
                                                + "Strength: " + this.getStrength() + "\n"
                                                + "Dexterity: " + this.getDexterity() + "\n"
                                                + "Intelligence: " + this.getIntelligence() + "\n"
                                                + "Toughness: " + this.getToughness() + "\n"
                                                + "Wisdom: " + this.getWisdom() + "\n"
                                                + "Strength: " + this.getStrength() + "\n"
                                                + "Defense: " + this.getDefense() + "\n"
                                                + "Melee Attack: " + this.getMeleeAttack() + "\n"
                                                + "Ranged Attck: " + this.getRangedAttack() + "\n"
                                                + "Block Chance: " + this.getBlockChance() + "\n"
                                                + "Critical Hit Chance: " + this.getCritChance() + "\n"
                                                + "Dispell Magic Chance: " + this.getDispelChance() + "\n"
                                                + "Dodge Chance: " + this.getDodgeChance() + "\n"
                                                + "Magic Intuativeness: " + this.getMagicIntuative() + "\n"
                                                + "Experience: " + this.getXP() + "/" + this.getXPToLevel() + "\n"
                                                + "Finances: " + "Gold: " +this.getGold() + "Silver: " + this.getSilver() + "Copper: " + this.getCopper() + "\n");


            section.add(content);

            document.add(chapter);
            document.close();
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
