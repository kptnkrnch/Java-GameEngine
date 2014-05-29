package gameplay;

import java.util.HashMap;

import engine.Entity;
import engine.EntityDictionary;
import engine.World;
import entities.Camera;
import entities.Player;

public class ActionController {
	
	public static void HandleEntityAction(World world, HashMap<String, Boolean> input, int fps_scaler) {
		if (input != null && !input.isEmpty()) {
			for (int i = 0; i < world.GetEntityCount(); i++) {
				if (input.get("KEY_INTERACT")) {
					
					Entity e = world.GetEntity(i);
					
					switch(e.type) {
					case EntityDictionary.PLAYER:
						Player.Interact(world, e);
						world.SetEntity(i, e);
						break;
					}
				}
			}
		}
	}
	
}
