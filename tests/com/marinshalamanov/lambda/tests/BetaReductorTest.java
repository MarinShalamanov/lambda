package com.marinshalamanov.lambda.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.lambda.Combinators;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.reductions.BetaReductor;

class BetaReductorTest {

	@Test
	void test() {
		LambdaExpression omegaReduced = BetaReductor.fullBetaReduce(Combinators.omega());
		
		assertTrue(omegaReduced.alphaEquivalent(Combinators.omega()));
	}

}
