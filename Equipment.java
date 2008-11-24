/*
 * Equipment.java
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
