package main;

import java.util.Scanner;

import belief.Belief;
import belief.BeliefBase;
import expression.CNF;
import expression.ExpressionFactory;

public class Main {
	public static void main(String[] args ) {
		showMenu() 	; 
	}

	private static void showMenu() {
		// TODO Auto-generated method stub
		BeliefBase beliefBase = new BeliefBase ()  ;
		Scanner scan = new Scanner(System .in ) ; 
		boolean loop = true ; 
		while( loop ) {
			printMenu() ; 
			try {
				String choice = scan. nextLine() ; 
				switch (choice ) { 
				case "1" : 
					System.out.println("Feed me belief : ") ; 
					String beliefString = scan.nextLine()	 ; 
					System.out.println("order (between 1 to 10 ) :  ") ; 
					String order = scan.nextLine() ; 						
	 				beliefBase.add(new Belief(beliefString, order.isEmpty() ? 10 : Integer.parseInt(order)))  ;
					break ; 
				case "2" : 
					System.out.println("Feed me belief : ") ; 
					beliefString = scan.nextLine()	 ; 
					if(beliefBase.entail(ExpressionFactory.createExpression(beliefString))) {
						System.out.println("logical entailment is true ")	 ; 
					}else 
						System.out.println("no logical entailment") ; 
					break ; 
				case "3" : 
					
					System.out.println("Enter belief for contraction: ") ; 
					beliefString = scan.nextLine() ; 
					beliefBase.contract(ExpressionFactory.createExpression(beliefString)) ; 
					break ; 
				case "4" : 
					beliefBase.print();
					break ;
				case "5" :
					if(beliefBase.getBeliefs().size() == 0 ) {
						System.out.println("no belief...") ; 
						break ;
					}
					ExpressionFactory.createMultiary(CNF.operator, beliefBase.getBeliefExpressionList()).getTruthTable().print() ; 
					break ;
				case "6" : 
					System.out.println("good bye ") ; 
					return ; 
				case "Test"  :
					printTestExpression()  ;
					break ; 
				default: 
					System.out.println("no such option, try again") ; 
				}
			}catch(Exception e )
			{
				e.printStackTrace();
			}
			
		}
		scan.close() ; 
	}

	private static void printMenu() {
		System.out.println("-----Menu----------------------");
        System.out.println("1. Feed new belief");
        System.out.println("2. check for logical entailment");
        System.out.println("3. contraction of belief base");
        System.out.println("4. print belief base")  ;
        System.out.println("5. print Truth Table") ; 
        System.out.println("6. Exit");
        System.out.println("-------------------------------");
	}
	
	private static void printTestExpression()	{
		StringBuilder sb = new StringBuilder()  ;
		for(int i = 0 ; i < 20 ; i++ ) {
			sb.append("A" + i + " && ") ; 
		}
		sb.append("BB" ) ; 
		System.out.println(sb.toString())  ;
	}
}
