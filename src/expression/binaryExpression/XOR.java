package expression.binaryExpression;

import expression.BinaryExpression;
import expression.Expression;

public class XOR extends BinaryExpression {
	public static final String operator = "xor"  ;
	public XOR( Expression e1, Expression e2) {
		super(operator, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean getValue()	{
		return (e1.getValue() && ! e2.getValue()) || (e2.getValue() && ! e1.getValue()) ;
	}

}
