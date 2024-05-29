package customObjects;

import utils.FileHandler;

// A class used to define an order object. Orders are comprised of
// venue and event information, and are used to define an event booked
// at a venue. Orders hold and calculate event pricing information.
public class Order {
	
	// Define variables for event to book, venue to book at, venue hire
	// fee, agent commission, and rate of commission depending on number of
	// events booked with agent.
	private Event event;
	private Venue venue;
	private double venueHire;
	private double commission = 0;
	private double commissionRate;
	
	// Constructor for order class. Initializes event, venue, and
	// comissionRate variables by being passed from the calling method.
	// Order cost is determined by the calculateCost method, and the
	// event is set to booked.
	public Order(Event event, Venue venue, double commissionRate) {
		this.event = event;
		this.venue = venue;
		this.commissionRate = commissionRate;
		calculateCost();
		event.book();
	}
	
	// Print event details as several lines.
	public void display() {
		
		System.out.printf("\n%-15s%s\n%-15s%5s\n%-15s%5s\n%-15s%5s\n%-15s%5s\n%-15s%5s\n%-15s%d\n" +
							"%d hours venue hire @ $%d = $%5.2f\nBrokering commission @ %.0f%% = $%5.2f\n\n", 
							"Client: ", this.event.getClient(), "Venue Name: ", this.venue.getClient(), 
							"Event Name: ", this.event.getTitle(), "Artist Name: ", this.event.getArtist(), 
							"Event Date: ", FileHandler.dateFormatter.format(this.event.getDate()), 
							"Event Time: ", FileHandler.timeFormatter.format(this.event.getTime()),
							"Event Target: ", event.getTarget(), 
							this.event.getDuration(), this.venue.getHourlyPrice(), 
							this.venueHire, (this.commissionRate*100), this.commission);
			
	}
	
	// Calculates the cost of an order.
	public void calculateCost() {
		
		// Calculate venue hire fee: event duration by venue hourly price.
		this.venueHire = this.event.getDuration() * this.venue.getHourlyPrice();
		
		// Calculate agent commission: venue hire by commission rate.
		this.commission = this.venueHire * this.commissionRate;
		
	}
	
	// Various get/set methods. setEvent and getEvent use calculate cost 
	// to update venue hire when order details are changed.
	public Event getEvent() {
		return this.event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
		calculateCost();
	}
	
	public Venue getVenue() {
		return this.venue;
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
		calculateCost();
	}
	
	public double getVenueHire() {
		return this.venueHire;
	}
	
	public double getCommission() {
		return this.commission;
	}
	
	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	public double getCommissionRate() {
		return this.commissionRate;
	}
	
	public void setComissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}
	
}
