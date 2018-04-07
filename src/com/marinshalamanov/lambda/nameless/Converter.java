package com.marinshalamanov.lambda.nameless;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;
import com.marinshalamanov.lambda.ast.VariableSet;

public class Converter {
	
	public static NamelessLambdaExpr flat(LambdaExpression expr) {
		Set<Variable> set = VariableSet.getInstance().getSet();
		
		Set<Variable> free = expr.getFreeVariableSet();
		set.removeAll(free);
		
		List<Variable> context = new ArrayList<>(set);
		context.addAll(free);
		
		return flat(expr, context);
	}
	
	public static NamelessLambdaExpr flat(LambdaExpression expr, List<Variable> context) {
		if (expr instanceof Variable) {
			Variable var = (Variable) expr;
			int deBrojnIdx = context.size() - context.indexOf(var) - 1;
			return new NamelessLambdaExpr(new NamelessVar(deBrojnIdx));
		} else if(expr instanceof Application) {
			Application app = (Application)expr;
			NamelessLambdaExpr left = flat(app.getLeft(), context);
			NamelessLambdaExpr right = flat(app.getRight(), context);
			
			ArrayList<NamelessToken> newTokens = new ArrayList<>();
			
			newTokens.add(Parenthesis.open);
			newTokens.addAll(left.getTokens());
			newTokens.addAll(right.getTokens());
			newTokens.add(Parenthesis.closed);
			
			NamelessLambdaExpr res = new NamelessLambdaExpr(newTokens);
			return res;
		} else if(expr instanceof Abstraction) {
			Abstraction abstraction = (Abstraction) expr;
			
			Variable arg = abstraction.getArgument();
			
			List<Variable> newContext = new ArrayList<>(context);
			newContext.remove(arg);
			newContext.add(arg);
			
			List<NamelessToken> bodyTokens = 
					flat(abstraction.getBody(), newContext).getTokens();
			
			//increaseDeBrojnIndex(bodyTokens, 1);
			
			ArrayList<NamelessToken> tokens = new ArrayList<>();
			tokens.add(LambdaToken.getInstance());
			tokens.addAll(bodyTokens);
			
			return new NamelessLambdaExpr(tokens);
		} else {
			throw new IllegalStateException("Unkown lambda expression token");
		}
	}
	
	public static LambdaExpression sharp(NamelessLambdaExpr expr) {
		Set<Variable> set = VariableSet.getInstance().getSet();
		List<Variable> context = new ArrayList<>(set);
		return sharp(expr, context);
	}
	
	public static LambdaExpression sharp(NamelessLambdaExpr expr, List<Variable> context) {
		List<NamelessToken> tokens = expr.getTokens();
		
		// Count the number of lambdas before each variable
		List<Integer> numLambdas = new ArrayList<>(tokens.size());
		int lambdas = 0;
		Stack<Integer> openParenthesis = new Stack<>();
		int openPars = 0;
		for (int i = 0; i < tokens.size(); i++) {
			NamelessToken token = tokens.get(i);
			if (token instanceof LambdaToken) {
				numLambdas.set(i, lambdas+1);
				if (tokens.get(i+1) instanceof NamelessVar) {
					numLambdas.set(i+1, lambdas+1);
					i++;
				} else {
					lambdas++;
					if (openPars > 0) {
						openParenthesis.push(openPars);
					}
				}
			} else if (token == Parenthesis.open) {
				openPars++;
			} else if(token == Parenthesis.closed) {
				openPars--;
				if (openPars == 0) lambdas--;
			} else if (token instanceof NamelessVar) {
				numLambdas.set(i, lambdas);
			}
		}
		
		
		// Construct the lambda AST
		Stack<LambdaExpression> st = new Stack<>();
		
		for (int i = tokens.size()-1; i >= 0; i--) {
			NamelessToken token = tokens.get(i);
			
			if (token == Parenthesis.closed) continue;
			else if(token == Parenthesis.open) {
				LambdaExpression left = st.pop();
				LambdaExpression right = st.pop();
				
				st.push(new Application(left, right));
			} else if(token == LambdaToken.getInstance()) {
				LambdaExpression body = st.pop();
				
				int idx = 0 - numLambdas.get(i);
				if (idx <= 0) idx += context.size();
				Variable namedVar = context.get(context.size() - idx);
				
				LambdaExpression application = new Abstraction(namedVar, body);
				st.push(application);
			} else {
				NamelessVar var = (NamelessVar) token;
				int idx = var.getDeBrojnIndex() - numLambdas.get(i);
				if (idx <= 0) idx += context.size();
				Variable namedVar = context.get(context.size() - idx);
				st.push(namedVar);
			}
		}
		
		if (st.size() != 1) {
			throw new IllegalStateException();
		}
		
		return st.get(0);
	}
	
	private void increaseDeBrojnIndex(List<NamelessToken> tokens , int increase) {
		for (NamelessToken token : tokens) {
			if(token instanceof NamelessVar) {
				NamelessVar var = (NamelessVar)token;
				var.setDeBrojnIndex(var.getDeBrojnIndex() + 1);
			}
		}
	}
}
