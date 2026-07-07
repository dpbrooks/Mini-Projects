/**
 * Rocket.java
 * @author Dylan Brooks 
 * Professor X 
 * CSCI 161 
 * September 13, 2022 
 * Lab 4 
 * Program to print a rocket using nested loops and methods
 *
 */
public class Rocket {

	// Static class variable that stands for the size of the rocket ship
	public static final int SIZE = 7;

	// Loops to create the top and bottom triangles of the rocket
	public static void printTriangle() {
		// Variable that saves a string of 2 stars
		String s1 = "**";
		// Sets the s2 variable as blank
		String s2 = "";
		// Sets the s3 variable as blank
		String s3 = "";
		// Outer for loop which determines the # of rows
		for (int i = 1; i <= SIZE * 2 - 1; i++) {
			// Inner loop that determines the number of spaces before the slashes
			for (int j = SIZE * 2 - 1; j >= i; j--) {
				System.out.print(" ");
			}
			// Inner loop that determines the number of slashes on the left side
			for (int j = 1; j <= i; j++) {
				s2 = s2 + "/";
			}
			// Inner loop that determines the number of slashes on the right side
			for (int j = 1; j <= i; j++) {
				s3 = s3 + "\\";
			}
			// Prints the variables s2, s1, and s3
			System.out.println(s2 + s1 + s3);
			// Resets s2 to a blank String variable
			s2 = "";
			// Resets s3 to a blank String variable
			s3 = "";
		}
	}

	// Loops to create the line that divides the rocket segments
	public static void printLine() {
		// Variable that saves a string of +
		String s1 = "+";
		// Sets the s2 variable as blank
		String s2 = "";
		// Outer loop that determines the # of rows
		for (int i = 1; i <= 1; i++) {
			// Inner loop that determines the number of equal signs and stars that need to be added
			for (int j = 0; j <= SIZE * 2 - 1; j++) {
				// Updates the s2 variable by adding another equal sign and star string
				s2 = s2 + "=*";
			}
			// Prints the s1, and s2 variables
			System.out.println(s1 + s2 + s1);

		}
	}

	// Loops to create the part of the rocket body with upward triangles
	public static void printBodyUp() {
		// Variable that saves s1 as |
		String s1 = "|";
		// Sets the s2 variable as blank
		String s2 = "";
		// Sets the s3 variable as blank
		String s3 = "";
		// Sets the s4 variable as blank
		String s4 = "";
		// Outer loop that determines the # of rows
		for (int i = 1; i <= SIZE; i++) {
			// Inner loop to determine the number of dots between the vertical bar and triangles
			for (int j = i; j <= SIZE - 1; j++) {
				s2 = s2 + ".";
			}
			// Inner loop to determine the number of upward triangles in each row
			for (int j = 1; j <= i; j++) {
				s3 = s3 + "/\\";
			}
			// Inner loop to determine the number of dots in the center in each row
			for (int j = i; j <= SIZE - 1; j++) {
				s4 = s4 + "..";
			}
			// Prints the variables s1, s2, s3, and s4
			System.out.println(s1 + s2 + s3 + s4 + s3 + s2 + s1);
			// Resets s2 to a blank String variable
			s2 = "";
			// Resets s3 to a blank String variable
			s3 = "";
			// Resets s4 to a blank String variable
			s4 = "";
		}
	}

	// Loops to create the part of the rocket body with downward triangles
	public static void printBodyDown() {
		// Variable that saves s1 as |
		String s1 = "|";
		// Sets the s2 variable as blank
		String s2 = "";
		// Sets the s3 variable as blank
		String s3 = "";
		// Sets the s4 variable as blank
		String s4 = "";
		// Outer loop that determines the # of rows
		for (int i = 1; i <= SIZE; i++) {
			// Inner loop to determine the number of dots between the vertical bar and triangles
			for (int j = SIZE - i; j < SIZE - 1; j++) {
				// Updates the s2 variable by adding another dot
				s2 = s2 + ".";
			}
			// Inner loop to determine the number of downward triangles in each row
			for (int j = i; j <= SIZE; j++) {
				// Updates the s3 variable by adding another upside down triangle
				s3 = s3 + "\\/";
			}

			// Inner loop to determine the number of dots in the center in each row
			for (int j = SIZE - i; j < SIZE - 1; j++) {
				// Updates the s4 variable by adding another 2 dots
				s4 = s4 + "..";
			}
			// Prints the variables s1, s2, s3, and s4
			System.out.println(s1 + s2 + s3 + s4 + s3 + s2 + s1);
			// Resets s2 to a blank String variable
			s2 = "";
			// Resets s3 to a blank String variable
			s3 = "";
			// Resets s4 to a blank String variable
			s4 = "";
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printTriangle(); // Call the printTriangle method
		printLine(); // Call the printLine method
		printBodyUp(); // Call the printBodyUp method
		printBodyDown(); // Call the printBodyDown method
		printLine(); // Call the printLine method
		printBodyDown(); // Call the printBodyDown method
		printBodyUp(); // Call the printBodyUp method
		printLine(); // Call the printLine method
		printTriangle(); // Call the printTriangle method

	}

}
