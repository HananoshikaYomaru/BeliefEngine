package expression;

import java.util.ArrayList;
import expression.binaryExpression.AND;
import expression.binaryExpression.IFF;
import expression.binaryExpression.IMPLIES;
import expression.binaryExpression.OR;
import expression.binaryExpression.XOR;

public class CNF extends MultiaryExpression{
	
	public static final String operator  = "&&" ; 
	/**
	 * <pre>
	 * a special CNF intermediate constructor such that it can be used to test whether the belief base is consistent
	 * if the belief base is not consistent, isAlwaysFalse() will return true ; 
	 * </pre>
	 * @param es, an arrayList with all Expression being CNF
	 */
	public CNF(ArrayList<Expression> es ) {
		super(operator,es) ;  
	}
	
	public CNF(Expression e ) {
		super(operator , new ArrayList<Expression> () {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8129510840314171342L;

			{
				add(e ) ; 
			}
		}) ; 
		es = convertToCNF(e ) ; 
	}
	
	
	/**
	 * this method will convert an expression into many CNF expression which return an arrayList
	 * @param e
	 * @return
	 */
	private ArrayList<Expression> convertToCNF(Expression e) {
		// TODO Auto-generated method stub
		ArrayList<Expression> result = new ArrayList<Expression> () ; 
		
		if(e.isAlwaysFalse()) {
			result.add(Expression.FALSE) ;
			return result ; 
		} 
		if(e.isAlwaysTrue())
		{
			result.add(Expression.TRUE ) ; 
			return result ; 
		}
		
		if(e instanceof CNF) {
			CNF temp = (CNF ) e  ;
			result.addAll(temp.getExpressions()) ; 
			return result ;
		}
		
		else if(e instanceof Variable) {
			result.add(e) ; 
			return result ; 
		}else if(e instanceof AND )
		{
			AND andExpression = (AND) e ; 
			result.addAll(convertToCNF(andExpression.e1)) ; 
			result.addAll(convertToCNF(andExpression.e2)) ; 
		}else if(e instanceof OR ) {
			OR orExpression = (OR)e ; 
			for(Expression e1 : convertToCNF(orExpression.e1))
				for(Expression e2 : convertToCNF(orExpression.e2))
					result.add(ExpressionFactory.createOR(e1, e2)) ; 
		}else if (e instanceof NOT) {
			NOT notExpression = (NOT )e ; 
			if(notExpression.e instanceof Variable) {
				result.add(e) ; 
			}
			else if(notExpression.e instanceof NOT)
				result =  convertToCNF(notExpression.e) ; 
			else if(notExpression.e instanceof AND) {
				AND temp = (AND) notExpression.e ; 
				NOT not1 = ExpressionFactory.createNOT(temp.e1) ; 
				NOT not2 = ExpressionFactory.createNOT(temp.e2) ; 
				OR Not1orNot2 = ExpressionFactory.createOR(not1, not2) ; 
				result =  convertToCNF(Not1orNot2) ; 
			}
			else if(notExpression.e instanceof OR) {
				OR temp = (OR) notExpression.e ; 
				NOT not1 = ExpressionFactory.createNOT(temp.e1) ; 
				NOT not2 = ExpressionFactory.createNOT(temp.e2) ; 
				AND Not1andNot2 = ExpressionFactory.createAND(not1, not2) ; 
				result =  convertToCNF(Not1andNot2) ; 
			}
		}else if (e instanceof IMPLIES) {
			IMPLIES temp = (IMPLIES ) e ; 
			NOT not1 = ExpressionFactory.createNOT(temp.e1) ; 
			OR not1or2 = ExpressionFactory.createOR(not1, temp.e2) ; 
			result = convertToCNF(not1or2)  ;
		}else if (e instanceof IFF) {
			IFF temp = (IFF) e ; 
			NOT not1 = ExpressionFactory.createNOT(temp.e1) ; 
			NOT not2 = ExpressionFactory.createNOT(temp.e2) ; 
			AND e1ande2 = ExpressionFactory.createAND(temp.e1, temp.e2) ; 
			AND not1andnot2 = ExpressionFactory.createAND(not1, not2) ; 
			OR e1ande2_or_not1andnot2 = ExpressionFactory.createOR(e1ande2, not1andnot2) ; 
			result = convertToCNF(e1ande2_or_not1andnot2) ; 
		}else if(e instanceof XOR ) {
			XOR temp = (XOR ) e ; 
			NOT not1 = ExpressionFactory.createNOT(temp.e1) ; 
			NOT not2 = ExpressionFactory.createNOT(temp.e2) ; 
			AND e1andnot2 = ExpressionFactory.createAND(temp.e1, not2) ; 
			AND e2andnot1 = ExpressionFactory.createAND(temp.e2, not1) ; 
			OR e1andnot2_or_e2andnot1 = ExpressionFactory.createOR(e1andnot2, e2andnot1) ; 
			result = convertToCNF(e1andnot2_or_e2andnot1) ; 

		}
		return result ; 
	}
	
	@Override
	protected void simplify(ArrayList<Expression> es ) {
		for(Expression e : es ) {
			if(e.isAlwaysTrue())
				e = Expression.TRUE ; 
			if(e.isAlwaysFalse())
				e = Expression.FALSE  ; 
		}
		ArrayList<Expression> temp = new ArrayList<Expression> () ; 
		for(Expression e : es) {
			if(!temp.contains(e))
				temp.add(e) ; 
		}
		this.es = temp ; 
	}

	@Override 
	public String toString() {
		return convertToStringFormat(this.es) ;  
	}
	
	public ArrayList<Expression> getExpressions() {
		return es ; 
	}
	
	public void printExpressionList() {
		StringBuilder sb= new StringBuilder () ; 
		for(Expression e : es ) 
			sb.append(e.toString()).append("\n" )  ;
		System.out.println(sb.toString()); 
	}
	
	@Override
	public boolean getValue() {
		boolean result = true ; 
		for(Expression e : es) {
			result &= e.getValue()  ; 
		}
		return result ; 
	}

}
