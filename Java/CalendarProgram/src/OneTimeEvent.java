import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An event that only happens once.
 * 
 * @author Dylan Brooks
 *
 */

public class OneTimeEvent extends Event {
	
	/** The day the event occurs.*/
	private LocalDate date;
	
	/**
	 * Constructs a one time event.
	 * 
	 * @param eventName The name of the event.
	 * @param date The date the event occurs on.
	 * @param startTime The time the event starts.
	 * @param endTime The time the event ends. Must be after the start time.
	 */
	public OneTimeEvent(String eventName, LocalDate date, LocalTime startTime, LocalTime endTime) {
		super(eventName, startTime, endTime);
		this.date = date;
	}
	
	@Override
	public boolean isOnDay(LocalDate when) {
		if (date.isAfter(when) || date.isBefore(when)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString() + " on " + date;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj) == false) {
			return false;
		}
		OneTimeEvent other = (OneTimeEvent) obj;
		return isOnDay(other.date);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + date.hashCode() * 5;
	}

}
