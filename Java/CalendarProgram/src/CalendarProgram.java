

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A program that lets you manage your event calendar.
 * 
 * @author Chad Hogg, Dylan Brooks
 */
public class CalendarProgram {

	/**
	 * Runs the program.
	 * 
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		ArrayList<Event> allEvents = new ArrayList<Event>();

		String menuSelection = getMainMenuSelection(console);
		while(!menuSelection.equals("quit")) {
			if(menuSelection.equals("add")) {
				Event newEvent = makeNewEvent(console);
				addEvent(allEvents, newEvent);
			}
			else if(menuSelection.equals("load")) {
				ArrayList<Event> myEvents = new ArrayList<>();
				myEvents.add(new WeeklyEvent("CSCI162-50A", LocalDate.of(2020, 8, 24), 15, LocalTime.of(10, 0), LocalTime.of(10, 50)));
				myEvents.add(new WeeklyEvent("CSCI162-50A", LocalDate.of(2020, 8, 28), 15, LocalTime.of(10, 0), LocalTime.of(10, 50)));
				myEvents.add(new WeeklyEvent("CSCI162-50A", LocalDate.of(2020, 8, 25), 15, LocalTime.of(10, 0), LocalTime.of(11, 50)));
				myEvents.add(new WeeklyEvent("CSCI162-50B", LocalDate.of(2020, 8, 24), 15, LocalTime.of(11, 0), LocalTime.of(11, 50)));
				myEvents.add(new WeeklyEvent("CSCI162-50B", LocalDate.of(2020, 8, 28), 15, LocalTime.of(11, 0), LocalTime.of(11, 50)));
				myEvents.add(new WeeklyEvent("CSIC162-50B", LocalDate.of(2020, 8, 26), 15, LocalTime.of(10, 0), LocalTime.of(11, 50)));
				myEvents.add(new WeeklyEvent("CSCI375-50A", LocalDate.of(2020, 8, 24), 15, LocalTime.of(13, 0), LocalTime.of(13, 50)));
				myEvents.add(new WeeklyEvent("CSCI375-50A", LocalDate.of(2020, 8, 28), 15, LocalTime.of(13, 0), LocalTime.of(13, 50)));
				myEvents.add(new WeeklyEvent("CSCI375-50A", LocalDate.of(2020, 8, 25), 15, LocalTime.of(13, 10), LocalTime.of(15, 0)));
				myEvents.add(new WeeklyEvent("Office Hours", LocalDate.of(2020, 8, 24), 15, LocalTime.of(15, 0), LocalTime.of(16, 50)));
				myEvents.add(new WeeklyEvent("Office Hours", LocalDate.of(2020, 8, 26), 15, LocalTime.of(12, 0), LocalTime.of(13, 50)));
				myEvents.add(new WeeklyEvent("Office Hours", LocalDate.of(2020, 8, 28), 15, LocalTime.of(14, 0), LocalTime.of(14, 50)));
				myEvents.add(new WeeklyEvent("Department Meeting", LocalDate.of(2020, 8, 26), 15, LocalTime.of(15, 0), LocalTime.of(16, 30)));
				myEvents.add(new OneTimeEvent("Academic Policies Meeting", LocalDate.of(2020, 9, 3), LocalTime.of(16, 0), LocalTime.of(16, 50)));
				myEvents.add(new OneTimeEvent("Academic Policies Meeting", LocalDate.of(2020, 9, 17), LocalTime.of(16, 0), LocalTime.of(16, 50)));
				myEvents.add(new OneTimeEvent("Academic Policies Meeting", LocalDate.of(2020, 10, 1), LocalTime.of(16, 0), LocalTime.of(16, 50)));
				myEvents.add(new OneTimeEvent("Academic Policies Meeting", LocalDate.of(2020, 10, 15), LocalTime.of(16, 0), LocalTime.of(16, 50)));
				myEvents.add(new OneTimeEvent("Academic Policies Meeting", LocalDate.of(2020, 11, 5), LocalTime.of(16, 0), LocalTime.of(16, 50)));
				myEvents.add(new OneTimeEvent("Academic Policies Meeting", LocalDate.of(2020, 11, 19), LocalTime.of(16, 0), LocalTime.of(16, 50)));
				myEvents.add(new OneTimeEvent("Academic Policies Meeting", LocalDate.of(2020, 12, 3), LocalTime.of(16, 0), LocalTime.of(16, 50)));
				myEvents.add(new MonthlyEvent("Meet With Mentor", LocalDate.of(2020, 9, 1), 4, LocalTime.of(18, 0), LocalTime.of(18, 30)));
				for(Event event : myEvents) {
					addEvent(allEvents, event);
				}
			}
			else if(menuSelection.equals("view")) {
				LocalDate date = getDate("View ", console);
				System.out.println("Your schedule for " + date + " includes:");
				int count = 0;
				for(Event event : allEvents) {
					if(event.isOnDay(date)) {
						System.out.println("\t" + event);
						count++;
					}
				}
				if(count == 0) {
					System.out.println("\tnothing at all!");
				}
			}
			menuSelection = getMainMenuSelection(console);
		}
		System.out.println("Have a good day!");

	}

	/**
	 * Gets the name of a new event.
	 * 
	 * @param console A Scanner connected to the keyboard.
	 * @return The name the user typed in.
	 */
	private static String getNewEventName(Scanner console) {
		System.out.print("Event name: ");
		// Dummy read
		console.nextLine();
		return console.nextLine();
	}

	/**
	 * Gets the user's menu selection.
	 * 
	 * @param console A Scanner connected to the keyboard.
	 * @return One of the words "add", "view", or "quit".
	 */
	private static String getMainMenuSelection(Scanner console) {
		System.out.println();
		System.out.println("add (a new event)");
		System.out.println("load (a subset of Dr. Hogg's Fall 2020 calendar)");
		System.out.println("view (the events for a day)");
		System.out.println("quit (the program)");
		System.out.print("Your choice: ");
		String answer = console.next().toLowerCase();
		while(!answer.equals("add") && !answer.equals("load") && !answer.equals("view") && !answer.equals("quit")) {
			System.out.println("That is not a valid response.");
			System.out.print("Your choice: ");
			answer = console.next().toLowerCase();
		}
		return answer;
	}

	/**
	 * Reads information about an event from the user.
	 * 
	 * @param console A Scanner connected to the console.
	 * @return The new event the user created.
	 */
	private static Event makeNewEvent(Scanner console) {
		Event newEvent = null;
		String name = getNewEventName(console);
		while(newEvent == null) {
			System.out.print("Type: (once, weekly, monthly, or yearly): ");
			String type = console.next().toLowerCase();
			if(type.equals("once")) {
				LocalDate date = getDate("Single", console);
				LocalTime startTime = getTime("Start", console);
				LocalTime endTime = getTime("End", console);
				newEvent = new OneTimeEvent(name, date, startTime, endTime);
			}
			else if(type.equals("monthly")) {
				LocalDate first = getDate("First", console);
				System.out.print("Repetitions (0 for infinite): ");
				int repetitions = console.nextInt();
				LocalTime startTime = getTime("Start", console);
				LocalTime endTime = getTime("End", console);
				newEvent = new MonthlyEvent(name, first, repetitions, startTime, endTime);
			}
			else if(type.equals("weekly")) {
				LocalDate first = getDate("First", console);
				System.out.print("Repetitions (0 for infinite): ");
				int repetitions = console.nextInt();
				LocalTime startTime = getTime("Start", console);
				LocalTime endTime = getTime("End", console);
				newEvent = new WeeklyEvent(name, first, repetitions, startTime, endTime);
			}
			else if(type.equals("yearly")) {
				LocalDate first = getDate("First", console);
				System.out.print("Repetitions (0 for infinite): ");
				int repetitions = console.nextInt();
				LocalTime startTime = getTime("Start", console);
				LocalTime endTime = getTime("End", console);
				newEvent = new YearlyEvent(name, first, repetitions, startTime, endTime);
			}
			else {
				System.out.println("I am sorry, but that is not a type of event.");
			}
		}   
		return newEvent;
	}

	/**
	 * Reads information about a date from the keyboard.
	 * 
	 * @param description A description of the requested date.
	 * @param console A Scanner connected to the keyboard.
	 * @return The user's chosen date.
	 */
	private static LocalDate getDate(String description, Scanner console) {
		System.out.print(description + " date year: ");
		int year = console.nextInt();
		System.out.print(description + " date month (1-12): ");
		int month = console.nextInt();
		System.out.print(description + " date day (1-31): ");
		int day = console.nextInt();
		return LocalDate.of(year, month, day);
	}

	/**
	 * Reads information about a time from the keyboard.
	 * 
	 * @param description A description of the requested time.
	 * @param console The Scanner connected to the keyboard.
	 * @return The user's chosen time (hour+minute resolution).
	 */
	private static LocalTime getTime(String description, Scanner console) {
		System.out.print(description + " time hour: ");
		int hour = console.nextInt();
		System.out.print(description + " time minute: ");
		int minute = console.nextInt();
		return LocalTime.of(hour, minute);
	}
	
	/**
	 * Adds an event to the calendar, if it did not already exist.
	 * 
	 * @param events A collection of events.
	 * @param event A new event to add.
	 * @postcondition If the collection did not already contain the event, it has been added.
	 */
	private static void addEvent(ArrayList<Event> events, Event event) {
		if(events.contains(event)) {
			System.out.println(event + " was already on your calendar!");
		}
		else {
			events.add(event);
			System.out.println("Added " + event);
		}		
	}
}
