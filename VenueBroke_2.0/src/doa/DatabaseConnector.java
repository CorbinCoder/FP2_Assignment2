package doa;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import model.*;

public class DatabaseConnector {
	
	Connection connection;
	StringBuilder elementBuilder;
	
	public DatabaseConnector() {
		
		try {
			
			this.connection = DriverManager.getConnection("jdbc:sqlite:C:src/data/VenueBroke.db");
			this.connection.setAutoCommit(false);
						
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
	
	public void initializeDB() {
		
		// Test DB
		try {
			Statement statement = this.connection.createStatement();
			PreparedStatement pStatement = this.connection.prepareStatement("INSERT or IGNORE INTO test (testID) VALUES (?)");
			statement.execute("CREATE TABLE IF NOT EXISTS test (testID INTEGER not NULL);");
			
			pStatement.setInt(1, 1);
			pStatement.addBatch();
			
			pStatement.setInt(1, 1);
			pStatement.addBatch();
			
			pStatement.executeBatch();
			
			ResultSet results = statement.executeQuery("SELECT * FROM test WHERE testID = 1");
			while (results.next()) {
				int testID = results.getInt("testID");
				System.out.println("Test ID: " + testID);
			}
			statement.close();
									
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Test DB
		
		if (setUpVenueTable() && setUpEventTable() && setUpOrderTable() && setUpUserTable()) {
			
			System.out.println("Database initialized successfully.");
			
		} else {
			
			System.out.println("Error - Unable to intialize database.");
			
		}
		
	}
	
	public void addData(ObservableList<Venue> venueList, ObservableList<Event> eventList,
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
	
	private boolean setUpVenueTable() {
		
		try {
			
			Statement statement = this.connection.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS venues ("
								+ "venueID INTEGER not NULL,"
								+ "venueClient VARCHAR(100),"
								+ "venueCapacity INTEGER,"
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
	
	// XXXXXXXXXXXXXXXXXXXXXXX
	private boolean addDataToVenueTable(ObservableList<Venue> venueList) throws SQLException{
		
		PreparedStatement statement = 
				this.connection.prepareStatement("INSERT or IGNORE INTO venues ("
												+ "venueID,venueClient,venueCapacity,"
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
	
		if (results.length > 0) {
			connection.commit();
			System.out.println("Venue table data added successfully");
			return true;
		} else {
			connection.rollback();
			System.out.println("Venue table not added");
			return false;
		}
	}
	
	private boolean setUpEventTable() {
		
		try {
			
			Statement statement = this.connection.createStatement();
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
	
	private boolean addDataToEventTable(ObservableList<Event> eventList) throws SQLException{
		
		PreparedStatement statement = 
				this.connection.prepareStatement("INSERT or IGNORE INTO events ("
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
		
		if (results.length > 0) {
			connection.commit();
			System.out.println("Event table data added successfully");
			return true;
		} else {
			connection.rollback();
			return false;
		}
	}
	
	private boolean setUpOrderTable() {
		
		try {
			
			Statement statement = this.connection.createStatement();
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
	
	private boolean addDataToOrderTable(ObservableList<Order> orderList) throws SQLException{
		
		PreparedStatement statement = 
				this.connection.prepareStatement("INSERT or IGNORE INTO orders ("
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
		
		if (results.length > 0) {
			connection.commit();
			System.out.println("Order table data added successfully");
			return true;
		} else {
			connection.rollback();
			return false;
		}
	}
	
	private boolean setUpUserTable() {
		
		try {
			
			Statement statement = this.connection.createStatement();
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
	
	private boolean addDataToUserTable(ObservableList<User> userList) throws SQLException{
		
		PreparedStatement statement = 
				this.connection.prepareStatement("INSERT or IGNORE INTO users ("
												+ "userID,fName,lName"
												+ "email,password"
												+ "VALUES (?,?,?,?,?)"
												+ ";");
		
		for (User user : userList) {
			
			statement.clearBatch();
			statement.setInt(1, user.userIDProperty().get());
			statement.setString(2, user.fNameProperty().get());
			statement.setString(3, user.lNameProperty().get());
			statement.setString(4, user.emailProperty().get());
			statement.setString(5, user.passwordProperty().get());
			statement.addBatch();
		}
		
		int[] results = statement.executeBatch();
		statement.close();
		
		if (results.length > 0) {
			connection.commit();
			System.out.println("User table data added successfully");
			return true;
		} else {
			connection.rollback();
			return false;
		}
	}
	
}
