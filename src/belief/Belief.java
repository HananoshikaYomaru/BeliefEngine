package belief;

import annotation.Done;
import expression.CNF;
import expression.Expression;
import expression.ExpressionFactory;
import utils.Utils;

@Done
public class Belief {
	private String originalString ; 
	private String validatedString ; 
	private Expression e ; 
	private int order ;
	
	public Belief(Expression e ) {
		this.e = e ; 
		this.order = 10 ; 
		testValidBelief(e, order ); 
		originalString = validatedString = e.toString() ; 
	}
	
	public Belief(Expression e , int order) {
		testValidBelief(e , order ) ; 
		this.order = order ; 
		this.e = e ; 
		originalString = validatedString = e.toString()  ; 
		
	}
	
	private void testValidBelief(Expression e, int order) {
		if(order > 10 || order < 1 )
			throw new IllegalArgumentException("the order input is invalid , order = " + order ) ; 
		if(e.isAlwaysFalse())
			throw new IllegalArgumentException("the belief is a contant false Expression" ); 
		if(e.isAlwaysTrue())
			throw new IllegalArgumentException("the belief is a constant true Expression, it is a fact , not a belief") ; 
		
	}

	public Belief(String input) {
		order = 10  ;
		originalString = input  ; 
		try {
			validatedString = Utils.validateInput(input) ; 
			e = ExpressionFactory.createExpression(input); 
			testValidBelief(e , order ) ; 
		}catch(Exception e ) {
			e.printStackTrace () ; 
		}
	}
	
	public Belief(String input, int order) {
		originalString = input ; 
		this.order = order ; 
		try {
			validatedString = Utils.validateInput(input) ; 
			e = ExpressionFactory.createExpression(input) ; 
	 		testValidBelief(e , order ) ; 
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * return the validated string but not belief string
	 * in this format : 
	 * "Belief{" + validatedString + ", order = " + order + "}" 
	 * 
	 * </pre>
	 */
	@Override 
	public String toString() {
		return "Belief{" + validatedString + ", order = " + order + "}" ; 
	}
	
	
	/**
	 * <pre>
	 * if two @Belief have same @Expression, then they are equals
	 * the equals function use equals function of @Expression class,
	 * which use the @TruthTable equals function
	 * </pre> 
	 */
	@Override 
	public boolean equals(Object o ) {
		if(o instanceof Belief == false  )
			return false ; 
		Belief temp = (Belief) o ; 
		return temp.e.equals(this.e) ; 
	}

	public Expression getExpression() {
		return e;
	}

	public String getOriginalString() {
		return originalString;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getValidatedString() {
		return validatedString;
	}

}
