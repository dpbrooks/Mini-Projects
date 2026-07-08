

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A thing that you want to do.
 * 
 * @author Chad Hogg, Dylan Brooks
 */
public abstract class Event {
	
	// Class invariants:
	// 1: startTime is before endTime

	/** The name of the event. */
	private String eventName;
	/** The time when the event starts. */
	private LocalTime startTime;
	/** The time when the event ends. */
	private LocalTime endTime;
	
	/**
	 * Constructs a new event.
	 * 
	 * @param eventName The name of the new event.
	 * @param startTime The time when the new event starts.
	 * @param endTime The time when the new event ends.  This must be after startTime.
	 */
	public Event(String eventName, LocalTime startTime, LocalTime endTime) {
		// TODO: Implement.
		if (startTime.isAfter(endTime)) {
			throw new IllegalArgumentException("Start time must be before end time");
		}
		this.eventName = eventName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	 * Gets the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return eventName; // TODO: Implement.
	}
	
	/**
	 * Gets the time when the event starts.
	 * 
	 * @return The time when the event starts.
	 */
	public LocalTime getStartTime() {
		return startTime; // TODO: Implement.
	}
	
	/**
	 * Gets the time when the event ends.
	 * 
	 * @return The time when the event ends.
	 */
	public LocalTime getEndTime() {
		return endTime; // TODO: Implement.
	}
	
	/**
	 * Determines whether or not the event occurs on a given day.
	 * 
	 * @param when The day it might occur on.
	 * @return True if the event occurs on that day, or false otherwise.
	 */
	public abstract boolean isOnDay(LocalDate when);
	
	@Override
	public String toString() {
		return eventName + " (" + startTime + "-" + endTime + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Event other = (Event) obj;
		if (!eventName.equals(other.eventName)) {
			return false;
		}
		if (!startTime.equals(other.startTime)) {
			return false;
		}
		if (!endTime.equals(other.endTime)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return eventName.hashCode() + 3 * startTime.hashCode() + 7 * endTime.hashCode();
	}
}
