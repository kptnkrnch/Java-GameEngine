package input;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import org.newdawn.slick.Input;

public class InputController {
	private static HashMap<String, Boolean> input = null;
	private static HashMap<String, Integer> keymap = null;
	
	public static HashMap<String, Boolean> HandleInput(Input input_obj) {
		if (keymap != null) {
			
			/* Resets the input pressed map */
			Iterator<Entry<String, Boolean>> inputIterator = input.entrySet().iterator();
			while (inputIterator.hasNext()) {
				Entry<String, Boolean> current = inputIterator.next();
				input.put(current.getKey(), false);
			}
			
			Iterator<Entry<String, Integer>> mapIterator = keymap.entrySet().iterator();
			while (mapIterator.hasNext()) {
				Entry<String, Integer> current = mapIterator.next();
				if (input_obj.isKeyDown(current.getValue())) {
					input.put(current.getKey(), true);
				}
			}
			return input;
		} else {
			return null;
		}
	}
	
	public static boolean LoadKeyMapping(String keyMapConfig_location) {
		keymap = new HashMap<String, Integer>();
		input = new HashMap<String, Boolean>();
		File keyMapFile = new File(keyMapConfig_location);
		try {
			Scanner scan = new Scanner(keyMapFile);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String command = null;
				int key = -1;
				line = line.replace('=', ' ');
				Scanner lineScanner = new Scanner(line);
				
				if (lineScanner.hasNext()) {
					command = lineScanner.next();
				} else {
					System.out.println("Error! Invalid key config file!");
					lineScanner.close();
					throw new Exception();
				}
				
				if (lineScanner.hasNextInt()) {
					key = lineScanner.nextInt();
				} else {
					System.out.println("Error! Invalid key config file!");
					lineScanner.close();
					throw new Exception();
				}
				
				lineScanner.close();
				keymap.put(command, key);
				input.put(command, false);
			}
			scan.close();
		} catch (Exception e) {
			keymap = null;
			input = null;
		}
		
		return true;
	}
}
