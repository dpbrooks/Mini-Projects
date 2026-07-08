import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An event that repeats weekly.
 * 
 * @author Dylan Brooks
 *
 */

public class WeeklyEvent extends RepeatingEvent{
	
	/**
	 * Constructs the object.
	 * 
	 * @param eventName The name of the event.
	 * @param firstOccurrence The first date the event occurs.
	 * @param repetitions The number of times the event is repeated.
	 * @param startTime The time the event starts at.
	 * @param endTime The time the event ends. Must be after the startTime.
	 */
	public WeeklyEvent(String eventName, LocalDate firstOccurrence, int repetitions, LocalTime startTime, LocalTime endTime) {
		super(eventName, firstOccurrence, repetitions, startTime, endTime);
	}
	
	@Override
	public boolean isOnDay(LocalDate when) {
		// TODO
		long rep = (long) getRepetitions();
		if (getFirstOccurrence().isAfter(when)) {
			return false;
		}
		if (getRepetitions() == 0) {
			long counter = 0;
			while (rep == 0) {
				if (getFirstOccurrence().plusWeeks(counter).getDayOfWeek() != when.getDayOfWeek()) {
					if (getFirstOccurrence().plusWeeks(counter).isAfter(when)) {
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
			if (getFirstOccurrence().plusWeeks(rep).isBefore(when)) {
				return false;
			}
		}
		for (long i = 0; i < rep; i++) {
			if (getFirstOccurrence().plusWeeks(i).getDayOfWeek() == when.getDayOfWeek()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (getRepetitions() == 0) {
			return super.toString() + " weeks starting on " + getFirstOccurrence();
		}
		return super.toString() + " weeks starting on " + getFirstOccurrence();
	}

}
