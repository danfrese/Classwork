package homework05;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
   A product has a code, description, price, and quantity in stock.
   This implementation uses a database table to contain its data.
*/
public class ProductDB {
	private String productCode;

	/**
	 * Constructs a product object for operations on the ProductsDB table.
	 * 
	 * @param aProductCode - the product code
	 */
	public ProductDB(String aProductCode) {
		productCode = aProductCode;
	}

	/**
	 * Add the data for a product to the database.
	 * 
	 * @param description - describes product
	 * @param quantity - count in inventory
	 * @throws SQLException - on any database error
	 */
	public void addProduct(String desc, int qty, double price) throws SQLException {
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(
					"INSERT INTO ProductsDB (Product_Code, Description, Quantity, Price) VALUES (?, ?, ?, ?)")) {
				stat.setString(1, productCode);
				stat.setString(2, desc);
				stat.setInt(3, qty);
				stat.setDouble(4, price);
				stat.execute();
			}
		}
	}

	/**
	 * Increases the quantity of product when we've
	 * purchased products to replenish our supply.
	 * @param number the count of products purchased.
	 * @throws SQLException - on any database error
	 */
	public void purchased(int qtyPurchased) throws SQLException {
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (PreparedStatement stat = conn
					.prepareStatement("UPDATE ProductsDB SET Quantity = Quantity + ? WHERE Product_Code = ?")) {
				stat.setInt(1, qtyPurchased);
				stat.setString(2, productCode);
				stat.execute();
			}
		}
	}

	/**
	 * Decrease the quantity of product when we've
	 * sold product to a customer.
	 * @param qtySold - Number of product sold
	 * @throws SQLException - on any database error
	 */
	public void sold(int qtySold) throws SQLException {
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(
					"UPDATE ProductsDB SET Quantity = Quantity - ? WHERE Product_Code = ?")) {
				stat.setInt(1, qtySold);
				stat.setString(2, productCode);
				stat.execute();
			}
		}
	}

	/**
	 * Gets the description for this product.
	 * @return the product description
	 */
	public String getDescription() throws SQLException {
		try (Connection conn = SimpleDataSource.getConnection()){
			try (PreparedStatement stat = conn.prepareStatement(
					"SELECT Description FROM ProductsDB WHERE Product_Code = ?")){
				stat.setString(1, productCode);
				ResultSet result = stat.executeQuery();
				result.next();
				// Replace this with the actual description from the ProductsDB table
				return result.getString(1);
			}
		} 
	}

	/**
	 * Gets the quantity of this product.
	 * @return the current quantity
	 * @throws SQLException - on any database error
	 */
	public int getQuantity() throws SQLException {
		// Query the ProductsDB table for the quantity of this object's product code.
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(
					"SELECT Quantity FROM ProductsDB WHERE Product_Code = ?")) {
				// Set the value for the first '?' in the prepared statement.
				stat.setString(1, productCode);
				// Run the query.
				ResultSet result = stat.executeQuery();
				// There should be only one row in the result set. Advance to
				// the first row and get the computed total value of this product.
				result.next();
				// The computed value is in the first column of this first row.
				return result.getInt(1);
			}
		}
	}

	/**
	 * Gets the price of this product.
	 * @return the current price
	 * @throws SQLException - on any database error
	 */
	public double getPrice() throws SQLException {
		// Query the ProductsDB table for the price of this object's product code.
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (PreparedStatement stat = conn.prepareStatement(
					"SELECT Price FROM ProductsDB WHERE Product_Code = ?")) {
				// Set the value for the first '?' in the prepared statement.
				stat.setString(1, productCode);
				ResultSet result = stat.executeQuery();
				// There should be only one row in the result set. Advance to
				// the first row and get the computed total value of this product.
				result.next();
				// The computed value is in the first column of this first row.
				return result.getDouble(1);
			}
		}
	}

	/**
	 * Gets the code for this product.
	 * @return the product code
	 */
	public String getCode() {
		// We keep the product code as the key in the object.
		return productCode;
	}

	/**
	 * Get the total value in inventory of this product
	 * (quantity times price).
	 * return value
	 * @throws SQLException - on any database error
	 */
	public double getTotalValue() throws SQLException {
		// Query the ProductsDB table for the quantity and price of this object's product code.
		try (Connection conn = SimpleDataSource.getConnection()) {
			try (PreparedStatement stat = conn
					.prepareStatement("SELECT Quantity * Price FROM ProductsDB WHERE Product_Code = ?")) {
				stat.setString(1, productCode);
				ResultSet result = stat.executeQuery();
				// There should be only one row in the result set. Advance to
				// the first row and get the computed total value of this product.
				result.next();
				// The computed value is in the first column of this first row.
				return result.getDouble(1);
			}
		}
	}

	/**
	 * Return a string describing this product.
	 */
	public String toString() {
		String result;
		try {
			// Query the ProductsDB table for the description, quantity, and price of this object's product code.
			try (Connection conn = SimpleDataSource.getConnection()) {
				try (PreparedStatement stat = conn.prepareStatement(
						"SELECT Description, Quantity, Price FROM ProductsDB WHERE Product_Code = ?")) {
					stat.setString(1, productCode);
					ResultSet rs = stat.executeQuery();
					// There should be only one row in the result set. Advance to
					// the first row and get the computed total value of this product.
					rs.next();
					result = String.format("Product: %s %s %d %.2f", productCode, rs.getString(1), rs.getInt(2),
							rs.getDouble(3));
				}
			}
		} catch (SQLException e) {
			result = "SQLException while getting product info: " + e.getMessage();
		}
		return result;
	}
}
