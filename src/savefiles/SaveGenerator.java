package savefiles;

import items.Item;
import items.ItemInventory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import engine.Entity;
import engine.Main;
import exceptions.PlayerNotFoundException;

public class SaveGenerator {
	
	public static final String path = "res/savefiles";
	
	public static boolean SaveGame(String filename) {
		boolean isSuccess = true;
		File saveFile = new File(path + "/" + filename);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(saveFile));
			if (writer != null) {
				/*PLAYER INFORMATION*/
				Entity player = null;
				try {
					player = Main.world.GetEntity(Main.world.FindPlayer());
				} catch (PlayerNotFoundException e) {
				}
				writer.write("PlayerData:");
				writer.write("\n");
				if (player != null) {
					writer.write("\tx:" + player.x);
					writer.write("\n");
					writer.write("\ty:" + player.y);
					writer.write("\n");
					writer.write("\twidth:" + player.width);
					writer.write("\n");
					writer.write("\theight:" + player.height);
					writer.write("\n");
					writer.write("\tlocationid:" + Main.world.currentLocationID);
					writer.write("\n");
					writer.write("\tmaxhealth:" + player.c_max_health);
					writer.write("\n");
					writer.write("\thealth:" + player.c_health);
					writer.write("\n");
					writer.write("\tattack:" + player.c_attack);
					writer.write("\n");
					writer.write("\tdefence:" + player.c_defence);
					writer.write("\n");
					writer.write("\tspeed:" + player.c_speed);
					writer.write("\n");
					writer.write("\tcriticalchance:" + player.c_critical_chance);
					writer.write("\n");
					writer.write("\tdodgechance:" + player.c_dodge_chance);
					writer.write("\n");
					writer.write("\tlevel:" + player.c_level);
					writer.write("\n");
					writer.write("\texp:" + player.c_exp);
					writer.write("\n");
					writer.write("\texpnext:" + player.c_exp_next);
					writer.write("\n");
					writer.write("\tcriticaldamage:" + player.c_critical_damage);
					writer.write("\n");
					writer.write("\tcooldown:" + player.c_cooldown);
					writer.write("\n");
				}
				writer.write("EndPlayerData:");
				writer.write("\n");
				/*INVENTORY INFORMATION*/
				writer.write("InventoryData:");
				writer.write("\n");
				for (int i = 0; i < ItemInventory.GetItemCount(); i++) {
					Item item = ItemInventory.GetItem(i);
					if (item != null) {
						writer.write("\tBeginItem:");
						writer.write("\n");
						writer.write("\t\t id:" + item.id);
						writer.write("\n");
						writer.write("\t\t name:" + item.name);
						writer.write("\n");
						writer.write("\t\t type:" + item.type);
						writer.write("\n");
						writer.write("\t\t rarity:" + item.rarity);
						writer.write("\n");
						writer.write("\t\t armor:" + item.armor);
						writer.write("\n");
						writer.write("\t\t damage:" + item.damage);
						writer.write("\n");
						writer.write("\t\t criticalchance:" + item.criticalChance);
						writer.write("\n");
						writer.write("\t\t criticaldamage:" + item.criticalDamage);
						writer.write("\n");
						writer.write("\t\t freezingdamage:" + item.freezingDamage);
						writer.write("\n");
						writer.write("\t\t freezingchance:" + item.freezingChance);
						writer.write("\n");
						writer.write("\t\t poisondamage:" + item.poisonDamage);
						writer.write("\n");
						writer.write("\t\t poisonchance:" + item.poisonChance);
						writer.write("\n");
						writer.write("\t\t firedamage:" + item.fireDamage);
						writer.write("\n");
						writer.write("\t\t firechance:" + item.fireChance);
						writer.write("\n");
						writer.write("\t\t stunchance:" + item.stunChance);
						writer.write("\n");
						writer.write("\t\t stuntime:" + item.stunTime);
						writer.write("\n");
						writer.write("\t\t slowchance:" + item.slowChance);
						writer.write("\n");
						writer.write("\t\t slowtime:" + item.slowTime);
						writer.write("\n");
						writer.write("\t\t dodgechance:" + item.dodgeChance);
						writer.write("\n");
						writer.write("\t\t attackspeed:" + item.attackSpeed);
						writer.write("\n");
						writer.write("\t\t healingamount:" + item.healingAmount);
						writer.write("\n");
						writer.write("\t\t healingchance:" + item.healingChance);
						writer.write("\n");
						writer.write("\t\t maxuses:" + item.maxUses);
						writer.write("\n");
						writer.write("\t\t usecount:" + item.useCount);
						writer.write("\n");
						writer.write("\t\t fireresist:" + item.fireResist);
						writer.write("\n");
						writer.write("\t\t freezingresist:" + item.freezingResist);
						writer.write("\n");
						writer.write("\t\t poisonresist:" + item.poisonResist);
						writer.write("\n");
						writer.write("\t\t cureburningchance:" + item.cureBurningChance);
						writer.write("\n");
						writer.write("\t\t curefreezingchance:" + item.cureFreezingChance);
						writer.write("\n");
						writer.write("\t\t curepoisonchance:" + item.curePoisonChance);
						writer.write("\n");
						writer.write("\t\t abilitycooldowntime:" + item.abilityCooldownTime);
						writer.write("\n");
						writer.write("\t\t currentcooldowntime:" + item.currentCooldownTime);
						writer.write("\n");
						writer.write("\t\t inventoryposition:" + item.inventoryPosition);
						writer.write("\n");
						writer.write("\t\t isdroppable:" + item.isDroppable);
						writer.write("\n");
						writer.write("\t\t isquestitem:" + item.isQuestItem);
						writer.write("\n");
						writer.write("\t\t isbanked:" + item.isBanked);
						writer.write("\n");
						writer.write("\t\t issold:" + item.isSold);
						writer.write("\n");
						writer.write("\t\t isequipped:" + item.isEquipped);
						writer.write("\n");
						writer.write("\t\t value:" + item.value);
						writer.write("\n");
						writer.write("\tEndItem:");
						writer.write("\n");
					}
				}
				writer.write("EndInventoryData:");
				writer.write("\n");
				/*MAP INFORMATION*/
				writer.write("MapData:");
				writer.write("\n");
				writer.write("EndMapData:");
				writer.write("\n");
				/*NPC INFORMATION*/
				writer.write("NPCData:");
				writer.write("\n");
				writer.write("EndNPCData:");
				writer.write("\n");
				/*QUEST INFORMATION*/
				writer.write("QuestData:");
				writer.write("\n");
				writer.write("EndQuestData:");
				
				writer.close();
			}
		} catch (IOException e1) {
		}
		
		return isSuccess;
	}
	
}
