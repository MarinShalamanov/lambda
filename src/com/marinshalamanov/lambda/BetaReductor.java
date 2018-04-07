package com.marinshalamanov.lambda;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;

public class BetaReductor {
	
	/**
	 * Performs a single step of beta reduction.
	 * @param expr
	 * @return 
	 */
	private static LambdaExpression betaReduce(LambdaExpression expr) {
		if (expr instanceof Application) {
			Application application = (Application) expr;
			if (application.getLeft() instanceof Abstraction) {
				Abstraction left = (Abstraction) application.getLeft();
				LambdaExpression right = application.getRight();
				Variable var = left.getArgument();
				LambdaExpression body = left.getBody();
				body = betaReduce(body);
				LambdaExpression result = body.substitute(var, right);
				return result;
			} else {
				LambdaExpression left = betaReduce(application.getLeft());
				LambdaExpression right = betaReduce(application.getRight());
				return new Application(left, right);
			}
		} else if (expr instanceof Abstraction) {
			Abstraction abst = (Abstraction) expr;
			Variable arg = abst.getArgument();
			LambdaExpression body = betaReduce(abst.getBody());
			return new Abstraction(arg, body);
		} else { // Variable
			return expr;
		}
	}
	
	/**
	 * Tries to fully reduce the given lambda expression. 
	 * 
	 * @param expr
	 * @return
	 */
	public static LambdaExpression fullBetaReduce(LambdaExpression expr) {
		LambdaExpression last, curr;
		curr = expr;
		
		do {
			last = curr;
			curr = betaReduce(curr);
		} while (!last.equals(curr));
		
		return curr;
	}
}
