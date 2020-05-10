package expression;

import java.util.HashSet;

public abstract class BinaryExpression extends Expression {
	public final String operator ; 
	protected Expression e1 ; 
	protected Expression e2 ; 
	protected BinaryExpression(String operator, Expression e1 , Expression e2){ 
		this.operator = operator ; 
		//simplify
		if(e1.isAlwaysTrue())
			e1 = Expression.TRUE ; 
		if(e1.isAlwaysFalse())
			e1 = Expression.FALSE ; 
		if(e2.isAlwaysTrue()) 
			e2 = Expression.TRUE ; 
		if(e2.isAlwaysFalse())
			e2 = Expression.FALSE ; 
		if(e1.equals(e2))
			e2 = e1 ; 
		this.e1 = e1 ; 
		this.e2 = e2 ; 
	}
	
	void connectVariables() {
		//if either one is true of false , don't need to connect 
		//if variable exist in expression 1 and expression 2, connect 
		boolean e1isVar = e1 instanceof Variable  ; 
		boolean e2isVar = e2 instanceof Variable  ;
		if(e1isVar) {
			for(Variable v : this.variables)
				if(((Variable)e1).equals(v))
					e1 = v ; 
		}else 
			e1.refreshVariables(this.variables);
		if(e2isVar) {
			for(Variable v : this.variables)
				if(((Variable)e2).equals(v))
					e2 = v; 
		}else 
			e2.refreshVariables(this.variables);
	}

	public Expression getExpression(int i ) {
		if(i != 1 && i != 2 )
			throw new IllegalArgumentException("no such child expression") ; 
		return i == 1 ? e1 : e2  ; 
	}
	
	@Override 
	public void init() {
		setVariables() ; 
		connectVariables() ; 
		setTruthTable(); 
	}
	
	/**
	 * this function set the variable of this expression to the union set of two expression
	 */
	@Override 
	public void setVariables() {
		this.variables = new HashSet<Variable> () ; 
		for(Variable v: e1.getVariables())
			if(!this.variables.contains(v))
				this.variables.add(ExpressionFactory.createVariable(v.getName())) ; 
		for(Variable v: e2.getVariables())
			if(!this.variables.contains(v))
				this.variables.add(ExpressionFactory.createVariable(v.getName())) ; 
	}
	
	@Override
	public String toString() { 
		return "(" + e1.toString() + ' ' +  operator  + ' ' + e2.toString() + ")"; 
	}
	
	
}
