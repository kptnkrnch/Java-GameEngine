package entities;

import engine.Entity;
import engine.Main;
import engine.World;

public class Camera {
	
	private static int followingID = -1;
	
	public static void Follow(int entityID) {
		followingID = entityID;
	}
	
	public static void MoveCamera(World world, Entity camera) {
		if (followingID != -1) {
			Entity following = world.GetEntity(followingID);
			camera.SetPosition(following.x + following.width / 2, following.y + following.height / 2);
		}
	}
	
	public static boolean IsMoveableX(World world) {
		Entity temp = world.GetEntity(followingID);
		int lowerlimit = Main.ResX / 2;
		int upperlimit = (world.width * world.tilesize) - (Main.ResX / 2);
		if (temp.x + temp.width / 2 < lowerlimit || temp.x + temp.width / 2 > upperlimit) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean IsMoveableY(World world) {
		Entity temp = world.GetEntity(followingID);
		int lowerlimit = Main.ResY / 2;
		int upperlimit = (world.height * world.tilesize) - (Main.ResY / 2);
		if (temp.y + temp.height / 2 < lowerlimit || temp.y + temp.height / 2 > upperlimit) {
			return false;
		} else {
			return true;
		}
	}
}
