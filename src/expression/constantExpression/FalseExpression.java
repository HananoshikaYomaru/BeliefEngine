package expression.constantExpression;

import expression.Expression;

public class FalseExpression extends Expression{

	public FalseExpression(){
		setTruthTable() ; 
	}
	
	@Override
	public boolean isAlwaysTrue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAlwaysFalse() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "False";
	}

	@Override
	protected void setVariables() {
	}

}
