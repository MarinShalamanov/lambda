package com.marinshalamanov.lambda.ast;

import java.util.Set;

import com.marinshalamanov.lambda.nameless.Converter;

public abstract class LambdaExpression {
	
	public abstract Set<Variable> getVariableSet();
	
	public abstract Set<Variable> getFreeVariableSet();
	
	public abstract Set<Variable> getBoundedVariableSet();
	
	public abstract LambdaExpression substitute(Variable var, LambdaExpression expr);
	
	public boolean equivalent(LambdaExpression expr) {
		return this.equals(expr);
	}
	
	public boolean alphaEquivalent(LambdaExpression expr) {		
		return Converter.flat(this).equals(Converter.flat(expr));
	}
	
}
