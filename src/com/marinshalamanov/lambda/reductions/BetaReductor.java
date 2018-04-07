package com.marinshalamanov.lambda.reductions;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;

public class BetaReductor extends Reductor {
	
	/**
	 * Performs a single step of beta reduction.
	 * @param expr
	 * @return 
	 */
	@Override
	/*default*/ LambdaExpression reduce(LambdaExpression expr) {
		if (expr instanceof Application) {
			Application application = (Application) expr;
			if (application.getLeft() instanceof Abstraction) {
				Abstraction left = (Abstraction) application.getLeft();
				LambdaExpression right = application.getRight();
				Variable var = left.getArgument();
				LambdaExpression body = left.getBody();
				body = reduce(body);
				LambdaExpression result = body.substitute(var, right);
				return result;
			} else {
				LambdaExpression left = reduce(application.getLeft());
				LambdaExpression right = reduce(application.getRight());
				return new Application(left, right);
			}
		} else if (expr instanceof Abstraction) {
			Abstraction abst = (Abstraction) expr;
			Variable arg = abst.getArgument();
			LambdaExpression body = reduce(abst.getBody());
			return new Abstraction(arg, body);
		} else { // Variable
			return expr;
		}
	}
}
