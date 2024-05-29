package model;

import javafx.beans.property.*;

// Abstract class that allows easy implementation of
// new client types in later versions.
public abstract class Client {
	
	protected final SimpleIntegerProperty clientID;
	private static int clientIDGenerator = 0;

	// Constructs client with client name;
	public Client() {
		this.clientID = new SimpleIntegerProperty(++clientIDGenerator);
	}

	// Abstract methods to be implemented in child classes.
	public abstract SimpleIntegerProperty clientIDProperty();

}
