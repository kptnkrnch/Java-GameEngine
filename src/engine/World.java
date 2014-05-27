package engine;

import java.util.ArrayList;

import exceptions.CameraNotFoundException;
import exceptions.PlayerNotFoundException;

public class World {
	
	public int width;
	public int height;
	public int tilesize;
	public Tile[][] tile;
	public ArrayList<Entity> entities;
	public TileDictionary tile_dictionary;
	public EntityDictionary entity_dictionary;
	
	public World() {
		this.tile_dictionary = new TileDictionary();
		this.entity_dictionary = new EntityDictionary();
	}
	
	public void Init(int width, int height, int tilesize) {
		this.width = width;
		this.height = height;
		this.tilesize = tilesize;
		this.tile = new Tile[width][height];
		this.entities = new ArrayList<Entity>();
	}
	
	public boolean LoadTileDictionary(String dictionary_location) {
		return this.tile_dictionary.LoadDictionary(dictionary_location);
	}
	
	public boolean LoadEntityDictionary(String dictionary_location) {
		return this.entity_dictionary.LoadDictionary(dictionary_location);
	}
	
	public Tile GetTile(int x, int y) {
		return this.tile[x][y];
	}
	
	public int GetEntityCount() {
		return this.entities.size();
	}
	
	public Entity GetEntity(int index) {
		return this.entities.get(index);
	}
	
	public void SetEntity(int index, Entity entity) {
		this.entities.set(index, entity);
	}
	
	public void AddEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public int FindPlayer() throws PlayerNotFoundException {
		boolean found = false;
		int index = -1;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).type == EntityDictionary.PLAYER) {
				found = true;
				index = i;
				break;
			}
		}
		
		if (found) {
			return index;
		} else {
			throw new PlayerNotFoundException();
		}
	}
	
	public int FindCamera() throws CameraNotFoundException {
		boolean found = false;
		int index = -1;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).type == EntityDictionary.CAMERA) {
				found = true;
				index = i;
				break;
			}
		}
		
		if (found) {
			return index;
		} else {
			throw new CameraNotFoundException();
		}
	}
	
}
