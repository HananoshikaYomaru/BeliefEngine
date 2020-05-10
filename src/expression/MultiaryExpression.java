package expression;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class MultiaryExpression extends Expression{
	public final String operator ; 
	protected ArrayList<Expression > es ; 
	
	protected MultiaryExpression(String operator ,ArrayList<Expression> es ) {
		this.operator  = operator ; 
		//simplify 
		simplify(es) ; 
		this.es = es ; 
			
	}
	
	protected void simplify(ArrayList<Expression> es ) {
		for(Expression e : es ) {
			if(e.isAlwaysTrue())
				e = Expression.TRUE ; 
			if(e.isAlwaysFalse())
				e = Expression.FALSE  ; 
		}
		ArrayList<Expression> temp = new ArrayList<Expression> () ; 
		outer : 
		for(Expression e : es) {
			for(int i  = 0; i < temp.size() ; i++)
				if(temp.get(i).equals(e)) {
					e = temp.get(i); 
					continue outer   ;
				}
			temp.add(e) ; 
		}
	}
	
	void connectVariables() {
		for(int i = 0 ; i < this.es.size() ; i++) {
			Expression e = this.es.get(i) ; 
			boolean isVar = e instanceof Variable ; 
			if(isVar ) {
				for(Variable v : this.variables)
					if(((Variable)e).equals(v))
						this.es.set(i, v) ; 
						
			}else {
				e.refreshVariables(this.variables);
			}
		}
	}
	
	@Override 
	public void init() {
		setVariables ()	 ; 
		connectVariables () ; 
		setTruthTable()  ; 
		simplify(es ) ; 
	}
	
	public Expression getExpression(int i ) { 
		if( i < 1 || i > es.size())
			throw new IllegalArgumentException("no such child expression"  ) ; 
		return es.get(i - 1 ) ; 
	}
	
	@Override 
	public void setVariables() {
		this.variables = new HashSet<Variable>() ; 
		for(Expression e : this.es) {
			for(Variable v : e.variables)
				if(!this.variables.contains(v))
					this.variables.add(ExpressionFactory.createVariable(v.getName())) ; 
		}
	}
	
	protected String convertToStringFormat(ArrayList<Expression > es ) {
		StringBuilder sb =new StringBuilder() ; 
		sb.append("(" + es.get(0).toString()) ; 
		for(int i = 1 ; i < es.size() ; i ++ )
			sb.append(" " + operator + " " + es.get(i).toString()) ; 
		sb.append(")") ; 
		return sb.toString() ; 
	}
}
