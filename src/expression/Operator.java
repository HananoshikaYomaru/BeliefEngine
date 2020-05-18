package expression;

import java.util.HashSet;

/**
 * if you want the string parser to identify the operator,
 * remember to add here . 
 * 
 * A cache class
 * @author Yomaru
 *
 */
public class Operator {
	public final static HashSet<String> legalOperators = new HashSet<String>() ;
	public final static HashSet<String> UnaryOperators = new HashSet<String>() ; 
	public final static HashSet<String> BinaryOperators = new HashSet<String>() ; 
	public final static HashSet<String> MultiaryOperators = new HashSet<String>() ; 
	
	
	public static final String AND = expression.binaryExpression.AND.operator  ; 
	public static final String OR = expression.binaryExpression.OR.operator ; 
	public static final String NOT = expression.NOT.operator ; 
	public static final String CNF = expression.CNF.operator ; 
	public static final String IMPLIES = expression.binaryExpression.IMPLIES.operator ; 
	public static final String IFF = expression.binaryExpression.IFF.operator ; 
	public static final String XOR = expression.binaryExpression.XOR.operator ; 
	
	static {
		UnaryOperators.add(NOT) ; 
		
		
		BinaryOperators.add(AND) ; 
		BinaryOperators.add(OR )  ;
		BinaryOperators.add(IMPLIES ) ; 
		BinaryOperators.add(IFF)  ; 
		BinaryOperators.add(XOR) ; 
		
		
		MultiaryOperators.add(CNF )  ;
		
		
		legalOperators.addAll(UnaryOperators) ; 
		legalOperators.addAll(BinaryOperators) ; 
		legalOperators.addAll(MultiaryOperators) ; 
	}
}
