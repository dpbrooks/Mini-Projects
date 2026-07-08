import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An event that repeats.
 * 
 * @author Dylan Brooks
 *
 */

public abstract class RepeatingEvent extends Event {
	// Class Invariants:
	// 1. repetitions must be non-negative.
	
	/** Date of the first time the event occurs.*/
	private LocalDate firstOccurrence;
	/** Number of times the even will repeat after firstOccurrence.*/
	private int repetitions;
	
	/**
	 * Constructs a repeating event.
	 * 
	 * @param eventName The name of the event.
	 * @param firstOccurrence The date of the first time the event occurs.
	 * @param repetitions The number of times the event repeats after the first occurrence. Must be non-negative.
	 * @param startTime The time the even starts.
	 * @param endTime The time the event ends. Must be after the startTime.
	 */
	public RepeatingEvent(String eventName, LocalDate firstOccurrence, int repetitions, LocalTime startTime, LocalTime endTime) {
		super(eventName, startTime, endTime);
		if (repetitions < 0) {
			throw new IllegalArgumentException("Repetitions must be non-negative.");
		}
		this.firstOccurrence = firstOccurrence;
		this.repetitions = repetitions;
	}
	
	/**
	 * Gets the date of the first occurrence of the event.
	 * 
	 * @return The date of the first occurrence of the event.
	 */
	public LocalDate getFirstOccurrence() {
		return firstOccurrence;
	}
	
	/**
	 * Gets the number of times the event repeats.
	 * 
	 * @return The number of times the event repeats.
	 */
	public int getRepetitions() {
		return repetitions;
	}
	
	@Override
	public String toString() {
		if (repetitions == 0) {
			return super.toString() + " repeating for all";
		}
		return super.toString() + " repeating for " + repetitions;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj) == false) {
			return false;
		}
		RepeatingEvent other = (RepeatingEvent) obj;
		if (!firstOccurrence.equals(other.firstOccurrence) || repetitions != other.repetitions) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + repetitions * 9 + firstOccurrence.hashCode() * 11;
	}

}
