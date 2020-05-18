package expression;

import java.util.ArrayList;
import java.util.List;

import expression.binaryExpression.AND;
import expression.binaryExpression.IFF;
import expression.binaryExpression.IMPLIES;
import expression.binaryExpression.OR;
import expression.binaryExpression.XOR;

public class ExpressionFactory {
	
	public static Variable createVariable(String name) {
		Variable temp = new Variable(name ) ; 
		temp.init();
		return temp ;
	}
	
	static AND createAND(Expression e1 , Expression e2 ) {
		AND temp = new AND(e1, e2) ; 
		temp.init(); 
		return temp ; 
	}
	
	static OR createOR(Expression e1, Expression e2) {
		OR temp = new OR(e1, e2 ) ; 
		temp.init();
		return temp ; 
	}
	
	static NOT createNOT (Expression e) {
		NOT temp = new NOT(e) ; 
		temp.init()	;
		return temp ; 
	}
	
	static CNF createCNF(ArrayList<Expression> es) {
		CNF temp = new CNF(es) ; 
		temp.init();  
		return temp ; 
	}
	
	//special
	public static CNF createCNF(Expression e ) {
		CNF temp = new CNF(e ) ; 
		temp .init();
		return temp ; 
	}
	
	
	/**
	 * @param operator string of the operator
	 * @param e1
	 * @return
	 */
	public static UnaryExpression createUnary(String operator , Expression e1 ) {
		return createUnaryHelp(operator ,e1) ; 
	}
	
	private static UnaryExpression createUnaryHelp(String operator, Expression e1) {
		UnaryExpression e = null ; 
		if(operator.equals(NOT.operator))
			e = new NOT(e1) ; 
		if(e == null)
			throw new RuntimeException("fail creation") ; 
		e.init();
		return e ; 
	}

	public static BinaryExpression createBinary(String operator , Expression e1 , Expression e2) {
		return createBinaryHelp(operator, e1, e2 ) ; 
	}
	
	private static BinaryExpression createBinaryHelp(String operator, Expression e1, Expression e2) {
		BinaryExpression e = null ; 
		if(operator.equals(AND.operator))
			e = new AND(e1, e2)  ; 
		else if(operator.equals(OR.operator))
			e = new OR(e1, e2)  ;
		else if(operator.equals(IMPLIES.operator))
			e = new IMPLIES(e1,e2) ; 
		else if(operator.equals(IFF.operator))
			e = new IFF(e1,e2) ; 
		else if(operator .equals(XOR.operator))
			e = new XOR(e1,e2) ; 
		if(e == null)
			throw new RuntimeException("fail creation") ; 
		e.init();
		return e ; 
	}
	
	public static MultiaryExpression createMultiary(String operator , ArrayList<Expression> es) {
		return createMultiaryHelp(operator, es )  ; 
	}
	
	private static MultiaryExpression createMultiaryHelp(String operator, ArrayList<Expression> es) {
		MultiaryExpression e = null ; 
		
		if(operator.equals(CNF.operator))
			e = new CNF(es ) ; 
		
		if(e == null)
			throw new RuntimeException("fail creation") ; 
		e.init();
		return e ; 
	}

	//((A || D) && (B || !C) && (A && D) 
	public static Expression createExpression(String input ) {
		Expression e  = null; 
		List<String> tokens = Utils.findTokens(input) ; 
		
		
		if(Utils.isVariable(tokens)) {
			e = createVariable(tokens.get(0)) ; 
			return e ; 
		}
		else if(Utils.isConstantTrue(tokens)) {
			e = Expression.TRUE ; 
			return e ; 
		}
		else if(Utils.isConstantFalse(tokens))
			e = Expression.FALSE ; 
		
		
		//might be even better if you use function pointer 
		for(String operator : Operator.UnaryOperators) {
			if(Utils.isUnaryExpression(operator, tokens)) {
				String temp = Utils.getUnary(operator, tokens) ; 
				return createUnary(operator, createExpression(temp)) ; 
			}
		}
		
		for(String operator : Operator.BinaryOperators) {
			if(Utils.isBinaryExpression(operator,tokens))
			{
				ArrayList<String> temp = Utils.getBinaryOrHigher(operator, tokens) ;
				return createBinary(operator, createExpression(temp.get(0)), createExpression(temp.get(1)))  ;
			}
		}
		for(String operator : Operator.MultiaryOperators) {
			if(Utils.isMultiaryExpression(operator , tokens)) {
				ArrayList<String> temp = Utils.getBinaryOrHigher(operator, tokens) ; 
				ArrayList<Expression> tempExpressionList = new ArrayList<Expression> () {
					/**
					 * 
					 */
					private static final long serialVersionUID = 3259486766516384381L;

					{
						for(String s : temp)
							add(createExpression(s)) ; 
					}
				} ; 
				return createMultiary(operator, tempExpressionList) ; 
			}
		}
		throw new RuntimeException("fail create Expression ") ; 
	}
}
