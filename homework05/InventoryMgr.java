package homework05;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class InventoryMgr {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		SimpleDataSource.init("database.properties");
		Scanner in = new Scanner(System.in);
		InventoryDB myInventory = new InventoryDB();
		boolean done = false;
		while (!done) {
			try {
				System.out.println(
						"I) Initialize database  A)dd Product  P)urchase Products  S)ell Products  C)heck Product  Q)uit");
				String input = in.nextLine().toUpperCase();
				if (input.equals("I")) {
					System.out.println("Enter 'YES' if you wish to reinitialize the inventory: ");
					String answer = in.nextLine();
					if (answer.equalsIgnoreCase("YES"))
						myInventory.initialize();
					else
						System.out.println("OK, existing data preserved");
				} else if (input.equals("A")) {
					String productCode = promptForWord(in, "Enter new product code: ");
					if (myInventory.find(productCode) != null) {
						System.out.printf("Error: product code %d already exists.\n", productCode);
					} else {
						String desc = promptForWord(in, "Enter new product description: ");
						int qty = promptForInt(in, "Enter new product quantity: ");
						double price = promptForDouble(in, "Enter new product price: ");
						ProductDB a = new ProductDB(productCode);
						a.addProduct(desc, qty, price);
					}
				} else if (input.equals("P")) {
					String productCode = promptForWord(in, "Enter product code for purchase: ");
					ProductDB a = myInventory.find(productCode);
					if (a == null) {
						System.out.printf("Error: product code %s does not exist.\n", productCode);
					} else {
						System.out.printf("Product %s: %s\n", productCode, a.toString());
						int qty = promptForInt(in, "Enter number of products purchased: ");
						a.purchased(qty);
						System.out.printf("Product %s now has quantity %d.\n", productCode, a.getQuantity());
					}
				} else if (input.equals("S")) {
					String productCode = promptForWord(in, "Enter product code for sale: ");
					ProductDB a = myInventory.find(productCode);
					if (a == null) {
						System.out.printf("Error: product %s does not exist.\n", productCode);
					} else {
						System.out.printf("Product %s: %s\n", productCode, a.toString());
						int quantitySold = promptForInt(in, "Enter number of products sold: ");
						if (a.getQuantity() < quantitySold) {
							System.out.printf("Error: Product %s quantity %d is less than requested quantity %d\n",
									productCode, a.getQuantity(), quantitySold);
						} else {
							a.sold(quantitySold);
							System.out.printf("Product %s now has quantity %d.\n", productCode, a.getQuantity());
						}
					}
				} else if (input.equals("C")) {
					String productCode = promptForWord(in, "Enter product code to check: ");
					ProductDB a = myInventory.find(productCode);
					if (a == null) {
						System.out.printf("Error: product %s does not exist.\n", productCode);
					} else {
						System.out.printf("Product: %s %s\n", productCode, a.toString());
					}
				} else if (input.equals("Q")) {
					done = true;
				}
			} catch (SQLException e) {
				System.out.printf("Database exception: %s\n", e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Ask the user for an integer input. Repeat until successful.
	 * @param in Scanner for reading input
	 * @param prompt String to show to user
	 * @return value entered by user
	 */
	public static int promptForInt(Scanner in, String prompt) {
		int result = 0;
		boolean done = false;
		while (!done) {
			System.out.print(prompt);
			String inputStr = in.nextLine().trim();
			try {
				result = Integer.parseInt(inputStr);
				done = true;
			} catch (NumberFormatException e) {
				System.out.printf("Error: '%s' was not recognized as an integer. Please try again.\n", inputStr);
			}
		}
		return result;
	}

	/**
	 * Ask the user for a double precision number. Repeat until successful.
	 * @param in Scanner for reading input
	 * @param prompt String to show to user
	 * @return value entered by user
	 */
	public static double promptForDouble(Scanner in, String prompt) {
		double result = 0;
		boolean done = false;
		while (!done) {
			System.out.print(prompt);
			String inputStr = in.nextLine().trim();
			try {
				result = Double.parseDouble(inputStr);
				done = true;
			} catch (NumberFormatException e) {
				System.out.printf("Error: '%s' was not recognized as a double. Please try again.\n", inputStr);
			}
		}
		return result;
	}

	/**
	 * Ask the user for a single word as a string. Repeat until successful.
	 * @param in Scanner for reading input
	 * @param prompt String to show to user
	 * @return value entered by user
	 */
	public static String promptForWord(Scanner in, String prompt) {
		System.out.print(prompt);
		return in.nextLine().trim();
	}
}
