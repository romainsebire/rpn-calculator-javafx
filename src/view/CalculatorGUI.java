package view;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import controller.CalculatorControler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CalculatorGUI implements CalculatorGUIInterface {
	
	private CalculatorControler controller;
	
	// Labels for the accumulator display and the 4 most recent stack elements
	private Label accuLabel = new Label();
	private Label stack4thLastElement = new Label();
	private Label stack3rdLastElement = new Label();
	private Label stack2ndLastElement = new Label();
	private Label stackLastElementLabel = new Label();
	
	// List containing all calculator buttons
	private List<Button> buttonsList = new ArrayList<>();
	
	/**
	 * Constructor for the calculator GUI
	 */
	public CalculatorGUI(Stage primaryStage) {
		controller = new CalculatorControler(this);
		
		// Set window title
		primaryStage.setTitle("RPN Calculator — Noam & Romain");
		
		// Create grid layout for buttons and labels
		// with 10px padding and gaps, black background
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setStyle("-fx-background-color: black;");
		
		// Create and configure the accumulator label
        accuLabel.setFont(new Font(25));
        accuLabel.setTextFill(Color.GRAY);
        accuLabel.setMaxWidth(Double.MAX_VALUE);
        accuLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(accuLabel, 1, 4, 3, 1); // Positioned on row 5, spanning 3 columns

        // Create and configure labels for the last 4 stack elements
        // The most recently pushed element is displayed at the bottom
        stackLastElementLabel.setFont(new Font(30));
        stackLastElementLabel.setTextFill(Color.WHITE);
        stackLastElementLabel.setMaxWidth(Double.MAX_VALUE);
        stackLastElementLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(stackLastElementLabel, 1, 3, 3, 1);
        
        stack2ndLastElement.setFont(new Font(25));
        stack2ndLastElement.setTextFill(Color.WHITE);
        stack2ndLastElement.setMaxWidth(Double.MAX_VALUE);
        stack2ndLastElement.setAlignment(Pos.CENTER_RIGHT);
        grid.add(stack2ndLastElement, 1, 2, 3, 1);
        
        stack3rdLastElement.setFont(new Font(25));
        stack3rdLastElement.setTextFill(Color.WHITE);
        stack3rdLastElement.setMaxWidth(Double.MAX_VALUE);
        stack3rdLastElement.setAlignment(Pos.CENTER_RIGHT);
        grid.add(stack3rdLastElement, 1, 1, 3, 1);
        
        stack4thLastElement.setFont(new Font(25));
        stack4thLastElement.setTextFill(Color.WHITE);
        stack4thLastElement.setMaxWidth(Double.MAX_VALUE);
        stack4thLastElement.setAlignment(Pos.CENTER_RIGHT);
        grid.add(stack4thLastElement, 1, 0, 3, 1);
        
        // Button labels
        String[] labels = {"C", "+/-", "swap",   "/",
                		   "7",   "8",    "9",   "x",
                		   "4",   "5",    "6",   "-",
                		   "1",   "2",    "3",   "+",
                		   "0",           ".", "push",
                		   "⌫", "drop"};
        
        // Create buttons with size, font, and attach controller as listener
        for (String label : labels) {
            Button button = new Button(label);
            button.setFont(new Font(20));
            button.setPrefSize(80, 80);
            buttonsList.add(button);
            button.setOnAction(e -> controller.handle(e));
        }
		
        // Add buttons to grid
        // First row
        grid.add(buttonsList.get(0), 0, 5);
        grid.add(buttonsList.get(1), 1, 5);
        grid.add(buttonsList.get(2), 2, 5);
        grid.add(buttonsList.get(3), 3, 5);
        
        // Second row
        grid.add(buttonsList.get(4), 0, 6);
        grid.add(buttonsList.get(5), 1, 6);
        grid.add(buttonsList.get(6), 2, 6);
        grid.add(buttonsList.get(7), 3, 6);
        
        // Third row
        grid.add(buttonsList.get(8), 0, 7);
        grid.add(buttonsList.get(9), 1, 7);
        grid.add(buttonsList.get(10), 2, 7);
        grid.add(buttonsList.get(11), 3, 7);
        
        // Fourth row
        grid.add(buttonsList.get(12), 0, 8);
        grid.add(buttonsList.get(13), 1, 8);
        grid.add(buttonsList.get(14), 2, 8);
        grid.add(buttonsList.get(15), 3, 8);
        
        // Fifth row
        grid.add(buttonsList.get(16), 0, 9, 2, 1); // Button 0 spans 2 columns
        grid.add(buttonsList.get(17), 2, 9);
        grid.add(buttonsList.get(18), 3, 9);
        
        // Special buttons row
        grid.add(buttonsList.get(19), 0, 4);
        grid.add(buttonsList.get(20), 0, 3);

        // Group-specific button styling (colors)
        buttonsList.forEach(button -> {
            String label = button.getText();
            if ("C +/- swap / x - + push".contains(label)) {
                button.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
            } else if (label.equals("0")) {
                button.setPrefWidth(170);
                button.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black;");
            } else if (label.equals("⌫")) {
            	button.setVisible(false);
            	button.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black;");
		        accuLabel.textProperty().addListener((observable, oldValue, newValue) -> {
		            button.setVisible(!newValue.isEmpty());     
		        });
            } else if (label.equals("drop")) {
            	button.setVisible(false);
            	button.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black;");
            	stackLastElementLabel.textProperty().addListener((observable, oldValue, newValue) -> {
		            button.setVisible(!newValue.isEmpty());
		        });
            } else {
                button.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black;");
            }
        });
		
        // Create scene and attach controller as keyboard listener
        Scene scene = new Scene(grid, 350, 550);
        scene.setOnKeyPressed(controller::handleKeyPress);
        
        // Set scene on stage and show
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Sets the accumulator label text
	 * @param string the text to display
	 */
	@Override
	public void setAccuLabel(String string) {
		this.accuLabel.setText(string);
	}

	/**
	 * Updates the 4 stack display labels from the current stack state
	 * @param stackData the stack to display
	 */
	@Override
	public void setStackLabels(List<Double> stackData) {
		int size = stackData.size();
		this.stackLastElementLabel.setText("");	
		this.stack2ndLastElement.setText("");
		this.stack3rdLastElement.setText("");
		this.stack4thLastElement.setText("");
		if(size > 3) {
			this.stack4thLastElement.setText(String.valueOf(stackData.get(size-4)));	
		} 
		if(size > 2) {
			this.stack3rdLastElement.setText(String.valueOf(stackData.get(size-3)));
		}
		if(size > 1) {
			this.stack2ndLastElement.setText(String.valueOf(stackData.get(size-2)));	
		}
		if(size > 0) {
			this.stackLastElementLabel.setText(String.valueOf(stackData.get(size-1)));
		}
	}
}
