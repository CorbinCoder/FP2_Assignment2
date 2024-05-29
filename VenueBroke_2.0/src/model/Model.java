package model;

import doa.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.FileHandler;

public class Model {
	
	// Declare ArrayList names for all lists used to contain custom objects.
	private static ObservableList<Event> eventList;
	private static ObservableList<Venue> venueList;
	private static ObservableList<Order> orderList;
	private static ObservableList<User> userList;
	private static DatabaseConnector dbConnector;
	private static User currUser;
	
	public Model() {
		importFromCSV();
		currUser = new User("john", "doe", "c.peever@live.com", "cPeever7*");
		userList.add(currUser);
		dbConnector = new DatabaseConnector();
		dbConnector.initializeDB();
		dbConnector.addData(venueList, eventList, orderList, userList);
		System.out.println(currUser.emailProperty().get() + " " + currUser.passwordProperty().get());
	}
	
	public static void importFromCSV() {
		
		eventList = FXCollections.observableArrayList(FileHandler.importEventList());
		venueList = FXCollections.observableArrayList(FileHandler.importVenueList());
		orderList = FXCollections.observableArrayList();
		userList = FXCollections.observableArrayList();
	}
	
	public void load() {
		
	}
	
	public void save() {
		
	}
	
	public void login() {
		
	}
	
	public static ObservableList<Event> getEvents() {
		return eventList;
	}
	
	public static ObservableList<Venue> getVenues() {
		return venueList;
	}
	
	public static ObservableList<User> getUsers() {
		return userList;
	}
	
	public static User getCurrUser() {
		return currUser;
	}
	
	public static void setCurrUser(User user) {
		currUser = user;
	}
	
	public static void logoutUser() {
		currUser = null;
	}
 
}
