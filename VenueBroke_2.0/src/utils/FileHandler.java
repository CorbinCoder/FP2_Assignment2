package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

//import customObjects.*;

// A custom class used to import event request and venue details from
// a csv file, and return them as ArrayLists prefilled with information.
public class FileHandler {
	
	// Define file path as string to source folder on local system.
	private static final String localDir = System.getProperty("user.dir");
	
	// Define file path as file object composed of filepath from local
	// system appended with filepath of data folder, then either request,
	// or venue folder.
	private static final File REQUESTS = new File(localDir + "\\src\\data\\requests.csv");
	private static final File VENUES = new File (localDir + "\\src\\data\\venues.csv");
	
	// Initialize formatters for date and time variables.
	public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
	
	// Define delimeter variable to indicate which character to separate
	// each line at.
	private static final String DELIMETER = ",|\\n";
	
	// Method used to import event request information as lines which are
	// separated using a delimeter. Information is used to fill an array 
	// list of event objects, which is returned to the calling method.
	public static ObservableList<Event> importEventList() {
		
		// Array temporary list to be filled with event object elements.
		ObservableList<Event> tempEvents = FXCollections.observableArrayList();
		
		// Define temporary variables to be used to initlialize event objects.
		String tempClient;
		String tempTitle;
		String tempArtist;
		String tempDate;
		LocalDate date;
		String tempTime;
		LocalTime time;
		int tempTarget = 0;
		int tempDuration = 0;
		String tempType;
		String tempCategory;
		
		// Count used to indicate import progress.
		int count = 0;
		
		// Define scanner variable and catch IOException if file not found.
		try(Scanner scanner = new Scanner(REQUESTS)) {
			
			// Add delimiter to scanner.
			scanner.useDelimiter(DELIMETER);
			
			// Skip first line of labeling information.
			scanner.nextLine();
			
			// Inform user that requests are being imported.
			System.out.print("\nLoading requests... ");
			
				// While there are more lines to be read, iterate through each
				// line and use the information to create a new event object then 
				// add it to a list of events.
				while(scanner.hasNextLine()) {

					// Define event details based on each new element extracted
					// from the delimetered line. Catch if integer variables are
					// saved in incorrect format and print which event to user.
					tempClient = scanner.next().strip();
					tempTitle = scanner.next().strip();
					tempArtist = scanner.next().strip();
					tempDate = scanner.next().strip();
					// Check if date is within range. If not, throw FileHandlerException.
					try {
						// If the date does not contain enough characters.
						if (tempDate.length() < 10) {
							// Split the date fields.
							String[] temp = tempDate.split("/");
							// If both day and month are too short, add a digit.
							if (temp[0].length() == 1 && temp[1].length() == 1) {
								tempDate = "0" + temp[0] + "/0" + temp[1] + "/" + temp[2];	
							// If only month is to short, add a digit.
							}	else if (temp[0].length() == 2 && temp[1].length() == 1) {
								tempDate = temp[0] + "/0" + temp[1] + "/" + temp[2];
							// If only day is to short, add a digit.
							} else if (temp[0].length() == 1 && temp[1].length() == 2) {
								tempDate = "0" + tempDate;
							// If date range is too large, throw FileHandlerException.
							} 	else {
								throw new FileHandlerException(tempTitle, "Event Date", tempDate);
							}
						}
					} catch (FileHandlerException e1) {
						e1.printStackTrace();
					}
					//Initialize date variable with parsed date String from CSV.
					date = LocalDate.parse(tempDate, dateFormatter);
					tempTime = scanner.next().strip();
					// Check if time is within range. If not, throw FileHandlerException.
					try {
						// Check if tempTime contains enough digits.
						if (tempTime.length() < 7) {
							tempTime = "0" + tempTime;
						} else if (tempTime.length() > 7) {
							throw new FileHandlerException(tempTitle, "Event Time", 
									tempTime.length());
						}
					} catch (FileHandlerException e1) {
						e1.printStackTrace();
					}
					// Initialize time variable with parsed time String from CSV.
					time = LocalTime.parse(tempTime, timeFormatter);
					// Catch NumberFormatException if tempTarget and tempDuration 
					// entered incorrectly.
					try {
						tempTarget = Integer.parseInt(scanner.next().strip());
						tempDuration = Integer.parseInt(scanner.next().strip());
					} catch (NumberFormatException e1) {
						System.out.println("\nError. Issue with " + tempTitle 
												+ " target or duration fields.");
						e1.printStackTrace();
					}
					tempType = scanner.next().strip();
					tempCategory = scanner.next().strip();
					
					// Initialize new event object using information extracted from 
					// line and add it to the event list.
					tempEvents.add(new Event(tempClient, tempTitle, tempArtist, 
												date, time,	tempTarget, tempDuration, 
												tempType, tempCategory));
					
					// Notify user of import progress then increment progress count.
					System.out.print(count + "... ");
					count++;
					
					// Move to the next line of import file.
					scanner.nextLine();
				}
					
			} catch (IOException e) {
				System.out.println("Error. Request file not found.");
				e.printStackTrace();
			}
			
			// Notify user that import of events is complete.
			System.out.println("Done!");
		
			// Return event list to calling method.
			return tempEvents;
			
		}
	
	// Method used to import venue information as lines which are
	// seperated using a delimeter. Information is used to fill an 
	// array list of venue objects, which is returned to the calling method.
	public static ObservableList<Venue> importVenueList() {
		
		// Define temporary list to be filled with venue object elements.
		ObservableList<Venue> tempVenues = FXCollections.observableArrayList();
		
		// Define temporary variables to be used to filled venue object information.
		String tempClient;
		int tempCapacity = 0;
		String[] tempSuitable;
		String tempCategory;
		int tempPrice = 0;
		
		// Count used to indicate import progress.
		int count = 0;
		
		// Define scanner object and catch NullPointerException if file not found.
		try(Scanner scanner = new Scanner(VENUES)) {
			
			// Add delimeter to scanner.
			scanner.useDelimiter(DELIMETER);
			
			// Skip first line of labelling information.
			scanner.nextLine();
			
			// Inform user that venues are being imported.
			System.out.print("Loading venues... ");
			
			// While there are more lines to be read, iterate through each
			// line as a new venue.
			while(scanner.hasNextLine()) {
				
				// Define venue details based on each new element extracted
				// from the delimetered line. Catch if integer variables are
				// saved in incorrect format and print which venue to user.
				tempClient = scanner.next().strip();
				try {
					tempCapacity = Integer.parseInt(scanner.next().strip());
				} catch (NumberFormatException e1) {
					System.out.println("\nError. Issue with " + tempClient 
											+ " capacity field.");
					e1.printStackTrace();
				}
				// tempSuitable is an array element that is comprised of single
				// elements separated by a semicolon.
				tempSuitable = (scanner.next().split("; "));
				// Strip suitability elements of any leading or trailling whitespace.
				for(String s : tempSuitable) {
					s.strip();
				}
				tempCategory = scanner.next().strip();
				try {
					tempPrice = Integer.parseInt(scanner.next().strip());
				} catch (NumberFormatException e1) {
					System.out.println("\nError. Issue with " + tempClient 
											+ " price field.");
				}
				
				// Initialize new venue object using information extratced from 
				// line and add it to the venue list.
				tempVenues.add(new Venue(tempClient, tempCapacity, tempSuitable, 
											tempCategory, tempPrice));
				
				// Notify user of import progress then increment progress count.
				System.out.print(count + "... ");
				count++;
				
				// Move to the next line of import file.
				scanner.nextLine();
			}
			
		} catch (IOException e1) {
			System.out.println("Error. Venue file not found.");
			e1.printStackTrace();
		}
		
		// Notify user that import of venues is complete.
		System.out.println("Done!");

		// Return venue list to calling method.
		return tempVenues;
		
	}
}
