package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class LoginController implements Initializable {

	@FXML
    private VBox root;
	
    @FXML
    private VBox textArea;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    
    @FXML
    private HBox buttonArea;
    @FXML
    private Button signUpButton;
    @FXML
    private Button submitButton;
    
    public LoginController() throws IOException {
    }
    
    @Override
	public void initialize(URL url, ResourceBundle arg1) {
    	
    	System.out.println("Printing");
    	submitButtonHandler();
    	signUpButtonHandler();
    	
    }
    
    public void showStage(Stage primaryStage) throws Exception {
		
    	this.root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
    	Scene scene = new Scene(this.root,500,300);
		scene.getStylesheets().add(getClass().getResource(Main.stylesheetPath).toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    @FXML
    private void submitButtonHandler() {
    	submitButton.setOnAction(e -> {
    		String slctEmail = usernameTextField.getText();
    		String slctPWord = passwordTextField.getText();
    		User tempUser = User.getUserMatch(slctEmail, slctPWord);
    		Alert alert = new Alert(AlertType.INFORMATION);
    		
    		if (tempUser != null) {
    			Model.setCurrUser(tempUser);
    			alert.setHeaderText("Confirmation...");
        		alert.setContentText("Signed in successfully!");
        		alert.showAndWait();
        		try {
        			Stage stage = (Stage) submitButton.getScene().getWindow();
        			MainMenuController mmController = new MainMenuController();
    				mmController.showStage(stage);
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    		} else {
    			alert.setHeaderText("Error!");
    			alert.setContentText("No match for that combination");
    			alert.showAndWait();
    		}
    	});
    }
    
    @FXML
    private void signUpButtonHandler() {
    	signUpButton.setOnAction(e -> {
    		try {
    			Stage stage = (Stage) submitButton.getScene().getWindow();
        		AdminController adController = new AdminController();
				adController.showStage(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    	});
    }
}
