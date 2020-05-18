package expression.constantExpression;

import expression.Expression;

public class TrueExpression extends Expression{
	
	public static final String TERM = "True" ; 
	
	public TrueExpression() {
		setTruthTable() ; 
	}

	@Override
	public boolean isAlwaysTrue() {
		// TODO Auto-generated method stub
		return true ;
	}

	@Override
	public boolean isAlwaysFalse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return TERM;
	}

	@Override
	protected void setVariables() {
	}

}
