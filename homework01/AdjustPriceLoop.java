package homework01;

import java.util.Scanner;

/**
 * Main class for the AdjustPriceLoop program.
 * @author Dan Frese
 */
public class AdjustPriceLoop
{
	public static void main(String[] args)
	{
		// Variable declarations
		String newCode;
		String newDescription;
		double newPrice;
		int newQuantity;
		boolean done = false;
		Scanner in = new Scanner(System.in);

		// Ask the user to enter the product code,
		// description, price, and quantity.
		System.out.println("Enter the product code: ");
		newCode = in.nextLine();
		System.out.println("Enter the description: ");
		newDescription = in.nextLine();
		System.out.print("Enter the price: ");
		newPrice = in.nextDouble();
		System.out.print("Enter the product quantity: ");
		newQuantity = in.nextInt();

		// Create a Product object using the product
		// code, description, price, and quantity.
		Product item = new Product(newCode, newDescription, newPrice, newQuantity);

		// Loop: Ask the user for the percent change
		// to the product's price and apply the change
		// using the adjustPrice() method. Print the
		// product information after adjusting the
		// price.
		// Finish the loop when the user enters 0.
		
		while (done == false) {
			System.out.print("\nPlease enter the percent change to the product's price: ");
			newPrice = in.nextDouble();
			if(newPrice != 0){
				item.adjustPrice(newPrice);
				System.out.println(item.toString());
			} else {
				done = true;
				System.out.println("Program finished");
			}
		}
		in.close();
	}
}
