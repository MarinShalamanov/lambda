package com.marinshalamanov.lambda.ast;

import java.util.Set;

public class Application extends LambdaExpression {
	private LambdaExpression left, right;
	
	public Application(LambdaExpression left, LambdaExpression right) {
		this.left = left;
		this.right = right;
	}
	
	public Application(Application app) {
		this.left = app.left;
		this.right = app.right;
	}
	
	private String parenthesize(String str) {
		if (str.length() > 1) 
			return '(' + str + ')';
		else 
			return str;
	}
	
	public LambdaExpression getLeft() {
		return left;
	}
	
	public LambdaExpression getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		String leftStr = parenthesize(left.toString());
		String rightStr = parenthesize(right.toString());
		return leftStr + rightStr;
	}

	@Override
	public Set<Variable> getVariableSet() {
		Set<Variable> variableSet = left.getVariableSet();
		variableSet.addAll(right.getVariableSet());
		return variableSet;
	}

	@Override
	public Set<Variable> getFreeVariableSet() {
		Set<Variable> variableSet = left.getFreeVariableSet();
		variableSet.addAll(right.getFreeVariableSet());
		return variableSet; 
	}

	@Override
	public Set<Variable> getBoundedVariableSet() {
		Set<Variable> variableSet = left.getBoundedVariableSet();
		variableSet.addAll(right.getBoundedVariableSet());
		return variableSet; 
	}

	@Override
	public LambdaExpression substitute(Variable var, LambdaExpression expr) {
		return new Application(left.substitute(var, expr), 
				right.substitute(var, expr));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Application) {
			Application o = (Application) obj;
			return left.equals(o.left) && right.equals(o.right);
		} else {
			return false;
		}
	}
}
