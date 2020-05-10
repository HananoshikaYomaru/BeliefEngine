package expression.constantExpression;

import expression.Expression;

public class TrueExpression extends Expression{
	
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
		return "True";
	}

	@Override
	protected void setVariables() {
	}

}
