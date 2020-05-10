package expression;

import java.util.HashSet;

import expression.binaryExpression.IMPLIES;
import expression.constantExpression.FalseExpression;
import expression.constantExpression.TrueExpression;

public abstract class Expression {
	
	protected HashSet<Variable> variables = new HashSet<Variable> () ; 
	protected TruthTable TT ; 
	
	public static final TrueExpression TRUE = new TrueExpression() ; 
	public static final FalseExpression FALSE = new FalseExpression() ; 
	
	public boolean isAlwaysTrue() {
		return this.TT.isAlwaysTrue();
	} 
	public boolean isAlwaysFalse()
	{
		return this.TT.isAlwaysFalse() ; 
	}
	public abstract boolean getValue() ; 
	@Override 
	public abstract String toString() ;
	
	/**
	 * <pre> 
	 * return true if 
	 * 1. two expressions contains the same variables (such that (A AND NOT A) is not equal to (B AND NOT B) 
	 * 2. two expressions is logically equivalent 
	 * 
	 * if you want to check whether they are logically equivalent only, 
	 * use checkEquivalence() function
	 * </pre> 
	 */
	@Override 
	public boolean equals(Object e ) { 
		if(e instanceof Expression == false ) 
			return false ; 
		Expression temp  = (Expression ) e  ;
		if((temp.isAlwaysTrue() && this.isAlwaysTrue()) ||(temp.isAlwaysFalse() && this.isAlwaysFalse()) )
			return true ; 
		return temp.TT.equals(this.TT)  ;
	}
	
	
	protected abstract void setVariables() ; 
	
	public HashSet<Variable> getVariables(){
		return variables ;
	}
	
	/**
	 * the variable input should be a larger set of variable than the varaible of this expression. 
	 * @param variables
	 * @throws IllegalStateException
	 */
	public void refreshVariables(HashSet<Variable> variables ) throws IllegalStateException{
		if(this.getClass().equals(Variable.class))
			throw new IllegalArgumentException("should not refresh variables of a variable") ; 
		
		
		int sizeCheck = this.variables.size() ; 
		HashSet<Variable> temp = new HashSet<>()  ;
		for(Variable v : variables)
			if(this.variables.contains(v)) {
				temp.add(v) ; 
				//System.out.println("temp contain " + v.toString()) ; 
			}
		if(temp.size() != sizeCheck) // after refresh they must have the same size 
			throw new IllegalStateException("not match size : temp size = " + temp.size() + "\t size check = " + sizeCheck ) ; 
		this.variables = temp ; 
		
		//recursively refresh the child expression
		if(this instanceof BinaryExpression)
		{
			BinaryExpression be = (BinaryExpression) this ; 
			be.connectVariables() ; 
		}
		
		if(this instanceof UnaryExpression) {
			UnaryExpression ue = (UnaryExpression) this ; 
			ue.refreshHelp() ; 
		}
		
		if(this instanceof MultiaryExpression) {
			MultiaryExpression me = (MultiaryExpression ) this ; 
			me.connectVariables();
		}
	}
	
	public void init() {
		setVariables()	; 
		setTruthTable() ; 
	}
	
	protected void setTruthTable() {
		TT = new TruthTable(this) ; 	
	}
	
	
	public void printVariables() {
		StringBuilder sb = new StringBuilder() ; 
		for(Variable v : this.variables)
			sb.append(v.toString() + "\t" + v.getValue() + "\n") ; 
		System.out.println(sb.toString()) ;
	}
	
	public TruthTable getTruthTable() {
		return TT ; 
	}
	
	public static boolean logicalEntailment(Expression e1 , Expression e2 ) {
		
		
		IMPLIES temp = (IMPLIES) ExpressionFactory.createBinary(IMPLIES.operator, e1,e2) ; 
		System.out.println("check logical entailment..." + temp.toString())  ; 
		temp.getTruthTable().print(); 
		return temp.isAlwaysTrue() ; 
	}
	
}
