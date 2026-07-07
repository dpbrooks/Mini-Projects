/**
 * @author Dylan Brooks
 * Professor X
 * CSCI 161
 * September 6, 2022 
 * Program to
 * breakdown change, calculate an electric bill, and break down time given in seconds
 */
public class Expressions {

	// Variable for the given initial change value
	public static int change = 97;

	// Variable of the given kwHours
	public static double kwHours = 161.0;

	// Variable for the given # of seconds
	public static int seconds = 104442;

	// Method to calculate the number of each cent denomination from a given value
	public static void makeChange() {
		// Variable to calculate the # of quarters
		int quarters = change / 25;
		// Variable to calculate the remaining amount of cents after removing quarters
		int Rchange = change - (quarters * 25);
		// Variable to calculate the 3 of dimes
		int dimes = Rchange / 10;
		// Update Rchange to calculate the remaining cents after removing dimes
		Rchange = Rchange - (dimes * 10);
		// Variable to calculate the # of nickels
		int nickels = Rchange / 5;
		// Update Rchange to reflect remaining cents after calculating nickels
		Rchange = Rchange - (nickels * 5);
		// Variable to calculate the # of pennies
		int pennies = Rchange;
		// Display the variable change
		System.out.println(change + " cents makes the following change:");
		// Display the variable quarters
		System.out.println(quarters + " quarter(s)");
		// Display the variable dimes
		System.out.println(dimes + " dime(s)");
		// Display the variable nickels
		System.out.println(nickels + " nickel(s)");
		// Display the variable pennies
		System.out.println(pennies + " pennies");

	}

	// Method to calculate an electric bill given kWhrs
	public static void calculateElectricBill() {
		// Variable to calculate the amount of electric used with a price of $0.50
		double amtPriceA = Math.min(50, kwHours);
		// Variable to calculate the amount of electric used with a price of $0.75
		double amtPriceB = ((Math.min(Math.max(50.0, kwHours), 100.0)) - 50.0);
		// Variable to calculate the amount of electric used with a price of $1.10
		double amtPriceC = ((Math.min(Math.max(100.0, kwHours), 150.0)) - 100.0);
		// Variable to calculate the amount of electric used with a price of $1.50
		double amtPriceD = (Math.max(kwHours, 150.0) - 150.0);
		// Variable to calculate the total cost of the electric bill
		double total = (amtPriceA * 0.5) + (amtPriceB * 0.75) + (amtPriceC * 1.10) + (amtPriceD * 1.50);
		// Display the kWh and total cost of the electric bill
		System.out.println("The cost to deliver " + kwHours + " kWh of power is $" + total);
	}

	// Method to calculate the days. hours, minutes, and seconds of a given # of
	// seconds
	public static void timeBreakdown() {
		// Variable to calculate the # of seconds
		int secs = seconds % 60;
		// Variable for remaining time needed in minutes
		int time = seconds / 60;
		// Variable to calculate the # of minutes
		int minutes = time % 60;
		// Update time to calculate the number of hours
		time = time / 60;
		// Variable to calculate the # of hours
		int hours = time % 24;
		// Update time to calculate the number of days
		time = time / 24;
		// Variable to calculate the remaining seconds
		int days = time % 24;
		// Display the given # of seconds
		System.out.println("A time of " + seconds + " seconds is equivalent to");
		// Display the # of days
		System.out.println(days + " day(s)");
		// Display the # of hours
		System.out.println(hours + " hour(s)");
		// Display the # of minutes
		System.out.println(minutes + " minute(s)");
		// Display the # of seconds
		System.out.println(secs + " second(s)");

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		makeChange(); // call the makeChange method
		System.out.println(); // generate a blank line
		System.out.println(); // generate a blank line
		calculateElectricBill(); // call the calculateElectricBill method
		System.out.println(); // generate a blank line
		System.out.println(); // generate a blank line
		timeBreakdown(); // call the timeBreakdown method

	}

}
