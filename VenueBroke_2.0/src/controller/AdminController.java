package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class AdminController implements Initializable {
	
	public AdminController() {		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Initilializing");
		setUpUserTable();
		exitButtonHandler();
	}
	
	
	@FXML
	private VBox root;
	
	@FXML
    private TableView<User> userTable;

	@FXML
    private TableColumn<User, SimpleIntegerProperty> userIDColumn;
	@FXML
    private TableColumn<User, SimpleStringProperty> fNameColumn;
	@FXML
    private TableColumn<User, SimpleStringProperty> lNameColumn;
    @FXML
    private TableColumn<User, SimpleStringProperty> emailColumn;

    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField pWordField;

    @FXML
    private Button exitButton;
    @FXML
    private Button submitButton;
    
    public void setUpUserTable() {
    	
    	userTable.setEditable(true);
    	
    	userIDColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleIntegerProperty>("userID"));
    	fNameColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleStringProperty>("fName"));
    	lNameColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleStringProperty>("lName"));
    	emailColumn.setCellValueFactory(new PropertyValueFactory<User, SimpleStringProperty>("email"));
    	
    	userTable.setItems(Model.getUsers());
    }
    
    public void showStage(Stage primaryStage) throws Exception {
		this.root = FXMLLoader.load(getClass().getResource("/view/AdminView.fxml"));
		Scene scene = new Scene(this.root,700,500);
		scene.getStylesheets().add(getClass().getResource(Main.stylesheetPath).toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

    @FXML
    void exitButtonHandler() {
    	exitButton.setOnAction(e -> {
    		try {
    			Stage primaryStage = (Stage) exitButton.getScene().getWindow();
				LoginController loginController = new LoginController();
				loginController.showStage(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    	});
    }

    @FXML
    void submitButtonHandler(ActionEvent event) {

    }

}
