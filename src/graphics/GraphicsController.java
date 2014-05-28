package graphics;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import engine.Entity;
import engine.EntityDictionary;
import engine.Main;
import engine.Tile;
import engine.TileDictionary;
import engine.World;
import entities.Camera;
import exceptions.CameraNotFoundException;
import exceptions.PlayerNotFoundException;

public class GraphicsController {
	
	public static void RenderWorld(World world, Graphics g) {
		try {
			int cameraID = world.FindCamera();
			Entity camera = world.GetEntity(cameraID);
			int playerID = world.FindPlayer();
			
			if (Camera.IsMoveableX(world)) {
				g.translate(-(camera.x - Main.ResX / 2), 0);
			} else if (camera.x > world.width * world.tilesize / 2) {
				g.translate(-(world.width * world.tilesize - Main.ResX), 0);
			}
			if (Camera.IsMoveableY(world)) {
				g.translate(0, -(camera.y - Main.ResY / 2));
			} else if (camera.y > world.height * world.tilesize / 2) {
				g.translate(0, -(world.height * world.tilesize - Main.ResY));
			}
			
			for (int y = 0; y < world.height; y++) {
				for (int x = 0; x < world.width; x++) {
					Tile temp = world.GetTile(x, y);
					if (temp.type == TileDictionary.WATER) {
						g.drawAnimation(Main.wa, temp.x, temp.y);
					} else {
						g.drawImage(world.tile_dictionary.GetImage(temp.type), temp.x, temp.y);
					}
				}
			}
			
			for (int i = 0; i < world.GetEntityCount(); i++) {
				Entity temp = world.GetEntity(i);
				if (temp.type != EntityDictionary.CAMERA) {
					g.drawImage(world.entity_dictionary.GetImage(temp.type), temp.x, temp.y);
				}
			}
			
		} catch (CameraNotFoundException e) {
			System.err.println("Error! Camera entity does not exist!");
		} catch (PlayerNotFoundException e) {
			System.err.println("Error! Player entity does not exist!");
		}
	}
	
}
