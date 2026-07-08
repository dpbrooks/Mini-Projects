/**
 * @author Dylan Brooks
 * Professor X
 * CSCI 161
 * November 15, 2022
 * A program to make songs a new data type
 *
 */
public class Song {

	/**
	 * @param args
	 */
	
	// Data Members
	String title;
	String artist;
	int length;
	
	// Constructors
	public Song (String trackTitle, String trackArtist, int trackLength) {
		this.title = trackTitle;
		this.artist = trackArtist;
		this.length = trackLength;
	}
	

	
	// Data Members
	// Getters
	public int getLength() {
		return this.length;
	}
	
	// Prints the song
	public void print() {
		System.out.printf("%-24s", title);
        System.out.printf("%-24s", artist);
        System.out.printf("%4d", length);
        System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
