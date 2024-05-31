package doa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class DatabaseConnector {
	
	public static Connection connection;
	
	public DatabaseConnector() {
		
		try {
			
			connection = DriverManager.getConnection("jdbc:sqlite:C:src/data/VenueBroke.db");
			connection.setAutoCommit(false);
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	
	public static void initializeDB() {
		
//		// Test DB
//		try {
//			Statement statement = connection.createStatement();
//			PreparedStatement pStatement = connection.prepareStatement("INSERT or IGNORE INTO test (testID) VALUES (?)");
//			pStatement.execute();
//			pStatement.clearParameters();
//			statement.execute("CREATE TABLE IF NOT EXISTS test (testID INTEGER not NULL);");
//			
//			pStatement.setInt(1, 1);
//			pStatement.addBatch();
//			
//			pStatement.setInt(1, 1);
//			pStatement.addBatch();
//			
//			pStatement.executeBatch();
//			
//			ResultSet results = statement.executeQuery("SELECT * FROM test WHERE testID = 1");
//			while (results.next()) {
//				int testID = results.getInt("testID");
//				System.out.println("Test ID: " + testID);
//			}
//			statement.close();
//									
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		// Test DB
		
		if (setUpVenueTable() && setUpEventTable() && setUpOrderTable() && setUpUserTable()) {
			
			System.out.println("Database initialized successfully.");
			
		} else {
			
			System.out.println("Error - Unable to intialize database.");
			
		}
		
	}
	
	public static void addAllData(ObservableList<Venue> venueList, ObservableList<Event> eventList,
							ObservableList<Order> orderList, ObservableList<User> userList) {
		
		try {
			
			if (addDataToVenueTable(venueList) && addDataToEventTable(eventList) 
					&& addDataToOrderTable(orderList) && addDataToUserTable(userList)) {
				
				System.out.println("Data successfully added to database.");
				
			} else {
				
				System.out.println("Error - Unable to add data to database.");
				
			}
			
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private static boolean setUpVenueTable() {
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS venues;");
			statement.execute("CREATE TABLE IF NOT EXISTS venues ("
								+ "venueID INTEGER not NULL,"
								+ "venueClient VARCHAR(100),"
								+ "capacity INTEGER,"
								+ "suitableFor VARCHAR(250),"
								+ "category VARCHAR(100),"
								+ "hourlyPrice INTEGER"
								+ ");");
			statement.close();
			
			System.out.println("Venue table success");
			return true;
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();			
		}
		
		return false;
	}
	
	public static boolean addDataToVenueTable(ObservableList<Venue> venueList) throws SQLException{
		
		PreparedStatement statement = connection.prepareStatement("DELETE FROM venues");
		
		statement = connection.prepareStatement("INSERT or REPLACE INTO venues ("
												+ "venueID,venueClient,capacity,"
												+ "suitableFor,category,hourlyPrice)"
												+ "VALUES (?,?,?,?,?,?)"
												+ ";");
		
		for (Venue venue : venueList) {
			
			statement.clearParameters();
			statement.setInt(1, venue.venueIDProperty().get());
			statement.setString(2, venue.venueClientProperty().get());
			statement.setInt(3, venue.capacityProperty().get());
			statement.setString(4, venue.suitableForToString());
			statement.setString(5, venue.categoryProperty().get());
			statement.setInt(6, venue.hourlyPriceProperty().get());
			statement.addBatch();
		}
		
		int[] results = statement.executeBatch();
		statement.close();
	
		if (results.length >= 0) {
			connection.commit();
			System.out.println("Venue table data added successfully");
			return true;
		} else {
			connection.rollback();
			System.out.println("Venue table not added");
			return false;
		}
	}
	
	public static boolean updateVenueTable(ObservableList<Venue> venueList) throws SQLException{
		
		// Available, sufficient capacity, event type, venue category
		
		PreparedStatement statement = 
				connection.prepareStatement("UPDATE venues SET ("
												+ "venueClient = ?,"
												+ "capacity = ?,"
												+ "suitableFor = ?,"
												+ "category = ?,"
												+ "hourlyPrice = ?)"
												+ "WHERE venueID = ?");
		
		for (Venue venue : venueList) {
			
			statement.clearParameters();
			statement.setString(1, venue.venueClientProperty().get());
			statement.setInt(2, venue.capacityProperty().get());
			statement.setString(3, venue.suitableForToString());
			statement.setString(4, venue.categoryProperty().get());
			statement.setInt(5, venue.hourlyPriceProperty().get());
			statement.setInt(6, venue.venueIDProperty().get());
			statement.addBatch();
		}
		
		int[] results = statement.executeBatch();
		statement.close();
	
		if (results.length >= 0) {
			connection.commit();
			System.out.println("Venue table data updated successfully");
			return true;
		} else {
			connection.rollback();
			System.out.println("Venue table not updated");
			return false;
		}
	}
	
	public static ObservableList<Venue> queryVenueTable(ArrayList<String> queries) throws SQLException{
		
		// Available, sufficient capacity, event type, venue category
		
		ObservableList<Venue> venueList = FXCollections.observableArrayList();
		StringBuilder queryStatement = new StringBuilder();
		String[] suitableFor;
		
		for (String query : queries) {
			if (query != null) {
				queryStatement.append(query);
			}
			if ((queries.indexOf(query)+1) < queries.size()) {
				queryStatement.append(",");
			}
		}
		System.out.println("Statement: " + queryStatement.toString());
		
		PreparedStatement statement = 
				connection.prepareStatement("SELECT * FROM venues WHERE " + queryStatement.toString());
		
		ResultSet results = statement.executeQuery();
		
		while (results.next()) {
			
			suitableFor = results.getString(4).split("; ");
			for(String s : suitableFor) {
				s.strip();
			}

			venueList.add(new Venue(results.getInt(1), results.getString(2), results.getInt(3), 
									suitableFor, results.getString(5), results.getInt(6)));
		}
		
		if (venueList.size() > 0) {
			return venueList;
		} else {
			System.out.println("Venue list empty");
			return null;
		}
	}
	
	private static boolean setUpEventTable() {
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS events;");
			statement.execute("CREATE TABLE IF NOT EXISTS events ("
								+ "eventID INTEGER not NULL,"
								+ "eventClient VARCHAR(100),"
								+ "title VARCHAR(100),"
								+ "artist VARCHAR(250),"
								+ "date DATE,"
								+ "time TIME,"
								+ "target INTEGER,"
								+ "duration INTEGER,"
								+ "type VARCHAR(100),"
								+ "category VARCHAR(100)"
								+ ");");
			statement.close();
			
			System.out.println("Event table success");
			return true;
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();			
		}
		
		return false;
	}
	
	public static boolean addDataToEventTable(ObservableList<Event> eventList) throws SQLException{
		
		PreparedStatement statement = connection.prepareStatement("DELETE FROM events");
		
		statement = connection.prepareStatement("INSERT or REPLACE INTO events ("
												+ "eventID,eventClient,title,"
												+ "artist,date,time,"
												+ "target,duration,type,category)"
												+ "VALUES (?,?,?,?,?,?,?,?,?,?)"
												+ ";");

		for (Event event : eventList) {
			
			statement.clearParameters();
			statement.setInt(1, event.eventIDProperty().get());
			statement.setString(2, event.eventClientProperty().get());
			statement.setString(3, event.titleProperty().get());
			statement.setString(4, event.artistProperty().get());
			statement.setDate(5, Date.valueOf(event.dateProperty()));
			statement.setTime(6, Time.valueOf(event.timeProperty()));
			statement.setInt(7, event.targetProperty().get());
			statement.setInt(8, event.durationProperty().get());
			statement.setString(9, event.typeProperty().get());
			statement.setString(10, event.categoryProperty().get());
			statement.addBatch();
			
		}
		
		int[] results = statement.executeBatch();
		statement.close();
		
		if (results.length >= 0) {
			connection.commit();
			System.out.println("Event table data added successfully");
			return true;
		} else {
			connection.rollback();
			return false;
		}
	}
	
	public static boolean updateEventTable(ObservableList<Event> eventList) throws SQLException{
		
		// Available, sufficient capacity, event type, venue category
		
		PreparedStatement statement = 
				connection.prepareStatement("UPDATE events SET ("
												+ "eventClient = ?,"
												+ "title = ?,"
												+ "artist = ?,"
												+ "date = ?,"
												+ "time = ?)"
												+ "target = ?"
												+ "duration = ?"
												+ "type = ?"
												+ "category = ?"
												+ "WHERE eventID = ?");
		
		for (Event event : eventList) {
			
			statement.clearParameters();
			statement.setString(1, event.eventClientProperty().get());
			statement.setString(2, event.titleProperty().get());
			statement.setString(3, event.artistProperty().get());
			statement.setDate(4, Date.valueOf(event.dateProperty()));
			statement.setTime(5, Time.valueOf(event.timeProperty()));
			statement.setInt(6, event.targetProperty().get());
			statement.setInt(7, event.durationProperty().get());
			statement.setString(8, event.typeProperty().get());
			statement.setString(9, event.categoryProperty().get());
			statement.setInt(10, event.eventIDProperty().get());
			statement.addBatch();
			
		}
		
		int[] results = statement.executeBatch();
		statement.close();
	
		if (results.length >= 0) {
			connection.commit();
			System.out.println("Event table data updated successfully");
			return true;
		} else {
			connection.rollback();
			System.out.println("Event table not updated");
			return false;
		}
	}
	
	private static boolean setUpOrderTable() {
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS orders;");
			statement.execute("CREATE TABLE IF NOT EXISTS orders ("
							+ "orderID INTEGER not NULL,"
							+ "eventID INTEGER not NULL,"
							+ "venueID INTEGER not NULL,"
							+ "bookedBy INTEGER not NULL,"
							+ "venueHire DOUBLE,"
							+ "commission DOUBLE"
							+ ");");
			statement.close();
			
			System.out.println("Order table success");
			return true;
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();			
		}
		
		return false;
	}
	
	public static boolean addDataToOrderTable(ObservableList<Order> orderList) throws SQLException{
		
		PreparedStatement statement = 
				connection.prepareStatement("INSERT or REPLACE INTO orders ("
												+ "orderID,eventID,venueID,"
												+ "bookedBy,venueHire,commission) "
												+ "VALUES (?,?,?,?,?,?)"
												+ ";");
		
		for (Order order : orderList) {
			
			statement.clearParameters();
			statement.setInt(1, order.orderIDProperty().get());
			statement.setInt(2, order.eventIDProperty().get());
			statement.setInt(3, order.venueIDProperty().get());
			statement.setInt(4, order.bookedByProperty().get());
			statement.setDouble(5, order.venueHireProperty().get());
			statement.setDouble(6, order.commissionProperty().get());
			statement.addBatch();
		}

		int[] results = statement.executeBatch();
		statement.close();
		
		if (results.length >= 0) {
			connection.commit();
			System.out.println("Order table data added successfully");
			return true;
		} else {
			connection.rollback();
			return false;
		}
	}
	
	public static boolean updateOrderTable(ObservableList<Order> orderList) throws SQLException{
		
		// Available, sufficient capacity, event type, venue category
		
		PreparedStatement statement = 
				connection.prepareStatement("UPDATE orders SET ("
												+ "eventID = ?,"
												+ "venueID = ?,"
												+ "bookedBy = ?,"
												+ "venueHire = ?,"
												+ "commission = ?)"
												+ "WHERE orderID = ?");
		
		for (Order order : orderList) {
			
			statement.clearParameters();
			statement.setInt(1, order.eventIDProperty().get());
			statement.setInt(2, order.venueIDProperty().get());
			statement.setInt(3, order.bookedByProperty().get());
			statement.setDouble(4, order.venueHireProperty().get());
			statement.setDouble(5, order.commissionProperty().get());
			statement.setInt(6, order.orderIDProperty().get());
			statement.addBatch();
		}
		
		int[] results = statement.executeBatch();
		statement.close();
	
		if (results.length >= 0) {
			connection.commit();
			System.out.println("Order table data updated successfully");
			return true;
		} else {
			connection.rollback();
			System.out.println("Order table not updated");
			return false;
		}
	}
	
	private static boolean setUpUserTable() {
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS users;");
			statement.execute("CREATE TABLE IF NOT EXISTS users ("
								+ "userID INTEGER not NULL,"
								+ "fName VARCHAR(100),"
								+ "lName VARCHAR(100),"
								+ "email VARCHAR(100),"
								+ "password VARCHAR(100)"
								+ ");");
			statement.close();
			
			System.out.println("User table success");
			return true;
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();			
		}
		
		return false;
	}
	
	public static boolean addDataToUserTable(ObservableList<User> userList) throws SQLException{
		
		PreparedStatement statement = 
				connection.prepareStatement("INSERT or REPLACE INTO users ("
												+ "userID,fName,lName,"
												+ "email,password) "
												+ "VALUES (?,?,?,?,?)"
												+ ";");
		
		for (User user : userList) {
			
			statement.clearParameters();
			statement.setInt(1, user.userIDProperty().get());
			statement.setString(2, user.fNameProperty().get());
			statement.setString(3, user.lNameProperty().get());
			statement.setString(4, user.emailProperty().get());
			statement.setString(5, user.passwordProperty().get());
			statement.addBatch();
			
		}

		int[] results = statement.executeBatch();
		statement.close();
		
		if (results.length >= 0) {
			connection.commit();
			System.out.println("User table data added successfully");
			return true;
		} else {
			connection.rollback();
			return false;
		}
	}
	
	public static boolean updateUserTable(ObservableList<User> userList) throws SQLException{
		
		// Available, sufficient capacity, event type, venue category
		
		PreparedStatement statement = 
				connection.prepareStatement("UPDATE users SET ("
												+ "fName = ?,"
												+ "lName = ?,"
												+ "email = ?,"
												+ "password = ?,"
												+ "WHERE userID = ?");
		
		for (User user : userList) {
			
			statement.clearBatch();
			statement.setString(1, user.fNameProperty().get());
			statement.setString(2, user.lNameProperty().get());
			statement.setString(3, user.emailProperty().get());
			statement.setString(4, user.passwordProperty().get());
			statement.setInt(5, user.userIDProperty().get());
			statement.addBatch();
		}
		
		int[] results = statement.executeBatch();
		statement.close();
	
		if (results.length >= 0) {
			connection.commit();
			System.out.println("Order table data updated successfully");
			return true;
		} else {
			connection.rollback();
			System.out.println("Order table not updated");
			return false;
		}
	}
	
}
