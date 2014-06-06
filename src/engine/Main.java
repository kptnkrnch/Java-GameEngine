package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import input.InputController;
import input.JoystickController;
import entities.Camera;
import exceptions.CameraNotFoundException;
import exceptions.PlayerNotFoundException;
import gameplay.ActionController;
import gameplay.CombatSystem;
import gameplay.MovementController;
import graphics.GUIController;
import graphics.GraphicsController;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.util.ResourceLoader;

import sound.SoundController;

public class Main extends BasicGame {
	public static boolean debug = true;
	public static GameContainer game_container;
	public static World world;
	public static int ScreenResX = 800;
	public static int ScreenResY = 600;
	public static int ResX = 960;
	public static int ResY = 540;
	public static boolean debug_mode = false;
	public static Controller controller = null;
	public static boolean FULLSCREEN = false;
	public static boolean VSYNC = false;
	public static Font font;
	public static TrueTypeFont ttf;
	public static UnicodeFont uni;
	
	public static int previous_state = States.PAUSED;
	public static int game_state = States.PAUSED;
	
	public Main(String title) {
		super(title);
	}
	
	/**
	 * Function:     main
	 * Description:  Main entry point for the program, initializes Slick2D.
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Default Engine"));
		ResX = ScreenResX;
		ResY = ScreenResY;
		app.setDisplayMode(ScreenResX, ScreenResY, FULLSCREEN);
		app.setIcon("res/icon/icon.png");
		app.setShowFPS(false);
		app.setVSync(VSYNC);
		app.start();
	}
	
	/**
	 * Function:     render
	 * Description:  Handles the rendering portion of the Slick2D Game Loop.
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//g.setFont(uni);
		GraphicsController.RenderWorld(world, g);
	}
	
	/**
	 * Function:     init
	 * Description:  Handles the initialization portion of the Slick2D Game Loop,
	 *               ie. Loading images, sounds, etc.
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		/*try {
			font = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream("res/fonts/MINECRAFTIA.ttf"));
			font = font.deriveFont(Font.PLAIN , 11.0f);
			uni = new UnicodeFont(font);
			uni.addAsciiGlyphs();
			ColorEffect a = new ColorEffect();
			a.setColor(Color.white);
			uni.getEffects().add(a);
			uni.loadGlyphs();
		} catch (Exception e) {
		}*/		
		world = new World();
		world.LoadTileDictionary("res/dictionaries/TileDictionary.dict");
		world.LoadEntityDictionary("res/dictionaries/EntityDictionary.dict");
		
		//world.entity_dictionary.LoadAnimations("res/sprites/player/player.anim");
		
		MapLoader.LoadMap(world, "res/maps/Map01.map");
		
		InputController.LoadKeyMapping("res/config/keymap.conf");
		String controller_name = JoystickController.LoadKeyMapping("res/config/joymap.conf");
		if (controller_name != null) {
			/* Finding controller */
			for (int i = 0; i < Controllers.getControllerCount(); i++) {
				Controller temp = Controllers.getController(i);
				if (temp.getName().contains(controller_name)) {
					controller = temp;
					System.out.println(temp.getName());
					break;
				}
			}
		}
		
		//Entity npc = EntityFactory.CreateEntity(world, EntityDictionary.NPC, 384, 160, 32, 32);
		//Menu menu = new Menu("info_panel", "res/gui/info_panel.png", 0, 0, 400, 96);
		//world.AddMenu(menu);
		world.menus = Menu.LoadMenus("res/config/menus.conf");
		//npc.dialog = new ArrayList<String>();
		//npc.dialog.add("Hello World");
		//world.AddEntity(npc);
		//world.AddEntity(EntityFactory.CreateEntity(world, EntityDictionary.ENEMY, 384, 416, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(world, EntityDictionary.PLAYER, 288, 128, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(world, EntityDictionary.CAMERA, ResX / 2, ResY / 2, 32, 32));
		try {
			int player = world.FindPlayer();
			Camera.Follow(player);
		} catch (PlayerNotFoundException e) {
		}
		SoundController.LoadMusic("res/sounds/Track-03.ogg");
		game_state = States.RUNNING;
	}
	
	/**
	 * Function:     update
	 * Description:  Handles input, movement, and actions during the Slick2D Game Loop.
	 */
	@Override
	public void update(GameContainer gc, int fps_scaler) throws SlickException {
		game_container = gc;
		if (!SoundController.IsPlaying()) {
			//SoundController.PlayMusic(true);
		}
		Input input = gc.getInput();
		HashMap<String, Boolean> held_keys = InputController.HandleHeldInput(input, controller);
		HashMap<String, Boolean> pressed_keys = InputController.HandlePressedInput(input, controller);
		MovementController.HandleMovement(world, held_keys, fps_scaler);
		ActionController.HandleEntityAction(world, pressed_keys, fps_scaler);
		world.tile_dictionary.UpdateAnimations(fps_scaler);
		world.UpdateEntityAnimations(fps_scaler);
		if (GetState() == States.RUNNING) {
			world.UpdateEntityHitTimers(fps_scaler);
			CombatSystem.UpdateCooldowns(world, fps_scaler);
			CombatSystem.CleanupDeadEntities(world);
			CombatSystem.UpdateSpawnTimers(world, fps_scaler);
		}
		
		if (debug && input.isKeyPressed(Input.KEY_F1)) {
			debug_mode = !debug_mode;
		}
	}
	
	public static void SetState(int state) {
		previous_state = game_state;
		game_state = state;
	}
	
	public static int GetState() {
		return game_state;
	}
	
	public static int GetPreviousState() {
		return previous_state;
	}
	
	public void keyPressed(int key, char c) {
		if (GetState() == States.MENU && GUIController.GetCurrentMenu() == GUIController.MENU_CONTROLS
				&& GUIController.selectedControlField != -1) {
			int menuID = world.FindMenu(GUIController.MENU_CONTROLS);
			Menu temp = world.GetMenu(menuID);
			
			for (int i = 0; i < temp.GetMenuItemCount(); i++) {
				MenuItem item = temp.GetMenuItem(i);
				
				if (i == GUIController.selectedControlField) {
					String keyname = "KEY_" + item.text;
					int key_value = GUIController.GetKeyValue(keyname);
					
					if (GUIController.GetKeyValue(keyname) != null) {
						if (key_value != 28 && !GUIController.tempKeyMap.containsValue(key)) {
							GUIController.tempKeyMap.put(keyname, key);
						}
					}
					break;
				}
			}
		}
	}
}
