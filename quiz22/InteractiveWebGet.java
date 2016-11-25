package quiz22;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
	   This program demonstrates how to use a socket to communicate
	   with a web server. Enter the name of the host and the
	   resource, for example horstmann.com and /index.html,
	   to access a resource.
 */
public class InteractiveWebGet {
	public static void main(String[] args) throws IOException
	{
		Scanner console = new Scanner(System.in);
		boolean done = false;
		while (!done) {
			System.out.print("Enter name of host (or \"quit\" to exit): ");
			String host = console.nextLine();
			if (host.equalsIgnoreCase("quit")) {
				done = true;
			} else {
				System.out.print("Enter resource: ");
				String resource = console.nextLine();
				// Open socket
				final int HTTP_PORT = 80;
				try (Socket s = new Socket(host, HTTP_PORT)) {
					// Get streams from socket
					InputStream instream = s.getInputStream();
					OutputStream outstream = s.getOutputStream();
					// Turn streams into scanners and writers
					Scanner in = new Scanner(instream);
					PrintWriter out = new PrintWriter(outstream);      
					// Send HTTP GET command
					String command = "GET " + resource + " HTTP/1.0\n" 
							+ "Host: " + host + "\n\n";
					System.out.println("Sending command:\n" + 
							"---------------------------------------------------------\n" + command +
							"---------------------------------------------------------");
					out.print(command);
					out.flush();
					// Read server response
					System.out.println("Server response:\n" +
							"---------------------------------------------------------");
					while (in.hasNextLine()) {
						String input = in.nextLine();
						System.out.println(input);
					}
					System.out.println("---------------------------------------------------------");
					in.close();
				}
			}
		}
		console.close();
	}
}