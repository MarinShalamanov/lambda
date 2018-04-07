package com.marinshalamanov.lambda.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.lambda.Combinators;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.reductions.BetaReductor;

class BetaReductorTest {

	@Test
	void test() {
		BetaReductor beta = new BetaReductor();
		LambdaExpression omegaReduced = beta.fullReduce(Combinators.omega());
		
		assertTrue(omegaReduced.alphaEquivalent(Combinators.omega()));
	}

}
