package interfaces;

// A custom interface used to define whether a custom class is bookable.
// Contains a boolean variable called booked that can be alternated between
// true and false to show if an event is or is not booked.
// Contains two methods that must be implemented by implementing classes:
// book, and isBooked.
public interface Bookable {
	
	// Defines booked status. Bookable classes are initialized as unbooked.
	// (They are not booked until they are actively booked [f -> t]).
	boolean booked = false;
	
	// Set booked status (t/f).
	public abstract void book();
	
	// Return booked status (t/f).
	public abstract boolean isBooked();
	
}
