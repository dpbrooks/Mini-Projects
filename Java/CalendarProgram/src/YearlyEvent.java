import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An event that repeats yearly.
 * 
 * @author Dylan Brooks
 */
public class YearlyEvent extends RepeatingEvent {
	
	/**
	 * Constructs the object
	 * 
	 * @param eventName The name of the event
	 * @param firstOccurrence The first date the event occurs
	 * @param repetitions The number of times the event repeats
	 * @param startTime The time the event starts
	 * @param endTime The time the event ends. Must be after startTime
	 */
	public YearlyEvent(String eventName, LocalDate firstOccurrence, int repetitions, LocalTime startTime, LocalTime endTime) {
		super(eventName, firstOccurrence, repetitions, startTime, endTime);
	}

	@Override
	public boolean isOnDay(LocalDate when) {
		// TODO Auto-generated method stub
		long rep = (long) getRepetitions();
		if (getFirstOccurrence().isAfter(when)) {
			return false;
		}
		if (getRepetitions() == 0) {
			long counter = 0;
			while (rep == 0) {
				if (getFirstOccurrence().plusYears(counter).getDayOfMonth() != when.getDayOfMonth()) {
					if (getFirstOccurrence().plusMonths(counter).isAfter(when)) {
						return false;
					}
					counter++;
				} else {
					rep += 1;
					return true;
				}
			}
		}
		if (getRepetitions() != 0) {
			if (getFirstOccurrence().plusYears(rep).isBefore(when)) {
				return false;
			}
		}
		for (long i = 0; i < rep; i++) {
			if (getFirstOccurrence().plusYears(i).getDayOfMonth() == when.getDayOfMonth()) {
				return true;
			}
		}
		return false;
	}

}
