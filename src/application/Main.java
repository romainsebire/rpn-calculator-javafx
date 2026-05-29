package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.CalculatorGUI;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		new CalculatorGUI(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
