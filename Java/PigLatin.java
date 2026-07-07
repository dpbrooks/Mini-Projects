/**
 * @author Dylan Brooks
 * Professor X
 * CSCI 161
 * November 1, 2022
 * A program that converts an English phrase into Pig Latin
 *
 */

// Creates a scanner
import java.util.Scanner;

public class PigLatin {

	/**
	 * @param args
	 */
	
	// Prompt and read a phrase to translate
	public static String readPhrase (Scanner console){
		// Prompts the user to enter a phrase
		System.out.print("Please enter a phrase ==> ");
		// Returns the entered phrase
		return console.nextLine();
	}
	
	// Convert a phrase to Pig Latin and return it
	public static String convertPhrase (String englishPhrase) {
	// Creates a new scanner to break up the phrase
	Scanner word = new Scanner(englishPhrase);
	// Initializes a  variable named pigLatin to equal the converted first word
	String pigLatin = convertWord(word.next());
	// Creates a while loop that will work so long as there is an unscanned token
	while (word.hasNext()) {
		// Creates a variable named token that is equal to the next unscanned token
		String token = word.next();
		//Updates pigLatin to equal the converted phrase
		pigLatin = pigLatin + " " + convertWord(token);
		}
	// Closes the scanner
	word.close();
	// Returns pigLatin
	return pigLatin;
	}
	
	// Convert a word to Pig Latin and return it
	public static String convertWord (String englishWord) {
		// Creates a string variable named token
		String token;
		// Creates an if statement that tests if the word starts with "th" or "TH"
		if (englishWord.startsWith("th") || englishWord.startsWith("TH")) {
			// Converts the englishWord into Pig Latin and assigns it to token
			token = englishWord.substring(2) + "-" + englishWord.substring(0,2) + "ay";
			// Returns token
			return token;
		// Tests if the word starts with "Th" or "tH"
		} else if (englishWord.startsWith("Th") || englishWord.startsWith("tH")) {
			// Converts the englishWord into Pig Latin and assigns it to token
			token = englishWord.substring(2) + "-" + englishWord.substring(0,2) + "ay";
			// Returns token
			return token;
		// Tests if the word starts with a vowel by calling the isVowel method
		} else if (isVowel(englishWord.charAt(0))) {
			// Converts the englishWord into Pig Latin and assigns it to token
			token = englishWord + "-way";
			// Returns token
			return token;
		// Used if the word does not meet any of the prior conditions
		} else {
			// Converts the englishWord into Pig Latin and assigns it to token
			token = englishWord.substring(1) + "-" + englishWord.substring(0, 1) + "ay";
			// Returns token
			return token;
		}
	}
	
	// Method that returns true if "c" is a vowel, false otherwise
		public static boolean isVowel (char c) {
			// Tests if char c is a lower case vowel
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				// Returns the boolean value true
				return true;
			// Tests if char c is an upper case vowel
			} else if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
				// Returns the boolean value true
				return true;
			// Used if char c is not a vowel
			}else {
				// Returns the boolean value false
				return false;
			}
		}
		
		// Print the result of the translation
		public static void printResult (String englishPhrase, String pigLatinPhrase) {
			// Prints the English phrase
			System.out.println("\"" + englishPhrase + "\"");
			// Prints "  in Pig Latin is"
			System.out.println("  in Pig Latin is");
			// Prints the Pig Latin phrase
			System.out.print("\"" + pigLatinPhrase + "\"");
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creates a new scanner named sc
		Scanner sc = new Scanner(System.in);
		// Prints the program explanation
		System.out.println("This program will convert an English phrase into Pig Latin.");
		// Creates a blank line
		System.out.println();
		// Calls the readWord method and assigns its value to the string variable word
		String word = readPhrase(sc);
		// Calls the convertWOrd method and assigns its value to the string variable pigLatin
		String pigLatin = convertPhrase(word);
		// Creates a blank line
		System.out.println();
		// Calls the printResult method
		printResult(word, pigLatin);
		// Closes the scanner
		sc.close();
	}

}
