package homework06;

import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

/* The input file has the format

Abbott, Amy
@aabott
Abeyta, Ric
@rica
Abrams, Arthur
@abrams
Abriam-Yago, Kathy
@kathy
Accardo, Dan
@dana
Acevedo, Elvira
@eacevedo
Acevedo, Gloria
@gacevedo
Achtenhagen, Stephen
@sachten
. . .

*/

public class TwitterLookup {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the account list file: ");
		String fileName = in.nextLine();

		TwitterTable table = new TwitterTable();
		FileReader reader = new FileReader(fileName);
		table.read(new Scanner(reader));

		boolean more = true;
		while (more) {
			System.out.println("Lookup by N)ame, A)ddress, Q)uit?");
			String cmd = in.nextLine();

			if (cmd.equalsIgnoreCase("Q"))
				more = false;
			else if (cmd.equalsIgnoreCase("N")) {
				System.out.println("Enter name:");
				String n = in.nextLine();
				System.out.println("Twitter account: " + table.findAddress(n));
			} else if (cmd.equalsIgnoreCase("A")) {
				System.out.println("Enter Twitter account:");
				String n = in.nextLine();
				System.out.println("Name: " + table.findName(n));
			}
		}
		in.close();
	}
}
