#Word problem instructions
(10 points) Review chapters 5 & 6 of Big Java 6th Edition and provide short answers to the following questions.  Submit your answers in a text document (plain text or Microsoft Word format, please).
* In a scheduling program, we want to check whether two appointments overlap. For simplicity, appointments start at a full hour, and we use military time (with hours 0–24). The following pseudocode describes an algorithm that determines whether the appointment with start time start1 and end time end1 overlaps with the appointment with start time start2 and end time end2.
 
```
if start1 > start2
    s = start1
Else
    s = start2
If end1 < end2
    e = endl
Else
    e = end2
If s < e
    The appointments overlap.
Else
    The appointments don’t overlap.
```
 
Show the steps that take place as you trace this algorithm with appointments from 8–10 and 9–11, and then with appointments from 8–9 and 10–12. Show the result each if statement (start1 > start2, end1 < end2, and s < e) in your work!

Rewrite the following while loop into a for loop:
```
int s = 0;
int i = 10;
while (i > 0) {
    s = s + i;
    i--;
}
```

#Instructions for code
(20 points) Write code to enter information about a product, and show the value of the product as its price is increased a number of times. The Product.java class is provided as a starting point; complete it by writing the AdjustPriceLoop.java class.

In the AdjustPriceLoop class’s main method:
  * Ask the user to enter the product code, description, price, and quantity quantity, and price [UPDATE: corrected order]
  * Create a Product object using the product code, description, price, and quantity.
  * Write a loop that asks the user for the percent change to the product’s price, and then uses the Product object’s adjustPrice() method to apply the percentage change. For example, a user might reduce the product’s price by entering -10 for a 10% reduction, and then enter 10 for a 10% increase. Continue the loop until the user enters 0.

Each time through the loop, after applying the price adjustment, use the Product’s toString() method to get the String of information about the Product, and print the String.
Sample program run (user input is bold):

#Input test
Enter product code: **160-015**

Enter product description: **Gallon 2% Milk**

Enter product quantity: **5**

Enter product price: **2.50**

Enter adjustment to price in percent (0 to quit): **5.0**

160-015 Gallon 2% Milk 5 2.63

Enter adjustment to price in percent (0 to quit): **-10.0**

160-015 Gallon 2% Milk 5 2.36 2.37

Enter adjustment to price in percent (0 to quit): **0**
