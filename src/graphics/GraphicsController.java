package graphics;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;

import engine.Entity;
import engine.EntityDictionary;
import engine.Main;
import engine.Tile;
import engine.World;
import entities.Camera;
import exceptions.CameraNotFoundException;

public class GraphicsController {
	
	private static int VIEWPORT_X = 0;
	private static int VIEWPORT_Y = 0;
	private static Rectangle VIEWPORT_BOX = new Rectangle(VIEWPORT_X, VIEWPORT_Y, Main.ResX, Main.ResY);
	
	
	public static void RenderWorld(World world, Graphics g) {
		
		try {
			int cameraID = world.FindCamera();
			Entity camera = world.GetEntity(cameraID);
			
			if (Camera.IsMoveableX(world)) {
				g.translate(-(camera.x - Main.ResX / 2), 0);
				VIEWPORT_X = (camera.x - Main.ResX / 2);
			} else if (camera.x > world.width * world.tilesize / 2) {
				g.translate(-(world.width * world.tilesize - Main.ResX), 0);
			}
			if (Camera.IsMoveableY(world)) {
				g.translate(0, -(camera.y - Main.ResY / 2));
				VIEWPORT_Y = (camera.y - Main.ResY / 2);
			} else if (camera.y > world.height * world.tilesize / 2) {
				g.translate(0, -(world.height * world.tilesize - Main.ResY));
			}
			
			VIEWPORT_BOX.x = VIEWPORT_X;
			VIEWPORT_BOX.y = VIEWPORT_Y;
			
			for (int y = 0; y < world.height; y++) {
				for (int x = 0; x < world.width; x++) {
					Tile temp = world.GetTile(x, y);
					if (VIEWPORT_BOX.intersects(temp.bounding_box)) {
						g.drawAnimation(world.tile_dictionary.GetImage(temp.type), temp.x, temp.y);
					}
				}
			}
			
			for (int i = 0; i < world.GetEntityCount(); i++) {
				Entity temp = world.GetEntity(i);
				if (VIEWPORT_BOX.intersects(temp.bounding_box)) {
					if (temp.type != EntityDictionary.CAMERA) {
						g.drawImage(world.entity_dictionary.GetImage(temp.type), temp.x, temp.y);
					}
				}
			}
			
		} catch (CameraNotFoundException e) {
			System.err.println("Error! Camera entity does not exist!");
		}
	}
	
}
