package homework06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A table for lookups and reverse lookups
 */
public class TwitterTable {
	private ArrayList<Item> byName;
	private ArrayList<Item> byAddress;

	/**
	 * Constructs a TwitterTable object.
	 */
	public TwitterTable() {
		byName = new ArrayList<Item>();
		byAddress = new ArrayList<Item>();
	}
   
	/**
	 * Reads name and twitter address pairs from the Scanner and adds them to
	 * the byName and byAddress array lists.
	 * 
	 * @param in -- Scanner for reading the input
	 */
	public void read(Scanner in) throws IOException {
		// Reads in the file and parses the values into the proper Array Lists
		try {
			String tempName = "";
			String tempHandle = "";
			while (in.hasNextLine()) {
				tempName = in.nextLine();
				tempHandle = in.nextLine();
				if (!(tempName.substring(0, 0).equals("@"))) {
					byName.add(new Item(tempName, tempHandle));
					byAddress.add(new Item(tempHandle, tempName));
				} else {
					byName.add(new Item(tempHandle, tempName));
					byAddress.add(new Item(tempName, tempHandle));
				}
			}
		} catch (Exception e) {
			System.out.println("Incorrect file input. Please check your formatting and try again.");
		}
	}
   
	/**
	 * Looks up an item in the table.
	 * @param name - the key to find
	 * @return the value with the given key, or "Not Found" if no such item was
	 *         found.
	 */
	public String findAddress(String name) {
		// Search the byName array list for an Item with a matching name,
		// and returns its value.
		Item entry = new Item(name, null);
		int indexResult = Collections.binarySearch(byName, null);
		if (indexResult >= 0) {
			//TODO
			return Collections.binarySearch(byName, null)
		}
		return "Not Found";
	}

	/**
	 * Looks up an item in the table.
	 * 
	 * @param twitterAddress - The value to find
	 * @return - The key with the given value, or "Not Found" if no such item was
	 *         found.
	 */
	public String findName(String twitterAddress) {
		// Search the byAddress array list for an Item with a matching
		// twitterAddress, and returns its value.
		return "Not Found";
	}
}
