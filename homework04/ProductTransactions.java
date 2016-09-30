package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Read the products and transactions files, and show the results
 * after the operations are completed.
 * @author Dan Frese
 */
public class ProductTransactions
{
	/**
	 * Main program.
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner console = new Scanner(System.in);
		System.out.print("Enter products file name: ");
		String productsFilename = console.nextLine();
		System.out.print("Enter transactions file name: ");
		String transactionsFilename = console.nextLine();
		Inventory myInventory = new Inventory();

		try (Scanner productScanner = new Scanner(new File(productsFilename))) {
			myInventory.readProducts(productScanner);
		}

		try (Scanner transactionsScanner = new Scanner(new File(transactionsFilename))) {
			myInventory.applyTransactions(transactionsScanner);
		}

		System.out.println(myInventory.toString());
	}
}
