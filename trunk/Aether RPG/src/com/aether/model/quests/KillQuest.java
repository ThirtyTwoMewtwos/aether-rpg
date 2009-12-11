
package com.aether.model.quests;


/*
 * KillQuest.java
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


public class KillQuest extends BaseEntry {

    private String enemyType;
    private int killsNeeded;
    private int killsMade;

    public KillQuest(String title, String description, int level_requirement, int kill_x_many, String enemy_type) {
        completeStatus(false);
        setName(title);
        setDescription(description);
        setLevelRequirement(level_requirement);
        setKillsNeeded(kill_x_many);
        setEnemyType(enemy_type);
    }

    public int getKillsMade()
    {
        return killsMade;
    }

    private void setKillsMade(int made)
    {
        killsMade = made;

        if(killsMade >= killsNeeded)
        {
            completeStatus(true);
            killsMade = killsNeeded;
        }
    }

    public int getKillsNeeded()
    {
        return killsNeeded;
    }

    private void setKillsNeeded(int need)
    {
        killsNeeded = need;
    }
    private void setEnemyType(String type)
    {
        enemyType = type;
    }
    public String getEnemyType()
    {
        return enemyType;
    }

    public String getStatus()
    {
        String status;
        if(!isComplete())
            status = killsMade + "/" + killsNeeded + " " + getEnemyType() + " killed.";
        else
            status = "Complete!";
        return status;
    }
    
    @Override
    public QuestType getQuestType() {
    	return QuestType.Hunt;
    }
}
