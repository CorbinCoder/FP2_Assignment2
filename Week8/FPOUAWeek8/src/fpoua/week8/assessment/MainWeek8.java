package fpoua.week8.assessment;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.____________;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/*
 * A program to run a JavaFX program to list members of a generic list on a TableView
 * 
 * ASSESSED WEEK 8 Exercise - AT4 Part 4 worth 1%
 * Your task it to fill in the blanks with the missing Java statements.
 */
public class MainWeek8 extends Application {
	@Override // Override the start in the Application
	public void start(Stage primaryStage) throws IOException {
		
		//Create and populate ArrayList of pandas
		ArrayList<Panda> pandas = new ArrayList<>();
		pandas.add(new Panda(1, "Puma", "black white blue", 7));
		pandas.add(new Panda(2, "Kioma", "black grey orange", 5));
		pandas.add(new Panda(3, "Simba", "white grey blue", 9));

		//Create and populate ArrayList of pandas
		ArrayList<Cat> cats = new ArrayList<>();
		cats.add(new Cat(1, "Hello Kitty", "white", 15));
		cats.add(new Cat(2, "Garfield", "orange", 20));
		cats.add(new Cat(3, "Grumpy cat", "brown and white", 7));
		
		//Instantiate a GenericAnimalList object to use its generic list
		GenericAnimalList animals = new GenericAnimalList();
		
		//populate the generic list with cats and pandas
		animals.populate(cats);
		animals.populate(pandas);
		
		//add a dog in animals
		animals.addAnAnimal(new Dog("Goofy", "yellow", 99));
		
		//instantiate tableView
		TableView tableView = new _____________;

		TableColumn<Animal, String> column1 = new TableColumn<>("Name");
		column1.setCellValueFactory(new PropertyValueFactory<>("name"));

		//instantiate column2
		TableColumn<Animal, String> column2 = __________________________;
		//associate column2 with colour using value factory
		__________________________________________________________________
		
		//instantiate column3
		TableColumn<Animal, Integer> column3 = _______________________;
		//associate column3 with age using value factory
		______________________________________________________________

		//add every column to tableView
		tableView.getColumns().add(_______);
		____________________________________
		____________________________________

		tableView.getItems().addAll(animals.getList());


		// Create a scene and place it in the stage
		VBox vbox = new VBox(tableView);
		Scene scene = new Scene(vbox);
		primaryStage.setTitle("My pets");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}