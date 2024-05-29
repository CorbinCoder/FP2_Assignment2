package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application {
	
	private Model model;
	public static final String mmLoaderPath = "/view/MainMenuView.fxml";
	public static final String loginLoaderPath = "/view/LoginView.fxml";
	public static final String stylesheetPath = "/view/application.css";
	
	
	@Override
	public void init() {
		model = new Model();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginController loginController = new LoginController();
		loginController.showStage(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
