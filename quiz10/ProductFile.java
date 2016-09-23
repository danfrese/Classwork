package quiz10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProductFile {
	public static void main(String[] args)
	{
		Scanner console = new Scanner(System.in);
		System.out.print("Enter name of file containing the product data: ");
		String inputFileName = console.nextLine();

		try
		{
			// Open the specified input file
			File inputFile = new File(inputFileName);
			Scanner in = new Scanner(inputFile);

			int numProducts = 0;
			double totalValue = 0;
			while (in.hasNextLine())
			{
				// Read the code as a line.
				String code = in.nextLine();

				// Read the description as a line.
				String desc = in.nextLine();

				// Read the quantity as an integer.
				int qty = in.nextInt();

				// Read the price as a double.
				double price = in.nextDouble();

				// Read the empty newline left after the price.
				in.nextLine();

				// Create the new Product object.
				Product myProduct = new Product(code, desc, price, qty);
				numProducts++;
				totalValue += myProduct.getTotalValue();
				System.out.println("Read product " + numProducts + ": " + myProduct.toString());
			}
			System.out.printf("Total value of inventory: %.2f\n", totalValue);
			
			in.close();// Close the in Scanner
			console.close();// Close the console Scanner
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not open file " + inputFileName);
		}
	}
}
