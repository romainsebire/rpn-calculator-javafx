package model;

import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface {
	
	// Accumulator string and stack for memory
	private String accu;
	private Stack<Double> stack;
	
	/**
	 * Constructor - initializes the accumulator and stack
	 */
	public CalculatorModel() {
		this.accu = "";
		this.stack = new Stack<Double>();
	}

	/**
	 * Getter for the accumulator
	 */
	@Override
	public String getAccu() {
		return this.accu;
	}
	
	/**
	 * Setter for the accumulator
	 * @param nb the new accumulator value
	 */
	@Override
	public void setAccu(String nb) {
		this.accu = nb;
	}
	
	/**
	 * Getter for the stack
	 */
	@Override
	public Stack<Double> getStack() {
		return stack;
	}
	
	/**
	 * Setter for the stack
	 * @param stack the new stack
	 */
	@Override
	public void setStack(Stack<Double> stack) {
		this.stack = stack;
	}

	/**
	 * Concatenates an element to the accumulator, ensuring no duplicate decimal point
	 * @param element the element to append
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
	 * Pushes the accumulator value onto the stack
	 * Only pushes if the accumulator is non-empty and not a lone decimal point
	 */
	@Override
	public void push() {
		if(this.accu != "" && !(this.accu.length() == 1 && Character.codePointAt(accu, 0) == 46)) {
			this.stack.push(Double.parseDouble(this.accu));
			this.accu = "";
		}
	}
	
	/**
	 * Performs addition
	 * Pushes the accumulator (if non-empty), pops two operands (if available), and pushes the result
	 */
	@Override
	public void add() {
		this.push();
		if(this.stack.size() > 1) {
			Double operand1 = this.stack.pop();
			Double operand2 = this.stack.pop();
			this.stack.push(operand2 + operand1);
		}
	}

	/**
	 * Performs subtraction
	 * Pushes the accumulator (if non-empty), pops two operands (if available), and pushes the result
	 */
	@Override
	public void substract() {
		this.push();
		if(this.stack.size() > 1) {
			Double operand1 = this.stack.pop();
			Double operand2 = this.stack.pop();
			this.stack.push(operand2 - operand1);
		}
	}

	/**
	 * Performs multiplication
	 * Pushes the accumulator (if non-empty), pops two operands (if available), and pushes the result
	 */
	@Override
	public void multiply() {
		this.push();
		if(this.stack.size() > 1) {
			Double operand1 = this.stack.pop();
			Double operand2 = this.stack.pop();
			this.stack.push(operand2 * operand1);
		}
	}

	/**
	 * Performs division
	 * Pushes the accumulator if non-zero (and non-empty), checks divisor is non-zero,
	 * pops two operands (if available), and pushes the result
	 */
	@Override
	public void divide() {
		if(this.accu != "" && !(this.accu.length() == 1 && Character.codePointAt(accu, 0) == 46) && Double.parseDouble(this.accu) != 0) {
			this.push();
		}
		if(this.stack.size() > 1 && this.stack.getLast() != 0) {
			Double operand1 = this.stack.pop();
			Double operand2 = this.stack.pop();
			this.stack.push(operand2 / operand1);
		}
	}

	/**
	 * Negates the top element of the stack
	 * If the stack is empty but accumulator is non-empty, pushes accumulator first then negates
	 */
	@Override
	public void opposite() {
		if(this.stack.size() > 0) {
			Double operand1 = this.stack.pop();
			this.stack.push(-operand1);
		} else if (this.accu != "") {
			this.push();
			Double operand1 = this.stack.pop();
			this.stack.push(-operand1);
		}
	}

	/**
	 * Removes the top element from the stack (if non-empty)
	 */
	@Override
	public void drop() {
		if(this.stack.size() > 0) {
			this.stack.pop();
		}
	}

	/**
	 * Swaps the two top elements of the stack
	 * Only swaps if the stack has at least 2 elements
	 */
	@Override
	public void swap() {
		if(this.stack.size() > 1) {
			Double operand1 = this.stack.pop();
			Double operand2 = this.stack.pop();
			this.stack.push(operand1);
			this.stack.push(operand2);
		}
	}

	/**
	 * Clears both the stack and the accumulator
	 */
	@Override
	public void clear() {
		this.stack.clear();
		this.accu = "";
	}
	
	/**
	 * Removes the last character from the accumulator (if non-empty)
	 */
	@Override
	public void undoAccu() {
        if (accu.length() > 0) {
            accu = accu.substring(0, accu.length() - 1);
        }
	}
}
