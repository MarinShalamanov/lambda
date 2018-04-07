package com.marinshalamanov.lambda.ast;

import java.util.Set;

public class Abstraction extends LambdaExpression {
	private Variable argument;
	private LambdaExpression body;
	
	public Abstraction(Variable argument, LambdaExpression expression) {
		this.argument = argument;
		this.body = expression;
	}
	
	public Abstraction(Abstraction abs) {
		this.argument = abs.argument;
		this.body = abs.body;
	}
	
	public Variable getArgument() {
		return argument;
	}
	
	public LambdaExpression getBody() {
		return body;
	}
	
	public Abstraction renameArgument(Variable newArg) {
		return new Abstraction(newArg, body.substitute(argument, newArg));
	}
	
	@Override
	public LambdaExpression substitute(Variable var, LambdaExpression expr) {
		if (var.equals(argument)) {
			return this;
		} else {
			if (expr.getFreeVariableSet().contains(argument)) {
				Set<Variable> vars = expr.getVariableSet();
				vars.addAll(body.getVariableSet());
				
				Variable newVar = VariableSet.getInstance().getNewVariable(vars);
				return this.renameArgument(newVar).substitute(var, expr);
			} else {
				return new Abstraction(argument, body.substitute(var, expr));
			}
		}
	}
	
	@Override
	public String toString() {
		return "\\" + argument + "." + body;
	}

	@Override
	public Set<Variable> getVariableSet() {
		Set<Variable> variableSet = body.getVariableSet();
		variableSet.add(argument);
		return variableSet;
	}

	@Override
	public Set<Variable> getFreeVariableSet() {
		Set<Variable> variableSet = body.getFreeVariableSet();
		variableSet.remove(argument);
		return variableSet;
	}

	@Override
	public Set<Variable> getBoundedVariableSet() {
		Set<Variable> variableSet = body.getBoundedVariableSet();
		variableSet.add(argument);
		return variableSet;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Abstraction) {
			Abstraction o = (Abstraction) obj;
			return o.argument.equals(argument) && o.body.equals(body);
		} else {
			return false;
		}
	}
}
