package model;

import doa.DatabaseConnector;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import utils.FileHandler;

// A class used to define an order object. Orders are comprised of
// venue and event information, and are used to define an event booked
// at a venue. Orders hold and calculate event pricing information.
public class Order {
	
	// Define variables for event to book, venue to book at, venue hire
	// fee, agent commission, and rate of commission depending on number of
	// events booked with agent.
	private static int orderIDGenerator = 0;
	private SimpleIntegerProperty orderID;
	private Event event;
	private Venue venue;
	private User bookedBy;
	private SimpleDoubleProperty venueHire;
	private SimpleDoubleProperty commission;
	private double commissionRate;
	
	// Constructor for order class. Initializes event, venue, and
	// comissionRate variables by being passed from the calling method.
	// Order cost is determined by the calculateCost method, and the
	// event is set to booked.
	public Order(Event event, Venue venue, double commissionRate) {
		this.orderID = new SimpleIntegerProperty(++orderIDGenerator);
		this.event = event;
		this.venue = venue;
		this.bookedBy = Model.getCurrUser();
		this.commissionRate = commissionRate;
		calculateCost();
		event.book();
	}
	
	// Print event details as several lines.
	public void display() {
		
		System.out.printf("\n%-15s%s\n%-15s%5s\n%-15s%5s\n%-15s%5s\n%-15s%5s\n%-15s%5s\n%-15s%d\n" +
							"%d hours venue hire @ $%d = $%5.2f\nBrokering commission @ %.0f%% = $%5.2f\n\n", 
							"Client: ", this.event.eventClientProperty().get(), "Venue Name: ", this.venue.venueClientProperty().get(), 
							"Event Name: ", this.event.titleProperty().get(), "Artist Name: ", this.event.artistProperty().get(), 
							"Event Date: ", FileHandler.dateFormatter.format(this.event.dateProperty()), 
							"Event Time: ", FileHandler.timeFormatter.format(this.event.timeProperty()),
							"Event Target: ", event.targetProperty().get(), 
							this.event.durationProperty().get(), this.venue.hourlyPriceProperty().get(), 
							this.venueHire, (this.commissionRate*100), this.commission);
			
	}
	
	// Calculates the cost of an order.
	public void calculateCost() {
		
		// Calculate venue hire fee: event duration by venue hourly price.
		this.venueHire = new SimpleDoubleProperty(this.event.durationProperty().get() * this.venue.hourlyPriceProperty().get());
		
		// Calculate agent commission: venue hire by commission rate.
		this.commission = new SimpleDoubleProperty(this.venueHire.get() * this.commissionRate);
		
	}
	
	// Various get/set methods. setEvent and getEvent use calculate cost 
	// to update venue hire when order details are changed.
	
	public SimpleIntegerProperty orderIDProperty() {
		return this.orderID;
	}
	
	
	public Event getEvent() {
		return this.event;
	}
	
	public SimpleIntegerProperty eventIDProperty() {
		return this.event.eventIDProperty();
	}
	
	public void setEvent(Event event) {
		this.event = event;
		calculateCost();
	}
	
	public Venue getVenue() {
		return this.venue;
	}
	
	public SimpleIntegerProperty venueIDProperty() {
		return this.venue.venueIDProperty();
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
		calculateCost();
	}
	
	public User getBookedBy() {
		return this.bookedBy;
	}
	
	public SimpleIntegerProperty bookedByProperty() {
		return this.bookedBy.userIDProperty();
	}
	
	public void setBookedBy(User bookedBy) {
		this.bookedBy = bookedBy;
	}
	
	public SimpleDoubleProperty venueHireProperty() {
		return this.venueHire;
	}
	
	public SimpleDoubleProperty commissionProperty() {
		return this.commission;
	}
	
	public void setCommission(double commission) {
		this.commission = new SimpleDoubleProperty(commission);
	}
	
	public double commissionRateProperty() {
		return this.commissionRate;
	}
	
	public void setComissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}
	
}
