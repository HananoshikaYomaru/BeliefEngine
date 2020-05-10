package expression;

public class NOT extends UnaryExpression{
	
	public static final String operator = "!" ; 
	public NOT(Expression e) {
		super(operator , e);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public boolean isAlwaysTrue() {
//		return e.isAlwaysFalse () ; 
//	}
//	
//	@Override 
//	public boolean isAlwaysFalse() {
//		return e.isAlwaysTrue() ; 
//	}
//	

	@Override
	public boolean getValue() {
		// TODO Auto-generated method stub
		return !e.getValue();
	}

}
