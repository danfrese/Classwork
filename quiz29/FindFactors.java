package quiz29;

import java.util.Scanner;

public class FindFactors {

	/**
	 * Ask the user to enter the number to factor, and then
	 * find factors and print them.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter integer number to factor: ");
		int n = in.nextInt();
		printFactors(n);
		in.close();
	}

	/**
	 * Recursively print the factors for the given number.
	 * @param n - The number to factor.
	 */
	public static void printFactors(int n)
	{
		// Determine whether to stop recursion if n is too small. If so, just return.
		if (n <= 1)
		{
			return;
		}
		
		// Loop over possible factors from 2 up to n.
		// When we find a factor, print it and recursively factor
		// n / f.
		for (int factor = 2; factor <= n; factor++)
		{
			if (n % factor == 0)
			{
				// Found one!
				System.out.println("Found factor " + factor);

				while (n % factor == 0)
				{
					n = n / factor;
				}
				printFactors(n);

				// Stop the for loop after finding a factor.
				break;
			}
		}
	}
}
