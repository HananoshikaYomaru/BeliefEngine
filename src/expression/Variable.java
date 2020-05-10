package expression;

import java.util.HashSet;

import annotation.Done;


@Done
public class Variable extends Expression{

	private String name ; 
	
	//by default, the value is true
	//but after init it will become false because truthtable constructor set it to false
	boolean value ; 
	
	public Variable(String name) throws IllegalArgumentException {
		if(!isValidName(name))
			throw new IllegalArgumentException("this is not a valid name") ; 
		this.name = name ; 
		this.value = true ; 
	}
	
	@Override
	public boolean isAlwaysTrue() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override 
	public boolean isAlwaysFalse() {
		return false; 
	}
	
	@Done
	public static boolean isValidName(String input) {
		if(input.isBlank())
			return false ; 
		return input.matches("[a-zA-Z]*") ; 
	}

	
	/**
	 * return the name of this variable
	 */
	@Override
	public String toString() {
		return name ; 
	}
	
	
	/**
	 * this is the same function as toString() ; 
	 * @return
	 */
	public String getName()	{
		return name ; 
	}
	
	@Override 
	public boolean getValue() {
		return value ; 
	}
	
	public void setValue(boolean value ) {
		this.value = value ; 
	}
	
	@Done 
	@Override 
	public boolean equals(Object o ) {
		if(o == this)
			return true ; 
		if(o instanceof Variable == false )
			return false ; 
		Variable temp = (Variable) o ; 
		return this.name.equals(temp.toString())  ;
	}

	@Override 
	public int hashCode() {
		return toString().hashCode() ; 
	}
	
	@Done 
	@Override
	protected void setVariables() {
		HashSet<Variable> temp  = new HashSet<Variable> () ; 
		temp.add(this) ; 
		this.variables = temp ; 
	}
}
