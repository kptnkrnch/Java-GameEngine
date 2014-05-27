package gameplay;

import java.util.HashMap;

import engine.Entity;
import engine.EntityDictionary;
import engine.World;
import entities.Camera;
import entities.Player;

public class MovementController {
	
	public static void HandleMovement(World world, HashMap<String, Boolean> input, int fps_scaler) {
		if (input != null && !input.isEmpty()) {
			for (int i = 0; i < world.GetEntityCount(); i++) {
				Entity e = world.GetEntity(i);
				switch(e.type) {
				case EntityDictionary.CAMERA:
					Camera.MoveCamera(world, e);
					world.SetEntity(i, e);
					break;
				case EntityDictionary.PLAYER:
					Player.MovePlayer(world, e, input, fps_scaler);
					world.SetEntity(i, e);
					break;
				case EntityDictionary.NPC:
					break;
				case EntityDictionary.ENEMY:
					break;
				}
			}
		}
	}
	
}
