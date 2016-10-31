package quiz21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class illustrates the use of the Item class to keep
 * keys with corresponding values.
 * @author ghelmer
 *
 */
public class FrenchEnglishDictionary {

	public static void main(String[] args) {
		ArrayList<ItemVerbose> words = new ArrayList<ItemVerbose>();
		initialize(words);

		Scanner in = new Scanner(System.in);
		System.out.print("Enter an English word: ");
		while (in.hasNext()) {
			String word = in.next();
			System.out.println("Searching for word " + word);
			int index = Collections.binarySearch(words, new ItemVerbose(word, null));
			// TODO: Fill in the missing code to check the index.
			// If less than 0, the word was not found.
			// Otherwise, print the French word corresponding to the
			// entered English word.
			if (index < 0) {
				System.out.println("French word not found.");
			} else if (index >= 0) {
				System.out.println("The French word is: " + words.get(index).getValue());
			}

			System.out.print("Enter an English word: ");
		}
		in.close();
	}
	/**
	 * Add a few English words with French equivalents
	 * to the words arraylist, and sort to enable
	 * use of binary search.
	 * @param words
	 */
	private static void initialize(ArrayList<ItemVerbose>words)
	{
		words.add(new ItemVerbose("dog", "chien"));
		words.add(new ItemVerbose("cat", "chat"));
		words.add(new ItemVerbose("horse", "cheval"));
		words.add(new ItemVerbose("street", "rue"));
		words.add(new ItemVerbose("one", "un"));
		words.add(new ItemVerbose("two", "deux"));
		words.add(new ItemVerbose("three", "trois"));
		words.add(new ItemVerbose("four", "quatre"));
		words.add(new ItemVerbose("five", "cinq"));
		words.add(new ItemVerbose("six", "six"));
		words.add(new ItemVerbose("seven", "sept"));
		words.add(new ItemVerbose("eight", "huit"));
		words.add(new ItemVerbose("nine", "neuf"));
		words.add(new ItemVerbose("ten", "dix"));
		System.out.println("Sorting the ItemVerbose objects");
		Collections.sort(words);	
	}
}
