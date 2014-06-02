package entities;

import java.awt.Rectangle;
import java.util.HashMap;

import engine.Direction;
import engine.Entity;
import engine.EntityDictionary;
import engine.World;
import gameplay.CollisionController;
import gameplay.CombatCalculator;

public class Player {
	
	public static int BASE_COOLDOWN = 1000;
	
	public static void MovePlayer(World world, Entity player, HashMap<String, Boolean> input, int fps_scaler) {
		if (input != null && player != null && player.type == EntityDictionary.PLAYER && !player.IsTalking()) {
			
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
					collisions = CollisionController.CheckEntityCollision(world, temp);
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
	
	public static void Interact(World world, Entity player) {
		Rectangle interaction_box = null;
		
		switch(player.last_animation) {
		case Direction.LEFT:
			interaction_box = new Rectangle(player.collision_box.x - 8, player.collision_box.y, 8, 32);
			break;
		case Direction.RIGHT:
			interaction_box = new Rectangle(player.collision_box.x + player.collision_box.width, 
					player.collision_box.y, 8, 32);
			break;
		case Direction.UP:
			interaction_box = new Rectangle(player.collision_box.x, player.collision_box.y - 8, 32, 8);
			break;
		case Direction.DOWN:
			interaction_box = new Rectangle(player.collision_box.x, 
					player.collision_box.y + player.collision_box.height, 32, 8);
			break;
		default:
			interaction_box = new Rectangle(player.collision_box.x, 
					player.collision_box.y + player.collision_box.height, 32, 8);
			break;
		}
		
		for (int i = 0; i < world.entities.size(); i++) {
			Entity temp = world.entities.get(i);
			if (interaction_box.intersects(temp.collision_box)) {
				switch (temp.type) {
				case EntityDictionary.NPC:
					player.SetTalking(NPC.Speak(world, temp));
					break;
				case EntityDictionary.ENEMY:
					if (player.c_cooldown <= 0) {
						temp.c_health -= CombatCalculator.CalculateDamage(temp, player);
						player.c_cooldown = CombatCalculator.CalculateCooldown(temp, BASE_COOLDOWN);
					}
					break;
				}
			}
			world.entities.set(i, temp);
		}
		
	}
	
	public static void Attack(World world, Entity player) {
		Rectangle interaction_box = null;
		
		switch(player.last_animation) {
		case Direction.LEFT:
			interaction_box = new Rectangle(player.collision_box.x - 8, player.collision_box.y, 8, 32);
			break;
		case Direction.RIGHT:
			interaction_box = new Rectangle(player.collision_box.x + player.collision_box.width, 
					player.collision_box.y, 8, 32);
			break;
		case Direction.UP:
			interaction_box = new Rectangle(player.collision_box.x, player.collision_box.y - 8, 32, 8);
			break;
		case Direction.DOWN:
			interaction_box = new Rectangle(player.collision_box.x, 
					player.collision_box.y + player.collision_box.height, 32, 8);
			break;
		default:
			interaction_box = new Rectangle(player.collision_box.x, 
					player.collision_box.y + player.collision_box.height, 32, 8);
			break;
		}
		
		for (int i = 0; i < world.entities.size(); i++) {
			Entity temp = world.entities.get(i);
			if (interaction_box.intersects(temp.collision_box)) {
				switch (temp.type) {
				case EntityDictionary.ENEMY:
					if (player.c_cooldown <= 0) {
						temp.c_health -= CombatCalculator.CalculateDamage(player, temp);
					}
					break;
				}
			}
			world.entities.set(i, temp);
		}
		
		if (player.c_cooldown <= 0) {
			player.c_cooldown = CombatCalculator.CalculateCooldown(player, BASE_COOLDOWN);
			player.attacking = true;
		}
	}
	
}
