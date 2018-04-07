package com.marinshalamanov.lambda.reductions;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;

public class EtaReductor {
	
	/**
	 * Performs a single step of beta reduction.
	 * @param expr
	 * @return 
	 */
	/*default*/ LambdaExpression reduce(LambdaExpression expr) {
		if (expr instanceof Application) {
			Application application = (Application) expr;
			LambdaExpression left = reduce(application.getLeft());
			LambdaExpression right = reduce(application.getRight());
			return new Application(left, right);
		} else if (expr instanceof Abstraction) {
			Abstraction abst = (Abstraction) expr;
			Variable arg = abst.getArgument();
			LambdaExpression body = reduce(abst.getBody());
			
			if (body instanceof Application) {
				Application bodyApp = (Application) body;
				if (bodyApp.getRight().equals(arg)) {
					LambdaExpression left = bodyApp.getLeft();
					left = reduce(left);
					return left;
				}
			}
			return new Abstraction(arg, body);
		} else { // Variable
			return expr;
		}
	}
}
