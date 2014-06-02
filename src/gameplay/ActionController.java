package gameplay;

import java.util.HashMap;

import engine.Entity;
import engine.EntityDictionary;
import engine.Main;
import engine.States;
import engine.World;
import entities.Camera;
import entities.Player;

public class ActionController {
	
	public static void HandleEntityAction(World world, HashMap<String, Boolean> input, int fps_scaler) {
		if (input != null && !input.isEmpty()) {
			for (int i = 0; i < world.GetEntityCount(); i++) {
				if (input.get("KEY_INTERACT")) {
					if (Main.GetState() != States.PAUSED) {
						Entity e = world.GetEntity(i);
						
						switch(e.type) {
						case EntityDictionary.PLAYER:
							Player.Interact(world, e);
							world.SetEntity(i, e);
							break;
						}
					}
				}
				if (input.get("KEY_ATTACK")) {
					if (Main.GetState() == States.RUNNING) {
						Entity e = world.GetEntity(i);
						
						switch(e.type) {
						case EntityDictionary.PLAYER:
							Player.Attack(world, e);
							world.SetEntity(i, e);
							break;
						}
					}
				}
			}
		}
	}
	
}
