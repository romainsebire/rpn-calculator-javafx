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
	
	//Création des labels pour l'affichage de l'accumulateur et des 4 derniers nombre en mémoire
	private Label accuLabel = new Label();
	private Label pile4thLastElement = new Label();
	private Label pile3rdLastElement = new Label();
	private Label pile2ndLastElement = new Label();
	private Label pileLastElementLabel = new Label();
	
	//Création d'une liste qui contiendra tous les boutons de la calculatrice
	private List<Button> buttonsList = new ArrayList<>();
	
	/**
     * Constructeur de l'interface graphique de la calculatrice
     */
	public CalculatorGUI(Stage primaryStage) {
		controller = new CalculatorControler(this);
		
		//Définition du titre
		primaryStage.setTitle("Calculatrice RPN Noam & Romain");
		
		//Création de la grile pour organiser la disposition des boutons et des labels
		//avec un contour et des espaces entre les cases de 10 pixels, et un fond noir
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setStyle("-fx-background-color: black;");
		
		//Création et parametrage du label pour afficher l'accumulateur
        accuLabel.setFont(new Font(25));
        accuLabel.setTextFill(Color.GRAY);
        accuLabel.setMaxWidth(Double.MAX_VALUE);
        accuLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(accuLabel, 1, 4, 3, 1); //Positionnement sur la 5ème ligne, sur les 3 colonnes de largeur

        //Création et parametrage des labels pour afficher les 4 derniers élements de la pile
        //On place le dernier élement empilé au plus bas et on remonte
        pileLastElementLabel.setFont(new Font(30));
        pileLastElementLabel.setTextFill(Color.WHITE);
        pileLastElementLabel.setMaxWidth(Double.MAX_VALUE);
        pileLastElementLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(pileLastElementLabel, 1, 3, 3, 1);
        
        pile2ndLastElement.setFont(new Font(25));
        pile2ndLastElement.setTextFill(Color.WHITE);
        pile2ndLastElement.setMaxWidth(Double.MAX_VALUE);
        pile2ndLastElement.setAlignment(Pos.CENTER_RIGHT);
        grid.add(pile2ndLastElement, 1, 2, 3, 1);
        
        pile3rdLastElement.setFont(new Font(25));
        pile3rdLastElement.setTextFill(Color.WHITE);
        pile3rdLastElement.setMaxWidth(Double.MAX_VALUE);
        pile3rdLastElement.setAlignment(Pos.CENTER_RIGHT);
        grid.add(pile3rdLastElement, 1, 1, 3, 1);
        
        pile4thLastElement.setFont(new Font(25));
        pile4thLastElement.setTextFill(Color.WHITE);
        pile4thLastElement.setMaxWidth(Double.MAX_VALUE);
        pile4thLastElement.setAlignment(Pos.CENTER_RIGHT);
        grid.add(pile4thLastElement, 1, 0, 3, 1);
        
        //Création d'une liste avec les noms des boutons
        String[] labels = {"C", "+/-", "swap",   "/",
                		   "7",   "8",    "9",   "x",
                		   "4",   "5",    "6",   "-",
                		   "1",   "2",    "3",   "+",
                		   "0",           ".", "push",
                		   "⌫", "drop"};
        
        //Création des boutons et parametrage de leur taille et de la police d'écriture et ajout du controller en écouteur
        for (String label : labels) {
            Button button = new Button(label);
            button.setFont(new Font(20));
            button.setPrefSize(80, 80);
            buttonsList.add(button);
            button.setOnAction(e -> controller.handle(e));
        }
		
        //Ajout des boutons à la grille
        //Première ligne de bouton
        grid.add(buttonsList.get(0), 0, 5);
        grid.add(buttonsList.get(1), 1, 5);
        grid.add(buttonsList.get(2), 2, 5);
        grid.add(buttonsList.get(3), 3, 5);
        
        //Seconde ligne de bouton
        grid.add(buttonsList.get(4), 0, 6);
        grid.add(buttonsList.get(5), 1, 6);
        grid.add(buttonsList.get(6), 2, 6);
        grid.add(buttonsList.get(7), 3, 6);
        
        //Troisième ligne de bouton
        grid.add(buttonsList.get(8), 0, 7);
        grid.add(buttonsList.get(9), 1, 7);
        grid.add(buttonsList.get(10), 2, 7);
        grid.add(buttonsList.get(11), 3, 7);
        
        //Quatrième ligne de bouton
        grid.add(buttonsList.get(12), 0, 8);
        grid.add(buttonsList.get(13), 1, 8);
        grid.add(buttonsList.get(14), 2, 8);
        grid.add(buttonsList.get(15), 3, 8);
        
        //Cinquième ligne de bouton
        grid.add(buttonsList.get(16), 0, 9, 2, 1); //Le bouton 0 fait 2 case de largeur
        grid.add(buttonsList.get(17), 2, 9);
        grid.add(buttonsList.get(18), 3, 9);
        
        //Boutons spéciaux ligne de bouton
        grid.add(buttonsList.get(19), 0, 4);
        grid.add(buttonsList.get(20), 0, 3);

        //Paramétrages spécifiques aux groupes de boutons (couleurs)
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
            	pileLastElementLabel.textProperty().addListener((observable, oldValue, newValue) -> {
		            button.setVisible(!newValue.isEmpty());
		        });
            } else {
                button.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: black;");
            }
        });
		
        //Création de la scène et ajout du controleur en écouteur des boutons claviers pressés
        Scene scene = new Scene(grid, 350, 550);
        scene.setOnKeyPressed(controller::handleKeyPress);
        
        //Ajout de la scène au stage de JavaFX et lancement de l'affichage
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
     * Méthode pour modifier la chaine de caractère du label de l'accumulateur
     * @param String string, chaine de caractère que l'on souhaite afficher
     */
	@Override
	public void setAccuLabel(String string) {
		this.accuLabel.setText(string);
	}

	/**
     * Méthode pour prélever et modifier les valeurs des 4 derniers élements à partir de la pile
     * @param List<Double> stackData, pile que l'on souhaite afficher
     */
	@Override
	public void setPileLabels(List<Double> stackData) {
		int size = stackData.size();
		this.pileLastElementLabel.setText("");	
		this.pile2ndLastElement.setText("");
		this.pile3rdLastElement.setText("");
		this.pile4thLastElement.setText("");
		if(size > 3) {
			this.pile4thLastElement.setText(String.valueOf(stackData.get(size-4)));	
		} 
		if(size > 2) {
			this.pile3rdLastElement.setText(String.valueOf(stackData.get(size-3)));
		}
		if(size > 1) {
			this.pile2ndLastElement.setText(String.valueOf(stackData.get(size-2)));	
		}
		if(size > 0) {
			this.pileLastElementLabel.setText(String.valueOf(stackData.get(size-1)));
		}
	}
}

