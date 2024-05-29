package tests;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.*;

import customObjects.Event;
import utils.FileHandler;

// Used to test the event class.
public class EventTest {
	
	// Declare a list for event objects.
	private ArrayList<Event> testEvents;
	
	@Before
	public void setUp() {
		testEvents = new ArrayList<>();
	}
	
	// Test adding normal data to the list.
	@Test
	public void addNormalData() {
		testEvents.add(new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1"));
		testEvents.add(new Event("Client 2", "Title 2", "Artist 2", 
				LocalDate.parse("02/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("01:00pm", FileHandler.timeFormatter), 
				2000, 2, "Type 2", "Category 2"));
		testEvents.add(new Event("Client 3", "Title 3", "Artist 3", 
				LocalDate.parse("03/01/2001", FileHandler.dateFormatter),
				LocalTime.parse("02:00pm", FileHandler.timeFormatter), 
				3000, 3, "Type 3", "Category 3"));
		testEvents.add(new Event("Client 4", "Title 4", "Artist 4", 
				LocalDate.parse("04/01/2001", FileHandler.dateFormatter),
				LocalTime.parse("03:00pm", FileHandler.timeFormatter), 
				4000, 4, "Type 4", "Category 4"));
		testEvents.add(new Event("Client 5", "Title 5", "Artist 5", 
				LocalDate.parse("05/01/2001", FileHandler.dateFormatter),
				LocalTime.parse("04:00pm", FileHandler.timeFormatter), 
				5000, 5, "Type 5", "Category 5"));
	}
	
	// Test list not null after adding data.
	@Test
	public void testListNotNull() {
		testEvents.add(new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1"));
		assert(testEvents.size() > 0);
	}
	
	// Test accuracy of list elements.
	@Test
	public void testListDataAccurate() {
		testEvents.add(new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1"));
		testEvents.add(new Event("Client 2", "Title 2", "Artist 2", 
				LocalDate.parse("02/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("01:00pm", FileHandler.timeFormatter), 
				2000, 2, "Type 2", "Category 2"));
		testEvents.add(new Event("Client 3", "Title 3", "Artist 3", 
				LocalDate.parse("03/01/2001", FileHandler.dateFormatter),
				LocalTime.parse("02:00pm", FileHandler.timeFormatter), 
				3000, 3, "Type 3", "Category 3"));
		testEvents.add(new Event("Client 4", "Title 4", "Artist 4", 
				LocalDate.parse("04/01/2001", FileHandler.dateFormatter),
				LocalTime.parse("03:00pm", FileHandler.timeFormatter), 
				4000, 4, "Type 4", "Category 4"));
		testEvents.add(new Event("Client 5", "Title 5", "Artist 5", 
				LocalDate.parse("05/01/2001", FileHandler.dateFormatter),
				LocalTime.parse("04:00pm", FileHandler.timeFormatter), 
				5000, 5, "Type 5", "Category 5"));
		assert("Category 3".equals(testEvents.get(2).getCategory()));
	}

	// Test output of toString method.
	@Test
	public void testToStringOutput() {
		testEvents.add(new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1"));
		String builtString = "ClientID: 1 Client: Client 1 Title: Title 1 "
								+ "Artist: Artist 1 Date: 2001-01-01 Time: 12:00 "
								+ "Target: 1000 Duration: 1 Type: Type 1 Category: Category 1";
		
		String stringFromList = testEvents.get(0).toString();
		
		assert(builtString.equals(stringFromList));
	}
	
	// Test whether event is initialized as not booked.
	@Test
	public void testNotBooked() {
		testEvents.add(new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1"));
		assert(!testEvents.get(0).isBooked());
	}
	
	// Test the booking function.
	@Test
	public void testBook() {
		testEvents.add(new Event("Client 1", "Title 1", "Artist 1", 
				LocalDate.parse("01/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("12:00pm", FileHandler.timeFormatter), 
				1000, 1, "Type 1", "Category 1"));
		testEvents.add(new Event("Client 2", "Title 2", "Artist 2", 
				LocalDate.parse("02/01/2001", FileHandler.dateFormatter), 
				LocalTime.parse("01:00pm", FileHandler.timeFormatter), 
				2000, 2, "Type 2", "Category 2"));
		boolean isBooked = testEvents.get(1).isBooked();
		testEvents.get(1).book();
		assert(testEvents.get(1).isBooked() != isBooked);
	}

}
