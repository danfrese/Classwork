package homework04;

//import java.io.File;
//import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class manages a collection of products in inventory.
 * @author Dan Frese
 */
public class Inventory {
	private ArrayList<Product> products;

	/**
	 * Initialize the list of products in our inventory.
	 */
	public Inventory() {
		products = new ArrayList<Product>();
	}

	/**
	 * Adds a product to inventory.
	 * @param p the product to add
	 */
	public void addProduct(Product p) {
		products.add(p);
	}

	/**
	 * Load the products from the given file.
	 * @param in - Scanner from which to read product information
	 */
	public void readProducts(Scanner in) {
		/*
		 * Read the products (product code, description, quantity, and price)
		 * and construct a new Product object from the information read from the
		 * file. Use the addProduct() method to store the new product in our
		 * inventory.
		 */
		// Please DO NOT open products.txt here.
		// Use the Scanner in, which already has opened the
		// products file specified by the user.
		// TODO: Fill in missing code.
		
		// Initialize variables
		int prodQuantity = 0;
		double prodPrice = 0.00;

		while (in.hasNextLine()) {
			// Assign values to variables by progressing through the text file
			String prodCode = in.nextLine();
			String prodDesc = in.nextLine();
			
			// Make sure we're looking at an int or double
			if (in.hasNextInt())
				prodQuantity = in.nextInt();

			if (in.hasNextDouble())
				prodPrice = in.nextDouble();

			in.nextLine();// Empty the Scanner buffer

			// Create a new product and add that product to the products collection
			Product newProduct = new Product(prodCode, prodDesc, prodPrice, prodQuantity);
			addProduct(newProduct);
		}
	}

	/**
	 * Read and apply the transactions from the given file.
	 * Transactions may be:
	 * "buy <product-code> <quantity>"
	 * "sell <product-code> <quantity>"
	 * "adjust <product-code> <percentage>"
	 *
	 * @param in - Scanner from which to read transactions
	 */
	public void applyTransactions(Scanner in) {
		/*
		 * Read the word determining the kind of transaction. Based on the type
		 * of transaction, read the product code and other information for the
		 * transaction. Find the matching product in inventory (hint: use the
		 * find() method), and use the appropriate method on the found product
		 * to apply the transaction.
		 */
		// Please DO NOT open transactions.txt here.
		// Use the Scanner in, which already has opened the
		// transactions file specified by the user.
		// TODO: Fill in missing code.
		
		// Variable declarations
		String tempInput, action, prodCode, temp;
		int num;
		
		while (in.hasNextLine()) {
			// Clear and initialize variables
			tempInput = "";
			action = "";
			prodCode = "";
			temp = "";
			num = 0;
			boolean status = true;
			
			// Accept and clean input
			tempInput = in.nextLine().replaceAll("\\s+", "");

			// Parse the cleaned input
			int i;
			for (i = 0; i < tempInput.length(); i++) {
				// Gather the action type characters into a string
				if (Character.isLetter(tempInput.charAt(i))) {
					action += tempInput.charAt(i);
				}
				
				// Check if the character is a digit to assign product code and amount
				if (Character.isDigit(tempInput.charAt(i)) && status) {
					prodCode = tempInput.substring(i, i + 7);
					i += 7;
					status = false;
					temp = tempInput.substring(i, tempInput.length());
					break;
				}
			}

			// Assign & cast the value to num
			num = (int) Double.parseDouble(temp);

			// Action logic
			if (action.equals("sell")) {
				find(prodCode).purchased(num * -1);
			} else if (action.equals("buy")) {
				find(prodCode).purchased(num);
			} else if (action.equals("adjust")) {
				find(prodCode).adjustPrice((double) num);
			}
		}
	}

	/**
	 * Gets the sum of the values of all products.
	 * @return the sum of the values
	 */
	public double getTotalValue() {
		double total = 0;
		for (Product p : products) {
			total = total + p.getTotalValue();
		}
		return total;
	}

	/**
	 * Finds a product with a given product code.
	 * @param code the product code to find
	 * @return the product with the given code, or null if there
	 * is no such product
	 */
	public Product find(String productCode) {
		for (Product p : products) {
			if (p.getCode().equals(productCode)) // Found a match
				return p;
		}
		// No match in the entire array list
		throw new IllegalArgumentException("Product " + productCode + " was not found");
	}
   
	/**
	 * Return a string that describes all the products in inventory.
	 */
	public String toString() {
		double totalValue = 0.0;
		StringBuffer sb = new StringBuffer();
		for (Product p : products) {
			sb.append(p.toString());
			sb.append('\n');
			totalValue += p.getTotalValue();
		}
		sb.append(String.format("Total value of inventory: %.2f\n", totalValue));
		return sb.toString();
	}
}
