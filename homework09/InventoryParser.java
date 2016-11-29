package homework09;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * An XML parser for product inventory.
 */
public class InventoryParser {
	private ArrayList<Product> products;
	private DocumentBuilder builder;
	private XPath path;

	/**
	 * Constructs a parser that can parse products in inventory, and initializes
	 * the list of products in our inventory.
	 */
	public InventoryParser() throws ParserConfigurationException {
		products = new ArrayList<Product>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// factory.setValidating(true);
		factory.setIgnoringElementContentWhitespace(true);
		builder = factory.newDocumentBuilder();
		XPathFactory xpfactory = XPathFactory.newInstance();
		path = xpfactory.newXPath();
	}

	/**
	 * Adds a product to inventory.
	 * 
	 * @param p
	 *            the product to add
	 */
	public void addProduct(Product p) {
		products.add(p);
	}

	/**
	 * Finds a product with a given product code.
	 * 
	 * @param code
	 *            the product code to find
	 * @return the product with the given code, or null if there is no such
	 *         product
	 */
	public Product find(String productCode) {
		for (Product p : products) {
			if (p.getCode().equals(productCode)) // Found a match
				return p;
		}
		// No match in the entire array list
		throw new IllegalArgumentException("Product " + productCode + " was not found");
	}

	/**
	 * Return a string that describes all the products in inventory.
	 */
	public String toString() {
		double totalValue = 0.0;
		StringBuffer sb = new StringBuffer();
		for (Product p : products) {
			sb.append(p.toString());
			sb.append('\n');
			totalValue += p.getTotalValue();
		}
		sb.append(String.format("Total value of inventory: %.2f\n", totalValue));
		return sb.toString();
	}

	/**
	 * Parses an XML file containing the inventory of products.
	 * 
	 * @param fileName
	 *            the name of the file
	 */
	public void parse(String fileName) throws SAXException, IOException, XPathExpressionException {
		File f = new File(fileName);
		Document doc = builder.parse(f);

		/*
		 * Count the number of products, and then get the code, desc, price, and
		 * quantity for the product from the document, create a new Product
		 * object from the code, desc, price, and quantity, and add the product
		 * to the inventory using addProduct().
		 * 
		 * Refer back to Homework 4's readProducts() method where we created a
		 * Product object and added it to the inventory.
		 */

		int prodCount = Integer.parseInt(path.evaluate("count(inventory/products/product)", doc));

		for (int i = 1; i <= prodCount; i++) {
			String code = path.evaluate("/inventory/products/product[" + i + "]/code", doc);
			String description = path.evaluate("/inventory/products/product[" + i + "]/desc", doc);
			int quantity = Integer.parseInt(path.evaluate("/inventory/products/product[" + i + "]/quantity", doc));
			double price = Double.parseDouble(path.evaluate("/inventory/products/product[" + i + "]/price", doc));

			Product pr = new Product(code, description, price, quantity);
			addProduct(pr);
		}

		// Count the inventory transactions purchased. Then,
		// for each purchased transaction, get the code and count,
		// find the product by its code, and call the "purchased" method
		// with the count.
		
		int transPurchased = Integer.parseInt(path.evaluate("count(inventory/transactions/purchased)", doc));
		
		for (int i = 1; i <= transPurchased; i++) {
			String code = path.evaluate("/inventory/transactions/purchased[" + i + "]/code", doc);
			int count = Integer.parseInt(path.evaluate("/inventory/transactions/purchased[" + i + "]/count", doc));
			
			find(code).purchased(count);
		}

		// Count the inventory transactions sold. Then,
		// for each sold transaction, get the code and count,
		// find the product by its code, and call the "sold" method
		// with the sold quantity.
		
		int transSold = Integer.parseInt(path.evaluate("count(inventory/transactions/sold)", doc));
		
		for (int i = 1; i <= transSold; i++) {
			String code = path.evaluate("/inventory/transactions/sold[" + i + "]/code", doc);
			int count = Integer.parseInt(path.evaluate("/inventory/transactions/sold[" + i + "]/count", doc));
			
			find(code).sold(count);
		}
	}
}