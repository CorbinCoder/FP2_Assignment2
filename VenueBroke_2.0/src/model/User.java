package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class User {
	
	private SimpleIntegerProperty userID;
	private SimpleStringProperty fName;
	private SimpleStringProperty lName;
	private SimpleStringProperty email;
	private SimpleStringProperty pWord;
	private static int userIDGenerator = 0;
	
	public User (String fName, String lName, String email, String pWord) {
		
//		if (verifyEmail(email) && verifyPassword(pWord)) {
			
			this.userID = new SimpleIntegerProperty(++userIDGenerator);
			this.fName = new SimpleStringProperty(fName);
			this.lName = new SimpleStringProperty(lName);
			this.email = new SimpleStringProperty (email);
			this.pWord = new SimpleStringProperty (pWord);
			
			System.out.println(userID.get()+fName+lName+email+pWord);
			
//		} else {
//			
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setHeaderText("Alert!");
//			alert.setContentText("Email or password are invalid. "
//									+ "Please try again with different inputs.");
//			alert.show();
//		}
		
	}
	
	public static User getUserMatch(String email, String pWord) {
		
		
	for (User user : Model.getUsers()) {
		
		if (user.emailProperty().get().equals(email)) {
			if (user.passwordProperty().get().equals(pWord)) {
				return user;
			}
		}
	}
		return null;
	}
	
	private static boolean verifyPassword(String pWord) {
		
		String regex = "^(?=.*[0-9])"
						+ "(?=.*[a-z])(?=.*[A-Z])"
						+ "(?=.*[@#$%^&+=])"
						+ "(?=\\S+$).{8,20}$";
		
		return compareExpressions(regex, pWord);
		
	}
	
	private static boolean verifyEmail(String email) {
		
		String regex = "^(.+)@(\\S+)$";
		
		return compareExpressions(regex, email);
	}
	
	private static boolean compareExpressions(String regex, String entry) {
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(entry);
		
		return matcher.matches();
		
	}
	
	// Get & Set
	public SimpleIntegerProperty userIDProperty() {
		return this.userID;
	}
	
	public SimpleStringProperty fNameProperty() {
		return this.fName;
	}
	
	public SimpleStringProperty lNameProperty() {
		return this.lName;
	}
	
	public SimpleStringProperty emailProperty() {
		return this.email;
	}
	
	public SimpleStringProperty passwordProperty() {
		return this.pWord;
	}

}
