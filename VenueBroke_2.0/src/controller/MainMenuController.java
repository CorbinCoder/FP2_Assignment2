package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import doa.DatabaseConnector;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.CheckBox;
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
	private TableView<String> bookingsTable;
	@FXML
	private TableColumn<String, LocalDate> dateColumn = new TableColumn<>("Date");
	@FXML
	private TableColumn<String, LocalTime> timeColumn = new TableColumn<>("Time");
	
	@FXML
	private Button showEventsButton;
	@FXML
	private Button filterButton;
	@FXML
	private Button automatchButton;
	
	@FXML
	private CheckBox availableCheckBox;
	@FXML
	private CheckBox capacityCheckBox;
	@FXML
	private CheckBox typeCheckBox;
	@FXML
	private CheckBox categoryCheckBox;
	
	@FXML
	private MenuItem loadMenuItem;
	@FXML
	private MenuItem saveMenuItem;
	@FXML
	private MenuItem importMenuItem;
	@FXML
	private MenuItem quitMenuItem;
	@FXML
	private MenuItem adminViewMenuItem;
	
	private boolean clicked = false;
	
	public MainMenuController() {
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initilializing");	
		setUpEventTable();
		setUpVenueTable();
		filterHandler();
		automatchHandler();
		importHandler();
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
	
	private void setUpBookingsTable() {
		
		bookingsTable.setEditable(true);
		
	}
	
	@FXML
	public void showEventsHandler() {
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
	public void filterHandler() {
		filterButton.setOnAction(e -> {
			
			Event event = eventTable.getSelectionModel().getSelectedItem();
			
			if (event != null) {
				
				System.out.println("Selected event - " + event.toString());
				
				ArrayList<String> queries = new ArrayList<>();
				
				if (availableCheckBox.isSelected()) {
					StringBuilder availableVenues = Model.getAvailableVenues(event);
					if (availableVenues.length() > 0) {
						queries.add("venueID !=" + availableVenues);
					}
				}
				if (capacityCheckBox.isSelected()) {
					queries.add("capacity <= " + event.targetProperty().get());
				}
				if (typeCheckBox.isSelected()) {
					queries.add("suitableFor LIKE '%" + event.typeProperty().get() + "%'");
				}
				if (categoryCheckBox.isSelected()) {
					queries.add("category LIKE '%" + event.categoryProperty().get() + "%'");
				}
				
				if (queries.size() > 0) {
					
					try {
						Model.setVenues(DatabaseConnector.queryVenueTable(queries));
						venueTable.setItems(Model.getVenues());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}			
		});
	}
	
	@FXML
	public void automatchHandler() {
		automatchButton.setOnAction(e -> {
			
		});
	}
	
	@FXML
	public void importHandler() {
		importMenuItem.setOnAction(e -> {
			Model.importFromCSV();
			refreshTables();
		});
	}
	
	private void refreshTables() {
		venueTable.setItems(Model.getVenues());
		eventTable.setItems(Model.getEvents());
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