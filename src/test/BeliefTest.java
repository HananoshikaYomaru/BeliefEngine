package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import belief.Belief;
import belief.BeliefBase;
import expression.CNF;
import expression.Expression;
import expression.ExpressionFactory;
import expression.NOT;
import expression.Variable;
import expression.binaryExpression.AND;
import expression.binaryExpression.OR;

public class BeliefTest {
	@Test
	void test1 () {
		BeliefBase bb = new BeliefBase()  ;
		bb.add(new Belief("A <-> B" , 10 ));
		bb.print();
		bb.add(new Belief("!B" , 5) );
		bb.print() ; 
		bb.add(new Belief("B" , 10 ));
		bb.print();
		
		bb.clear();
		bb.add(new Belief(" A <-> B", 10 )  );
		bb.add(new Belief("! (A <-> B)"));
		bb.print() ; 
	}
}
