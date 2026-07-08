/**
 * @author Dylan Brooks
 * Professor X
 * CSCI 161
 * November 15, 2022
 * A program to output songs, and determine the total time, longest song, and shortest song
 *
 */

// imports a scanner
import java.util.Scanner;

public class MusicLibrary {

	/**
	 * @param args
	 */
	
	Song[] songs;
		
	public MusicLibrary(Scanner fileScan) {
		int size = fileScan.nextInt();
		fileScan.nextLine();
		songs = new Song[size];
		for (int i = 0; i < size; i++) {
			String[] parts = fileScan.nextLine().split(":");
			songs[i] = new Song(parts[1], parts[2], Integer.parseInt(parts[0]));
		}
	}
	
	public void print() {
		 System.out.printf("%-24s", "TITLE");
	     System.out.printf("%-24s", "ARTIST");
	     System.out.printf("%4s", "TIME");
	     System.out.println();
	     System.out.printf("%-24s", "-----");
	     System.out.printf("%-24s", "------");
	     System.out.printf("%4s", "----");
	     System.out.println();
	     for (int i = 0; i < songs.length; i++) {
	    	 songs[i].print();
	     }
	}
	
	public int getTotalTime() {
		int sum = 0;
		for (int i = 0; i < songs.length; i++) {
			sum += songs[i].getLength();
		}
		return sum;
	}

	public Song getLongestSong() {
		int max = songs[0].getLength();
        int index = 0;
        for (int i = 0; i < songs.length; i++) {
            if (songs[i].getLength() >= max) {
                max = songs[i].getLength();
                index = i;
            }
        }
        return songs[index];
	}
	
	public Song getShortestSong() {
		int min = songs[0].getLength();
        int index = 0;
        for (int i = 0; i < songs.length; i++) {
            if (songs[i].getLength() <= min) {
                min = songs[i].getLength();
                index = i;
            }
        }
        return songs[index];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
