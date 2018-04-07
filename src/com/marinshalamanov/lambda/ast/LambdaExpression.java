package com.marinshalamanov.lambda.ast;

import java.util.Set;

import com.marinshalamanov.lambda.nameless.Converter;
import com.marinshalamanov.lambda.reductions.BetaEtaReductor;
import com.marinshalamanov.lambda.reductions.BetaReductor;

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
	
	public boolean betaEquivalent(LambdaExpression expr) {
		// TODO: Proof implementation is correct
		BetaReductor beta = new BetaReductor();
		LambdaExpression thisReduced = beta.fullReduce(this);
		LambdaExpression exprReduced = beta.fullReduce(expr);
		return thisReduced.alphaEquivalent(exprReduced);
	}
	
	public boolean betaEtaEquivalent(LambdaExpression expr) {
		// TODO: Proof implementation is correct
		BetaEtaReductor betaEta = new BetaEtaReductor();
		LambdaExpression thisReduced = betaEta.fullReduce(this);
		LambdaExpression exprReduced = betaEta.fullReduce(expr);
		return thisReduced.alphaEquivalent(exprReduced);
	}
}
