package tests;

import java.time.*;
import org.junit.*;

import customObjects.*;
import utils.FileHandler;

// Used to test the Order class.
public class OrderTest {
	
	// Declare variables used for tests.
	private Order testOrder;
	private Event testEvent;
	private Venue testVenue;
	
	@Before
	public void setUp() {
		testEvent = new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1");
		testVenue = new Venue("Venue 1", 1000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 1", 100);
	}
	
	// Test adding new order not null.
	@Test
	public void testAddNewOrder() {
		
		testOrder = new Order(testEvent, testVenue, 0.1);
		assert(testOrder != null);
	}

	// Test data added accurately.
	@Test
	public void testOrderDataAccurate() {
		
		testOrder = new Order(testEvent, testVenue, 0.1);
		assert(testOrder.getEvent().equals(testEvent) && testOrder.getVenue().equals(testVenue));
		
	}
}
