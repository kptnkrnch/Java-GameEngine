package gameplay;

import java.util.HashMap;

import engine.Entity;
import engine.EntityDictionary;
import engine.Main;
import engine.Menu;
import engine.MenuItem;
import engine.MenuOptionProcessor;
import engine.States;
import engine.World;
import entities.Camera;
import entities.Player;
import graphics.GUIController;

public class ActionController {
	
	public static void HandleEntityAction(World world, HashMap<String, Boolean> input, int fps_scaler) {
		if (input != null && !input.isEmpty()) {
			/* GUI Keys */
			if (input.get("KEY_MENU")) {
				HandleMenuKey(world);
			}
			if (input.get("KEY_UP")) {
				HandleKeyUp(world);
			}
			if (input.get("KEY_DOWN")) {
				HandleKeyDown(world);
			}
			
			/* Gameplay Keys*/
			for (int i = 0; i < world.GetEntityCount(); i++) {
				if (input.get("KEY_INTERACT")) {
					HandleInteractKey(world, input, i, fps_scaler);
				}
				if (input.get("KEY_ATTACK")) {
					HandleAttackKey(world, input, i, fps_scaler);
				}
			}
		}
	}
	
	public static void HandleInteractKey(World world, HashMap<String, Boolean> input, int entityIndex, int fps_scaler) {
		if (Main.GetState() != States.PAUSED) {
			Entity e = world.GetEntity(entityIndex);
			
			switch(e.type) {
			case EntityDictionary.PLAYER:
				Player.Interact(world, e);
				world.SetEntity(entityIndex, e);
				break;
			}
		}
	}
	
	public static void HandleAttackKey(World world, HashMap<String, Boolean> input, int entityIndex, int fps_scaler) {
		if (Main.GetState() == States.RUNNING) {
			Entity e = world.GetEntity(entityIndex);
			
			switch(e.type) {
			case EntityDictionary.PLAYER:
				Player.Attack(world, e);
				world.SetEntity(entityIndex, e);
				break;
			}
		}
	}
	
	public static void HandleMenuKey(World world) {
		if (Main.GetState() != States.MENU) {
			Main.SetState(States.MENU);
			GUIController.SetCurrentMenu(GUIController.MENU_MAIN);
		} else if (Main.GetState() == States.MENU){
			int menuIndex = world.FindMenu(GUIController.GetCurrentMenu());
			Menu menu = world.GetMenu(menuIndex);
			MenuItem menuItem = menu.GetMenuItem(menu.selectedItem);
			MenuOptionProcessor.HandleMenuOption(menuItem.Option());
		} else {
			Main.SetState(Main.previous_state);
		}
	}
	
	public static void HandleKeyUp(World world) {
		if (Main.GetState() == States.MENU) {
			Menu temp = null;
			int tempMenuIndex = -1;
			switch(GUIController.GetCurrentMenu()) {
			case GUIController.MENU_MAIN:
				tempMenuIndex = world.FindMenu(GUIController.GetCurrentMenu());
				temp = world.GetMenu(tempMenuIndex);
				temp.DecrementMenuItem();
				world.SetMenu(tempMenuIndex, temp);
				break;
			case GUIController.MENU_CONTROLS:
				if (GUIController.selectedControlField == -1) {
					tempMenuIndex = world.FindMenu(GUIController.GetCurrentMenu());
					temp = world.GetMenu(tempMenuIndex);
					temp.DecrementMenuItem();
					world.SetMenu(tempMenuIndex, temp);
				}
				break;
			}
		}
	}
	
	public static void HandleKeyDown(World world) {
		if (Main.GetState() == States.MENU) {
			Menu temp = null;
			int tempMenuIndex = -1;
			switch(GUIController.GetCurrentMenu()) {
			case GUIController.MENU_MAIN:
				tempMenuIndex = world.FindMenu(GUIController.GetCurrentMenu());
				temp = world.GetMenu(tempMenuIndex);
				temp.IncrementMenuItem();
				world.SetMenu(tempMenuIndex, temp);
				break;
			case GUIController.MENU_CONTROLS:
				if (GUIController.selectedControlField == -1) {
					tempMenuIndex = world.FindMenu(GUIController.GetCurrentMenu());
					temp = world.GetMenu(tempMenuIndex);
					temp.IncrementMenuItem();
					world.SetMenu(tempMenuIndex, temp);
				}
				break;
			}
		}
	}
	
}
