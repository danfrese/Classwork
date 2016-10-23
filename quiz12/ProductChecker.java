package quiz12;

import java.util.Scanner;

/**
 * Main class for the ProductChecker program.
 * 
 * @author
 */
public class ProductChecker {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		while (!done) {
			try {
				Product p = readProduct(in);
				System.out.println(p.toString());
				done = true;
			} catch (BadInputException e) {
				System.err.println("Error while reading product: " + e.getMessage() + "\nPlease try again.\n");
			}
		}
	}

	private static Product readProduct(Scanner in) throws BadInputException {
		// Ask the user to enter the product code,
		// description, price, and quantity.
		System.out.print("Enter product code: ");
		String code = in.nextLine();
		System.out.print("Enter product description: ");
		String desc = in.nextLine();
		System.out.print("Enter product quantity: ");
		int qty = in.nextInt();
		System.out.print("Enter product price: ");
		double price = in.nextDouble();

		// Consume the remaining newline in the input buffer.
		in.nextLine();

		// Check the product's quantity and price to make sure they
		// are valid. If not, throw a BadInputException.
		if (qty < 0 || price <= 0.0) {
			throw new BadInputException("Value(s) entered for quantity and/or price is less than zero.");
		}

		// Create a Product object using the product
		// code, description, price, and quantity.
		return new Product(code, desc, price, qty);
	}
}
