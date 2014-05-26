package engine;

import java.io.File;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TileDictionary {
	
	public static final int GRASS = 1;
	public static final int TREE = 2;
	public static final int WATER = 3;
	
	public Image[] tiles;
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
					tiles = new Image[entry_count];
					types = new int[entry_count];
				} else {
					System.out.println("Invalid dictionary file. Missing entry count.");
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
						System.out.println("Error on line: " + (i + 1));
						System.out.println("Missing tile type code.");
						lineScanner.close();
						throw new Exception();
					}
					if (lineScanner.hasNext()) {
						String image_src = lineScanner.next();
						try {
							tiles[i] = new Image(image_src);
						} catch (SlickException e) {
							System.out.println("Could not find image resource.");
							lineScanner.close();
							throw new Exception();
						}
					} else {
						System.out.println("Error on line: " + (i + 1));
						System.out.println("Missing tile image location.");
						lineScanner.close();
						throw new Exception();
					}
					lineScanner.close();
				}
			}
			scan.close();
		} catch (Exception e) {
			System.out.println("Failed to load Tile Dictionary.");
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
	public Image GetImage(int type) {
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
					tiles = new Image[entry_count];
					types = new int[entry_count];
				} else {
					System.out.println("Invalid dictionary file. Missing entry count.");
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
						System.out.println("Error on line: " + (i + 1));
						System.out.println("Missing tile type code.");
						lineScanner.close();
						throw new Exception();
					}
					if (lineScanner.hasNext()) {
						String image_src = lineScanner.next();
						try {
							tiles[i] = new Image(image_src);
						} catch (SlickException e) {
							System.out.println("Could not find image resource.");
							lineScanner.close();
							throw new Exception();
						}
					} else {
						System.out.println("Error on line: " + (i + 1));
						System.out.println("Missing tile image location.");
						lineScanner.close();
						throw new Exception();
					}
					lineScanner.close();
				}
			}
			scan.close();
		} catch (Exception e) {
			System.out.println("Failed to load Tile Dictionary.");
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
}
