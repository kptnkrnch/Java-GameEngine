package entities;

import java.util.ArrayList;

import engine.Entity;
import engine.World;

public class NPC {
	
	public static void Speak(World world, Entity NPC) {
		if (Dialog.NPC_ID != NPC.id) {
			Dialog.CreateDialog(world, NPC.dialog, NPC.id, NPC.x - 235, NPC.y - 140);
		} else {
			Dialog.UpdateDialog(world);
		}
	}
	
	public static Entity SetDialog(ArrayList<String> dialog, Entity NPC) {
		NPC.dialog = new ArrayList<String>(dialog);
		return NPC;
	}

}
