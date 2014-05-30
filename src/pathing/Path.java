package pathing;

import java.util.ArrayList;

import engine.Direction;
import engine.Entity;

public class Path {
	
	public ArrayList<Integer> targetX;
	public ArrayList<Integer> targetY;
	public ArrayList<Integer> direction;
	public int currentTarget;
	public int targetCount;
	
	public Path() {
		targetX = new ArrayList<Integer>();
		targetY = new ArrayList<Integer>();
		direction = new ArrayList<Integer>();
		currentTarget = 0;
		targetCount = 0;
	}
	
	public int GetNextDirection() {
		return direction.get(currentTarget);
	}
	
	public void AddTarget(int x, int y, int direction) {
		this.targetX.add(x);
		this.targetY.add(y);
		this.direction.add(direction);
		this.targetCount += 1;
	}
	
	public int getDistance(Entity e) {
		int distance = 0;
		
		switch (direction.get(currentTarget)) {
		case Direction.LEFT:
			distance = Math.abs(e.collision_box.x - targetX.get(currentTarget));
			break;
		case Direction.RIGHT:
			distance = Math.abs(e.collision_box.x - targetX.get(currentTarget));
			break;
		case Direction.UP:
			distance = Math.abs(e.collision_box.y - targetY.get(currentTarget));
			break;
		case Direction.DOWN:
			distance = Math.abs(e.collision_box.y - targetY.get(currentTarget));
			break;
		}
		
		return distance;
	}
	
}
