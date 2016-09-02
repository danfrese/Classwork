package quiz05;

import java.util.ArrayList;
import java.util.Scanner;

/* Goal: Re-write the program to use an ArrayList<String> instead 
 * of a String[] array to hold the list of Strings. 
 * The program is intended to read all the strings entered by the user, 
 * store them, and then print them.
 */

public class ArrayListQuiz {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>(1);
		boolean done = false;

		while (!done) {
			System.out.print("Enter string (empty string to quit): ");
			String input = in.nextLine();
			
			// Check if we have an empty string to terminate the input stream
			if (input.equals("")) {
				done = true;
				in.close();
			} else {
				list.add(input);
			}
		}
		// Print out the entire array list
		System.out.println(list);
	}
}
