package entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import pathing.Path;
import pathing.PathFindingController;
import engine.Direction;
import engine.Entity;
import engine.EntityDictionary;
import engine.World;
import gameplay.CollisionController;
import gameplay.CombatCalculator;

public class Enemy {
	
	public static int BASE_COOLDOWN = 1000;
	
	public static void MoveEnemy(World world, Entity enemy, int targetX, int targetY) {
		PathFindingController.HandlePathFinding(world, enemy, targetX, targetY);
		if (enemy.pathFinder.IsFound()) {
			
		}
	}
	
	public static void MoveEnemy(World world, Entity enemy, int fps_scaler) {
		Entity target = null;
		for (int i = 0; i < world.entities.size(); i++) {
			Entity temp = world.GetEntity(i);
			if (temp.type == EntityDictionary.PLAYER) {
				Rectangle fov = CalculateFieldOfView(enemy);
				target = temp;
				break;
				/*if (fov.intersects(temp.collision_box)) {
					target = temp;
					break;
				}*/
			}
		}
		
		if (target != null) {
			if (enemy.path == null && target != null) {
				PathFindingController.HandlePathFinding(world, enemy, target.collision_box.x, target.collision_box.y);
			}
			if (enemy.pathFinder.IsFound()) {
				if (enemy.path != null && target != null) {
					if (enemy.path.Update(enemy)) {
						PathFindingController.HandlePathFinding(world, enemy, target.collision_box.x, target.collision_box.y);
						enemy.path = enemy.pathFinder.FindPath();
					}
				} else if (target != null) {
					PathFindingController.HandlePathFinding(world, enemy, target.collision_box.x, target.collision_box.y);
					enemy.path = enemy.pathFinder.FindPath();
				}
				if (enemy.path != null && !enemy.path.IsComplete()) {
					HashMap<Long, Integer> entity_collisions = null;
					if (!enemy.path.IsComplete()) {
						int direction = enemy.path.GetNextDirection();
						Entity temp = new Entity(enemy);
						switch(direction) {
						case Direction.LEFT:
							temp.Move(direction, temp.speed, fps_scaler);
							
							entity_collisions = CollisionController.CheckEntityCollision(world, temp);
							if (entity_collisions != null) {
								temp.UndoLastMove();
								temp.x += 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						case Direction.RIGHT:
							temp.Move(direction, temp.speed, fps_scaler);
							
							entity_collisions = CollisionController.CheckEntityCollision(world, temp);
							if (entity_collisions != null) {
								temp.UndoLastMove();
								temp.x -= 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						case Direction.UP:
							temp.Move(direction, temp.speed, fps_scaler);
							
							entity_collisions = CollisionController.CheckEntityCollision(world, temp);
							if (entity_collisions != null) {
								temp.UndoLastMove();
								temp.y += 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						case Direction.DOWN:
							temp.Move(direction, temp.speed, fps_scaler);
							
							entity_collisions = CollisionController.CheckEntityCollision(world, temp);
							if (entity_collisions != null) {
								temp.UndoLastMove();
								temp.y -= 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						}
						
						if (entity_collisions != null && target != null) {
							Iterator<Entry<Long, Integer>> iterator = entity_collisions.entrySet().iterator();
							while(iterator.hasNext()) {
								Entry<Long, Integer> current = iterator.next();
								if (current.getValue() != EntityDictionary.PLAYER && current.getValue() != EntityDictionary.CAMERA) {
									PathFindingController.HandlePathFinding(world, temp, target.collision_box.x, target.collision_box.y);
									temp.path = temp.pathFinder.FindPath();
									break;
								}
							}
						}
						
						if (entity_collisions != null) {
							Iterator<Entry<Long, Integer>> iterator = entity_collisions.entrySet().iterator();
							while(iterator.hasNext()) {
								Entry<Long, Integer> current = iterator.next();
								switch(current.getValue()) {
								case EntityDictionary.PLAYER:
									if (temp.c_cooldown <= 0) {
										for (int n = 0; n < world.entities.size(); n++) {
											Entity player = world.GetEntity(n);
											if (player.type == EntityDictionary.PLAYER) {
												player.c_health -= CombatCalculator.CalculateDamage(temp, player);
												temp.c_cooldown = CombatCalculator.CalculateCooldown(temp, BASE_COOLDOWN);
												break;
											}
										}
									}
									break;
								}
							}
						}
						
						enemy.Copy(temp);
					}
				}
			}
		}
	}
	
	public static Rectangle CalculateFieldOfView(Entity enemy) {
		Rectangle fov = null;
		
		switch (enemy.last_animation) {
		case Direction.LEFT:
			fov = new Rectangle(enemy.collision_box.x - 32 * 4,
					enemy.collision_box.y - 64,
					32 * 4,
					32 * 5);
			break;
		case Direction.RIGHT:
			fov = new Rectangle(enemy.collision_box.x + enemy.collision_box.width,
					enemy.collision_box.y - 64,
					32 * 4,
					32 * 5);
			break;
		case Direction.UP:
			fov = new Rectangle(enemy.collision_box.x - 64,
					enemy.collision_box.y + enemy.collision_box.height - 32 * 5,
					32 * 5,
					32 * 5);
			break;
		case Direction.DOWN:
			fov = new Rectangle(enemy.collision_box.x - 64,
					enemy.collision_box.y,
					32 * 5,
					32 * 5);
			break;
		default:
			fov = new Rectangle(enemy.collision_box.x - 64,
					enemy.collision_box.y - 64,
					32 * 5,
					32 * 7);
			break;
		}
		
		return fov;
	}
	
	public static void SetCollisionBox(Entity enemy, int x, int y) {
		enemy.collision_box.x = x + 1;
		enemy.collision_box.y = y + 1;
	}
	
}
