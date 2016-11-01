(20 points) Complete the program to find the phase of the moon for a specified day, month and year. 
A server at the URL: http://aa.usno.navy.mil/cgi-bin/aa_moonphases.pl?year=NNNN 
(where NNNN is the four-digit year for which the chart is requested) returns a page 
containing a text table showing the moon phases in a given year.

Complete the program that asks the user for a year, appends the year to the URL above, 
sends the request to the server, and then prints the phase of the moon for the selected 
year, month and day.

Note that the year needs to be included in the URL string as the parameter to the request. 
We will only test days that appear in the table for the requested year. Some sample dates 
for testing: 2012-5-28 (First Quarter), 2012-12-28 (Full Moon), 2010-1-7 (Last Quarter), 
2016-12-29 (New Moon).

Fill in the code to create the urlString as shown above, including the year, create a URL 
from the urlString, open the URLConnection from the URL object, and get the 
HttpURLConnection from the URLConnection object.

Then, fill in the missing code in the for loop that processes the lines in the table.
Use the line’s indexOf() method to find the matching index to the searchString, and 
compare the index to 4, 20, 36, or 52 to determine if the phase is New Moon, First Quarter, 
Full Moon, or Last Quarter, respectively – see below for details about how we’re parsing 
the text from the HTML, and the format of the table’s lines.

An HTML parsing library, jsoup.jar, has been included to ease parsing the HTML document sent 
by the server. Be sure to add it to your Project’s Properties – Build Path – Libraries.