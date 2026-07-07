/**
 * @author Dylan Brooks
 *  Dr. Hogg
 * CSCI 162
 * January 26, 2023
 * A program to play the game of life.
 *
 */

import java.util.Scanner;
import java.util.Random;
public class Life {
	
	/**
	 * Method to create a 2D boolean array based on the user input row and column sizes.
	 * 
	 * @param rows Number of rows the array will have.
	 * @param columns Number of columns the array will have.
	 * @return a 2D boolean array with all false values.
	 */
	public static boolean[][] createArray(int rows, int columns) {
		boolean[] [] matrix = new boolean [rows] [columns];
		return matrix;
	}
	
	
	/**
	 * Method to randomly assign boolean values to every space in the array.
	 * 
	 * @param matrix The 2D boolean array with all false values.
	 * @param seed The number that will determine how values are assigned.
	 * @return the array after randomly assigning values.
	 */
	public static boolean[][] assignValues(boolean[][] matrix, long seed) {
		Random rand = new Random(seed);
		for (int row = 1; row < matrix.length - 1; row++) {
			for (int column = 1; column < matrix[row].length -1; column++) {
				matrix[row][column] = rand.nextBoolean();
			}
		}
		return matrix;
	}
	
	
	/**
	 * Method to print the matrix with "- " for false values and "# " for true values.
	 * 
	 * @param matrix The 2D array with the randomly assigned boolean values.
	 */
	public static void printMatrix (boolean [][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[row].length; column++) {
				if (matrix[row][column] == true) {
					System.out.print("# ");
				}else if (matrix[row][column] == false) {
					System.out.print("- ");
				} 
			}
			System.out.println();
		}
	}

	/**
	 * Method to create a copy of the matrix.
	 * 
	 * @param matrix The matrix being copied.
	 * @param rows The number of rows that was input by the user.
	 * @param columns The number of columns that was input by the user.
	 * @return A copy of the matrix.
	 */
	public static boolean[][] copyOfMatrix (boolean[][] matrix, int rows, int columns) {
		boolean[][] copyMatrix = new boolean[rows][columns];
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[row].length; column++) {
				copyMatrix[row][column] = matrix[row][column];
			}
		}
		return copyMatrix;
	}
	
	/**
	 * Method to update the previous matrix.
	 * 
	 * @param matrix The original 2D array.
	 * @param copy The copy of the 2D array.
	 * @param minBirth The min value for a birth to occur.
	 * @param maxBirth The max value for a birth to occur.
	 * @param minDeath The min value for survival.
	 * @param maxDeath The max value for survival.
	 * @return The updated matrix.
	 */
	public static boolean[][] updateMatrix (boolean[][] matrix, boolean[][] copy, int minBirth, int maxBirth, int minDeath, int maxDeath) {
		int counter = 0;
		for (int row = 1; row < matrix.length - 1; row++) {
			for (int column = 1; column < matrix[row].length - 1; column++) {
				if (copy[row][column] == true) {
					for (int r = row - 1; r <= row + 1; r++) {
						for (int c = column - 1; c <= column + 1; c++) {
							if (copy[r][c] == true) {
								counter++;
							}
						}
					}
					if (counter < minDeath || counter > maxDeath) {
						matrix[row][column] = false;
					}
				} else if (copy[row][column] == false) {
					for (int r = row - 1; r <= row + 1; r++) {
						for (int c = column - 1; c <= column + 1; c++) {
							if (copy[r][c] == true) {
								counter ++;
							}
						}
						
					}
					if (counter >= minBirth && counter <= maxBirth) {
						matrix[row][column] = true;
					}
				}
				counter = 0;
			}
		}
		return matrix;
	}
	
	/**
	 * The main method used to call the other methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter number of rows: ");
		int rows = sc.nextInt();
		System.out.print("Enter number of columns: ");
		int columns = sc.nextInt();
		System.out.print("Enter the simulation seed: ");
		long seed = sc.nextLong();
		System.out.print("Enter minimum value for birth: ");
		int minBirth = sc.nextInt();
		System.out.print("Enter maximum value for birth: ");
		int maxBirth = sc.nextInt();
		System.out.print("Enter minimum value for death: ");
		int minDeath = sc.nextInt();
		System.out.print("Enter maximum value for death: ");
		int maxDeath = sc.nextInt();
		boolean[][] matrix = new boolean[rows][columns];
		matrix = assignValues(createArray(rows, columns), seed);
		boolean[][] copy = new boolean[rows][columns];
		printMatrix(matrix);
		System.out.println();
		for (int i = 0; i < 5; i++) {
			copy = copyOfMatrix(matrix, rows, columns);
			matrix = updateMatrix(matrix, copy, minBirth, maxBirth, minDeath, maxDeath);
			printMatrix(matrix);
			System.out.println();
		}
		sc.close();

	}

}
