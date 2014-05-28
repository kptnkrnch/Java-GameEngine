package engine;

import java.util.HashMap;

import input.InputController;
import entities.Camera;
import exceptions.CameraNotFoundException;
import exceptions.PlayerNotFoundException;
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
	
	World world;
	public static final int ResX = 800;
	public static final int ResY = 600;
	
	public static Image[] w;// = new Image[2];
	//w[0] = new Image("res/animations/water_animation/water-1.png");
	//w[1] = new Image("res/animations/water_animation/water-2.png");
	public static Animation wa;// = new Animation(w, 1000, true);
	
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
		MapLoader.LoadMap(world, "res/maps/Map02.map");
		InputController.LoadKeyMapping("res/config/keymap.conf");
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.PLAYER, 288, 128, 32, 32));
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.CAMERA, ResX / 2, ResY / 2, 32, 32));
		try {
			int player = world.FindPlayer();
			Camera.Follow(player);
		} catch (PlayerNotFoundException e) {
		}
		
		w = new Image[2];
		w[0] = new Image("res/animations/water_animation/water-1.png");
		w[1] = new Image("res/animations/water_animation/water-2.png");
		wa = new Animation(w, 700);
	}
	
	/**
	 * Function:     update
	 * Description:  Handles input, movement, and actions during the Slick2D Game Loop.
	 */
	@Override
	public void update(GameContainer gc, int fps_scaler) throws SlickException {
		Input input = gc.getInput();
		HashMap<String, Boolean> pressed = InputController.HandleInput(input);
		MovementController.HandleMovement(world, pressed, fps_scaler);
		wa.update(fps_scaler);
	}

}
