package customObjects;

import javafx.beans.property.*;

// Abstract class that allows easy implementation of
// new client types in later versions.
public abstract class Client {
	
	protected SimpleStringProperty client;
	protected final SimpleIntegerProperty clientID;
	protected static int clientIDGenerator = 0;

	// Constructs client with client name;
	public Client(String client) {
		this.client = new SimpleStringProperty(client);
		this.clientID = new SimpleIntegerProperty(++clientIDGenerator);
	}

	// Abstract methods to be implemented in child classes.
	public abstract SimpleStringProperty clientProperty();
	public abstract void setClient(String client);
	public abstract SimpleIntegerProperty clientIDProperty();

}
