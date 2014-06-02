package engine;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Menu {
	public String name;
	public Image background;
	public ArrayList<String> menuitems;
	
	public Menu(String menu_name) {
		name = menu_name;
		background = null;
		menuitems = new ArrayList<String>();
	}
	
	public Menu(String menu_name, String background_location) {
		try {
			background = new Image(background_location);
			name = menu_name;
			menuitems = new ArrayList<String>();
		} catch (SlickException e) {
		}
	}
	
	public void AddMenuItem(String data) {
		menuitems.add(data);
	}
	
	public int GetMenuItemCount() {
		return menuitems.size();
	}
	
	public String GetMenuItem(int index) {
		if (index >= 0 && index < GetMenuItemCount()) {
			return menuitems.get(index);
		} else {
			return null;
		}
	}
}
