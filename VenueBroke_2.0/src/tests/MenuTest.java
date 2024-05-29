package tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.*;

import customObjects.*;
import utils.FileHandler;

// Used to test the menu class.
public class MenuTest {
	
	// Lists used to hold data.
	private ArrayList<Event> testEvents;
	private ArrayList<Venue> testVenues;
	private ArrayList<Order> testOrders;
	
	@Before
	public void setUp() {
		
		testEvents = new ArrayList<>();
		testVenues = new ArrayList<>();
		testOrders = new ArrayList<>();
		
	}
	
	// Test commission rate accurate.
	@Test
	public void testComissionRate() {
		testEvents.add(new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1"));
		testVenues.add(new Venue("Venue 1", 1000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 1", 100));
		testOrders.add(new Order(testEvents.get(0), testVenues.get(0), 0.1));
		assert(0.1 == testOrders.get(0).getCommissionRate());
	}
}
