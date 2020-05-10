package utils;

import java.util.Stack;

import annotation.NotDone;
import expression.CNF;
import expression.Expression;
import expression.ExpressionFactory;
import expression.NOT;

public class Utils {

	
	/**
	 * remove the white space etc. and turn it into a format that can be cast to expression
	 * @param input
	 * @return
	 */
	@NotDone
	public static String validateInput(String input) {
		// TODO Auto-generated method stub
		return input;
	}

	
	/**
	 * the String will be converted to certain logical format. depend on what operators
	 * are accepted in the format 
	 * by default, this is CNF format
	 * 
	 * @param beliefString which is a validated string 
	 * @return
	 */
	@NotDone 
	public static String convertToCNFString(String validatedString) {
		// TODO Auto-generated method stub
		Expression temp = ExpressionFactory.createExpression(validatedString) ; 
		return ExpressionFactory.createCNF(temp).toString(); 
	}
	
	/**
	 * check whether one expression is the negation of another 
	 * @param e1
	 * @param e2
	 * @return
	 */
	public static boolean isNegation(Expression e1 , Expression e2 ) {
		if(e1 instanceof NOT && !(e2 instanceof NOT) && ((NOT)e1).getExpression().equals(e2) )
			return true; 
		if(e2 instanceof NOT && !(e1 instanceof NOT) && ((NOT)e2).getExpression().equals(e1) )
			return true ;
		else 
			return false ; 
	}
	
//	public static String removeAllOuterBracket(String input) {
//		String temp = input ; 
//		while(true) {
//			String newtemp = removeOuterBracket(temp) ;
//			if(newtemp.equals(temp))
//				break ; 
//			else 
//				temp = newtemp ; 
//		}
//		return temp; 
//	}

    public static String removeOuterBracket(String input) {
    	int indexOfOpenBracket = input.indexOf("(");
    	int indexOfLastBracket = input.lastIndexOf(")");
    	if(indexOfOpenBracket == 0 && indexOfLastBracket == input.length() - 1 ) {
    		String temp = input.substring(indexOfOpenBracket+1, indexOfLastBracket);
        	if(balancedParenthensies(temp))
        		return removeOuterBracket(temp) ; 
        	else 
        		return input ; 
    	}
    	else 
    		return input ; 
	}

    public static boolean balancedParenthensies(String s) {
        Stack<Character> stack  = new Stack<Character>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '[' || c == '(' || c == '{' ) {     
                stack.push(c);
            } else if(c == ']') {
                if(stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else if(c == ')') {
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }           
            } else if(c == '}') {
                if(stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }

}
