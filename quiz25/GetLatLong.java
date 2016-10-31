package quiz25;

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
 * This program gets the latitude and longitude corresponding to a given Zip
 * code. It queries melissadata.com for the info and shows the table rows
 * containing the info.
 */
public class GetLatLong {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the 5-digit zip code: ");
		int zip = in.nextInt();

		// Build the URL string and open a URLConnection.
		String urlstring = "http://www.melissadata.com/lookups/GeoCoder.asp?submit=Search&InData=" + zip;
		URL url = new URL(urlstring);
		URLConnection uc = url.openConnection();
		HttpURLConnection hc = (HttpURLConnection) uc;

		if (hc.getResponseCode() != 200) {
			System.out.printf("Error from www.melissadata.com: %d (%s)\n", hc.getResponseCode(),
					hc.getResponseMessage());
			System.exit(1);
		}

		// Make sure we received HTML text data.
		if (!hc.getContentType().startsWith("text/html")) {
			System.err.println(
					"Error: data received from " + urlstring + " is not text/html, but " + hc.getContentType());
			in.close();
			return;
		}

		// Parse the HTML into objects, starting at the Document level.
		Document doc = Jsoup.parse(hc.getInputStream(), null, hc.getURL().toString());
		Elements table = doc.select("table > tbody > tr > td");
		// Find the table's cells containing the latitude and longitude.
		for (Element e : table) {
			// Use e.text() to get this element's text data.
			// Compare it to "Latitude", "Longitude", and "County Name"
			// to determine whether to print this data.
			// If so, use e.nextElementSibling() to get the next table
			// element, and print that element's data.
			if (e.text().equals("Latitude") || e.text().equals("Longitude") || e.text().equals("County Name")) {
				System.out.println(e.text() + " " + e.nextElementSibling().text());
			}
		}
		in.close();
	}
}