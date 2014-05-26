package graphics;

import org.newdawn.slick.Graphics;

import engine.Entity;
import engine.Tile;
import engine.World;

public class GraphicsController {
	
	public static void RenderWorld(World world, Graphics g) {
		for (int y = 0; y < world.height; y++) {
			for (int x = 0; x < world.width; x++) {
				Tile temp = world.GetTile(x, y);
				g.drawImage(world.tile_dictionary.GetImage(temp.type), temp.x, temp.y);
			}
		}
		
		for (int i = 0; i < world.GetEntityCount(); i++) {
			Entity temp = world.GetEntity(i);
			g.drawImage(world.entity_dictionary.GetImage(temp.type), temp.x, temp.y);
		}
	}
	
}
