package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.*;

public class MainMenuController implements Initializable {
	
	@FXML
	private VBox root;
	
	// Set up Event table
	@FXML
	private TableView<Event> eventTable;
	@FXML
	private TableColumn<Event, SimpleIntegerProperty> eventIDColumn = new TableColumn<>("Request No");
	@FXML
	private TableColumn<Event, SimpleStringProperty> titleColumn = new TableColumn<>("Title");
	@FXML
	private TableColumn<Event, SimpleStringProperty> artistColumn = new TableColumn<>("Artist");
	@FXML
	private TableColumn<Event, SimpleStringProperty> eventClientColumn = new TableColumn<>("Client");
	
	// Set up Venue table
	@FXML
	private TableView<Venue> venueTable;
	@FXML
	private TableColumn<Venue, SimpleIntegerProperty> venueIDColumn = new TableColumn<>("Venue No");
	@FXML
	private TableColumn<Venue, SimpleStringProperty> venueClientColumn = new TableColumn<>("Venue Name");
	@FXML
	private TableColumn<Venue, SimpleIntegerProperty> compatibilityScore = new TableColumn<>("Compatibility Score");
	
	@FXML
	private Button showEventsButton;
	@FXML
	private MenuItem quitMenuItem;
	
	private boolean clicked = false;
	
	public MainMenuController() {
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initilializing");	
		setUpEventTable();
		setUpVenueTable();
		quitHandler();
	}
	
	public void showStage(Stage primaryStage) throws Exception {
		this.root = FXMLLoader.load(getClass().getResource("/view/MainMenuView.fxml"));
		Scene scene = new Scene(this.root,700,500);
		scene.getStylesheets().add(getClass().getResource(Main.stylesheetPath).toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void setUpEventTable() {
		
		eventTable.setEditable(true);
		
		eventIDColumn.setCellValueFactory(new PropertyValueFactory<Event, SimpleIntegerProperty>("eventID"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<Event, SimpleStringProperty>("title"));
		artistColumn.setCellValueFactory(new PropertyValueFactory<Event, SimpleStringProperty>("artist"));
		eventClientColumn.setCellValueFactory(new PropertyValueFactory<Event, SimpleStringProperty>("eventClient"));

		eventTable.setItems(Model.getEvents());
	}
	
	private void setUpVenueTable() {
		
		venueTable.setEditable(true);
		
		venueIDColumn.setCellValueFactory(new PropertyValueFactory<Venue, SimpleIntegerProperty>("venueID"));
		venueClientColumn.setCellValueFactory(new PropertyValueFactory<Venue, SimpleStringProperty>("venueClient"));
		compatibilityScore.setCellValueFactory(new PropertyValueFactory<Venue, SimpleIntegerProperty>("compatibilityScore"));
		
		venueTable.setItems(Model.getVenues());
	}	
	
	@FXML
	public void showEventsButtonHandler() {
		showEventsButton.setOnMouseClicked(e -> {
			if (!clicked) {
				Model.importFromCSV();
				showEventsButton.setText("Clicked!");
				clicked = true;
			} else {
				System.out.println("Already clicked!");
			}
		});
	}
	
	@FXML
	public void quitHandler() {
		quitMenuItem.setOnAction(e -> {
			
			Model.logoutUser();
			try {
				Stage primaryStage = (Stage) showEventsButton.getScene().getWindow();
				LoginController loginController = new LoginController();
				loginController.showStage(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}
}