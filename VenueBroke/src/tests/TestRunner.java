package tests;

import org.junit.internal.TextListener;
import org.junit.runner.*;

// Used to run a test suite upon opening the system.
public class TestRunner {
	
	// Declare variables for a JUnitCore test suite, and to store the result information.
	static JUnitCore junit;
	static Result result;
	
	// Constructor for TestRunner class.
	public TestRunner() {
		
		// Initialize JUnitCore suite.
		junit = new JUnitCore();
		// Add a listener to observe the test output.
		junit.addListener(new TextListener(System.out));
		
	}
	
	// Run the test suite.
	public void run() {
		
		// Run the tests using all test classes and print result to the user.
		System.out.println("\n--------------------------------------------------");
		System.out.println("> Running Tests");
		System.out.println("--------------------------------------------------\n");
		result = junit.run(EventTest.class, VenueTest.class, OrderTest.class,
							FileHandlerTest.class, MenuTest.class);
		
	}
}
