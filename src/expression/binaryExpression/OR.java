package expression.binaryExpression;

import expression.BinaryExpression;
import expression.Expression;

public class OR extends BinaryExpression{
	
	public static final String operator = "||" ; 

	public OR(Expression e1, Expression e2) {
		super(operator , e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return e1.getValue() || e2.getValue();
	}

}
