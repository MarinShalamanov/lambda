package com.marinshalamanov.lambda.reductions;

import com.marinshalamanov.lambda.ast.LambdaExpression;

public class BetaEtaReductor extends Reductor {

	private BetaReductor beta = new BetaReductor();
	private EtaReductor eta = new EtaReductor();
	
	@Override
	LambdaExpression reduce(LambdaExpression expr) {
		expr = beta.reduce(expr);
		expr = eta.reduce(expr);
		return expr;
	}
}
