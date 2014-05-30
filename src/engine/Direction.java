package engine;

public class Direction {
	public static final int NONE = -1;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	public static int Opposite(int direction) {
		switch(direction) {
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		}
		return 0;
	}
}
