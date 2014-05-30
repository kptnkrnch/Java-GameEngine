package pathing;
import java.util.ArrayList;
import java.util.Iterator;

import engine.Direction;
import engine.Tile;


public class PathFinder {
	private PathTile[][] map;
	private int startX, startY;
	private int targetX, targetY;
	private int tilesize;
	private boolean found;
	private boolean finding;
	
	public PathFinder() {
		map = null;
		startX = 0;
		startY = 0;
		targetX = 0;
		targetY = 0;
		tilesize = 0;
		found = false;
		finding = false;
	}
	
	
	public void run() {
		this.finding = true;
		ArrayList<PathTile> open = new ArrayList<PathTile>();
		ArrayList<PathTile> closed = new ArrayList<PathTile>();
		PathTile current;
		
		for (int x = 0; x < this.map.length; x++) {
			for (int y = 0; y < this.map[0].length; y++) {
				this.map[x][y].h = Math.abs(this.targetX - x) + Math.abs(this.targetY - y);
			}
		}
		
		current = CalculateTotalCost(this.map, this.startX, this.startY, open, closed);
		
		this.found = false;
		while (!this.found) {
			current = FindLeastCost(this.map, open, closed);
			if (current.x == this.targetX && current.y == this.targetY) {
				this.found = true;
			}
			if (open.size() == 0) {
				break;
			}
		}
		this.finding = false;
	}
	
	/* Takes in game coordinates, not tile coordinates */
	public void SetStart(int x, int y) {
		if (map != null) {
			startX = (int)Math.round(Math.floor((double)x / tilesize));
			startY = (int)Math.round(Math.floor((double)y / tilesize));
		}
	}
	
	/* Takes in game coordinates, not tile coordinates */
	public void SetTarget(int x, int y) {
		if (map != null) {
			targetX = (int)Math.round(Math.floor((double)x / tilesize));
			targetY = (int)Math.round(Math.floor((double)y / tilesize));
		}
	}
	
	public void LoadMap(Tile[][] tile) {
		if (tile != null && tile.length > 0 && tile[0].length > 0) {
			this.map = new PathTile[tile.length][tile[0].length];
			this.tilesize = tile[0][0].width;
			int id = 0;
			
			for (int y = 0; y < tile[0].length; y++) {
				for (int x = 0; x < tile.length; x++) {
					this.map[x][y] = new PathTile(id, x, y, tile[x][y].IsSolid());
					id++;
				}
			}
		}
	}
	
	public PathTile GetNorth(PathTile[][] map, int x, int y) {
		if (y > 0) {
			return map[x][y - 1];
		} else {
			return null;
		}
	}
	
	public PathTile GetSouth(PathTile[][] map, int x, int y) {
		if (y < map[0].length - 1) {
			return map[x][y + 1];
		} else {
			return null;
		}
	}
	
	public PathTile GetEast(PathTile[][] map, int x, int y) {
		if (x < map.length - 1) {
			return map[x + 1][y];
		} else {
			return null;
		}
	}
	
	public PathTile GetWest(PathTile[][] map, int x, int y) {
		if (x > 0) {
			return map[x - 1][y];
		} else {
			return null;
		}
	}
	
	public PathTile CalculateTotalCost(PathTile[][] map, int x, int y, ArrayList<PathTile> open, ArrayList<PathTile> closed) {
		PathTile current = map[x][y];
		PathTile temp;
		
		temp = GetNorth(map, x, y);
		if (temp != null && !temp.solid) {
			if (!closed.contains(temp) && !open.contains(temp)) {
				temp.g = current.g + 1;
				temp.f = temp.g + temp.h;
				temp.parent = current;
				map[temp.x][temp.y] = temp;
				open.add(temp);
			}
		}
		
		temp = GetSouth(map, x, y);
		if (temp != null && !temp.solid) {
			if (!closed.contains(temp) && !open.contains(temp)) {
				temp.g = current.g + 1;
				temp.f = temp.g + temp.h;
				temp.parent = current;
				map[temp.x][temp.y] = temp;
				open.add(temp);
			}
		}
		
		temp = GetEast(map, x, y);
		if (temp != null && !temp.solid) {
			if (!closed.contains(temp) && !open.contains(temp)) {
				temp.g = current.g + 1;
				temp.f = temp.g + temp.h;
				temp.parent = current;
				map[temp.x][temp.y] = temp;
				open.add(temp);
			}
		}
		
		temp = GetWest(map, x, y);
		if (temp != null && !temp.solid) {
			if (!closed.contains(temp) && !open.contains(temp)) {
				temp.g = current.g + 1;
				temp.f = temp.g + temp.h;
				temp.parent = current;
				map[temp.x][temp.y] = temp;
				open.add(temp);
			}
		}
		
		
		open.remove(current);
		closed.add(current);
		
		return current;
	}
	
	public PathTile FindLeastCost(PathTile[][] map, ArrayList<PathTile> open, ArrayList<PathTile> closed) {
		Iterator<PathTile> open_it = open.iterator();
		int leastX = 0;
		int leastY = 0;
		int lowestCost = Integer.MAX_VALUE;
		while (open_it.hasNext()) {
			PathTile temp = open_it.next();
			if (temp.f < lowestCost) {
				lowestCost = temp.f;
				leastX = temp.x;
				leastY = temp.y;
			}
		}
		return CalculateTotalCost(map, leastX, leastY, open, closed);
	}
	
	/* returned Path contains game coordinates, not tile coordinates */
	public Path FindPath() {
		if (this.found) {
			PathTile end = this.map[this.targetX][this.targetY];
			PathTile current = end.parent;
			ArrayList<PathTile> temp = new ArrayList<PathTile>();
			Path path = new Path();
			
			end.onPath = true;
			temp.add(end);
			while (current != null) {
				current.onPath = true;
				if (!(current.x == this.startX && current.y == this.startY)) {
					temp.add(current);
				}
				current = current.parent;
			}
			
			for (int i = temp.size() - 1; i >= 0; i--) {
				PathTile tempTile = temp.get(i);
				
				if (tempTile.x < this.startX) {
					path.AddTarget(tempTile.x * tilesize, tempTile.x * tilesize, Direction.LEFT);
				} else if (tempTile.x > this.startX) {
					path.AddTarget(tempTile.x * tilesize, tempTile.x * tilesize, Direction.RIGHT);
				} else if (tempTile.y < this.startY) {
					path.AddTarget(tempTile.x * tilesize, tempTile.x * tilesize, Direction.UP);
				} else if (tempTile.y > this.startY) {
					path.AddTarget(tempTile.x * tilesize, tempTile.x * tilesize, Direction.DOWN);
				}
			}
			
			return path;
		} else {
			return null;
		}
		
	}
	
	public boolean IsFound() {
		return this.found;
	}
	
	public boolean IsFinding() {
		return this.finding;
	}
	
}
