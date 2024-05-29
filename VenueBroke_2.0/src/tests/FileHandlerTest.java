package tests;

import java.util.ArrayList;

import org.junit.*;

import utils.FileHandler;
import customObjects.Event;
import customObjects.Venue;

// Used to test the FileHandler class.
public class FileHandlerTest {
	
	// Declare variables for holding data.
	ArrayList<Event> tempEvents;
	ArrayList<Venue> tempVenues;
	
	@Before
	public void fileHandlerTestSetUp() {
		tempEvents = FileHandler.importRequestList();
		tempVenues = FileHandler.importVenueList();
	}

	// Test that the request list is not imported as null.
	@Test
	public void testImportRequestListNotNull() {
		assert(tempEvents != null);
	}
	
	// Test that the venue list is not imported as null.
	public void testImportVenueListNotNull() {
		assert(tempVenues != null);
	}
}