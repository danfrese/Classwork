package homework07;

import java.util.Scanner;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *  This program shows the phase of the moon based on the year, month and day.
 *  @author Dan Frese
 */
public class MoonPhaseParser {
	public static void main(String[] args) throws IOException
	{  
		Scanner console = new Scanner(System.in);
		System.out.print("Please enter the year (e.g. 1977): ");
		int year = console.nextInt();
		System.out.print("Please enter the month (e.g. 9): ");
		int month = console.nextInt();
		System.out.print("Please enter the day (e.g. 13): ");
		int day = console.nextInt();

		String[] monthNames = 
			{ 
					"Jan", "Feb", "Mar", "Apr", "May", "Jun",
					"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
			};
		// Build a string to match the month and day of month.
		String monthName = monthNames[month - 1];
		String searchString = String.format("%s %2d", monthName, day);
		String foundPhase = "unknown";

		// Build the URL string. Note that the year *must* be added as a parameter to the URL!
		// Open a URLConnection using the URL.
		String urlstring = ...;
		URL url = new URL(urlstring);
		URLConnection uc = ...;
		HttpURLConnection hc = (HttpURLConnection) uc;

		// Check to see if the request was successful.
		if (hc.getResponseCode() != 200)
		{
			System.err.println("Error: HTTP server response code is " +
					hc.getResponseCode() + " " + hc.getResponseMessage());
			return;
		}
		// Make sure we received HTML text data.
		if (!hc.getContentType().startsWith("text/html"))
		{
			System.err.println("Error: data received from " + urlstring +
					" is not text/html, but " + hc.getContentType());
			return;
		}
		
		// Parse the HTML into objects, starting at the Document level.
		Document doc = Jsoup.parse(hc.getInputStream(), null, hc.getURL().toString());
		// Find the element chain: <table> <tbody> <tr> <td> <pre> in the document.
		Elements table = doc.select("table > tbody > tr > td > pre");
		// Convert the table's text (one large string containing newlines) to an array of lines.
		String[] tableLines = table.text().split("\n");
		// Find the line containing the month & day we are looking for.
		for (String line : tableLines)
		{
			// Find the location of the search string in the line, or -1 if not found.
			// Then, compare the location of the match to the search string
			// to 4, 20, 36, or 52 to determine if the matching phase is "New Moon",
			// "First Quarter", "Full Moon", or "Last Quarter". Set foundPhase
			// to the appropriate value if a match is found.
			...
		}
		System.out.println("Phase: " + foundPhase);
	}
}
