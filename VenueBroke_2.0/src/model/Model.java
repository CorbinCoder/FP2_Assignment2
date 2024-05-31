package model;

import java.sql.SQLException;
import java.util.ArrayList;

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
		
		eventList = FXCollections.observableArrayList();
		venueList = FXCollections.observableArrayList();
		orderList = FXCollections.observableArrayList();
		userList = FXCollections.observableArrayList();
			
		Admin admin =  new Admin("john", "doe", "admin", "password");
		System.out.println("Admin email: " + admin.emailProperty().get() 
							+ "\nAdmin password: " + admin.passwordProperty().get());
		userList.add(admin);
		
		dbConnector = new DatabaseConnector();
		DatabaseConnector.initializeDB();
		DatabaseConnector.addAllData(venueList, eventList, orderList, userList);
	}
	
	public static void importFromCSV() {
		
		try {
			
			venueList = null;
			eventList = null;
			
			venueList = FXCollections.observableArrayList(FileHandler.importVenueList());
			eventList = FXCollections.observableArrayList(FileHandler.importEventList());
			
			DatabaseConnector.addDataToVenueTable(venueList);
			DatabaseConnector.addDataToEventTable(eventList);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void load() {
		
	}
	
	public void save() {
		
	}
	
	public void login(String username, String password) {
		
		
		
	}
	
	public static StringBuilder getAvailableVenues (Event event) {
		
		StringBuilder queryBuilder = new StringBuilder();
		
		for (Order order : orderList) {
			
			if (order.getEvent().dateProperty().equals(event.dateProperty())) {
				
				queryBuilder.append(order.getVenue().venueIDProperty());
				
			}
			
			if (orderList.indexOf(order) < orderList.size()) {
				
				queryBuilder.append(",");
				
			} else {
				continue;
			}
		}
		
		return queryBuilder;
	}
	
	public static ObservableList<Event> getEvents() {
		return eventList;
	}
	
	public static void setEvents(ObservableList<Event> events) {
		eventList = events;
	}
	
	public static void clearEvents() {
		eventList = null;
		eventList = FXCollections.observableArrayList();
	}
	
	public static ObservableList<Venue> getVenues() {
		return venueList;
	}
	
	public static void setVenues(ObservableList<Venue> venues) {
		venueList = venues;
	}
	
	public static void clearVenues() {
		venueList = null;
		venueList = FXCollections.observableArrayList();
	}
	
	public static ObservableList<Order> getOrders() {
		return orderList;
	}
	
	public static void setOrders(ObservableList<Order> orders) {
		orderList = orders;
	}
	
	public static ObservableList<User> getUsers() {
		return userList;
	}
	
	public static void setUsers(ObservableList<User> users) {
		userList = users;
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
