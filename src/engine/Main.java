package engine;

import java.util.ArrayList;
import java.util.HashMap;

import input.InputController;
import entities.Camera;
import exceptions.CameraNotFoundException;
import exceptions.PlayerNotFoundException;
import gameplay.ActionController;
import gameplay.MovementController;
import graphics.GraphicsController;

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
	public static final int ResX = 800;
	public static final int ResY = 600;
	public static boolean debug_mode = false;
	
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
		app.setShowFPS(true);
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
		world = new World();
		world.LoadTileDictionary("res/dictionaries/TileDictionary.dict");
		world.LoadEntityDictionary("res/dictionaries/EntityDictionary.dict");
		MapLoader.LoadMap(world, "res/maps/Map01.map");
		InputController.LoadKeyMapping("res/config/keymap.conf");
		Entity npc = EntityFactory.CreateEntity(EntityDictionary.NPC, 384, 160, 32, 32);
		npc.dialog = new ArrayList<String>();
		npc.dialog.add("Hello Kurt");
		world.AddEntity(npc);
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384, 416, 32, 32));
		/*world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384 + 32, 416, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384 + 64, 416, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384 + 96, 416, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384 + 128, 416, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384 + 32, 416 + 32, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384 + 64, 416 + 32, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.ENEMY, 384 + 96, 416 + 32, 32, 32));*/
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.PLAYER, 288, 128, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.CAMERA, ResX / 2, ResY / 2, 32, 32));
		try {
			int player = world.FindPlayer();
			Camera.Follow(player);
		} catch (PlayerNotFoundException e) {
		}
		SoundController.LoadMusic("res/sounds/Track-03.ogg");
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
		
		HashMap<String, Boolean> held_keys = InputController.HandleHeldInput(input);
		HashMap<String, Boolean> pressed_keys = InputController.HandlePressedInput(input);
		MovementController.HandleMovement(world, held_keys, fps_scaler);
		ActionController.HandleEntityAction(world, pressed_keys, fps_scaler);
		world.tile_dictionary.UpdateAnimations(fps_scaler);
		world.UpdateEntityAnimations(fps_scaler);
		
		if (input.isKeyPressed(Input.KEY_F1)) {
			debug_mode = !debug_mode;
		}
		
		if (input.isKeyDown(Input.KEY_F2)) {
			for (int i = 0; i < world.entities.size(); i++) {
				Entity e = world.entities.get(i);
				if (e.type == EntityDictionary.ENEMY) {
					e.SetPosition(e.x + 30, e.y);
				}
				world.entities.set(i, e);
			}
		}
	}

}
