package expression.binaryExpression;

import expression.BinaryExpression;
import expression.Expression;

public class IFF extends BinaryExpression{

	public static final String operator = "<->" ; 
	
	public IFF( Expression e1, Expression e2) {
		super(operator, e1, e2);
	}
	
	public boolean getValue() {
		return e1.getValue() == e2.getValue() ; 
  	}
	
}
