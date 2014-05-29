package engine;

public class TileFactory {
	
	public static Tile CreateTile(int type, int x, int y, int tilesize) {
		
		Tile tile = null;
		
		switch(type) {
		case TileDictionary.GRASS: //grass tile
			tile = new Tile(type, x * tilesize, y * tilesize, tilesize, tilesize);
			break;
		case TileDictionary.TREE: //tree tile
			tile = new Tile(type, x * tilesize, y * tilesize, tilesize, tilesize, true);
			break;
		case TileDictionary.WATER: //water tile
			tile = new Tile(type, x * tilesize, y * tilesize, tilesize, tilesize, true);
			break;
		case TileDictionary.BEACH_TOP:
			tile = new Tile(type, x * tilesize, y * tilesize, tilesize, tilesize);
		}
		
		return tile;
	}
	
}
