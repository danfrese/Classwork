package homework05;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * An inventory consisting of multiple products.
 * Uses a JDBC database interface to manage the data. 
*/
public class InventoryDB {
	/**
	 * Create or re-create the ProductsDB table in the database with
	 * some default data.
	 * @param conn - Database connection
	 * @throws SQLException - on any database error
	 */
	public void initialize() throws SQLException {
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (Statement stat = conn.createStatement()) {
				try {
					// This will fail if the table doesn't exist. That is OK.
					stat.execute("DROP TABLE ProductsDB");
				} catch (SQLException e) {
					System.out.println("Notice: Exception during DROP TABLE Products: " + e.getMessage()
							+ " (This is expected when the database is empty)");
				}
				// If an execption occurs after this point, allow it to be thrown.
				// ProductsDB table: Product_Code, Description, Quantity, Price
				stat.execute(
						"CREATE TABLE ProductsDB (Product_Code VARCHAR(7), Description VARCHAR(40), Quantity INT, Price DECIMAL(10,2))");
				// Add default list of products using parallel arrays.
				String productCodes[] = { "116-064", "257-535", "643-119", "011-025" };
				String descriptions[] = { "Toaster", "Hair dryer", "Car vacuum", "Gallon 2% Milk" };
				int quantities[] = { 50, 75, 43, 111 };
				double prices[] = { 24.95, 29.95, 19.99, 2.95 };
				for (int i = 0; i < productCodes.length; i++) {
					ProductDB a = new ProductDB(productCodes[i]);
					a.addProduct(descriptions[i], quantities[i], prices[i]);
					System.out.printf("Notice: inserted product %s %s %d %.2f\n", productCodes[i], descriptions[i],
							quantities[i], prices[i]);
				}
			}
		}
	}

	/**
	 * Obtain an array list of all the Products in the Inventory.
	 * @return arraylist of ProductDB
	 * @throws SQLException - on any database error
	 */
	public ArrayList<ProductDB> getAllProducts() throws SQLException {
		ArrayList<ProductDB> products = new ArrayList<ProductDB>();
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (Statement stat = conn.createStatement()) {
				// ProductsDB table: AccountNumber, Balance
				ResultSet result = stat.executeQuery("SELECT Product_Code FROM Products");
				while (result.next()) {
					ProductDB a = new ProductDB(result.getString(1));
					products.add(a);
				}
			}
		}
		return products;
	}
	
	/**
	 * Finds a product with a given code or null if not found.
	 * @param productCode the number to find
	 * @return the product with the given code
	 * @throws SQLException - on any database error
	 */
	public ProductDB find(String productCode) throws SQLException {
		try (Connection conn = SimpleDataSource.getConnection()) {
			// Does the product exist?
			try (PreparedStatement stat = conn
					.prepareStatement("SELECT COUNT(*) FROM ProductsDB WHERE Product_Code = ?")) {
				stat.setString(1, productCode);
				ResultSet result = stat.executeQuery();
				// There must be one row returned.
				result.next();
				if (result.getInt(1) == 0) {
					return null;
				}
				// Product exists: return it.
				ProductDB a = new ProductDB(productCode);
				return a;
			}
		}
	}
	
	/**
	 * Gets the sum of the products in this inventory.
	 * @return the sum of the balances
	 * @throws SQLException - on any database error
	 */
	public double getTotalValue() throws SQLException {
		double total = 0;
		ArrayList<ProductDB> products = getAllProducts();
		for (ProductDB a : products) {
			total = total + a.getTotalValue();
		}
		return total;
	}

	/**
	 * Return a string that describes all the products in the inventory.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		ArrayList<ProductDB> products;
		try {
			products = getAllProducts();
			for (ProductDB a : products) {
				sb.append(a.toString());
			}
		} catch (SQLException e) {
			sb.append("SQLException occurred: " + e.getMessage());
		}
		return sb.toString();
	}
}
