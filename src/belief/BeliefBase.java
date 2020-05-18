package belief;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import annotation.NotDone;
import expression.CNF;
import expression.Expression;
import expression.ExpressionFactory;
import expression.NOT;

public class BeliefBase {
	private ArrayList< Belief> beliefs ; 
	
	public BeliefBase()	{
		this.beliefs = new ArrayList< Belief >() {
			@Override
			public boolean add ( Belief b) { 
				super.add(b);
				super.sort(new Comparator<Belief>() {

					@Override
					public int compare(Belief o1, Belief o2) {
						// TODO Auto-generated method stub
						return o1.getOrder() > o2.getOrder()? -1 : 1 ;
					} 
					
				});
				return true;
			}
		}; 
	}
	
	//shallow copy
	public BeliefBase(BeliefBase bb ) {
		this.beliefs = bb.getBeliefs() ; 
 	}
	
	@Override 
	public String toString() {
		return "BeliefBase{" + beliefs + "}"; 
	}
	
	public ArrayList<Belief> getBeliefs()	{
		return beliefs ; 
	}

	/**
	 * <pre>
	 * add the new belief and check for consistency
	 * if consistent, simply add the new belief 
	 * if not consistent, resolve the conflict
	 * </pre> 
	 * @param belief
	 */
	public void add(Belief belief) {
		// TODO Auto-generated method stub
		if(belief.getExpression().isAlwaysFalse()) // if the belief is inconsistency with itself, then don't add the belief
			return ; 
		
		//check when belief is consistent with belief base 
		try {
			if(checkConsistency(this, belief)) {
				beliefs.add(belief) ; 
			}else {
				System.out.println("inconsistent belief, solve conflict...") ; 
				solveInconsistency(belief) ; 
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}

	/**
	 * this function sort an arraylist of belief according to their order 
	 * @param beliefList
	 */
	private void sortBeliefs(ArrayList<Belief> beliefList ) {
		beliefList.sort(new Comparator<Belief> () {
			@Override
			public int compare(Belief o1, Belief o2) {
				// TODO Auto-generated method stub
				return o1.getOrder() > o2.getOrder()? -1 : 1 ;
			}
		});
	}

	private void solveInconsistency(Belief newBelief) {
		ArrayList<Belief> temp = this.beliefs ; 
		temp.add(newBelief) ; 
		sortBeliefs(temp); 
		BeliefBase tempBB = new BeliefBase( ) ; 
		for(Belief b : temp) {
			if(checkConsistency(tempBB, b))
				tempBB.add(b);
			else
				System.out.println(b.toString() + " is removed ") ; 
		}
		this.beliefs = tempBB.getBeliefs() ; 
	}

	
	/**
	 * 
	 * @param bb
	 * @param belief
	 * @return whether the new belief is consistency with the belief base, true if yes , false if no 
	 */
	private boolean checkConsistency(BeliefBase bb , Belief newBelief) {
		// TODO Auto-generated method stub
		ArrayList<Expression> temp = bb.getBeliefExpressionList() ; 
		temp.add(newBelief.getExpression()) ; 
		CNF tempCNF =  (CNF) ExpressionFactory.createMultiary(CNF.operator, temp) ; 
		//if(tempCNF.isAlwaysFalse())
			//tempCNF.getTruthTable().print();  
		return ! tempCNF.isAlwaysFalse() ;  // if always false , inconsistent
	}

	/**
	 * print this belief base in this format
	 * <pre>
	 * ------------print belief base-----------------\n
	 * belief	order\n
	 * belief 	order\n
	 * ...
	 * belief 	order\n
	 * </pre>
	 */
	public void print() {
		// TODO Auto-generated method stub
		StringBuilder sb= new StringBuilder(); 
		sb.append("------------print belief base-----------------\n" ) ; 
		for(Belief b : beliefs)
			sb.append(b.toString()).append("\n") ; 
		System.out.println(sb.toString()) ; 
	}
	
	public  ArrayList<Expression> getBeliefExpressionList(){
		ArrayList<Expression> temp = new ArrayList<Expression>() ; 
		for(Belief belief : beliefs)
			temp.add(belief.getExpression()) ; 
		return temp ; 
	}
	
	public void clear() { 
		System.out.println("clear belief base...") ; 
		beliefs.clear();
	}
	
	public boolean entail(Expression e ) {
		Expression cnf = ExpressionFactory.createMultiary(CNF.operator, getBeliefExpressionList()) ; 
		return Expression.logicalEntailment(cnf, e) ; 
	}
	
	public void removeBelief(Belief b) {
		this.beliefs.remove(b) ; 
	}

	
	public void contract(Expression expression) {
		Expression temp = ExpressionFactory.createUnary(NOT.operator, expression) ; 
		Belief tempBelief = new Belief(temp) ; 
		tempBelief.setOrder(11);
		this.add(tempBelief);
		this.removeBelief(tempBelief);
	}
}
