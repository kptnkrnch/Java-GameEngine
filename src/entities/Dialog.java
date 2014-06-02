package entities;

import java.util.ArrayList;

import engine.Entity;
import engine.EntityDictionary;
import engine.EntityFactory;
import engine.Main;
import engine.States;
import engine.World;

public class Dialog {
	
	public static ArrayList<String> text = null;
	public static int dialog_pos = -1;
	public static int NPC_ID = -1;
	
	public static void CreateDialog(World world, ArrayList<String> dialog, int id, int x, int y) {
		if (dialog != null) {
			text = new ArrayList<String>(dialog);
			dialog_pos = 0;
			NPC_ID = id;
			Entity entity = EntityFactory.CreateDialog(x, y);
			world.AddEntity(entity);
			Main.SetState(States.TALKING);
		}
	}
	
	public static boolean UpdateDialog(World world) {
		if (text != null) {
			if (dialog_pos + 1 != text.size()) {
				dialog_pos++;
				return true;
			} else {
				for (int i = 0; i < world.entities.size(); i++) {
					if (world.entities.get(i).type == EntityDictionary.DIALOG_BOX) {
						world.RemoveEntity(i);
						Dialog.NPC_ID = -1;
						Main.SetState(States.RUNNING);
						return false;
					}
				}
			}
		}
		return false;
	}
}
