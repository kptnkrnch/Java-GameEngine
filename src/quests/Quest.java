package quests;

import java.util.ArrayList;

public class Quest {
	public int id;
	public int giverNPCID;
	public ArrayList<Integer> requiredItems;
	public ArrayList<Integer> requiredItemCount;
	public ArrayList<Integer> currentItems; //each time the players inventory is changed, if requiredItems or requiredItemCount are not empty, this is updated.
	public ArrayList<Integer> requiredConversations; //NPC IDs
	public ArrayList<Integer> requiredLocationDiscoveries; //map IDs
	public ArrayList<Integer> requiredEnemyTypes;
	public ArrayList<Integer> requiredKillCount;
	public ArrayList<Integer> currentKillCount;
	public int rewardEXP;
	public int rewardMoney;
	public ArrayList<Integer> rewardItems;
	public int subsequentQuestID; //-1 is none
	public int requiredPriorQuestID; //-1 is none
	public int turnInNPCID; //-1 is no NPC
	public String recommendedLevel; 
	public int minimumLevel; //-1 is none
	public int maximumLevel; //-1 is none
	public String questName;
	public String questDescription;
	public String acquiredLocation; //optional
	public String turnInLocation;
	public boolean isActive;
	public ArrayList<Quest> innerQuests;
	public boolean isPrimaryQuest;
	public boolean isInnerQuest;
}
