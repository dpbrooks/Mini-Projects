/**
 * @author Dylan Brooks
 * Professor X
 * CSCI 161
 * September 27, 2022
 * A program to solve the quadratic formula
 *
 */

// Creates a scanner
import java.util.Scanner;

public class QuadraticFormula {

	/**
	 * @param args
	 */
	// Method to calculate the discriminant
	public static double calcDiscriminant(int a, int b, int c) {
		// Returns the value of the discriminant
		return Math.pow(b, 2) - (4 * a * c);
	}

	// Method to print the equation that is being used
	public static void printEquation(int a, int b, int c) {
		// Prints the equation with the parameters slotted in
		System.out.print("(" + a + ")x^2 + (" + b + ")x + (" + c + ") = 0");
	}

	// Method to calculate both roots and print the results
	public static void printQuadraticRoots(int a, int b, int c) {
		// Calculates the first root and saves it as the variable x1
		double x1 = ((-b + Math.sqrt(calcDiscriminant(a, b, c))) / (2.0 * a));
		// Calculates the second root and saves it as the variable x2
		double x2 = ((-b - Math.sqrt(calcDiscriminant(a, b, c))) / (2.0 * a));
		// Prints a blank line
		System.out.println();
		// Prints the beginning of the sentence with the equation
		System.out.print("For the equation \"");
		// Calls the printEquation method
		printEquation(a, b, c);
		// Finishes the sentence with the equation
		System.out.println("\", the roots are");
		// Gives the value of the first root
		System.out.println("  x = " + x1);
		// Prints and
		System.out.println(" and");
		// Prints the value of the second root
		System.out.println("  x = " + x2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a scanner object named sc
		Scanner sc = new Scanner(System.in);
		// Prints a ==> before where the parameter is given its value
		System.out.print("a ==> ");
		// Allows a to be given a value by the user
		int a = sc.nextInt();
		// Prints b ==> before where the parameter is given its value
		System.out.print("b ==> ");
		// Allows b to be given a value by the user
		int b = sc.nextInt();
		// Prints c ==> before where the parameter is given its value
		System.out.print("c ==> ");
		// Allows c to be given a value by the user
		int c = sc.nextInt();
		// Calls the printQuadraticRoots method
		printQuadraticRoots(a, b, c);
		// Closes the scanner sc
		sc.close();

	}

}
