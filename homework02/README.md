#Homework Writeup
(10 points) Read chapter 7 of Big Java 6th Edition and provide short answers to the following questions in the Big Java textbook.  Submit your answers in a text document (plain text or Microsoft Word format, please), **with your name, email address, and section number at the top of the document.**

Show the Java code (just the loops – complete Java methods are not necessary): Write a loop that reads 10 strings (assume you have a Scanner object named "in" to read strings from the keyboard) and inserts them into an ArrayList (not an array). Write a second loop that prints out the strings in the opposite order from which they were entered.

Write a loop that checks whether two integer arrays, a[] and b[], have the same values in the same order, and print a message if a difference is found.  Assume both arrays are of the same length.

Rewrite the following for loop using the “for each” construct to add each element from the data array to the sum.  The data[] array is an array of values of type double.

```
for (i = 0; i < data.length; i++) sum += data[i];
```

#Programming
(20 points) Write a program ProductArrayTester that manages an array of Product objects by performing these steps:
1. Asks the user how many different products are in the inventory

2. Make a new **array** to hold the specified number of Product objects

3. In a loop for the specified number of products, ask the user for the product code, description, quantity, and price, construct a new Product object with the product data, and put the new Product object in the array

4. After the user has entered all the products, use a **for** loop (or a **for each** loop) to compute the sum of the total value of all of the products in the array (hint: if using a for loop, use something like products[i].getTotalValue() to get the total value for the product at index i)

5. Print the computed sum total value of the products

Use the provided `Product.java` class for your Product objects. Implement the main() method in the `ProductArrayTester.java` to do the five steps outlined above.

Do not assume there will always be 3 products.

**If you do not use the exact class names (with the capital letters specified), the automatic testing program will not be able to run your program and points will be lost.
Be sure to read the input values in exact order as shown so the automatic testing program can provide the input correctly to your program. 
Fill in your name in the @author section of the comment in the ProductArrayTester. If you do not, points will be deducted.**
