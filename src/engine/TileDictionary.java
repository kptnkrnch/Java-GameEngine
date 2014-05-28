package engine;

import graphics.AnimationLoader;

import java.io.File;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TileDictionary {
	
	public static final int GRASS = 1;
	public static final int TREE = 2;
	public static final int WATER = 3;
	
	public Animation[] tiles;
	
	public int[] types;
	
	private int entry_count;
	private Scanner scan;
	
	public TileDictionary() {
		
	}
	
	/**
	 * Loads a new tile image dictionary.
	 * @param dictionary_location - the location of the tile dictionary file.
	 */
	public TileDictionary(String dictionary_location) {
		entry_count = 0;
		File dictionary = new File(dictionary_location);
		try {
			scan = new Scanner(dictionary);
			if (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner lineScanner = new Scanner(line);
				if (lineScanner.hasNextInt()) {
					entry_count = lineScanner.nextInt();
					tiles = new Animation[entry_count];
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
						System.err.println("Missing tile type code.");
						lineScanner.close();
						throw new Exception();
					}
					if (lineScanner.hasNext()) {
						String image_src = lineScanner.next();
						try {
							Image[] temp = new Image[1];
							temp[0] = new Image(image_src);
							tiles[i] = new Animation(temp, 0);
						} catch (SlickException e) {
							System.err.println("Could not find image resource.");
							lineScanner.close();
							throw new Exception();
						}
					} else {
						System.err.println("Error on line: " + (i + 1));
						System.err.println("Missing tile image location.");
						lineScanner.close();
						throw new Exception();
					}
					lineScanner.close();
				}
			}
			scan.close();
		} catch (Exception e) {
			System.err.println("Failed to load Tile Dictionary.");
			if (entry_count != 0) {
				try {
					for (int i = 0; i < entry_count; i++) {
						tiles[i] = null;
						types[i] = -1;
					}
				} catch (Exception outofbounds) {
					
				}
			}
			entry_count = 0;
		}
	}
	
	/**
	 * Looks up an image in the dictionary based on a tiles type.
	 * @param type - the tile type to look up.
	 * @return the image if it is found, else null.
	 */
	public Animation GetImage(int type) {
		for (int i = 0; i < this.entry_count; i++) {
			if (type == this.types[i]) {
				return tiles[i];
			}
		}
		return null;
	}
	
	/**
	 * Loads a new tile image dictionary.
	 * @param dictionary_location - the location of the tile dictionary file.
	 * @return true if the dictionary was successfully loaded.
	 */
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
					tiles = new Animation[entry_count];
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
						System.err.println("Missing tile type code.");
						lineScanner.close();
						throw new Exception();
					}
					if (lineScanner.hasNext()) {
						String anim_src = lineScanner.next();
						AnimationLoader loader = new AnimationLoader(anim_src);
						if (loader.IsLoaded()) {
							tiles[i] = new Animation(loader.frames, loader.durations);
						} else {
							System.err.println("Failed to load animation on line: " + (i + 2));
							lineScanner.close();
							throw new Exception();
						}
					} else {
						System.err.println("Error on line: " + (i + 1));
						System.err.println("Missing tile image location.");
						lineScanner.close();
						throw new Exception();
					}
					lineScanner.close();
				}
			}
			scan.close();
		} catch (Exception e) {
			System.err.println("Failed to load Tile Dictionary.");
			if (entry_count != 0) {
				try {
					for (int i = 0; i < entry_count; i++) {
						tiles[i] = null;
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
	
	public void UpdateAnimations(int fps_scaler) {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].update(fps_scaler);
		}
	}
}
