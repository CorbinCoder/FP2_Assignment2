package customObjects;

import javafx.beans.property.*;

// Class used to define a venue object. Venues are locations owned
// by clients that can be used for the purposes of holding an event
// for an hourly price. Venues may book events as part of an order.
// Extends the Client class.
public class Venue extends Client {
	
	// Declare variables for client name, venue capacity, an array of 
	// suitability types for differing events, venue category, and 
	// hourly hire price.

	private SimpleIntegerProperty capacity;
	private SimpleStringProperty[] suitableFor;
	private SimpleStringProperty category;
	private SimpleIntegerProperty hourlyPrice;
	
	// Constructor for a venue object. Uses super to define abstract 
	// class variables and defines all other variables when constructed.
	// suitableFor is an array of string elements.
	public Venue(String client, int capacity, String[] suitableFor, 
					String category, int hourlyPrice) {
		
		super(client);
		this.capacity = new SimpleIntegerProperty(capacity);
		this.suitableFor = new SimpleStringProperty[suitableFor.length];
		this.suitableFor = convertSuitableFor(suitableFor);
		this.category = new SimpleStringProperty(category);
		this.hourlyPrice = new SimpleIntegerProperty(hourlyPrice);
		
	}
	
	private SimpleStringProperty[] convertSuitableFor(String[] suitableFor) {
		SimpleStringProperty[] temp = new SimpleStringProperty[suitableFor.length];
		for (int i = 0; i < suitableFor.length; i++) {
			if (suitableFor[i] != null) {
				temp[i] = new SimpleStringProperty(suitableFor[i]);
			}
		}
		return temp;
	}
	
	// Returns the venue details as a single-line string.
	// Overrides the built-in toString method.
	@Override
	public String toString() {
		
		// Temporary string to build with venue details.
		String temp = "";
		
		// Build single-line string with venue details.
		temp =  "Client ID: " + this.clientID + " "
				+ "Name: " + this.client + " "
				+ "Capacity: " + this.capacity + " "
				+ "Suitable For: " + this.suitableForToString() + " "
				+ "Category: " + this.category + " "
				+ "Hire Fee: " + this.hourlyPrice;
		
		// Return string of venue details.
		return temp;
		
	}
	
	// Return a single-line string of suitableFor elements.
	public String suitableForToString() {
		
		// String to be filled with suitability details.
		String suitableForString = "";
		
		// Iterate through suitableFor elements and add
		// them to a single-line string.
		for (int i = 0; i < suitableFor.length; i++) {
			
			// Append string with current suitability details.
			suitableForString += suitableFor[i];
			
			// If there are more elements in the array, add a comma and space.
			if ( (i+1) < suitableFor.length) {
				
				suitableForString += ", ";
				
			}
		}
		
		
		
		// Return string of suitability elements.
		return suitableForString;
		
	}
	
	// Various get/set methods.
	public SimpleStringProperty[] getSuitableFor() {
		return this.suitableFor;
	}
	
	public void setSuitableFor(String[] suitableFor) {
		this.suitableFor = convertSuitableFor(suitableFor);
	}
	
	public SimpleStringProperty getClient() {
		return this.client;
	}
	
	public void setClient(String client) {
		this.client = new SimpleStringProperty(client);
	}
 
	public int getCapacity() {
		return this.capacity.intValue();
	}
	
	public void setCapacity(int capacity) {
		this.capacity = new SimpleIntegerProperty(capacity);
	}
	
	public String getCategory() {
		return this.category.toString();
	}
	
	public void setCategory(String category) {
		this.category = new SimpleStringProperty(category);
	}
	
	public int getHourlyPrice() {
		return this.hourlyPrice.intValue();
	}
	
	public void setHourlyPrice(int hourlyPrice) {
		this.hourlyPrice = new SimpleIntegerProperty(hourlyPrice);
	}

	@Override
	public SimpleIntegerProperty getClientID() {
		return this.clientID;
	}
}
