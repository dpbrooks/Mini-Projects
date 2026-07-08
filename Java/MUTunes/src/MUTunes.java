/**
 * 
 */

/**
 * @author Dylan Brooks
 * Professor X
 * CSCI 161
 * November 15, 2022
 * A program to output songs, and determine the total time, longest song, and shortest song
 *
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MUTunes {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File("Music.txt"));
		MusicLibrary library = new MusicLibrary(sc);

	    library.print();
	    System.out.println();


        int totalTime = library.getTotalTime();
        System.out.printf("%-48s%4d\n\n", "TOTAL TIME", totalTime);

        Song longest = library.getLongestSong();
        System.out.println("LONGEST SONG");
        System.out.println("------------");
        longest.print();
        System.out.println();

        Song shortest = library.getShortestSong();
        System.out.println("SHORTEST SONG");
        System.out.println("-------------");
        shortest.print();
        System.out.println();

	}

}
