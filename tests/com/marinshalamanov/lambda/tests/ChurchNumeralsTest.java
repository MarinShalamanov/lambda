package com.marinshalamanov.lambda.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.lambda.Combinators;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;

class ChurchNumeralsTest {

	@Test
	void test1Plus2() {
		LambdaExpression c3 = Combinators.C(3);
		LambdaExpression c2 = Combinators.C(2);
		LambdaExpression c1 = Combinators.C(1);
		
		LambdaExpression result = new Application(new Application(Combinators.plus(), c1), c2);
		
		assertTrue(c3.betaEquivalent(result));
	}
	
	@Test
	void test7Times8() {
		LambdaExpression c7 = Combinators.C(7);
		LambdaExpression c8 = Combinators.C(8);
		LambdaExpression c56 = Combinators.C(56);
		
		LambdaExpression result = new Application(new Application(Combinators.mult(), c7), c8);
		
		assertTrue(c56.betaEquivalent(result));
	}

}
