#Homework writeup
Read chapter 11 of Big Java 6th Edition and provide short answers to the following questions.  Submit your answers in a text document (plain text or Microsoft Word format, please).

1. What happens if you try to open a file for **reading** that doesn't exist? How does that compare to opening a file for **writing** that doesn't exist?
2. How do you open a file on Windows whose name contains a backslash, like `C:\Users\jane\Desktop\output.dat` using a String to contain the name? Show the sample code that creates a new File object for the filename string for `C:\Users\jane\Desktop\output.dat`, and then the following new Scanner object that opens the file for reading.
3. What is the difference between throwing an exception and catching an exception?
4. What can your program do with the exception object that a catch clause receives? Describe at least two different things a program can do with an exception.

#Programming
Finish the `ProductTransactions` program that reads product data from a file and then applies a series of transactions to the products. Its `main()` method uses the `Inventory` class to manage a collection of `Product` objects: reading product data & transactions, and obtaining a String showing the product data after the operations are complete.

You will need to complete the `readProducts()` and `applyTransactions()` methods in the `Inventory` class.

First, complete the `readProducts()` method in the `Inventory` class to read product data (product code, description, quantity, and price) values from a text file, create a `Product` object from the product code, description, quantity, and price, and uses the addProduct() method to add the new product object into its `products` collection. A sample products file contains lines like:

```
016-023
Gallon 2% Milk
10
2.49
016-043
Saltine Crackers
20
1.49
019-011
Paper Towels
15
2.23
```

Second, complete the `applyTransactions()` method in the `Inventory` class to read transactions and apply them to the products. Each line will have a word specifying the action (`“sell”`, `“buy”`, or `“adjust”`), followed by the product code and amount for the transaction. The `sell` and `buy` words are followed by one product code and the quantity that was sold or purchased. The adjust word is followed by the product code and the percent adjustment to the product price. A sample transactions file contains lines like:

```
sell 016-023 5
sell 016-043 4
buy 016-043 5
adjust 019-011 -15.0
sell 019-011 5
```

After the program has read the products and applied the transactions, it prints the list of all the products and values (this code is already completed for you). Sample output looks like this:
         ```
         016-023 Gallon 2% Milk 5 2.49
         016-042 Saltine Crackers 21 1.49
         019-011 Paper Towels  10 1.90
         Total value of inventory: 62.74
         ```

Test your program using the provided `products.txt` and `transactions.txt` files, but be aware that we will test your program using different products and transactions files.

We will not use invalid data (such as incorrect product codes in the transactions file) when we test your program.

**Fill in your name in the @author section of the comment in the ProductTransactions.java and Inventory.java files. If you do not, points will be deducted.**
