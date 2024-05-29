package interfaces;

// A custom interface used to define whether a custom class is bookable.
// Contains a boolean variable called booked that can be alternated between
// true and false to show if an event is or is not booked.
// Contains two methods that must be implemented by implementing classes:
// book, and isBooked.
public interface Bookable {
	
	// Set booked status (t/f).
	public void book();
	
	// Return booked status (t/f).
	public boolean isBooked();
	
}
