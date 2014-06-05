package graphics;

import input.InputController;

import java.awt.Rectangle;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

import engine.Entity;
import engine.EntityDictionary;
import engine.Main;
import engine.Menu;
import engine.MenuItem;
import engine.States;
import engine.World;
import exceptions.PlayerNotFoundException;

public class GUIController {
	
	public static String currentMenuName = "info_panel";
	public static String previousMenuName = null;
	
	public static final String MENU_MAIN = "main";
	public static final String MENU_INFO_PANEL = "info_panel";
	public static final String MENU_PAUSE = "pause";
	public static final String MENU_CONTROLS = "controls";
	
	public static int selectedControlField = -1;
	public static HashMap<String, Integer> tempKeyMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> tempJoyMap = new HashMap<String, Integer>();
	
	public static void SetCurrentMenu(String menu) {
		previousMenuName = currentMenuName;
		currentMenuName = menu;
	}
	
	public static String GetCurrentMenu() {
		return currentMenuName;
	}
	
	public static String GetPreviousMenu() {
		return previousMenuName;
	}
	
	public static void DrawMenus(World world, Graphics g) {
		if (Main.GetState() == States.RUNNING) {
			SetCurrentMenu(GUIController.MENU_INFO_PANEL);
		}
		String menu = GetCurrentMenu();
		if (menu != null) {
			switch(menu) {
			case MENU_MAIN:
				DrawMainMenu(world, g);
				break;
			case MENU_INFO_PANEL:
				DrawPlayerInfoPanel(world, g);
				break;
			case MENU_PAUSE:
				DrawPauseScreen(world, g);
				break;
			case MENU_CONTROLS:
				DrawControlsMenu(world, g);
				break;
			}
		}
	}
	
	public static void DrawPlayerInfoPanel(World world, Graphics g) {
		int menuID = world.FindMenu(MENU_INFO_PANEL);
		Menu temp = world.GetMenu(menuID);
		if (temp != null && temp.background != null
				&& Main.GetState() != States.TALKING) {
			
			try {
				Entity player = world.GetEntity(world.FindPlayer());
				Rectangle infoPanelBox = new Rectangle(temp.x + GraphicsController.VIEWPORT_X, 
						temp.y + GraphicsController.VIEWPORT_Y, temp.width, temp.height);
				
				if (!infoPanelBox.intersects(player.collision_box)) {
					g.drawImage(temp.background, temp.x + GraphicsController.VIEWPORT_X, 
							temp.y + GraphicsController.VIEWPORT_Y);
					DrawHealthBar(world, g, temp.x, temp.y);
				}
			} catch (PlayerNotFoundException e) {
			}
		}
	}
	
	public static void DrawHealthBar(World world, Graphics g, int menux, int menuy) {
		try {
			int entityIndex = world.FindPlayer();
			Entity temp = world.entities.get(entityIndex);
			if (temp != null) {
				g.setColor(new Color(255, 0, 0));
				if (temp.c_health > 0) {
					g.fillRect(menux + 13 + GraphicsController.VIEWPORT_X, menuy + 13 + GraphicsController.VIEWPORT_Y, 
							(int)((188) * ((double)temp.c_health / temp.c_max_health)), 
							13);
				}
				g.setColor(new Color(255, 255, 255));
				g.drawString(temp.c_health + "/" + temp.c_max_health, 
						menux + 14 + GraphicsController.VIEWPORT_X, menuy + 10 + GraphicsController.VIEWPORT_Y);
			}
		} catch (PlayerNotFoundException e) {
		}
	}
	
	public static void DrawPauseScreen(World world, Graphics g) {
		if (Main.GetState() == States.PAUSED) {
			g.drawString("paused",
					Main.ResX / 2 - 20 + GraphicsController.VIEWPORT_X, 
					Main.ResY / 2 + GraphicsController.VIEWPORT_Y);
		}
	}
	
	public static void DrawMainMenu(World world, Graphics g) {
		int menuID = world.FindMenu(MENU_MAIN);
		Menu temp = world.GetMenu(menuID);
		if (temp != null && temp.background != null
				&& Main.GetState() == States.MENU) {
			
			g.drawImage(temp.background, temp.x + GraphicsController.VIEWPORT_X, 
					temp.y + GraphicsController.VIEWPORT_Y);
			
			for (int i = 0; i < temp.GetMenuItemCount(); i++) {
				MenuItem item = temp.GetMenuItem(i);
				if (item.IsHighlighted()) {
					g.setColor(Color.black);
				}
				g.drawString(item.text, item.x + GraphicsController.VIEWPORT_X,
						item.y + GraphicsController.VIEWPORT_Y);
				g.setColor(Color.white);
			}
		}
	}
	
	public static void DrawControlsMenu(World world, Graphics g) {
		int menuID = world.FindMenu(MENU_CONTROLS);
		Menu temp = world.GetMenu(menuID);
		if (temp != null && temp.background != null
				&& Main.GetState() == States.MENU) {
			try {
				Image unfocused_input = new Image("res/gui/textfield/textfield_unfocused.png");
				Image focused_input = new Image("res/gui/textfield/textfield_focused.png");
				g.drawImage(temp.background, temp.x + GraphicsController.VIEWPORT_X, 
						temp.y + GraphicsController.VIEWPORT_Y);
				if (tempKeyMap.isEmpty() == false) {
					for (int i = 0; i < temp.GetMenuItemCount(); i++) {
						MenuItem item = temp.GetMenuItem(i);
						if (item.IsHighlighted()) {
							g.setColor(Color.black);
						} else {
							g.setColor(Color.white);
						}
						g.drawString(item.text, item.x + GraphicsController.VIEWPORT_X,
								item.y + GraphicsController.VIEWPORT_Y);
						
						if (i == selectedControlField) {
							String key = "KEY_" + item.text;
							String key_value = Input.getKeyName(GetKeyValue(key));
							
							g.setColor(Color.black);
							if (InputController.GetKeyValue(key) != null) {
								g.drawImage(focused_input, temp.x + temp.width - 100, item.y);
								g.drawString(key_value, temp.x + temp.width - 96, item.y + 3);
							}
						} else {
							String key = "KEY_" + item.text;
							String key_value = Input.getKeyName(GetKeyValue(key));
							
							g.setColor(Color.black);
							if (InputController.GetKeyValue(key) != null) {
								g.drawImage(unfocused_input, temp.x + temp.width - 100, item.y);
								g.drawString(key_value, temp.x + temp.width - 96, item.y + 3);
							}
						}
						
						g.setColor(Color.white);
					}
				}
			} catch (Exception e) {
			}
		}
	}
	
	public static Integer GetKeyValue(String key) {
		if (tempKeyMap.containsKey(key)) {
			return tempKeyMap.get(key);
		} else {
			return null;
		}
	}
	
}
