package quiz31;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program searches files for matches.
 * 
 * @author ghelmer
 */
public class JavaGrepInteractive {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.print("Enter regular expression: ");
		String patternString = console.nextLine();
		Pattern pattern = Pattern.compile(patternString);
		System.out.println("Compiled pattern: '" + patternString + "'");
		
		boolean done = false;
		while (!done) {
			System.out.print("Enter filename to search, or an empty string to stop: ");
			String filename = console.nextLine();
			if (filename.length() == 0) {
				done = true;
			} else {
				Scanner in = new Scanner(new File(filename));
				System.out.println("Opened file " + filename);
				
				while (in.hasNextLine()) {
					String line = in.nextLine();
					Matcher matcher = pattern.matcher(line);
					if (matcher.find()) {
						System.out.println(filename + ": " + line);
					}
				}
				in.close();
			}
		}
		console.close();
	}
}
