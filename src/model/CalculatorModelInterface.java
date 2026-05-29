package model;

import java.util.Stack;

public interface CalculatorModelInterface {
	
	public void setAccu(String accu);
    public String getAccu();
    public void setPile(Stack<Double> pile);
    public Stack<Double> getPile();
    
	public void add();
	public void substract();
	public void multiply();
	public void divide();
	public void opposite();
	public void push();
	public void drop();
	public void swap();
	public void clear();
	public void writeAccu(String element);
	public void undoAccu();
	
}
