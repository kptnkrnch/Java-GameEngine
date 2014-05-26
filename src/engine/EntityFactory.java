package engine;

public class EntityFactory {
	
	public static Entity CreateEntity(int type, int x, int y, int width, int height) {
		switch (type) {
		case EntityDictionary.PLAYER:
			return CreatePlayer(x, y, width, height);
		case EntityDictionary.NPC:
			break;
		case EntityDictionary.ENEMY:
			break;
		}
		
		return null;
	}
	
	private static Entity CreatePlayer(int x, int y, int width, int height) {
		Entity e = new Entity(EntityDictionary.PLAYER, x, y, width, height);
		e.speed = 0.2f;
		e.controlled = true;
		e.solid = true;
		e.moveable = true;
		return e;
	}
	
}
