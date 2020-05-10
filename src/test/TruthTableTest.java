package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import expression.Expression;
import expression.ExpressionFactory;
import expression.NOT;
import expression.TruthTable;
import expression.Variable;
import expression.binaryExpression.AND;
import expression.binaryExpression.OR;

public class TruthTableTest {
	
	@Test
	public void test1() { 
		Variable A = ExpressionFactory.createVariable("A") ; 
		TruthTable t1 = new TruthTable(A) ; 
		t1.print();
		
	}
	
	@Test 
	public void test2 () {
		Variable A = ExpressionFactory.createVariable("A") ; 
		Variable B =  ExpressionFactory.createVariable("B") ; 
		
		
		
	}
	
}
