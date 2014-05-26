package engine;

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
			switch(direction) {
			case Direction.LEFT:
				this.movx -= speed * fps_scaler;
				this.x = (int)Math.floor(this.movx);
				break;
			case Direction.RIGHT:
				this.movx += speed * fps_scaler;
				this.x = (int)Math.floor(this.movx);
				break;
			case Direction.UP:
				this.movy -= speed * fps_scaler;
				this.y = (int)Math.floor(this.movy);
				break;
			case Direction.DOWN:
				this.movy += speed * fps_scaler;
				this.y = (int)Math.floor(this.movy);
				break;
			}
		}
	}
}
