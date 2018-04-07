package com.marinshalamanov.lambda.ast;

import java.util.HashSet;
import java.util.Set;

public class Variable extends LambdaExpression {
	private char symbol;
	
	public Variable(char symbol) {
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	@Override
	public String toString() {
		return String.valueOf(symbol);
	}
	
	@Override
	public LambdaExpression substitute(Variable var, LambdaExpression expr) {
		if (this.equals(var)) {
			return expr;
		} else {
			return this;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Variable) {
			return ((Variable)obj).getSymbol() == this.getSymbol(); 
		} else {
			return false;
		}
	}

	@Override
	public Set<Variable> getVariableSet() {
		HashSet<Variable> set = new HashSet<>();
		set.add(this);
		return set;
	}
	
	@Override
	public int hashCode() {
		return Character.hashCode(symbol);
	}

	@Override
	public Set<Variable> getFreeVariableSet() {
		return getVariableSet();
	}

	@Override
	public Set<Variable> getBoundedVariableSet() {
		return new HashSet<Variable>();
	}
}
