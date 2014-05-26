package engine;

import java.util.HashMap;

import input.InputController;
import gameplay.MovementController;
import graphics.GraphicsController;

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

public class Main extends BasicGame {
	
	World world;
	
	public Main(String title) {
		super(title);
	}
	
	/**
	 * Function:     main
	 * Description:  Main entry point for the program, initializes Slick2D.
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Default Engine"));
		app.setDisplayMode(800, 600, false);
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
		world.AddEntity(EntityFactory.CreateEntity(EntityDictionary.PLAYER, 1, 1, 32, 32));
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
	}

}
