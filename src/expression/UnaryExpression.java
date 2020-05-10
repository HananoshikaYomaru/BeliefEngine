package expression;

import java.util.HashSet;

public abstract class UnaryExpression extends Expression{
	public final String operator ; 
	protected Expression e ; 
	UnaryExpression(String operator ,Expression e){
		this.operator = operator ; 
		if(e.isAlwaysTrue())
			e = Expression.TRUE ; 
		if(e.isAlwaysFalse())
			e= Expression.FALSE ; 
		this.e = e ; 
	}
	
	public Expression getExpression() {
		return e ; 
	}
	
	public void setExpression(Expression e) {
		this.e = e ; 
	}
	
	@Override 
	public void init() {
		setVariables() ; 
		refreshHelp();
		setTruthTable(); 
	}
	
	@Override 
	public void setVariables() {
		this.variables = new HashSet<Variable> () ; 
		for(Variable v : e.variables)
			if(!this.variables.contains(v))
				this.variables.add(ExpressionFactory.createVariable(v.getName()))  ;
	}
	
	void refreshHelp() {
		if(e instanceof Variable) {
			for(Variable v : this.variables)
				if(((Variable)e).equals(v))
					e = v ; 
		}
		else 
			e.refreshVariables(this.variables);
	}
	
	@Override 
	public String toString() {
		return operator + e.toString() ; 
	}
}
