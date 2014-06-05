package engine;

import input.InputController;

import org.newdawn.slick.Color;

import graphics.GUIController;
import graphics.GraphicsController;

public class MenuOptionProcessor {
	
	/* Main Menu Options */
	public static final String GRAPHICS_OPTION = "graphics_option";
	public static final String RESUME_OPTION = "resume_option";
	public static final String CONTROLS_OPTION = "controls_option";
	public static final String SAVE_OPTION = "save_option";
	public static final String LOAD_OPTION = "load_option";
	public static final String EXIT_OPTION = "exit_option";
	
	/* Controls Menu Options */
	public static final String UP_CONTROL_OPTION = "up_control_option";
	public static final String DOWN_CONTROL_OPTION = "down_control_option";
	public static final String LEFT_CONTROL_OPTION = "left_control_option";
	public static final String RIGHT_CONTROL_OPTION = "right_control_option";
	public static final String ATTACK_CONTROL_OPTION = "attack_control_option";
	public static final String INTERACT_CONTROL_OPTION = "interact_control_option";
	public static final String BACK_OPTION = "back_option";
	
	public static void HandleMenuOption(String option) {
		if (option != null && option.length() > 0) {
			switch(GUIController.GetCurrentMenu()) {
			case GUIController.MENU_MAIN:
				HandleMainMenuOption(option);
				break;
			case GUIController.MENU_CONTROLS:
				HandleControlMenuOption(option);
				break;
			}
		}
	}
	
	public static void HandleMainMenuOption(String option) {
		if (option != null && option.length() > 0) {
			switch(option) {
			case GRAPHICS_OPTION:
				break;
			case RESUME_OPTION:
				Main.SetState(States.RUNNING);
				break;
			case CONTROLS_OPTION:
				GUIController.SetCurrentMenu(GUIController.MENU_CONTROLS);
				break;
			case SAVE_OPTION:
				break;
			case LOAD_OPTION:
				break;
			case EXIT_OPTION:
				OptionExit();
				break;
			}
		}
	}
	
	public static void HandleControlMenuOption(String option) {
		if (option != null && option.length() > 0) {
			int menuID = Main.world.FindMenu(GUIController.MENU_CONTROLS);
			Menu temp = Main.world.GetMenu(menuID);
			int currentOption = -1;
			
			for (int i = 0; i < temp.GetMenuItemCount(); i++) {
				MenuItem item = temp.GetMenuItem(i);
				String tempOption = option.replace("_control_option", "");
				if (!item.text.contains("back") && item.text.toLowerCase().equals(tempOption)) {
					currentOption = i;
				}
			}
			
			switch(option) {
			case UP_CONTROL_OPTION:
				if (GUIController.selectedControlField != currentOption) {
					GUIController.selectedControlField = currentOption;
				} else {
					GUIController.selectedControlField = -1;
				}
				break;
			case DOWN_CONTROL_OPTION:
				if (GUIController.selectedControlField != currentOption) {
					GUIController.selectedControlField = currentOption;
				} else {
					GUIController.selectedControlField = -1;
				}
				break;
			case LEFT_CONTROL_OPTION:
				if (GUIController.selectedControlField != currentOption) {
					GUIController.selectedControlField = currentOption;
				} else {
					GUIController.selectedControlField = -1;
				}
				break;
			case RIGHT_CONTROL_OPTION:
				if (GUIController.selectedControlField != currentOption) {
					GUIController.selectedControlField = currentOption;
				} else {
					GUIController.selectedControlField = -1;
				}
				break;
			case ATTACK_CONTROL_OPTION:
				if (GUIController.selectedControlField != currentOption) {
					GUIController.selectedControlField = currentOption;
				} else {
					GUIController.selectedControlField = -1;
				}
				break;
			case INTERACT_CONTROL_OPTION:
				if (GUIController.selectedControlField != currentOption) {
					GUIController.selectedControlField = currentOption;
				} else {
					GUIController.selectedControlField = -1;
				}
				break;
			case BACK_OPTION:
				GUIController.SetCurrentMenu(GUIController.previousMenuName);
				break;
			}
		}
	}
	
	private static void OptionExit() {
		Main.game_container.exit();
	}
	
}
