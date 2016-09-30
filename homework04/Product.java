package homework04;

/**
 * A Product has a product code, description, price and 
 * a quantity that can be changed by purchases and sales.
 */
public class Product {
	private String code;
	private String description;
	private double price;
	private int quantity;

	/**
	 * Constructs a product with a specific code,
	 * description, price and quantity.
	 * @param code - product lookup code
	 * @param description - description of the product
	 * @param price - cost of one product
	 * @param quantity - number of products in inventory
	 */
	public Product(String newCode, String newDescription, double newPrice, int newQuantity) {
		code = newCode;
		description = newDescription;
		price = newPrice;
		quantity = newQuantity;
	}
	
	/**
	 * Increases the quantity of product when we've
	 * purchased products to replenish our supply.
	 * @param number the count of products purchased.
	 */
	public void purchased(int number) {
		int newQuantity = quantity + number;
		quantity = newQuantity;
	}

	/**
	 * Decrease the quantity of product when we've
	 * sold product to a customer.
	 * @param number the count of products sold.
	 */
	public void sold(int number) {
		int newQuantity = quantity - number;
		quantity = newQuantity;
	}

	/**
	 * Gets the quantity of this product.
	 * @return the current quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * Gets the code for this product.
	 * @return the product code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the code for this product.
	 * @return the product code
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the product's price.
	 * @return the product price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Get the total value in inventory of this product
	 * (quantity times price).
	 * return value
	 */
	public double getTotalValue() {
		return quantity * price;
	}

	/**
	 * Change the product's price by a given percent.
	 * @param percent - percentage to change the product's price.
	 */
	public void adjustPrice(double percent) {
		double change = price * (percent * 0.01);
		// Round the change in price to two decimal points.
		price = price + Math.round(change * 100.0) * 0.01;
	}

	/**
	 * Get a String that describes this Product.
	 * @return info about this product
	 */
	public String toString() {
		return String.format("%s %s %d %.2f", code, description, quantity, price);
	}
}
