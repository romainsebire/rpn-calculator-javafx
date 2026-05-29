package model;

import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface {
	
	//Création de la chaine de caractère contenant l'accumulateur et de la pile pour la mémoire
	private String accu;
	private Stack<Double> pile;
	
	/**
     * Constructeur du model et initialisation des attributs
     */
	public CalculatorModel() {
		this.accu = "";
		this.pile = new Stack<Double>();
	}

	/**
     * Getter de l'accumulateur
     */
	@Override
	public String getAccu() {
		return this.accu;
	}
	
	/**
     * Setter de l'accumulateur
     *  @param String nb
     */
	@Override
	public void setAccu(String nb) {
		this.accu = nb;
	}
	
	/**
     * Getter de la pile
     */
	@Override
	public Stack<Double> getPile() {
		return pile;
	}
	
	/**
     * Setter de la pile
     * @param Stack<Double> pile
     */
	@Override
	public void setPile(Stack<Double> pile) {
		this.pile = pile ;
	}

	/**
     * Méthode de concaténation de l'accumulateur à partir d'un élement, en s'assurant de ne pas pouvoir ajouter 2 virgules
     * @param String element, élement à concaténer
     */
	@Override
	public void writeAccu(String element) {
		if(element == ".") {
			if(!this.accu.contains(".")) {
				this.accu += element;
			}
		} else {
			this.accu += element;
		}
	}
	
	/**
     * Méthode pour empiler l'accumulateur
     * Si l'accumulateur est non vide ET n'est pas une virgule seule, alors on l'empile et réinitialise l'accumulateur
     */
	@Override
	public void push() {
		if(this.accu != "" && !(this.accu.length() == 1 && Character.codePointAt(accu, 0) == 46)) {
			this.pile.push(Double.parseDouble(this.accu));
			this.accu = "";
		}
	}
	
	/**
     * Méthode pour réaliser une addition
     * On empile l'accumulateur (possible seulement s'il est non vide), on dépile les opérandes nécessaires (s'ils sont suffisants) et on rempile le résultat
     */
	@Override
	public void add() {
		this.push();
		if(this.pile.size() > 1) {
			Double operand1 = this.pile.pop();
			Double operand2 = this.pile.pop();
			this.pile.push(operand2 + operand1);
		}
	}

	/**
     * Méthode pour réaliser une soustraction
     * On empile l'accumulateur (possible seulement s'il est non vide), on dépile les opérandes nécessaires (s'ils sont suffisants) et on rempile le résultat
     */
	@Override
	public void substract() {
		this.push();
		if(this.pile.size() > 1) {
			Double operand1 = this.pile.pop();
			Double operand2 = this.pile.pop();
			this.pile.push(operand2 - operand1);
		}
	}

	/**
     * Méthode pour réaliser une multiplication
     * On empile l'accumulateur (possible seulement s'il est non vide), on dépile les opérandes nécessaires (s'ils sont suffisants) et on rempile le résultat
     */
	@Override
	public void multiply() {
		this.push();
		if(this.pile.size() > 1) {
			Double operand1 = this.pile.pop();
			Double operand2 = this.pile.pop();
			this.pile.push(operand2 * operand1);
		}
	}

	/**
     * Méthode pour réaliser une division
     * On empile l'accumulateur s'il est non nul (possible seulement s'il est également non vide)
     * On vérifie que le diviseur est non nul
     * On dépile les opérandes nécessaires (s'ils sont suffisants) et on rempile le résultat
     */
	@Override
	public void divide() {
		if(this.accu != "" && !(this.accu.length() == 1 && Character.codePointAt(accu, 0) == 46) && Double.parseDouble(this.accu) != 0) {
			this.push();
		}
		if(this.pile.size() > 1 && this.pile.getLast() != 0) {
			Double operand1 = this.pile.pop();
			Double operand2 = this.pile.pop();
			this.pile.push(operand2 / operand1);
		}
	}

	/**
     * Méthode pour réaliser une opposition du dernier élement de la pile
     * Si la pile est non vide, on dépile le dernier élement et on rempile l'opposé
     * Sinon, on regarde si l'accumulateur est non vide et on push l'opposé
     */
	@Override
	public void opposite() {
		if(this.pile.size() > 0) {
			Double operand1 = this.pile.pop();
			this.pile.push(-operand1);
		} else if (this.accu != "") {
			this.push();
			Double operand1 = this.pile.pop();
			this.pile.push(-operand1);
		}
	}

	/**
     * Méthode pour supprimer le dernier élement de la pile
     * Si la pile est non vide, on supprime le dernier élement
     */
	@Override
	public void drop() {
		if(this.pile.size() > 0) {
			this.pile.pop();
		}
	}

	/**
     * Méthode pour réaliser échanger les deux derniers élements de la pile
     * Si la pile possède suffisament d'élements, on dépile les 2 derniers et on les rempile dans le sens inverse
     */
	@Override
	public void swap() {
		if(this.pile.size() > 1) {
			Double operand1 = this.pile.pop();
			Double operand2 = this.pile.pop();
			this.pile.push(operand1);
			this.pile.push(operand2);
		}
	}

	/**
     * Méthode pour effacer la pile et l'accumulateur
     */
	@Override
	public void clear() {
		this.pile.clear();
		this.accu = "";
	}
	
	/**
     * Méthode pour effacer le dernier caractère de l'accumulateur, si celui ci est non vide
     */
	@Override
	public void undoAccu() {
        if (accu.length() > 0) {
            accu = accu.substring(0, accu.length() - 1);
        }
	}
}
