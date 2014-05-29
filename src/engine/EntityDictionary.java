package engine;

import java.io.File;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class EntityDictionary {
	
	public static final int CAMERA = 0;
	public static final int PLAYER = 1;
	public static final int NPC = 2;
	public static final int ENEMY = 3;
	
	public Image[] sprites;
	public String[] animation_files;
	public int[] types;
	
	private int entry_count;
	private Scanner scan;
	
	public EntityDictionary() {
		
	}
	
	public EntityDictionary(String dictionary_location) {
		entry_count = 0;
		File dictionary = new File(dictionary_location);
		try {
			scan = new Scanner(dictionary);
			if (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner lineScanner = new Scanner(line);
				if (lineScanner.hasNextInt()) {
					entry_count = lineScanner.nextInt();
					sprites = new Image[entry_count];
					types = new int[entry_count];
				} else {
					System.err.println("Invalid dictionary file. Missing entry count.");
					lineScanner.close();
					throw new Exception();
				}
				lineScanner.close();
			}
			for (int i = 0; i < entry_count; i++) {
				if (scan.hasNextLine()) {
					String line = scan.nextLine();
					Scanner lineScanner = new Scanner(line);
					if (lineScanner.hasNextInt()) {
						types[i] = lineScanner.nextInt();
					} else {
						System.err.println("Error on line: " + (i + 1));
						System.err.println("Missing entity type code.");
						lineScanner.close();
						throw new Exception();
					}
					if (lineScanner.hasNext()) {
						String image_src = lineScanner.next();
						try {
							sprites[i] = new Image(image_src);
						} catch (SlickException e) {
							System.err.println("Could not find image resource.");
							lineScanner.close();
							throw new Exception();
						}
					} else {
						System.err.println("Error on line: " + (i + 1));
						System.err.println("Missing entity image location.");
						lineScanner.close();
						throw new Exception();
					}
					lineScanner.close();
				}
			}
			scan.close();
		} catch (Exception e) {
			System.err.println("Failed to load Entity Dictionary.");
			if (entry_count != 0) {
				try {
					for (int i = 0; i < entry_count; i++) {
						sprites[i] = null;
						types[i] = -1;
					}
				} catch (Exception outofbounds) {
					
				}
			}
			entry_count = 0;
		}
	}
	
	public Image GetImage(int type) {
		for (int i = 0; i < this.entry_count; i++) {
			if (type == this.types[i]) {
				return sprites[i];
			}
		}
		return null;
	}
	
	public boolean LoadDictionary(String dictionary_location) {
		entry_count = 0;
		File dictionary = new File(dictionary_location);
		try {
			scan = new Scanner(dictionary);
			if (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner lineScanner = new Scanner(line);
				if (lineScanner.hasNextInt()) {
					entry_count = lineScanner.nextInt();
					sprites = new Image[entry_count];
					types = new int[entry_count];
				} else {
					System.err.println("Invalid dictionary file. Missing entry count.");
					lineScanner.close();
					throw new Exception();
				}
				lineScanner.close();
			}
			for (int i = 0; i < entry_count; i++) {
				if (scan.hasNextLine()) {
					String line = scan.nextLine();
					Scanner lineScanner = new Scanner(line);
					if (lineScanner.hasNextInt()) {
						types[i] = lineScanner.nextInt();
					} else {
						System.err.println("Error on line: " + (i + 1));
						System.err.println("Missing entity type code.");
						lineScanner.close();
						throw new Exception();
					}
					if (lineScanner.hasNext()) {
						String image_src = lineScanner.next();
						try {
							sprites[i] = new Image(image_src);
						} catch (SlickException e) {
							System.err.println("Could not find image resource.");
							lineScanner.close();
							throw new Exception();
						}
					} else {
						System.err.println("Error on line: " + (i + 1));
						System.err.println("Missing entity image location.");
						lineScanner.close();
						throw new Exception();
					}
					lineScanner.close();
				}
			}
			scan.close();
		} catch (Exception e) {
			System.err.println("Failed to load Entity Dictionary.");
			if (entry_count != 0) {
				try {
					for (int i = 0; i < entry_count; i++) {
						sprites[i] = null;
						types[i] = -1;
					}
				} catch (Exception outofbounds) {
					return false;
				}
			}
			entry_count = 0;
			return false;
		}
		return true;
	}
}
