package engine;

import java.awt.Rectangle;

public class Entity {
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
	public Rectangle bounding_box;
	
	private int last_direction;
	private float last_distance;
	
	public Entity(Entity temp) {
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
		this.bounding_box = new Rectangle(temp.bounding_box);
		this.last_direction = temp.last_direction;
		this.last_distance = temp.last_distance;
	}

	public Entity(int type, int x, int y, int width, int height) {
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
		this.bounding_box = new Rectangle(x + 1, y + 1, width - 2, height - 2);
		
		this.last_distance = 0;
		this.last_direction = -1;
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
				break;
			case Direction.RIGHT:
				this.movx += distance;
				this.x = (int)Math.floor(this.movx);
				this.last_direction = direction;
				this.last_distance = distance;
				break;
			case Direction.UP:
				this.movy -= distance;
				this.y = (int)Math.floor(this.movy);
				this.last_direction = direction;
				this.last_distance = distance;
				break;
			case Direction.DOWN:
				this.movy += distance;
				this.y = (int)Math.floor(this.movy);
				this.last_direction = direction;
				this.last_distance = distance;
				break;
			}
			this.bounding_box.x = this.x + 1;
			this.bounding_box.y = this.y + 1;
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
				this.last_direction = -1;
				this.last_distance = 0;
				break;
			case Direction.RIGHT:
				this.movx += distance;
				this.x = (int)Math.floor(this.movx);
				this.last_direction = -1;
				this.last_distance = 0;
				break;
			case Direction.UP:
				this.movy -= distance;
				this.y = (int)Math.floor(this.movy);
				this.last_direction = -1;
				this.last_distance = 0;
				break;
			case Direction.DOWN:
				this.movy += distance;
				this.y = (int)Math.floor(this.movy);
				this.last_direction = -1;
				this.last_distance = 0;
				break;
			}
			this.bounding_box.x = this.x + 1;
			this.bounding_box.y = this.y + 1;
		}
	}
	
	public void SetPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.bounding_box.x = this.x;
		this.bounding_box.y = this.y;
	}
	
	public boolean Intersects(Tile tile) {
		return bounding_box.intersects(tile.bounding_box);
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
		this.bounding_box.x =temp.bounding_box.x;
		this.bounding_box.y =temp.bounding_box.y;
		this.last_direction = temp.last_direction;
		this.last_distance = temp.last_distance;
	}
	
}
