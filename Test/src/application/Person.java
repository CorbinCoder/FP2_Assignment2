package application;

import javafx.beans.property.SimpleStringProperty;

public class Person {
	
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty email;
	
	Person(String fName, String lName, String email) {
		
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(lName);
		this.email = new SimpleStringProperty(email);
		
	}
	
	
	
	public String getFirstName() {
		return this.firstName.get();
	}
	
	public void setFirstName(String fName) {
		this.firstName.set(fName);		
	}
	
	public String getLastName() {
		return this.lastName.get();
	}
	
	public void setLastName(String lName) {
		this.lastName.set(lName);
	}
	
	public String getEmail() {
		return this.email.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
	
}
