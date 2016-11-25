package quiz16;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
*/

public class VulnerableSql {
	public static void main(String[] args) {
		try {
			SimpleDataSource.init("database.properties");
			Scanner in = new Scanner(System.in);
			initializeUsersTable();
			System.out.print("Enter username: ");
			String username = in.nextLine().trim();
			System.out.print("Enter password: ");
			String password = in.nextLine().trim();
			if (checkUser(username, password)) {
				System.out.printf("User %s granted access!\n", username);
			} else {
				System.out.printf("User %s denied access!\n", username);
			}
			in.close();
		} catch (SQLException e) {
			System.err.println("Unexpected SQLException: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Unexpected IOException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("Unexpected ClassNotFoundException: " + e.getMessage());
		}
	}

	/**
	 * Check the specified user and password in the database.
	 * @param username - user to check
	 * @param password - password for the user
	 * @return true if authentication succeeds
	 * @throws SQLException
	 */
	public static boolean checkUser(String username, String password) throws SQLException {
		// Query the ProductsDB table for the quantity of this object's product code.
		try (Connection conn = SimpleDataSource.getConnection()) {
			// Use a PreparedStatement to prevent SQL injection attacks!
			String query = "SELECT Username FROM Users WHERE Username = ? AND Password = ?";
			System.out.println("Running query:");
			System.out.println(query);
			try (PreparedStatement stat = conn.prepareStatement(query)) {
				stat.setString(1, username);
				stat.setString(2, password);
				ResultSet result = stat.executeQuery();
				// There should be zero rows in the result set if the username
				// and password did not match, or one row in the result if
				// there was a match.
				if (result.next()) {
					// We received a row in the result set, so we assume
					// the username and password matched.
					return true;
				}
				return false;
			}
		}
	}
	
	/**
	 * Create and initialize the users table in the database.
	 * Creates a user 'bugs' with the password 'bunny'.
	 * @throws SQLException 
	 * 
	 */
	public static void initializeUsersTable() throws SQLException {
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (Statement stat = conn.createStatement()) {
				try {
					// This will fail if the table doesn't exist. That is OK.
					stat.execute("DROP TABLE Users");
				} catch (SQLException e) {
					System.out.println("Notice: Exception during DROP TABLE Users: " + e.getMessage()
							+ " (This is expected when the database is empty)");
				}
				// If an execption occurs after this point, allow it to be thrown.
				// ProductsDB table: Product_Code, Description, Quantity, Price
				stat.execute("CREATE TABLE Users (Username VARCHAR(64), Password VARCHAR(64))");
				System.out.println("Created table Users");
			}
			// Add default list of users using parallel arrays.
			String usernames[] = { "bugs" };
			String passwords[] = { "bunny" };
			for (int i = 0; i < usernames.length; i++) {
				try (PreparedStatement stat = conn
						.prepareStatement("INSERT INTO Users (Username, Password) VALUES (?, ?)")) {
					stat.setString(1, usernames[i]);
					stat.setString(2, passwords[i]);
					stat.execute();
					System.out.printf("Created user %s\n", usernames[i]);
				}
			}
		}
	}
}