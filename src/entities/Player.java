package entities;

import java.util.HashMap;

import engine.Direction;
import engine.Entity;
import engine.EntityDictionary;

public class Player {

	public static void MovePlayer(Entity player, HashMap<String, Boolean> input, int fps_scaler) {
		if (input != null && player != null && player.type == EntityDictionary.PLAYER) {
			
			float speed = player.speed;
			
			if (input.get("KEY_LEFT") && input.get("KEY_UP")) {
				
				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				player.Move(Direction.LEFT, speed, fps_scaler);
				player.Move(Direction.UP, speed, fps_scaler);
				
			} else if (input.get("KEY_LEFT") && input.get("KEY_DOWN")) {
				
				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				player.Move(Direction.LEFT, speed, fps_scaler);
				player.Move(Direction.DOWN, speed, fps_scaler);
				
			} else if (input.get("KEY_RIGHT") && input.get("KEY_UP")) {

				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				player.Move(Direction.RIGHT, speed, fps_scaler);
				player.Move(Direction.UP, speed, fps_scaler);
				
			} else if (input.get("KEY_RIGHT") && input.get("KEY_DOWN")) {

				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				player.Move(Direction.RIGHT, speed, fps_scaler);
				player.Move(Direction.DOWN, speed, fps_scaler);
				
			} else if (input.get("KEY_LEFT")) {
				
				player.Move(Direction.LEFT, speed, fps_scaler);
				
			} else if (input.get("KEY_RIGHT")) {
				
				player.Move(Direction.RIGHT, speed, fps_scaler);
				
			} else if (input.get("KEY_UP")) {
				
				player.Move(Direction.UP, speed, fps_scaler);
				
			} else if (input.get("KEY_DOWN")) {
				
				player.Move(Direction.DOWN, speed, fps_scaler);
				
			}
			
		}
	}
	
}
