package quiz30;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class InventoryBuilder {
	public static void main(String[] args) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		// Create the root element named "inventory" and add it to the document.
		Element rootElement = doc.createElement("inventory");
		doc.appendChild(rootElement);
		
		// Create the products child element and add it under the root.
		Element productsElement = doc.createElement("products");
		rootElement.appendChild(productsElement);
		
		// Create the product element with:
		//  code: 123-456
		//  desc: Hair dryer
		//  quantity: 5
		//  price: 19.95
		// And append the product element to the products parent.
		Element productElement = doc.createElement("product");
		
		//. . .
		productElement.appendChild(createTextElement("code", "123-456", doc));
		productElement.appendChild(createTextElement("desc", "Hair dryer", doc));
		productElement.appendChild(createTextElement("quantity", "5", doc));
		productElement.appendChild(createTextElement("price", "19.95", doc));

		// Attach product and all its children to the product element
		productsElement.appendChild(productElement);

		// Output the XML for this inventory document.
		DOMImplementation impl = doc.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
		LSSerializer ser = implLS.createLSSerializer();
		ser.getDomConfig().setParameter("format-pretty-print", true);
		System.out.println(ser.writeToString(doc));
	}

	/**
	 * Helper method to build a leaf element with text.
	 * 
	 * @param name - Name of leaf element
	 * @param text - Text to attach to leaf
	 * @param doc - XML document being looked at
	 * @return Element with text attached
	 */
	private static Element createTextElement(String name, String text, Document doc) {
		Text t = doc.createTextNode(text);
		Element e = doc.createElement(name);
		e.appendChild(t);
		return e;
	}
}