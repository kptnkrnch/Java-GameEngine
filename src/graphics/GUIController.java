package graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import engine.Entity;
import engine.EntityDictionary;
import engine.Main;
import engine.Menu;
import engine.States;
import engine.World;

public class GUIController {
	
	public static void DrawMenus(World world, Graphics g) {
		DrawGUIContainer(world, g);
		DrawPauseScreen(world, g);
	}
	
	public static void DrawGUIContainer(World world, Graphics g) {
		for (int i = 0; i < world.GetMenuCount(); i++) {
			Menu temp = world.GetMenu(i);
			if (temp.name.contentEquals("infopanel") && temp.background != null
					&& Main.GetState() != States.TALKING) {
				g.drawImage(temp.background, GraphicsController.VIEWPORT_X, GraphicsController.VIEWPORT_Y);
				DrawHealthBar(world, g);
			}
		}
	}
	
	public static void DrawHealthBar(World world, Graphics g) {
		for (int i = 0; i < world.entities.size(); i++) {
			Entity temp = world.entities.get(i);
			if (temp.type == EntityDictionary.PLAYER) {
				g.setColor(new Color(255, 0, 0));
				if (temp.c_health > 0) {
					g.fillRect(13 + GraphicsController.VIEWPORT_X, 13 + GraphicsController.VIEWPORT_Y, 
							(int)((188) * ((double)temp.c_health / temp.c_max_health)), 
							13);
				}
				g.setColor(new Color(255, 255, 255));
				break;
			}
		}
	}
	
	public static void DrawPauseScreen(World world, Graphics g) {
		if (Main.GetState() == States.PAUSED) {
			g.drawString("paused",
					Main.ResX / 2 - 20 + GraphicsController.VIEWPORT_X, 
					Main.ResY / 2 + GraphicsController.VIEWPORT_Y);
		}
	}
	
}
