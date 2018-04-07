package com.marinshalamanov.lambda.reductions;

import com.marinshalamanov.lambda.ast.LambdaExpression;

public abstract class Reductor {
	/*default*/ abstract LambdaExpression reduce(LambdaExpression expr);
	
	public LambdaExpression fullReduce(LambdaExpression expr) {
		LambdaExpression last, curr;
		curr = expr;
		
		do {
			last = curr;
			curr = reduce(curr);
		} while (!last.equals(curr));
		
		return curr;
	}
}
