package model;

import javafx.beans.property.*;

// Class used to define a venue object. Venues are locations owned
// by clients that can be used for the purposes of holding an event
// for an hourly price. Venues may book events as part of an order.
// Extends the Client class.
public class Venue extends Client {
	
	// Declare variables for client name, venue capacity, an array of 
	// suitability types for differing events, venue category, and 
	// hourly hire price.

	private static int venueIDGenerator = 0;
	private SimpleIntegerProperty venueID;
	private SimpleStringProperty venueClient;
	private SimpleIntegerProperty capacity;
	private SimpleStringProperty[] suitableFor;
	private SimpleStringProperty category;
	private SimpleIntegerProperty hourlyPrice;
	private final SimpleIntegerProperty compatibilityScore;
	
	// Constructor for a venue object. Uses super to define abstract 
	// class variables and defines all other variables when constructed.
	// suitableFor is an array of string elements.
	public Venue(String venueClient, int capacity, String[] suitableFor, 
					String category, int hourlyPrice) {
		
		this.venueID = new SimpleIntegerProperty(++venueIDGenerator);
		this.venueClient = new SimpleStringProperty(venueClient);
		this.capacity = new SimpleIntegerProperty(capacity);
		this.suitableFor = new SimpleStringProperty[suitableFor.length];
		this.suitableFor = convertSuitableFor(suitableFor);
		this.category = new SimpleStringProperty(category);
		this.hourlyPrice = new SimpleIntegerProperty(hourlyPrice);
		this.compatibilityScore = new SimpleIntegerProperty(1);
		
	}
	
	public Venue(int venueID, String venueClient, int capacity, String[] suitableFor, 
			String category, int hourlyPrice) {

		this.venueID = new SimpleIntegerProperty(venueID);
		this.venueClient = new SimpleStringProperty(venueClient);
		this.capacity = new SimpleIntegerProperty(capacity);
		this.suitableFor = new SimpleStringProperty[suitableFor.length];
		this.suitableFor = convertSuitableFor(suitableFor);
		this.category = new SimpleStringProperty(category);
		this.hourlyPrice = new SimpleIntegerProperty(hourlyPrice);
		this.compatibilityScore = new SimpleIntegerProperty(1);
	
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
		temp =  "Client ID: " + this.venueID.get() + " "
				+ "Name: " + this.venueClient.get() + " "
				+ "Capacity: " + this.capacity.get() + " "
				+ "Suitable For: " + this.suitableForToString() + " "
				+ "Category: " + this.category.get() + " "
				+ "Hire Fee: " + this.hourlyPrice.get();
		
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
			suitableForString += suitableFor[i].get();
			
			// If there are more elements in the array, add a comma and space.
			if ( (i+1) < suitableFor.length) {
				
				suitableForString += ", ";
				
			}
		}
		
		
		
		// Return string of suitability elements.
		return suitableForString;
		
	}
	
	public boolean isBooked(Event event) {
		
		for (Order order : Model.getOrders()) {
			
			if (order.venueIDProperty().equals(this.venueIDProperty())) {
				
				if (order.getEvent().dateProperty().equals(event.dateProperty())) {
					
					return true;
					
				}	
			}
		}
		return false;
	}
	
	// Various get/set methods.
	public SimpleStringProperty[] suitableForProperty() {
		return this.suitableFor;
	}
	
	public void setSuitableFor(String[] suitableFor) {
		this.suitableFor = convertSuitableFor(suitableFor);
	}
	
	public SimpleIntegerProperty venueIDProperty() {
		return this.venueID;
	}

	public SimpleStringProperty venueClientProperty() {
		return this.venueClient;
	}
 
	public SimpleIntegerProperty capacityProperty() {
		return this.capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity.set(capacity);
	}
	
	public SimpleStringProperty categoryProperty() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category.set(category);
	}
	
	public SimpleIntegerProperty hourlyPriceProperty() {
		return this.hourlyPrice;
	}
	
	public void setHourlyPrice(int hourlyPrice) {
		this.hourlyPrice.set(hourlyPrice);
	}
	
	public SimpleIntegerProperty compatibilityScoreProperty() {
		return this.compatibilityScore;
	}

	@Override
	public SimpleIntegerProperty clientIDProperty() {
		return this.clientIDProperty();
	}
}
