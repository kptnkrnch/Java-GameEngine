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
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.Move(Direction.UP, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				if (temp.last_animation != Direction.LEFT && temp.last_animation != Direction.UP) {
					temp.last_animation = Direction.LEFT;
				}
				
			} else if (input.get("KEY_LEFT") && input.get("KEY_DOWN")) {
				
				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				temp.Move(Direction.LEFT, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.Move(Direction.DOWN, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				if (temp.last_animation != Direction.LEFT && temp.last_animation != Direction.DOWN) {
					temp.last_animation = Direction.LEFT;
				}
				
			} else if (input.get("KEY_RIGHT") && input.get("KEY_UP")) {

				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				temp.Move(Direction.RIGHT, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.Move(Direction.UP, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				if (temp.last_animation != Direction.RIGHT && temp.last_animation != Direction.UP) {
					temp.last_animation = Direction.RIGHT;
				}
				
			} else if (input.get("KEY_RIGHT") && input.get("KEY_DOWN")) {

				speed = (float) (Math.sqrt(Math.pow(speed, 2) / 2));
				
				temp.Move(Direction.RIGHT, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.Move(Direction.DOWN, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				if (temp.last_animation != Direction.RIGHT && temp.last_animation != Direction.DOWN) {
					temp.last_animation = Direction.RIGHT;
				}
				
			} else if (input.get("KEY_LEFT")) {
				
				temp.Move(Direction.LEFT, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.last_animation = Direction.LEFT;
				
			} else if (input.get("KEY_RIGHT")) {
				
				temp.Move(Direction.RIGHT, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.last_animation = Direction.RIGHT;
				
			} else if (input.get("KEY_UP")) {
				
				temp.Move(Direction.UP, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.last_animation = Direction.UP;
				
			} else if (input.get("KEY_DOWN")) {
				
				temp.Move(Direction.DOWN, speed, fps_scaler);
				SetCollisionBox(temp, temp.x, temp.y);
				if (!CollisionController.CheckEntityOutOfBounds(world, temp)) {
					collisions = CollisionController.CheckTileCollision(world, temp);
					if (collisions != null) {
						temp.UndoLastMove();
						SetCollisionBox(temp, temp.x, temp.y);
					}
				} else {
					temp.UndoLastMove();
					SetCollisionBox(temp, temp.x, temp.y);
				}
				
				temp.last_animation = Direction.DOWN;
				
			} else {
				temp.last_direction = Direction.NONE;
			}
			player.Copy(temp);
		}
	}
	
	public static void SetCollisionBox(Entity player, int x, int y) {
		player.collision_box.x = x + 4;
		player.collision_box.y = y + 10;
	}
	
	public static void HandleCollision() {
		
	}
	
}
