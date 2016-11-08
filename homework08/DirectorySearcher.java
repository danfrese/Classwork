package homework08;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Find the files in the given directory and subdirectories that end
 * with the specified extension.
 * @author your name
 *
 */
public class DirectorySearcher {
	private String extension;
	private ArrayList<String> foundFiles;
	
	/**
	 * Build a DirectorySearcher object to find matches with the given extension.
	 * @param ext Filename extension to match, such as ".java"
	 * @param results ArrayList to hold found files
	 */
	public DirectorySearcher(String ext, ArrayList<String> results)
	{
		extension = ext;
		foundFiles = results;
	}

	/**
	 * Recursively add files with matching extension to the list of files.
	 * If the file is a directory, recursively call the method to add files
	 * from the subdirectory to the list.
	 * @param f File to check: if a file, check its name; if a directory, check its files
	 */
	public void findMatchingFiles(File f)
	{
		// Use the pseudocode outlined in the homework
		// to help solve this recursive method.
	}
}
