package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import expression.CNF;
import expression.Expression;
import expression.ExpressionFactory;
import expression.NOT;
import expression.Operator;
import expression.Variable;
import expression.binaryExpression.AND;
import expression.binaryExpression.OR;

class ExpressionTest {

	//@Test
	void refreshTest() {
		System.out.println("---------------refresh test -------------") ; 
		Variable A = new Variable("A") ; 
		Variable B = new Variable("B") ; 
		Variable A2 = new Variable("A") ; 
		assertTrue(A.equals(A2)) ; 
		assertTrue(A2.equals(A)) ; 
		assertTrue(A2.getVariables().contains(A2)) ;
		assertTrue(A2.getVariables().contains(A)) ;
		assertFalse(A2.getVariables().contains(B)) ; 
		
		A.printVariables() ; 
		B.printVariables();
		assertTrue(A.getValue() && B.getValue()); 
		AND AandB = new AND ( A ,B)  ;
		assertTrue(AandB.getVariables().contains(A) && AandB.getVariables().contains(B)) ; 
		assertTrue(A.equals(A2)) ; 
		assertTrue(AandB.getValue())  ;
		AandB.printVariables();
		
		
		System.out.println("Set A to false ") ; 
		A.setValue(false );
		assertFalse(A.getValue()) ; 
		System.out.println(A.getValue()); 
		A.printVariables() ; 
		B.printVariables();
		AandB.printVariables();
		assertFalse(AandB.getValue()) ; 
		System.out.println("A and B value : " + AandB.getValue()) ; 
		
		AND AandAandB = new AND(A, AandB)  ;
		AandAandB.printVariables();
		AandAandB.getExpression(1).printVariables();
		AandAandB.getExpression(2).printVariables();
	}
	
	@Test 
	void refreshTest2 () {
		Variable A = new Variable("A") ; 
		Variable A2 = new Variable("A") ; 
		AND AA  = new AND (A, A2) ; 
		AA.printVariables();
		A.setValue(false);
		AA.printVariables();
		A2.setValue(false);
		AA.printVariables();
		A.printVariables();
		A2.printVariables();
		AA.getExpression(1).printVariables();
		AA.getExpression(2).printVariables();
		
		System.out.println(AA.getValue()) ; 
	}
	
	@Test
	void CNFTest() {
		System.out.println("----------------CNF TEST------------------") ; 
		Expression test = ExpressionFactory.createExpression("(A || D) && ((B || !C) || D) && (A && D) && (A || D) && ((B || !C) || D) && (A && D) && (A || D) && ((B || !C) || D) && (A && D) && (A || D) && ((B || !C) || D) && (A && D) && (A || D) && ((B || !C) || D) && (A && D)") ; 
		test.getTruthTable().print();
		test.printVariables();;  
		((CNF)test).printExpressionList(); ; 
	}
	
	@Test
	void createExpressionTest()	{
		System.out.println("-----------------------create expression test -----------------------") ; 
		Expression nota = ExpressionFactory.createExpression("!A") ; 
		System.out.println(nota.toString()) ; 
		Expression aandb = ExpressionFactory.createExpression("A && B") ; 
		System.out.println(aandb.toString()) ; 
		Expression e1 = ExpressionFactory.createExpression("A || D") ; 
		System.out.println(e1.toString()) ; 
		Expression e2 = ExpressionFactory.createExpression("(A || D) && ((B || !C) || D) && (A && D)") ; 
		Expression e3 = ExpressionFactory.createExpression("!A && D") ; 
		System.out.println(e1.toString()) ; 
		System.out.println(e2.toString()) ; 
	}
	
	@Test
	void impliesTest()	{
		System.out.println ("---------implies test------------");
		Expression aimpliesb = ExpressionFactory.createExpression("(A -> B) && ((B -> A) -> C )  ") ; 
		System.out.println(aimpliesb.toString()) ; 
		aimpliesb = ExpressionFactory.createExpression(" A -> B") ; 
		aimpliesb.getTruthTable().print();
	}
	
	@Test
	void IFFtest() {
		System.out.println ("---------IFF test------------");
		Expression ab = ExpressionFactory.createExpression(" A <-> B" ) ; 
		System.out.println(ab.toString()) ; 
		ab.getTruthTable().print()  ;
		ab.printVariables();
	}
	
	@Test
	void entailmentTest() {
		System.out.println("--------entail test--------------")  ; 
		Expression A = ExpressionFactory.createExpression(" A ") ; 
		//assertTrue(Expression.logicalEntailment(A, A)) ; 
		Expression ab = ExpressionFactory.createExpression(" A <-> B" ) ; 
		Expression aimpliesb = ExpressionFactory.createExpression(" A -> B" ) ; 
		Expression bimpliesa = ExpressionFactory.createExpression(" B -> A" ) ; 
		assertTrue(Expression.logicalEntailment(ab, aimpliesb)) ; 
		assertTrue(Expression.logicalEntailment(ab, bimpliesa)) ; 
	}
	
	

}
