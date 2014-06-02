package engine;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EntityFactory {
	
	public static Entity CreateEntity(int type, int x, int y, int width, int height) {
		switch (type) {
		case EntityDictionary.CAMERA:
			return CreateCamera(x, y);
		case EntityDictionary.PLAYER:
			return CreatePlayer(x, y, width, height);
		case EntityDictionary.NPC:
			return CreateNPC(x, y, width, height);
		case EntityDictionary.ENEMY:
			return CreateEnemy(x, y, width, height);
		}
		
		return null;
	}
	
	private static Entity CreatePlayer(int x, int y, int width, int height) {
		Entity e = new Entity(EntityDictionary.PLAYER, x, y, width, height);
		e.speed = 0.2f;
		e.controlled = true;
		e.solid = true;
		e.moveable = true;
		
		/* COMBAT SECTION */
		e.c_max_health = 100;
		e.c_health = 100;
		e.c_attack = 10;
		e.c_defence = 5;
		e.c_speed = 50;
		
		Image[] left = new Image[4];
		Image[] right = new Image[4];
		Image[] up = new Image[3];
		Image[] down = new Image[3];
		
		try {
			left[0] = new Image("res/sprites/player/josh_walk_left_1.png");
			left[1] = new Image("res/sprites/player/josh_walk_left_2.png");
			left[2] = new Image("res/sprites/player/josh_walk_left_3.png");
			left[3] = new Image("res/sprites/player/josh_walk_left_4.png");
			
			right[0] = new Image("res/sprites/player/josh_walk_right_1.png");
			right[1] = new Image("res/sprites/player/josh_walk_right_2.png");
			right[2] = new Image("res/sprites/player/josh_walk_right_3.png");
			right[3] = new Image("res/sprites/player/josh_walk_right_4.png");
			
			down[0] = new Image("res/sprites/player/josh_walk_front_1.png");
			down[1] = new Image("res/sprites/player/josh_walk_front_2.png");
			down[2] = new Image("res/sprites/player/josh_walk_front_3.png");
			
			up[0] = new Image("res/sprites/player/josh_walk_back_1.png");
			up[1] = new Image("res/sprites/player/josh_walk_back_2.png");
			up[2] = new Image("res/sprites/player/josh_walk_back_3.png");
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		e.left_anim = new Animation(left, 300);
		e.left_anim.setPingPong(true);
		e.right_anim = new Animation(right, 300);
		e.right_anim.setPingPong(true);
		e.up_anim = new Animation(up, 300);
		e.up_anim.setPingPong(true);
		e.down_anim = new Animation(down, 300);
		e.down_anim.setPingPong(true);
		
		return e;
	}
	
	private static Entity CreateCamera(int x, int y) {
		Entity e = new Entity(EntityDictionary.CAMERA, x, y, 32, 32);
		e.speed = 0.2f;
		e.controlled = false;
		e.solid = false;
		e.moveable = false;
		return e;
	}
	
	private static Entity CreateNPC(int x, int y, int width, int height) {
		Entity e = new Entity(EntityDictionary.NPC, x, y, width, height);
		e.speed = 0f;
		e.controlled = false;
		e.solid = true;
		e.moveable = false;
		
		Image[] down = new Image[1];
		
		try {
			down[0] = new Image("res/sprites/npc.png");
		} catch (SlickException e1) {
		}
		e.down_anim = new Animation(down, 1);
		
		return e;
	}
	
	private static Entity CreateEnemy(int x, int y, int width, int height) {
		Entity e = new Entity(EntityDictionary.ENEMY, x, y, width, height);
		e.speed = 0.1f;
		e.controlled = false;
		e.solid = true;
		e.moveable = true;
		
		/* COMBAT SECTION */
		e.c_max_health = 50;
		e.c_health = 50;
		e.c_attack = 10;
		e.c_defence = 3;
		e.c_speed = 5;
		
		Image[] down = new Image[1];
		
		try {
			down[0] = new Image("res/sprites/enemy.png");
		} catch (SlickException e1) {
		}
		e.down_anim = new Animation(down, 1);
		
		return e;
	}
	
	public static Entity CreateDialog(int x, int y) {
		Entity e = new Entity(EntityDictionary.DIALOG_BOX, x, y, 240, 160);
		e.speed = 0f;
		e.controlled = false;
		e.solid = false;
		e.moveable = false;
		
		Image[] down = new Image[1];
		
		try {
			down[0] = new Image("res/sprites/dialog_box.png");
		} catch (SlickException e1) {
		}
		e.down_anim = new Animation(down, 1);
		
		return e;
	}
	
}
