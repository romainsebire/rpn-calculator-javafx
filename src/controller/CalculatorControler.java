package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import model.CalculatorModel;
import model.CalculatorModelInterface;
import view.CalculatorGUI;

public class CalculatorControler implements EventHandler<ActionEvent>, CalculatorControlerInterface {

	// The controller knows the GUI and a model
	private CalculatorGUI gui;
    private CalculatorModelInterface model = new CalculatorModel();
    
    /**
     * Controller constructor
     * @param gui the calculator GUI
     */
    public CalculatorControler(CalculatorGUI gui) 
    {
        this.gui = gui;
    }
    
    /**
     * Button click event listener
     * @param event the action event from a button click
     */
	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
    	if (source instanceof Button) {
    	    Button b = (Button) source;
    	    String text = b.getText(); // Get the clicked button's label

    	    // Based on the clicked button, call the corresponding model method
    	    // then refresh the GUI labels whose values have changed
	    	switch (text) {
	    		case "push":
	    			model.push();
	                refreshStack();
	                refreshAccu();
	                break;
	    		case "+":
	    			model.add();
	    			refreshStack();
	                refreshAccu();
	                break;
	    		case "-":
	    			model.substract();
	    			refreshStack();
	                refreshAccu();
	                break;
	    		case "x":
	    			model.multiply();
	    			refreshStack();
	                refreshAccu();
	                break;
	    		case "/":
	    			model.divide();
	    			refreshStack();
	                refreshAccu();
	                break;
	    		case "swap":
	    			model.swap();
	    			refreshStack();
	                break;
	    		case "C":
	    			model.clear();
	    			refreshStack();
	                refreshAccu();
	                break;
	    		case "+/-":
	    			model.opposite();
	    			refreshStack();
	                refreshAccu();
	                break;
	    		case "drop":
	    			model.drop();
	    			refreshStack();
	                refreshAccu();
	                break;
	    		case "⌫":
	    			model.undoAccu();
	                refreshAccu();
	                break;
	            default:
	            	model.writeAccu(text);
	                refreshAccu();
	                break;
    		}
    	}
	}
	
	/**
	 * Keyboard key press event listener
	 * Keys: +, -, *, /, p for push, c for clear, o for opposite, d for drop, s for swap,
	 * backspace to delete, and digits 0-9 plus . for decimal
	 * @param event the key event
	 */
	public void handleKeyPress(KeyEvent event) {
        switch (event.getText()) {
            case "0":
            	model.writeAccu("0");
                refreshAccu();
                break;
            case "1": 
            	model.writeAccu("1");
                refreshAccu();
                break;
            case "2": 
            	model.writeAccu("2");
                refreshAccu();
                break;
            case "3":
            	model.writeAccu("3");
                refreshAccu();
                break;
            case "4": 
            	model.writeAccu("4");
                refreshAccu();
                break;
            case "5": 
            	model.writeAccu("5");
                refreshAccu();
                break;
            case "6": 
            	model.writeAccu("6");
                refreshAccu();
                break;
            case "7":
            	model.writeAccu("7");
                refreshAccu();
                break;
            case "8": 
            	model.writeAccu("8");
                refreshAccu();
                break;
            case "9":
            	model.writeAccu("9");
                refreshAccu();
                break;
            case ".":
            	model.writeAccu(".");
                refreshAccu();
                break;
            case "+":
            	model.add();
    			refreshStack();
                refreshAccu();
                break;
            case "-":
            	model.substract();
    			refreshStack();
                refreshAccu();
                break;
            case "*":
            	model.multiply();
    			refreshStack();
                refreshAccu();
                break;
            case "/":
            	model.divide();
    			refreshStack();
                refreshAccu();
                break;
            case "p": case "P":
            	model.push();
    			refreshStack();
                refreshAccu();
                break;
            case "c": case "C":
            	model.clear();
    			refreshStack();
                refreshAccu();
                break;
            case "o": case "O":
            	model.opposite();
    			refreshStack();
                refreshAccu();
                break;
            case "d": case "D":
            	model.drop();
    			refreshStack();
                refreshAccu();
                break;
            case "s": case "S":
            	model.swap();
    			refreshStack();
                break;
            default:
                break;
        }
        switch (event.getCode()) {
	        case BACK_SPACE:
	        	model.undoAccu();
	            refreshAccu();
	            break;  
		default:
			break;
        }
    } 

	/**
	 * Refreshes the accumulator display
	 * Gets the accumulator value from the model and updates the GUI label
	 */
	@Override
	public void refreshAccu() {
        gui.setAccuLabel(model.getAccu());
    }
	
	/**
	 * Refreshes the stack display
	 * Gets the stack from the model and updates the GUI labels
	 */
	@Override
	public void refreshStack() {
        gui.setStackLabels(model.getStack());
    }
}
