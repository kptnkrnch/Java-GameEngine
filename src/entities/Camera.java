package entities;

import engine.Entity;
import engine.Main;
import engine.World;
import graphics.GraphicsController;

public class Camera {
	
	private static long followingID = -1;
	
	public static void Follow(long entityID) {
		followingID = entityID;
	}
	
	public static void MoveCamera(World world, Entity camera) {
		if (followingID != -1 && !GraphicsController.room_changed) {
			Entity following = FindEntity(world, followingID);
			if (following != null) {
				camera.SetPosition(following.x + following.width / 2, following.y + following.height / 2);
			}
		}
	}
	
	public static boolean IsMoveableX(World world) {
		Entity temp = FindEntity(world, followingID);
		if (temp != null) {
			int lowerlimit = Main.ResX / 2;
			int upperlimit = (world.width * world.tilesize) - (Main.ResX / 2);
			if (temp.x + temp.width / 2 < lowerlimit || temp.x + temp.width / 2 > upperlimit) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public static boolean IsMoveableY(World world) {
		Entity temp = FindEntity(world, followingID);
		if (temp != null) {
			int lowerlimit = Main.ResY / 2;
			int upperlimit = (world.height * world.tilesize) - (Main.ResY / 2);
			if (temp.y + temp.height / 2 < lowerlimit || temp.y + temp.height / 2 > upperlimit) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	private static Entity FindEntity(World world, long followingID) {
		for (int i = 0; i < world.GetEntityCount(); i++) {
			Entity temp = world.GetEntity(i);
			if (temp.id == followingID) {
				return temp;
			}
		}
		return null;
	}
}
