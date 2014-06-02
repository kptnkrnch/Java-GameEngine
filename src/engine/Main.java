package engine;

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
import graphics.GraphicsController;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

import sound.SoundController;

public class Main extends BasicGame {
	
	private World world;
	public static final int ResX = 1600;
	public static final int ResY = 900;
	public static boolean debug_mode = false;
	public static Controller controller = null;
	
	public static int game_state = 0;
	
	public Main(String title) {
		super(title);
	}
	
	/**
	 * Function:     main
	 * Description:  Main entry point for the program, initializes Slick2D.
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Default Engine"));
		app.setDisplayMode(ResX, ResY, false);
		app.setIcon("res/icon/icon.png");
		app.setShowFPS(false);
		app.setVSync(false);
		app.start();
	}
	
	/**
	 * Function:     render
	 * Description:  Handles the rendering portion of the Slick2D Game Loop.
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		GraphicsController.RenderWorld(world, g);
	}
	
	/**
	 * Function:     init
	 * Description:  Handles the initialization portion of the Slick2D Game Loop,
	 *               ie. Loading images, sounds, etc.
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		/* Finding Xbox 360 controller */
		for (int i = 0; i < Controllers.getControllerCount(); i++) {
			Controller temp = Controllers.getController(i);
			if (temp.getName().contains("XBOX 360")) {
				controller = temp;
				System.out.println(temp.getName());
				break;
			}
		}
		
		world = new World();
		world.LoadTileDictionary("res/dictionaries/TileDictionary.dict");
		world.LoadEntityDictionary("res/dictionaries/EntityDictionary.dict");
		MapLoader.LoadMap(world, "res/maps/Map01.map");
		InputController.LoadKeyMapping("res/config/keymap.conf");
		JoystickController.LoadKeyMapping("res/config/joymap.conf");
		Entity npc = EntityFactory.CreateEntity(EntityDictionary.NPC, 384, 160, 32, 32);
		Menu menu = new Menu("infopanel", "res/gui/info_panel.png");
		world.AddMenu(menu);
		npc.dialog = new ArrayList<String>();
		npc.dialog.add("Hello World");
		world.AddEntity(npc);
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384, 416, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.PLAYER, 288, 128, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.CAMERA, ResX / 2, ResY / 2, 32, 32));
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
		if (!SoundController.IsPlaying()) {
			SoundController.PlayMusic(true);
		}
		Input input = gc.getInput();
		
		HashMap<String, Boolean> held_keys = InputController.HandleHeldInput(input, controller);
		HashMap<String, Boolean> pressed_keys = InputController.HandlePressedInput(input, controller);
		MovementController.HandleMovement(world, held_keys, fps_scaler);
		ActionController.HandleEntityAction(world, pressed_keys, fps_scaler);
		world.tile_dictionary.UpdateAnimations(fps_scaler);
		world.UpdateEntityAnimations(fps_scaler);
		CombatSystem.UpdateCooldowns(world, fps_scaler);
		CombatSystem.CleanupDeadEntities(world);
		
		if (input.isKeyPressed(Input.KEY_F1)) {
			debug_mode = !debug_mode;
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if (GetState() == States.PAUSED) {
				SetState(States.RUNNING);
			} else if (GetState() == States.RUNNING) {
				SetState(States.PAUSED);
			}
		}
	}
	
	public static void SetState(int state) {
		game_state = state;
	}
	
	public static int GetState() {
		return game_state;
	}

}
