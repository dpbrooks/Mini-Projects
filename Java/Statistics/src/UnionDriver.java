import java.util.Scanner;


/**
 * A class that tries out the Statistician class's union operator.
 * 
 * @author Beth Katz, Chad Hogg
 */
public class UnionDriver {
	
	/**
	 * Prints a labeled summary of a Statistician.
	 * 
	 * @param statistician A Statistician to be printed.
	 * @param label The label for that Statistician.
	 */
	public static void print(Statistician statistician, String label) {
		System.out.println();
		System.out.print("Stat <<" + label + ">> has ");
		System.out.printf("%d entries totalling %5.3f with mean of %5.3f\n",
				statistician.getLength(), statistician.getSum(), statistician.getMean());
		System.out.printf("Largest value is %5.3f and smallest is %5.3f\n", 
				statistician.getLargest(), statistician.getSmallest());
	}

	/**
	 * Obtains several values from Scanner and adds them to a Statistician.
	 * 
	 * @param scan A connection to the keyboard.
	 * @param statistician A Statistician to be filled.
	 * @param count The number of values to obtain from user.
	 */
	public static void fillStat(Scanner scan, Statistician statistician, int count) {
		System.out.print("Enter " + count + " values to add: ");
		for(int counter = 1; counter <= count; counter++) {
			double value = scan.nextDouble();
			statistician.insert(value);
		}
	}

	/**
	 * Builds two Statisticians, fills them with data from user, and 
	 *   unions them to create a new Statistician.
	 *
	 * @param scan A connection to the keyboard.
	 */
	public static void run (Scanner scan) {
		Statistician first = new Statistician();
		Statistician second = new Statistician();
		Statistician union;

		fillStat(scan, first, 5);
		fillStat(scan, second, 4);

		print(first, "a");
		print(second, "b");

		union = first.union(second);
		print(union, "c is union(a,b)");

	}
}
