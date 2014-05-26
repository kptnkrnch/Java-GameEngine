package entities;

import java.util.HashMap;

import engine.Direction;
import engine.Entity;
import engine.EntityDictionary;
import engine.World;
import gameplay.CollisionController;

public class Player {

	public static void MovePlayer(World world, Entity player, HashMap<String, Boolean> input, int fps_scaler) {
		if (input != null && player != null && player.type == EntityDictionary.PLAYER) {
			
			float speed = player.speed;
			int[] collisions = null;
			Entity temp = new Entity(player);
			
			if (input.get("KEY_LEFT") && input.get("KEY_UP")) {
				
				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				temp.Move(Direction.LEFT, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
				temp.Move(Direction.UP, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			} else if (input.get("KEY_LEFT") && input.get("KEY_DOWN")) {
				
				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				temp.Move(Direction.LEFT, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
				temp.Move(Direction.DOWN, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			} else if (input.get("KEY_RIGHT") && input.get("KEY_UP")) {

				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				temp.Move(Direction.RIGHT, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
				temp.Move(Direction.UP, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			} else if (input.get("KEY_RIGHT") && input.get("KEY_DOWN")) {

				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				temp.Move(Direction.RIGHT, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
				temp.Move(Direction.DOWN, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			} else if (input.get("KEY_LEFT")) {
				
				temp.Move(Direction.LEFT, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			} else if (input.get("KEY_RIGHT")) {
				
				temp.Move(Direction.RIGHT, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			} else if (input.get("KEY_UP")) {
				
				temp.Move(Direction.UP, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			} else if (input.get("KEY_DOWN")) {
				
				temp.Move(Direction.DOWN, speed, fps_scaler);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
					}
				} else {
					temp.UndoLastMove();
				}
				
			}
			player.Copy(temp);
		}
	}
	
	public static void HandleCollision() {
		
	}
	
}
