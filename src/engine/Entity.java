package engine;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Animation;

import pathing.Path;
import pathing.PathFinder;

public class Entity {
	public int id;
	public int x;
	public int y;
	public int width;
	public int height;
	public int type;
	public float movx;
	public float movy;
	public float speed;
	public boolean solid;
	public boolean controlled;
	public boolean moveable;
	public Rectangle collision_box;
	public Rectangle image_box;
	
	public ArrayList<String> dialog;
	
	public int last_direction;
	private float last_distance;
	public int last_animation;
	
	public Animation left_anim;
	public Animation right_anim;
	public Animation down_anim;
	public Animation up_anim;
	public boolean animating;
	
	public PathFinder pathFinder;
	public Path path;
	
	public Entity(Entity temp) {
		this.id = temp.id;
		this.x = temp.x;
		this.y = temp.y;
		this.width = temp.width;
		this.height = temp.height;
		this.type = temp.type;
		this.movx = temp.movx;
		this.movy = temp.movy;
		this.speed = temp.speed;
		this.solid = temp.solid;
		this.controlled = temp.controlled;
		this.moveable = temp.moveable;
		this.collision_box = new Rectangle(temp.collision_box);
		this.last_direction = temp.last_direction;
		this.last_distance = temp.last_distance;
		
		this.left_anim = temp.left_anim;
		this.right_anim = temp.right_anim;
		this.down_anim = temp.down_anim;
		this.up_anim = temp.up_anim;
		this.last_animation = temp.last_animation;
		this.animating = temp.animating;
		
		this.pathFinder = temp.pathFinder;
		this.path = temp.path;
	}

	public Entity(int type, int x, int y, int width, int height) {
		this.id = -1;
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.movx = x;
		this.movy = y;
		this.solid = false;
		this.controlled = false;
		this.moveable = false;
		this.speed = 0f;
		this.collision_box = new Rectangle(x + 1, y + 1, width - 2, height - 2);
		
		this.last_distance = 0;
		this.last_direction = -1;
		
		this.left_anim = null;
		this.right_anim = null;
		this.down_anim = null;
		this.up_anim = null;
		this.last_animation = Direction.NONE;
		this.animating = false;
		
		this.pathFinder = new PathFinder();
		this.path = new Path();
	}
	
	public boolean IsMoveable() {
		return moveable;
	}
	
	public boolean IsSolid() {
		return solid;
	}
	
	public boolean IsControlled() {
		return controlled;
	}
	
	public void Move(int direction, float speed, int fps_scaler) {
		if (this.IsMoveable()) {
			float distance = speed * fps_scaler;
			switch(direction) {
			case Direction.LEFT:
				this.movx -= distance;
				this.x = (int)Math.floor(this.movx);
				this.last_direction = direction;
				this.last_distance = distance;
				this.animating = true;
				break;
			case Direction.RIGHT:
				this.movx += distance;
				this.x = (int)Math.floor(this.movx);
				this.last_direction = direction;
				this.last_distance = distance;
				this.animating = true;
				break;
			case Direction.UP:
				this.movy -= distance;
				this.y = (int)Math.floor(this.movy);
				this.last_direction = direction;
				this.last_distance = distance;
				this.animating = true;
				break;
			case Direction.DOWN:
				this.movy += distance;
				this.y = (int)Math.floor(this.movy);
				this.last_direction = direction;
				this.last_distance = distance;
				this.animating = true;
				break;
			default:
				this.last_direction = Direction.NONE;
				this.last_distance = 0;
				this.animating = false;
				break;
			}
			this.collision_box.x = this.x + 1;
			this.collision_box.y = this.y + 1;
		}
	}
	
	public void UndoLastMove() {
		if (this.IsMoveable()) {
			int direction = Direction.Opposite(this.last_direction);
			float distance = this.last_distance;
			switch(direction) {
			case Direction.LEFT:
				this.movx -= distance;
				this.x = (int)Math.floor(this.movx);
				//this.last_direction = -1;
				this.last_distance = 0;
				break;
			case Direction.RIGHT:
				this.movx += distance;
				this.x = (int)Math.floor(this.movx);
				//this.last_direction = -1;
				this.last_distance = 0;
				break;
			case Direction.UP:
				this.movy -= distance;
				this.y = (int)Math.floor(this.movy);
				//this.last_direction = -1;
				this.last_distance = 0;
				break;
			case Direction.DOWN:
				this.movy += distance;
				this.y = (int)Math.floor(this.movy);
				//this.last_direction = -1;
				this.last_distance = 0;
				break;
			}
			this.collision_box.x = this.x + 1;
			this.collision_box.y = this.y + 1;
		}
	}
	
	public void SetPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.collision_box.x = this.x;
		this.collision_box.y = this.y;
	}
	
	public boolean Intersects(Tile tile) {
		return collision_box.intersects(tile.collision_box);
	}
	
	public boolean Intersects(Entity entity) {
		return collision_box.intersects(entity.collision_box);
	}
	
	public void Copy(Entity temp) {
		this.x = temp.x;
		this.y = temp.y;
		this.width = temp.width;
		this.height = temp.height;
		this.type = temp.type;
		this.movx = temp.movx;
		this.movy = temp.movy;
		this.speed = temp.speed;
		this.solid = temp.solid;
		this.controlled = temp.controlled;
		this.moveable = temp.moveable;
		this.collision_box.x = temp.collision_box.x;
		this.collision_box.y = temp.collision_box.y;
		this.last_direction = temp.last_direction;
		this.last_distance = temp.last_distance;
		this.last_animation = temp.last_animation;
	}
	
	public void UpdateAnimations(int fps_scaler) {
		if (left_anim != null) {
			left_anim.update(fps_scaler);
		}
		if (right_anim != null) {
			right_anim.update(fps_scaler);
		}
		if (down_anim != null) {
			down_anim.update(fps_scaler);
		}
		if (up_anim != null) {
			up_anim.update(fps_scaler);
		}
	}
	
	public boolean IsAnimating() {
		return this.animating;
	}
	
}
