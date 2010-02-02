package com.aether.model.character;

/*
 * PlayerCharacter.java
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
import java.awt.Color;
import java.awt.Point;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.Vector;

import com.aether.model.Equipment;
import com.aether.model.items.CharacterBackpack;
import com.aether.model.items.EquipmentContainer;
import com.aether.model.items.GenericItem;
import com.aether.model.items.Item;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfWriter;

// TODO Character sheet seems to be the central hub for all things player related.  
// we should try to break away the character sheet to it's logical elements for
// maintainability.  Quests as a service locator, xp as a separate module, attributes
// into it's own manager.  This would help the CharacterSheet to be more focused
// and make it into a more maintainable implementation of a container as opposed
// to the full implementation.  Character sheets, in rpg's are always broken up
// into it's separate containers/divisions.
public class PlayerCharacter implements CharacterSheet, Serializable {
	private static final long serialVersionUID = 1L;

	public final static int MOVE_MODIFIER = 2;

    private final int ATTRIBUTE_MODIFER = 2;
    private final double EXPIERENCE_MODIFER = 0.05;
    private final double ATTACK_MODIFER = 0.01;
    private final double CHANCE_MODIFER = 0.01;
    private final double LARGE_STAT_MODIFIER = 0.05;
    private final double SMALL_STAT_MODIFIER = 0.02;

    private Statistic health;
    private Statistic mana;
    
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
    private Sex sex;
    private Race race;

    private Point location;
    private Vector<Item> inventory;
    private Equipment[] equiped;
    private Classification role;

	private CharacterBackpack backpack;
    
    public PlayerCharacter(String hName,String hBio, Sex sex, Classification hClass) {
    	backpack = new CharacterBackpack();
//    	backpack.addItem(new GenericItem("Satchel", "Used for carrying items", "?", 1));
//    	backpack.addItem(new GenericItem("Broken Sword", "Round thing to look at.  Kinda pretty", "weapons_1", 2));
    	
        level = 1;
        setXPToLevel(100);
        setLocation(new Point(20,20));

        setStrength(10);
        setDexterity(10);
        setToughness(10);
        setIntelligence(10);
        setWisdom(10);
        defense = 100;
        
        setCombatValues();
        
        setName(hName);
        setBio(hBio);
        this.sex = sex;
        setHeroClass(hClass);
        
        health = new Statistic(10);
        mana = new Statistic(10);
        mana.setValue(2);
    } 
    
    private void updateStats(Equipment equipment, boolean equip)
    {
        if(equip)
        {
            setStrength(getStrength() + equipment.getStrengthMod());
            setDexterity(getDexterity() + equipment.getDexterityMod());
            setToughness(getToughness() + equipment.getToughnessMod());
            setIntelligence(getIntelligence() + equipment.getIntelligenceMod());
            setWisdom(getWisdom() + equipment.getWisdomMod());
            defense = getDefense() + equipment.getDefenseMod();
        }
        else
        {
            setStrength(getStrength() - equipment.getStrengthMod());
            setDexterity(getDexterity() - equipment.getDexterityMod());
            setToughness(getToughness() - equipment.getToughnessMod());
            setIntelligence(getIntelligence() - equipment.getIntelligenceMod());
            setWisdom(getWisdom() - equipment.getWisdomMod());
            defense = getDefense() - equipment.getDefenseMod(); 
        }
        
        setCombatValues(); 
    }
    
    private void setCombatValues()
    {
        meleeAttack = (getStrength() * (getStrength() * LARGE_STAT_MODIFIER)) + (getDexterity() * (getDexterity() * SMALL_STAT_MODIFIER));
        magicIntuative = (getIntelligence() * (getIntelligence() * LARGE_STAT_MODIFIER)) + (getWisdom() * (getWisdom() * SMALL_STAT_MODIFIER));
        rangedAttack = (getDexterity() * (getDexterity() * LARGE_STAT_MODIFIER)) + (getStrength() * (getStrength() * SMALL_STAT_MODIFIER));
        critChance = (getDexterity() * (getDexterity() * LARGE_STAT_MODIFIER)) + (getStrength() * SMALL_STAT_MODIFIER);
        double dodge = (getToughness() * (getToughness() * LARGE_STAT_MODIFIER)) - (getDexterity()* (getDexterity() * SMALL_STAT_MODIFIER)) + (getStrength() * (getStrength() * SMALL_STAT_MODIFIER));
        blockChance = (getDexterity() * (getDexterity() * LARGE_STAT_MODIFIER)) - (getToughness()* (getToughness() * SMALL_STAT_MODIFIER)) + (getStrength() * (getStrength() * SMALL_STAT_MODIFIER));
        double dispel = (((getWisdom() + getIntelligence()) * LARGE_STAT_MODIFIER) * getWisdom()) - (getDexterity() * SMALL_STAT_MODIFIER) - (getStrength() * SMALL_STAT_MODIFIER);

        setDodgeChance(dodge);
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

	@Override
	public EquipmentContainer getEquipmentContainer() {
		return backpack;
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
    
    public Sex getSex()
    {
        return sex;
    }
    
    public void setRace(Race hRace)
    {
        race = hRace;
    }
    public Race getRace()
    {
        return race;
    }
    public void setHeroClass(Classification hClass)
    {
        role = hClass;
        setRace(role.getRace());
    }
    public Classification getClassification()
    {
        return role;
    }
    
    public int getDefense()
    {
        return defense;
    }
    
    public double getCritChance() {
        return statRound(critChance);
    }
    public double getDodgeChance() {
        return statRound(dodgeChance);
    }
    public void setDodgeChance(double dodge)
    {
        dodgeChance = dodge;
    }
    public double getMeleeAttack()
    {
        return statRound(meleeAttack);
    }
    public double getRangedAttack()
    {
        return statRound(rangedAttack);
    }
    public double getMagicIntuative()
    {
        return statRound(magicIntuative);
    }
    public double getBlockChance() {
        return statRound(blockChance);
    }

    public double getDispelChance() {
        return statRound(dispelChance);
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
    
    public Statistic getHealth() {
    	return health;
    }
    
	public Statistic getMana() {
		return mana;
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
    public int getLevel() {
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
        
        meleeAttack = getMeleeAttack() + ATTACK_MODIFER;
        rangedAttack = getRangedAttack() + ATTACK_MODIFER;
        magicIntuative = getMagicIntuative() + ATTACK_MODIFER;
        
        blockChance = (getBlockChance() + CHANCE_MODIFER);
        setDispelChance(getDispelChance() + CHANCE_MODIFER);
        critChance = (getCritChance() + CHANCE_MODIFER);
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

    public double format(Double attribute)
    {
        int decimal_place = 2;
        java.math.BigDecimal bd = new java.math.BigDecimal(attribute);
        bd = bd.setScale(decimal_place,java.math.BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }
    public String format(String incString)
    {
        String formatted = "";
        String bytes;
        int count = 0;
        StringTokenizer st = new StringTokenizer(incString);

        while(st.hasMoreTokens())
        {
            if(count < 32)
            {
                bytes = st.nextToken();
                count += bytes.length();
                formatted += bytes + " ";
            }
            else
            {
                count = 0;
                bytes = st.nextToken();
                count += bytes.length();
                formatted += "\n" + bytes + " ";
            }
        }


        return formatted;
    }

    double statRound(double d) {
        java.text.DecimalFormat twoDForm = new java.text.DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof CharacterSheet) {
    		CharacterSheet sheet = (CharacterSheet) obj;
    		return sheet.toString().equals(toString());
    	}
    	return false;
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getName() + "\n");
        buffer.append("                " + "\n");
        buffer.append("Level: " + getLevel() + "\n");
        buffer.append(getRace() + "\n");
        buffer.append(getSex() + "\n");
        buffer.append( getClassification() + "\n");
        buffer.append("Bio: " + format(getBio()) + "\n\n");
        buffer.append("HP: " + getHealth().getValue() + "/" + getHealth().getMax() +"\n");
        buffer.append("MP: " + getMana().getMax() + "/" + getMana().getValue() +"\n\n");
        buffer.append("Strength: " + getStrength() + "\n");
        buffer.append("Dexterity: " + getDexterity() + "\n");
        buffer.append("Intelligence: " + getIntelligence() + "\n");
        buffer.append("Toughness" + getToughness() + "\n");
        buffer.append("Wisdom: " + getWisdom() + "\n");
        buffer.append("Defense: " + getDefense() + "\n");
        buffer.append("Block: " + format(getBlockChance()) + "%\n");
        buffer.append("Critical: " + format(getCritChance()) + "%\n");
        buffer.append("Dispell: " + format(getDispelChance()) + "%\n");
        buffer.append("Dodge: " + format(getDodgeChance()) + "%\n\n");
        buffer.append("Magic Intuative: " + getMagicIntuative() + "\n");
        buffer.append("Melee Attack: " + getMeleeAttack() + "\n");
        buffer.append("Ranged Attack: " + getRangedAttack() + "\n\n");
        buffer.append("Experience: " + getXP() + "/" + getXPToLevel() + "\n");
        buffer.append("Located: " + getLocation() + "\n");
        buffer.append("Gold: " + getGold() + "\n");
        buffer.append("Silver: " + getSilver() + "\n");
        buffer.append("Copper: " + getCopper() + "\n");

        return buffer.toString();
    }

    public void exportCharToPDF()
    {
        try{
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream("heroes/"+ getName() +".pdf"));

            document.open();

            Paragraph title = new Paragraph(getName(), FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 255)));

            Chapter chapter = new Chapter(title, 1);
            chapter.setNumberDepth(0);

            Paragraph subTitle = new Paragraph("Level " + getLevel() + " " + getRace() + " " + getSex() + " " + getClassification(), FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
            Section section = chapter.addSection(subTitle);

            Paragraph content = new Paragraph("Biography: " + getBio() + "\n"
                                                + "HP: " + getHealth().getValue() + "/" + getHealth().getMax() + "\n"
                                                + "MP: " + getMana().getMax() + "/" + getMana().getValue() + "\n"
                                                + "Strength: " + getStrength() + "\n"
                                                + "Dexterity: " + getDexterity() + "\n"
                                                + "Intelligence: " + getIntelligence() + "\n"
                                                + "Toughness: " + getToughness() + "\n"
                                                + "Wisdom: " + getWisdom() + "\n"
                                                + "Defense: " + getDefense() + "\n"
                                                + "Melee Attack: " + getMeleeAttack() + "\n"
                                                + "Ranged Attck: " + getRangedAttack() + "\n"
                                                + "Block Chance: " + getBlockChance() + "\n"
                                                + "Critical Hit Chance: " + getCritChance() + "\n"
                                                + "Dispell Magic Chance: " + getDispelChance() + "\n"
                                                + "Dodge Chance: " + getDodgeChance() + "\n"
                                                + "Magic Intuativeness: " + getMagicIntuative() + "\n"
                                                + "Experience: " + getXP() + "/" + getXPToLevel() + "\n"
                                                + "Finances: " + "Gold: " +getGold() + "Silver: " + getSilver() + "Copper: " + getCopper() + "\n");


            section.add(content);

            document.add(chapter);
            document.close();
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}