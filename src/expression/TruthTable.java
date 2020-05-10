package expression;

import java.util.ArrayList;
import java.util.Comparator;
import annotation.Done;
import expression.constantExpression.FalseExpression;
import expression.constantExpression.TrueExpression;


@Done 
public class TruthTable {
	private Expression e ; 
	private ArrayList<Variable> variables = new ArrayList<Variable >() ; 
	boolean[][] content ; 
	String stringFormat ; 
	
	//cache 
	private int col ; 
	private int row ; 
	private int index = 0 ; 
	
	/**
	 * <pre> 
	 * if the expression is a constant expression 
	 * the content and string format will simply be true or false 
	 * </pre>
	 * @param e
	 */
	public TruthTable(Expression e) {
		this.e = e ; 
		
		//constant Expression 
		if(e instanceof TrueExpression || e instanceof FalseExpression )
		{
			content = new boolean [1][1] ; 
			content[0][0] = e instanceof TrueExpression ? true : false ; 
			stringFormat = convertToString() ; 
			return ; 
		}
		
		for(Variable v : e.variables)
			this.variables.add(v) ; 
		this.variables.sort(new Comparator<Variable>() {

			@Override
			public int compare(Variable o1, Variable o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		col = this.variables.size() + 1  ; 
		row = (int) Math.pow(2, this.variables.size()) ; 
		assert row % 2 == 0 : "type casting problem , row % 2 =" + row %2 + "but not 0"  ;
		this.content = new boolean[row][col] ; 
		fillTable( ) ; 
		stringFormat = convertToString() ; 
	}
	
	private void fillTable() {
		//by default, all value of variable is true 
		setAllVariablesTrue () ; 
		changeIndexValue(0, true); 
		changeIndexValue(0, false) ; 
		setAllVariablesTrue();
	}
	
	private void changeIndexValue(int index, boolean value ) {
		variables.get(index).value = value ; 
		if(index == variables.size() - 1) {
			fillRow() ; 
		}else
		{
			changeIndexValue(index + 1 , true ) ; 
			changeIndexValue(index + 1 , false); 
		}
	}
	
	private void fillRow() {
		for(int i = 0 ; i < this.variables.size()  ; i++)
			content[index][i] = variables.get(i).getValue() ; 
		content[index][col - 1 ] = e.getValue() ; 
		index++ ; 
	}

	private String convertToString() {
		StringBuilder sb = new StringBuilder() ; 
		for(Variable v : variables ) {
			sb.append(v.getName()).append("\t") ; 
		}
		sb.append("Expression\n") ; 
		for(int i = 0 ; i < content.length ; i++ ) {
			for(int j = 0 ; j < content[0].length ; j++)
				sb.append(content[i][j]).append("\t") ; 
			sb.append("\n") ; 
		}
		return sb.toString() ; 
	}

	@Override 
	public boolean equals (Object o ) {
		if (!( o instanceof TruthTable) )
			return false ; 
		TruthTable temp = (TruthTable ) o ; 
		return temp.stringFormat.equals(stringFormat ) ; 
	}
	
	@Override 
	public int hashCode() {
		return stringFormat.hashCode() ; 
	}
	
	@Override
	public String toString() { 
		return stringFormat ; 
	}
	
	public void print() {
		System.out.println(stringFormat) ; 
	}
	
	public boolean isAlwaysTrue() {
		boolean result = true ; 
		for(int i = 0 ; i < row ; i++)
			result &= content[i][col-1 ] ; 
		return result ; //return true if all values are true 
	}
	
	public boolean isAlwaysFalse() {
		boolean result = false ; 
		for(int i = 0 ; i < row ; i++)
			result |= content[i][col -1 ];  
		return !result ; //return true if all values are  false 
	}
	
	public void setAllVariablesTrue() {
		for(Variable v : this.variables ) 
			v.setValue(true);
	}
}
