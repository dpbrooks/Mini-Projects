/**
 * @author Dylan Brooks
 * Professor X
 * CSCI 161
 * November 8, 2022
 * A file to calculate a diver's score
 *
 */

// Imports a scanner
import java.util.Scanner;
// Imports all io java applications
import java.io.*;

public class Diving {

	/**
	 * @param args
	 */
	
	// Print the intro message
    public static void printIntro() {
        // Prints the first line of the intro
        System.out.println("Welcome to the Diver Scoring program. This program will calculate an");
        // Prints the second line of the intro
        System.out.println("  overall score for a diver, based on individual dives.");
        // Creates a blank line
        System.out.println();
    }
    
    // Read each line from the input file and output the scores and average
    public static void processDives (Scanner fileScanner) {
        // Creates a double variable named sum and set it equal to 0
        double sum = 0;
        // Creates a double variable named avg
        double avg;
        // Creates int variables named diveNum and rowNum and sets them equal to 0
        int diveNum = 0;
        int rowNum = 0;
        // Creates a while loop that will repeat while there is an unscanned line in the file
        while (fileScanner.hasNextLine()) {
        	// Adds 1 to diveNum
            diveNum++;
            // Sets rowNum equal to the first number in the dive line
            rowNum = fileScanner.nextInt();
            // Calls the calculateDiveScore method and sets score equal to its result
        	double score = calculateDiveScore(fileScanner.nextLine().substring(1));
            // Prints the score for each dive number
            System.out.print("The diver's score for dive " + rowNum + " is ");
            System.out.printf("%.2f", score);
            System.out.println(".");
            // Sums up all of the diver scores
            sum += score;
        }
        // Creates a blank line
        System.out.println();
        // Calculate the average of the scores
        avg = sum / diveNum;
        // Prints the average score of the dives
        System.out.print("The average score for these dives is ");
        System.out.printf("%.2f", avg);
        System.out.println(".");
    }
    
    // Calculate the score for one dive
    public static double calculateDiveScore (String diveLine) {
        // Creates double variables token, min, and max
        double token;
        double min = 10;
        double max = 0;
        // Creates a double variable named score and sets it equal to 0
        double score = 0;
        // Creates an array named diveScores equal to the values in diveLine
        String[] diveScores = diveLine.split(" ");
        // i starts at 1 b/c difficulty needs to be omitted
        // Creates a for loop to calculate the diver's score
        for (int i = 1; i < diveScores.length; i++) {
            // Sets token equal to the value given in diveScores
            token = Double.parseDouble(diveScores[i]);
            // Finds the min value
            if (token <= min) min = token;
            // Finds the max value
            if (token >= max) max = token;
            // Adds all of the judge's scores
            score += token;
        }
        // Subtracts the min and max values from the added scores
        score = score - (min + max);
        // Calculates the total score for the dive
        score = score * Double.parseDouble(diveScores[0]) * 0.6;
        // Returns score
        return score;
    }
    
    
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Creates a file object named f1
        File f1 = new File ("DiveData.txt");
        // Creates a scanner named sc that scans f1
        Scanner sc = new Scanner (f1);
        // Calls the printIntro method
        printIntro();
        // Calls the processDives method
        processDives(sc);
        // Closes the scanner
        sc.close();

	}

}
