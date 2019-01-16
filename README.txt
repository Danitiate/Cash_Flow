1. RUNNING THIS PROGRAM

	1.1: The main class is stored in "Budget.java"

	1.2: To compile, simply type "javac Budget.java"

	1.3: Run with "java Budget"


2. ABOUT

	2.1: I wanted to make a program which can be practical in my everyday life.
	     There are many budget and economy based software on the internet, but
	     there is this huge learning curve in actually making it yourself.

	2.2: At the 1st of January 2019, Stortinget made a new law, stating all
	     personal discounts have to be taxed after reaching a certain treshold.

		2.2.1: This is a huge disadvantage for many employees, where some of
		       their salary is somewhat based on these kinds of advantages.

		2.2.2: The treshold is any discount, where the paid sum is half of the
		       original sum. You also have to tax every krone, if the total
		       amount saved is over 7,000 NOK.

		2.2.3: This program gives the user the ability to log every discounted
		       transaction, so that they know just how much they need to tax.

		2.2.4: I made this program because I'm currently working two jobs, and
		       I get discount in four different stores. It's very hard to track
		       everything, and without this, I could easily end up on a tax blow.

	2.3: I also find the budget menu in my bank is somewhat hard to navigate, and also
	     contains way to many menus. I wanted to make it as simple as possible.


3. CLASSES

	3.1: Budget.java

		3.1.1: This is the main class.

		3.1.2: Upon running, it allows the File_Reader to access the files in the
		       Files/ folder. If no file is found, it will create new, blank ones.

		3.1.3: There is a user input loop, which will only break when the user
		       enters either '9' or '0'. There is a check, to make sure the user
		       entered a digit, using the regular expression "\\d+" 

		3.1.4: If '9' is entered, all changes made will be saved.


	3.2: User_Interface.java

		3.2.1: All the inputs from Budget.java is handled in this class.

		3.2.2: The input is made into a switch, where every input not on the menu,
		       is considered unknown.

		3.2.3: When the user wants to display data, the ball is thrown to the
		       File_Reader class, as this class handles the data and files.

		3.2.4: When the user wants to log data, all the neccessary user information
		       is extracted, handled, and sent to the File_Reader class.


	3.3: File_Reader.java

		3.3.1: As the name implies, it reads files to extract data. 
		       However it also writes to files.

		3.3.2: The get_values() method takes a filename as parameter, and reads a
		       file. It then returns the values stored in this file as a HashMap.

		3.3.3: The add_value() method takes a keyname and a value as parameter.
		       If this key exists in the "values" HashMap, the value is added to
		       the current value. If not, this entry is created.

		3.3.4: The add_value_discount() is slightly complicated, as it takes both
		       the key and original value, but also the discounted price. 
		       Based on these numbers, the program knows whether or not this
		       transaction have to be taxed.

		3.3.5: The add_value_monthly() is a faster way to log certain payments
		       every month, as simply typing "-m" og "-M" when logging transactions,
		       will add every value in the "Monthly_Fees.txt" into the "values" HashMap.

		3.3.6: The show_categories() method simply shows all categories (keys) in the
		       "values" HashMap. This lets the user know what categories already exist
		       when logging transactions, preventing typos, thus preventing duplicate 
		       categories.

		3.3.7: The read_to_file() method is used to save all changes made. All values
		       stored in "values" is printed to "Cash_Flow.txt" and "discount" to
		       "Cash_Flow_Discount.txt". 

		3.3.8: The show_budget() method prints out the current budget for this month,
		       as well as all the transactions made. This gives the user the ability
		       to compare just how much money they have spent, and see how far
		       above/below they are to the budget.

		3.3.9: The show_budget_discount() method prints all employee discounts made.
		       Both how much is spent and saved is printed. Also the amount of taxes
		       that need to be paid is shown.


	3.4: Date.java

		3.4.1: A simple class that gives a layer of abstraction to the Calendar class.

		3.4.2: Only does two things; find the current month and the current year.
 

4. FILES

	4.1: All files are written the same way. There is a comment in every file, telling
	     them what they do. Every comment starts with a '#', so that they are ignored
	     by the File_Reader. 

	4.2: Every value is written like this:	<identifier (string)>: <value (int)>
	     including the colon and space.

	4.3: Budget.txt contains the estimated budget for the current month.

	4.4: Cash_Flow.txt contains all transactions for the current month. Filename is
	     changed automatically every month.

	4.5: Cash_Flow_Discount.txt contains all employee discount transactions for the current
	     year. Automatically changes name every year.

	4.6: Monthly_Fees.txt is a small file used to keep track of constant fees. Speeds up
	     the process instead of logging everything 1 by 1, by simply typing "-m" or "-M" 
	     when logging transactions. 

5. BUGS
	
	5.1: There is no check for category names, so one can simply log an income in the
	     "log transaction" menu by typing " Income" behind the category name.

		5.1.1: This also applies to " Discount" and "Taxes" when logging employee discounts.

	5.2: Logging a budget is not yet implemented, so typing '4' will simply yield "Unkown input".

		5.2.1: This option should also give the user the ability to create their 
		       own Monthly_Fees.txt
