package homework08;

import java.util.ArrayList;
import java.util.Scanner;

public class FindFiles {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("Enter name of directory to start search: ");
		String directory = console.nextLine();
		System.out.print("Enter filename extension to search for: ");
		String extension = console.nextLine();

		ArrayList<String> foundFiles = new ArrayList<String>();
		DirectorySearcher ds = new DirectorySearcher(extension, foundFiles);
		ds.findMatchingFiles(new File(directory));

		System.out.println("Found these files under directory " + directory + ":");
		for (String f : foundFiles)
		{
			System.out.println(f);
		}
	}

}
