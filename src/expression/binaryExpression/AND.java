package expression.binaryExpression;

import expression.BinaryExpression;
import expression.Expression;

public class AND extends BinaryExpression{
	
	public static final String operator = "&&"; 

	public AND(Expression e1, Expression e2) {
		super(operator , e1, e2);
	}

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return e1.getValue() && e2.getValue();
	}
	
}
