/**
 * @author Dylan Brooks
 *  Professor X
 * CSCI 161
 * October 4, 2022
 * A program to calculate CD interest when the user inputs
 * the initial balance, interest rate, and number of years.
 *
 */

// Create a scanner
import java.util.Scanner;

public class CDInterest {

	// Print the intro to the program
	public static void printIntro() {
		// Prints the first line of the intro
		System.out.println("This program will calculate the interest earned");
		// Prints the second line of the intro
		System.out.println("  on a CD over a period of several years.");
		// Creates a blank line
		System.out.println();
	}

	// Prints the table which shows the CD interest and balance
	public static void printTable(int numRows, double balance, double rate) {
		// Prints and formats Year
		System.out.printf("%-4s", "Year");
		// Prints and formats Balance
		System.out.printf("%12s", "Balance");
		// Prints and formats Interest
		System.out.printf("%13s", "Interest");
		// Prints and formats New Balance and creates a new line
		System.out.printf("%17s", "New Balance\n");
		// Prints and formats hyphens under Year
		System.out.printf("%-4s", "----");
		// Prints and formats hyphens under Balance
		System.out.printf("%12s", "-------");
		// Prints and formats hyphens under Interest
		System.out.printf("%13s", "--------");
		// Prints and formats hyphens under New Balance and creates a new line
		System.out.printf("%17s", "-----------\n");
		// Creates a variable named interest
		double interest;
		// A for loop to repeat the printRow method in order to make the table
		for (int i = 1; i <= numRows; i++) {
			//Creates a temp variable to store the initial value of numRows
			int tempnumRows = numRows;
			// Changes the value of numRows to be i
			numRows = i;
			// Assigns a value to the interest variable
			interest = calcInterest(balance, rate);
			// Calls the printRow method
			printRow(numRows, balance, interest);
			// Updates the balance by adding the interest
			balance = balance + interest;
			// Resets the numRows variable to the user input value
			numRows = tempnumRows;
			// Creates a blank line
			System.out.println();
		}
	}

	// Method to print a row of the table
	public static void printRow(int rowNum, double balance, double interest) {
		// Prints and formats the value assigned to rowNum
		System.out.printf("%-4d", rowNum);
		// Prints and formats the value assigned to balance when rowNum < 10
		System.out.printf("%,12.2f", balance);
		// Calls the calcInterest method and then prints and formats the returned value
		System.out.printf("%,13.2f", interest);
		// Adds the balance and the interest, and then prints and formats the sum
		System.out.printf("%,16.2f", balance + interest);
	}

	// Method to calculate the interest generated each year
	public static double calcInterest(double balance, double rate) {
		// Returns the amount of interest
		return balance * (rate / 100);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creates a scanner object name sc
		Scanner sc = new Scanner(System.in);
		// Calls the printIntro method
		printIntro();
		// Prints the statement calling for the initial balance
		System.out.print("Please enter the initial balance: ");
		// Allows balance to be given a value by the user
		double balance = sc.nextDouble();
		// Prints the statement calling for the interest rate
		System.out.print("Please enter the interest rate  : ");
		// Allows rate to be given a value by the user
		double rate = sc.nextDouble();
		// Prints the statement calling for the number of years
		System.out.print("Please enter the number of years: ");
		// Allows year to be given a value by the user
		int years = sc.nextInt();
		// Creates a blank line
		System.out.println();
		// Calls the printTable method
		printTable(years, balance, rate);
		// Closes the scanner
		sc.close();

	}

}
