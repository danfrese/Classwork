package quiz23;

import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

/**
 * This program looks up the exchange rate for USD / CAD.
*/
public class ExchangeRateLookupExercise {
	public static void main(String[] args) throws IOException {
		// Build the URL string and open a URLConnection.
		String urlstring = "http://www.bankofcanada.ca/rates/exchange/cad-usd-rate-lookup/";
		URL url = new URL(urlstring);
		URLConnection uc = url.openConnection();
		HttpURLConnection hc = (HttpURLConnection) uc;

		// TODO: Change this to false after completing the code below.
		// When this flag is true, we will just print all lines without
		// modification.
		final boolean isTesting = true;

		if (hc.getResponseCode() != 200) {
			System.out.printf("Error from www.bankofcanada.ca: %d (%s)\n", hc.getResponseCode(),
					hc.getResponseMessage());
			System.exit(1);
		}

		// Parse the HTML into objects, starting at the Document level.
		Document doc = Jsoup.parse(hc.getInputStream(), null, hc.getURL().toString());
		// Find the element chain: <div> <table> <tr> in the document.
		Elements table = doc.select("table > tbody > tr");

		// System.out.println("Table: " + table.toString());
		for (Element e : table) {
			// Look for the table row with the heading "High" and Low".
			// Print the
			// System.out.println(e.text());
			if (e.select("th").text().contains("Latest Low/High")) {
				System.out.println("Latest Low/High: ");
				// Show next two siblings
				Element lowDateSibling = e.nextElementSibling();
				Element lowCADSibling = lowDateSibling.nextElementSibling();
				Element highDateSibling = lowCADSibling.nextElementSibling();
				Element highCADSibling = highDateSibling.nextElementSibling();
				System.out.println(lowDateSibling.text());
				System.out.println(lowCADSibling.text());
				System.out.println(highDateSibling.text());
				System.out.println(highCADSibling.text());
			}
			e = e.nextElementSibling();
		}
	}
}
