package gameplay;

import engine.Entity;
import engine.World;

public class CombatSystem {
	
	public static void UpdateCooldowns(World world, int fps_scaler) {
		for (int i = 0; i < world.GetEntityCount(); i++) {
			Entity temp = world.GetEntity(i);
			
			if (temp.c_cooldown > 0) {
				temp.c_cooldown -= fps_scaler;
			}
			
			world.SetEntity(i, temp);
		}
	}
	
	public static void CleanupDeadEntities(World world) {
		for (int i = 0; i < world.GetEntityCount(); i++) {
			Entity temp = world.GetEntity(i);
			
			if (temp.c_health <= 0) {
				world.RemoveEntity(i);
			}
		}
	}
}
