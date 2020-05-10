package expression;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	static boolean isVariable(List<String> tokens ) {
		if(tokens.size() == 1 && Variable.isValidName(tokens.get(0)))
			return true ; 
		return false ; 
	}
	
	static List<String> findTokens(String input) {
		String temp = utils.Utils.removeOuterBracket(input ) ; 
        int index = 0;
        char[] chars = temp.toCharArray();
        List<String> tokens = new ArrayList<>();
        while(index < chars.length){
            String token = nextToken(chars, index);
            index += token.length();
            if(token.equals(" ")) continue;
            token = token.trim();
            tokens.add(token);
        }
        return tokens;
    }

	/**
	 * <pre>
	 * note that there must be space before and after the operator, so it " " + operator + " " 
	 *  otherwise, it cannot recognise AandB. This will be treated as variable
	 *  it should be A and B
	 *  </pre>
	 * @param chars
	 * @param index
	 * @return
	 */
	//Dont include space dip
    static String nextToken(char[] chars, int index) {
        String s = "";
        int parenthesis = 0;
        while(index < chars.length) {
            char c = chars[index++];
            s += c;
            if(c == '(')
                parenthesis++;
            if(parenthesis > 0){
                if(c == ')' && --parenthesis == 0)
                    return s;
            } else {
                if (c == ' ')
                    return s;
                if (Operator.legalOperators.contains(s))
                    return s;
            }
        }
        return s;
    }

	static boolean isConstantFalse(List<String> tokens) {
		return (tokens.size() == 1 && tokens.get(0).equals("False")) ? true : false ; 
	}

	static boolean isConstantTrue(List<String> tokens) {
		return (tokens.size() == 1 && tokens.get(0).equals("True")) ? true : false ; 
	}

	static boolean isBinaryExpression(String operator,List<String> tokens) {
		int count = 0 ; 
		for(String s  : tokens )
			if(s.equals(operator))
				count++ ; 
		return count == 1 ? true : false ; 
	}

	static boolean isUnaryExpression( String operator , List<String> tokens) {
		return tokens.size() == 2 && tokens.get(0).equals(operator) ? true : false ; 
	} 

	static ArrayList<String> getBinaryOrHigher(String operatorString, List<String> tokens) {
		StringBuilder sb = new StringBuilder() ;
		ArrayList<String> result = new ArrayList<String> () ; 
		for(String s : tokens ) {
			if(s.equals(operatorString)) {
				result .add(sb.toString()) ; 
				sb = new StringBuilder()  ;
			}else 
				sb.append(s) ; 
		}
		result.add(sb.toString()) ; 
		return result ; 
	}

	static String getUnary(String operatorString,List<String> tokens) {
		for(String s : tokens)
			if(s.equals(operatorString))
				continue ; 
			else 
				return s ; 
		throw new IllegalArgumentException("no child expression found") ; 
	}

	static boolean isMultiaryExpression(String operator , List<String> tokens) {
		int count = 0 ; 
		for(String s : tokens)
			if(s.equals(operator))
				count++ ; 
		return count > 1 ? true : false ; 
	}
}
