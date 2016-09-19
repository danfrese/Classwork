package quiz09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WorldPopulation {
	public static void main(String[] args) throws FileNotFoundException {
		// Prompt for the input file name
		Scanner console = new Scanner(System.in);
		System.out.print("Input file: ");
		String inputFileName = console.nextLine();

		// Construct the Scanner object for reading
		File inputFile = new File(inputFileName);
		Scanner in = new Scanner(inputFile);

		double totalPopulation = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			if (line.length() > 0) {
				int i = 0; // Locate the start of the first digit
				while (!Character.isDigit(line.charAt(i))) {
					i++;
				}
				// Extract the country name
				String country = line.substring(0, i).trim();
				// Extract the value
				double population = Double.parseDouble(line.substring(i).trim());
				System.out.printf("%-25s %10.0f\n", country, population);
				totalPopulation += population;
			}
		}
		System.out.printf("%-25s %10.0f\n", "Total:", totalPopulation);
		in.close();
		console.close();
	}
}