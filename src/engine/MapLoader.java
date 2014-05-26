package engine;

import java.io.File;
import java.util.Scanner;

public class MapLoader {
	
	private static Scanner scan;
	
	/**
	 * Loads a map into a world object from a file.
	 * @param map_location
	 * @return true if map load is successful
	 */
	public static boolean LoadMap(World world, String map_location) {
		File fmap = new File(map_location);
		try {
			scan = new Scanner(fmap);
			if (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner lineScanner = new Scanner(line);
				int width = 0, height = 0, tilesize = 0;
				if (lineScanner.hasNextInt()) {
					width = lineScanner.nextInt();
				} else {
					System.out.println("Error on line: 1 - Missing Width Parameter.");
					lineScanner.close();
					throw new Exception();
				}
				if (lineScanner.hasNextInt()) {
					height = lineScanner.nextInt();
				} else {
					System.out.println("Error on line: 1 - Missing Height Parameter.");
					lineScanner.close();
					throw new Exception();
				}
				if (lineScanner.hasNextInt()) {
					tilesize = lineScanner.nextInt();
				} else {
					System.out.println("Error on line: 1 - Missing Tile Size Parameter.");
					lineScanner.close();
					throw new Exception();
				}
				world.Init(width, height, tilesize);
				lineScanner.close();
			}
			for (int y = 0; y < world.height; y++) {
				if (scan.hasNextLine()) {
					String line = scan.nextLine();
					Scanner lineScanner = new Scanner(line);
					for (int x = 0; x < world.width; x++) {
						if (lineScanner.hasNextInt()) {
							int type = lineScanner.nextInt();
							world.tile[y][x] = TileFactory.CreateTile(type, x, y, world.tilesize);
						} else {
							System.out.println("Error! There seems to be too few columns of tiles.");
							lineScanner.close();
							throw new Exception();
						}
					}
					lineScanner.close();
				} else {
					System.out.println("Error! There seems to be too few rows of tiles.");
					throw new Exception();
				}
			}
			scan.close();
		} catch (Exception e) {
			System.out.println("Failed to load map.");
			world.width = 0;
			world.tilesize = 0;
			world.height = 0;
			world.tile = null;
			world.entities = null;
			scan.close();
			return false;
		}
		return true;
	}
}
