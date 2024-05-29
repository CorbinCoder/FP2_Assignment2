package application;

import java.time.*;
import java.util.Arrays;

import customObjects.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.FileHandler;


public class Controller {
	
	@FXML
	private Scene scene;
	
	@FXML
	private TableView<Event> requestTable;
	
	@FXML
	private Button requestButton = new Button();
	
	@FXML
	public TableColumn<Event, SimpleIntegerProperty> requestNoColumn = new TableColumn<>("RequestNo");
	@FXML
	public TableColumn<Event, SimpleStringProperty> eventTitleColumn = new TableColumn<>("Title");
	@FXML
	public TableColumn<Event, SimpleStringProperty> artistColumn = new TableColumn<>("Artist");
	@FXML
	public TableColumn<Event, SimpleStringProperty> clientColumn = new TableColumn<>("Venue");
	
	public ObservableList<Event> eventList = FXCollections.observableArrayList(
			new Event("Client", "Title", "Artist", LocalDate.parse("10/10/2020", FileHandler.dateFormatter),
					LocalTime.parse("08:00pm", FileHandler.timeFormatter), 1000, 2, "outdoor", "gig"),
			new Event("Client", "Title", "Artist", LocalDate.parse("10/10/2020", FileHandler.dateFormatter),
					LocalTime.parse("08:00pm", FileHandler.timeFormatter), 1000, 2, "outdoor", "gig"),
			new Event("Client", "Title", "Artist", LocalDate.parse("10/10/2020", FileHandler.dateFormatter),
					LocalTime.parse("08:00pm", FileHandler.timeFormatter), 1000, 2, "outdoor", "gig")
	);	
	
	public Controller() {
		
		for (Event event : eventList) {
			event.printDetails();
		}
		
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	@FXML
	@SuppressWarnings("unchecked")
	public void addDataToTable() {
		
		System.out.println("addData");
		requestNoColumn.setCellValueFactory(new PropertyValueFactory<>("clientID"));
		eventTitleColumn.setCellValueFactory( new PropertyValueFactory<>("title"));
		artistColumn.setCellValueFactory( new PropertyValueFactory<>("artist"));
		clientColumn.setCellValueFactory( new PropertyValueFactory<>("client"));
		
		
		requestTable.setEditable(true);
		requestTable.getColumns().addAll( Arrays.asList(requestNoColumn, eventTitleColumn, 
															artistColumn, clientColumn));
		requestTable.getItems().addAll(eventList);
		var tempID = requestTable.getColumns().getFirst().getCellData(0);
		System.out.println("!!!!" + tempID.toString() + "!!!!");
		
	}
	
	@FXML
	public void setColumnValuesMouseClickHandler() {
		requestButton.setOnMouseClicked(e -> {
			requestTable.getColumns().getFirst().setText("Hello!");
//			SimpleIntegerProperty tempInt = (SimpleIntegerProperty) requestTable.getColumns().getFirst().getCellData(0);
//			System.out.println(tempInt.get());
			requestTable.refresh();
		});
	}
}