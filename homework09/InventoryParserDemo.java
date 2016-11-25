package homework09;

import java.util.Scanner;

/**
 * This program parses an XML file containing inventory information. It prints
 * out the inventory inforamation after processing the products, purchases and
 * sales that are described in the XML file.
 */
public class InventoryParserDemo {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		InventoryParser inventory = new InventoryParser();
		System.out.print("Enter name of the inventory XML file: ");
		String filename = in.nextLine();
		inventory.parse(filename);
		System.out.println("Products:\n" + inventory.toString());
		in.close();
	}
}
