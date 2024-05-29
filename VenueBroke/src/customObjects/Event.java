package customObjects;

import java.time.*;

import interfaces.Bookable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import utils.FileHandler;

// Class used to define an Event object. Events are performances
// that are sold with the intention of attendees purchasing tickets.
// Events may be booked to a venue as part of an order.
// Extends the Client class, and implements the Bookable interface.
public class Event extends Client implements Bookable {
	
	// Declare event variables client name, event title, artist name,
	// event date, time, target attendees, duration, type, and category.
	// isBooked is used as part of the Bookable interface.

	private SimpleStringProperty title;
	private SimpleStringProperty artist;
	private LocalDate date;
	private LocalTime time;
	private SimpleIntegerProperty target;
	private SimpleIntegerProperty duration;
	private SimpleStringProperty type;
	private SimpleStringProperty category;
	private boolean isBooked;
	
	// Constructor for an Event object. Uses super to define abstract class 
	// variables and defines all other variables when constructed. Does not 
	// require isBooked to be passed from the calling method, as it is defined 
	// by the constructor.
	public Event(String client, String title, String artist, 
					LocalDate date, LocalTime time, int target, 
					int duration, String type, String category) {
		super(client);
		this.title = new SimpleStringProperty(title);
		this.artist = new SimpleStringProperty(artist);
		this.date = date;
		this.time = time;
		this.target = new SimpleIntegerProperty(target);
		this.duration = new SimpleIntegerProperty(duration);
		this.type = new SimpleStringProperty(type);
		this.category = new SimpleStringProperty(category);
		this.isBooked = false;
		formatDateTime();
	}
	
	// Formats the date/time variables to accept the stream from the
	// CSV file.
	public void formatDateTime() {
		
		this.date.format(FileHandler.dateFormatter);
		this.time.format(FileHandler.timeFormatter);
		
	}
	
	// A method to return the event details as a single line string. 
	// Overrides the built-in toString method.
	@Override
	public String toString() {
		
		// Temporary string to build with event details.
		String temp = "";
		
		// Try/Catch to catch NullPointerException if one
		// or more event details are null.
		try {
			// Build temporary string with event details.
			temp =  "ClientID: " + this.clientID + " "
					+ "Client: " + this.client + " " 
					+ "Title: " + this.title + " "
					+ "Artist: " + this.artist + " " 
					+ "Date: " + this.date + " "
					+ "Time: " + this.time + " " 
					+ "Target: " + this.target + " "
					+ "Duration: " + this.duration + " " 
					+ "Type: " + this.type + " " 
					+ "Category: " + this.category;
		} catch(NullPointerException e) {
			System.out.println("Error. One or more event details are incomplete.");
		}
		
		// Return the built string.
		return temp;
	}
	
	// A method to print the details of an event over several lines.
	// Does not return a string, but prints the details directly.
	public void printDetails() {
		
		// Try/Catch to catch NullPointerException if one
		// or more details are null.
		try {
			
			System.out.printf("%-15s%d\n"
								+ "%-15s%s\n" 
								+ "%-15s%s\n"
								+ "%-15s%s\n"
								+ "%-15s%s\n"
								+ "%-15s%s\n"
								+ "%-15s%d\n"
								+ "%-15s%d\n"
								+ "%-15s%s\n"
								+ "%-15s%s\n",
								"Client ID: ", this.clientID.get(), "Client: ", this.client.get(), 
								"Event Name: ", this.title.get(), "Artist: ", this.artist.get(), 
								"Event Date: ", FileHandler.dateFormatter.format(this.date), 
								"Event Time: ", FileHandler.timeFormatter.format(this.time),
								"Event Target: ", this.target.get(), "Duration: ", this.duration.get(),
								"Type: ", this.type.get(), "Category: ", this.category.get());
			
		} catch (NullPointerException e1) {
			System.out.println("Error. One or more event details are incomplete.");
		}
	}
	
	// Implement the isBooked method from the Bookable interface.
	// Returns whether the event is booked, or unbooked as a t/f
	public boolean isBooked() {
		
		return isBooked;
		
	}
	
	// Implement the book method from the Bookable interface.
	// Updates the isBooked variable when booked.
	public void book() {
		this.isBooked = true;
	}
	
	// Various get/set methods.
	
	public SimpleStringProperty titleProperty() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = new SimpleStringProperty(title);
	}
	
	public SimpleStringProperty artistProperty() {
		return this.artist;
	}
	
	public void setArtist(String artist) {
		this.artist = new SimpleStringProperty(artist);
	}
	
	public LocalDate dateProperty() {
		return this.date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalTime timeProperty() {
		return this.time;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public SimpleIntegerProperty targetProperty() {
		return this.target;
	}

	public void setTarget(int target) {
		this.target = new SimpleIntegerProperty(target);
	}
	
	public SimpleIntegerProperty durationProperty() {
		return this.duration;
	}
	
	public void setDuration(int duration) {
		this.duration = new SimpleIntegerProperty(duration);
	}
	
	public SimpleStringProperty typeProperty() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = new SimpleStringProperty(type);
	}
	
	public SimpleStringProperty categoryProperty() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category = new SimpleStringProperty(category);
	}
	
	@Override
	public SimpleStringProperty clientProperty() {
		return this.client;
	}
	
	@Override
	public void setClient(String client) {
		this.client = new SimpleStringProperty(client);
	}

	@Override
	public SimpleIntegerProperty clientIDProperty() {
		return this.clientID;
	}
}
