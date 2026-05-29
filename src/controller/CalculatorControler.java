package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import model.CalculatorModel;
import model.CalculatorModelInterface;
import view.CalculatorGUI;

public class CalculatorControler implements EventHandler<ActionEvent>, CalculatorControlerInterface {

	//Le controller connait le gui et un model
	private CalculatorGUI gui;
    private CalculatorModelInterface model = new CalculatorModel();
    
    /**
     * Constructeur du controller
     * @param CalculatorGUI gui
     */
    public CalculatorControler(CalculatorGUI gui) 
    {
        this.gui = gui;
    }
    
    /**
     * Ecouteur des boutons du GUI
     * @param ActionEvent event
     */
	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
    	if (source instanceof Button) {
    	    Button b = (Button) source;
    	    String text = b.getText(); //On récupère le nom du bouton cliqué

    	    //En fonction du bouton cliqué, on appelle la méthode correspondante du model
    	    //puis on actualise les labels du gui dont les valeurs ont changées, via les méthodes refresh
	    	switch (text) {
	    		case "push":
	    			model.push();
	                refreshPile();
	                refreshAccu();
	                break;
	    		case "+":
	    			model.add();
	    			refreshPile();
	                refreshAccu();
	                break;
	    		case "-":
	    			model.substract();
	    			refreshPile();
	                refreshAccu();
	                break;
	    		case "x":
	    			model.multiply();
	    			refreshPile();
	                refreshAccu();
	                break;
	    		case "/":
	    			model.divide();
	    			refreshPile();
	                refreshAccu();
	                break;
	    		case "swap":
	    			model.swap();
	    			refreshPile();
	                break;
	    		case "C":
	    			model.clear();
	    			refreshPile();
	                refreshAccu();
	                break;
	    		case "+/-":
	    			model.opposite();
	    			refreshPile();
	                refreshAccu();
	                break;
	    		case "drop":
	    			model.drop();
	    			refreshPile();
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
     * Ecouteur des boutons du clavier
     * Les touches sont +, -, *, /, p pout push, c pour clear, o pour opposite, d pour drop, s pour swap et backspace pour effacer
     * et les chiffres de 0 à 9 ainsi que le . pour la virgule
     * @param KeyEvent event
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
    			refreshPile();
                refreshAccu();
                break;
            case "-":
            	model.substract();
    			refreshPile();
                refreshAccu();
                break;
            case "*":
            	model.multiply();
    			refreshPile();
                refreshAccu();
                break;
            case "/":
            	model.divide();
    			refreshPile();
                refreshAccu();
                break;
            case "p": case "P":
            	model.push();
    			refreshPile();
                refreshAccu();
                break;
            case "c": case "C":
            	model.clear();
    			refreshPile();
                refreshAccu();
                break;
            case "o": case "O":
            	model.opposite();
    			refreshPile();
                refreshAccu();
                break;
            case "d": case "D":
            	model.drop();
    			refreshPile();
                refreshAccu();
                break;
            case "s": case "S":
            	model.swap();
    			refreshPile();
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
     * Méthode pour actualiser ll'affichage de l'accumulateur
     * On récupere la valeur de l'accumulateur dans le model et on set le label correspondant dans le gui
     */
	@Override
	public void refreshAccu() {
        gui.setAccuLabel(model.getAccu());
    }
	
	/**
     * Méthode pour actualiser l'affichage de la pile
     * On récupere la pile dans le model et on envoie les valeurs à la méthode set du gui
     */
	@Override
	public void refreshPile() {
        gui.setPileLabels(model.getPile());
    }
}
