module FPOUAWeek8 {
	requires javafx.controls;
	
	opens fpoua.week8.solution to javafx.graphics, javafx.fxml, javafx.base;
	opens fpoua.week8.assessment to javafx.graphics, javafx.fxml, javafx.base;
}
