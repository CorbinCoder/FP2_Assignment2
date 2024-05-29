package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;


public class Main extends Application {
	
	private TableView<Person> table = new TableView<Person>();
	private ObservableList<Person> data = FXCollections.observableArrayList(
			new Person("Jacob", "Smith", "jacob.smith@example.com"),
			new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
			new Person("Ethan", "Williams", "ethan.williams@example.com"),
			new Person("Emma", "Jones", "emma.jones@example.com"),
			new Person("Michael", "Brown", "michael.brown@example.com"));
	
	HBox hb = new HBox();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		
		try {
			Scene scene = new Scene(new Group());
			stage.setTitle("Table View Example");
			stage.setWidth(450);
			stage.setHeight(550);
			
			final Label label = new Label("Address Book");
			label.setFont(new Font("Arial", 20));
			
			table.setEditable(true);
			
			TableColumn<Person, String> fNameColumn = new TableColumn<Person, String>("First Name");
			fNameColumn.setMinWidth(100);
			fNameColumn.setCellValueFactory(
					new PropertyValueFactory<Person, String>("firstName"));
			
			TableColumn<Person, String> lNameColumn = new TableColumn<Person, String>("Last Name");
			lNameColumn.setMinWidth(100);
			lNameColumn.setCellValueFactory(
					new PropertyValueFactory<Person, String>("lastName"));
			
			TableColumn<Person, String> emailColumn = new TableColumn<Person, String>("Email");
			emailColumn.setMinWidth(200);
			emailColumn.setCellValueFactory(
					new PropertyValueFactory<Person, String>("email"));
			
			table.setItems(data);
			table.getColumns().addAll(fNameColumn, lNameColumn, emailColumn);
			
			TextField addFirstName = new TextField();
			addFirstName.setPromptText("First Name");
			addFirstName.setMaxWidth(fNameColumn.getPrefWidth());
			
			TextField addLastName = new TextField();
			addLastName.setPromptText("Last Name");
			addLastName.setMaxWidth(lNameColumn.getPrefWidth());
			
			TextField addEmail = new TextField();
			addEmail.setPromptText("Email");
			addEmail.setMaxWidth(emailColumn.getPrefWidth());
			
			Button addButton = new Button("Add");
			addButton.setOnAction(e -> {
				data.add(new Person(addFirstName.getText(), 
									addLastName.getText(), 
									addEmail.getText()));
				addFirstName.clear();
				addLastName.clear();
				addEmail.clear();
			});
			
			hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
			hb.setSpacing(3);
			
			VBox vbox = new VBox();
			vbox.setSpacing(5);
			vbox.setPadding(new Insets(10, 0, 0, 10));
			vbox.getChildren().addAll(label, table, hb);
			
			((Group) scene.getRoot()).getChildren().addAll(vbox);
			
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
