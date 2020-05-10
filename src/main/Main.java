package main;

import java.util.Scanner;

import belief.Belief;
import belief.BeliefBase;
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
			String choice = scan. nextLine() ; 
			switch (choice ) { 
			case "1" : 
				System.out.println("Feed me belief : ") ; 
				String beliefString = scan.nextLine()	 ; 
				System.out.println("order (between 1 to 10 ) :  ") ; 
				String orderString = scan.nextLine() ; 
				int order = Integer.parseInt(orderString) ; 
 				beliefBase.add(new Belief(beliefString, order))  ;
				break ; 
			case "2" : 
				System.out.println("Feed me belief : ") ; 
				beliefString = scan.nextLine()	 ; 
				beliefBase.entail(ExpressionFactory.createExpression(beliefString)) ; 
				break ; 
			case "3" : 
				break ; 
			case "4" : 
				beliefBase.print();
				break ;
			case "5" : 
				break ; 
			default: 
				System.out.println("no such option, try again") ; 
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
        System.out.println("5. Exit");
        System.out.println("-------------------------------");
	}
}
