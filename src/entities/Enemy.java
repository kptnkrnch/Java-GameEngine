package entities;

import java.awt.Point;
import java.awt.Rectangle;

import pathing.Path;
import pathing.PathFindingController;
import engine.Direction;
import engine.Entity;
import engine.EntityDictionary;
import engine.World;
import gameplay.CollisionController;

public class Enemy {
	
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
		
		if (target != null || !enemy.path.IsComplete()) {
			if (enemy.path == null) {
				PathFindingController.HandlePathFinding(world, enemy, target.x, target.y);
			}
			if (enemy.pathFinder.IsFound()) {
				if (enemy.path != null) {
					if (enemy.path.Update(enemy)) {
						PathFindingController.HandlePathFinding(world, enemy, target.x, target.y);
						enemy.path = enemy.pathFinder.FindPath();
					}
				} else {
					PathFindingController.HandlePathFinding(world, enemy, target.x, target.y);
					enemy.path = enemy.pathFinder.FindPath();
				}
				if (enemy.path != null) {
					int[] collisions = null;
					if (!enemy.path.IsComplete()) {
						int direction = enemy.path.GetNextDirection();
						Entity temp = new Entity(enemy);
						switch(direction) {
						case Direction.LEFT:
							temp.Move(direction, temp.speed, fps_scaler);
							
							collisions = CollisionController.CheckEntityCollision(world, temp);
							if (collisions != null) {
								temp.UndoLastMove();
								temp.x += 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						case Direction.RIGHT:
							temp.Move(direction, temp.speed, fps_scaler);
							
							collisions = CollisionController.CheckEntityCollision(world, temp);
							if (collisions != null) {
								temp.UndoLastMove();
								temp.x -= 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						case Direction.UP:
							temp.Move(direction, temp.speed, fps_scaler);
							
							collisions = CollisionController.CheckEntityCollision(world, temp);
							if (collisions != null) {
								temp.UndoLastMove();
								temp.y += 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						case Direction.DOWN:
							temp.Move(direction, temp.speed, fps_scaler);
							
							collisions = CollisionController.CheckEntityCollision(world, temp);
							if (collisions != null) {
								temp.UndoLastMove();
								temp.y -= 1;
								SetCollisionBox(temp, temp.x, temp.y);
							}
							break;
						}
						
						if (collisions != null) {
							for (int i = 0; i < collisions.length; i++) {
								if (collisions[i] != EntityDictionary.PLAYER && collisions[i] != EntityDictionary.CAMERA) {
									PathFindingController.HandlePathFinding(world, temp, target.x, target.y);
									temp.path = temp.pathFinder.FindPath();
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
