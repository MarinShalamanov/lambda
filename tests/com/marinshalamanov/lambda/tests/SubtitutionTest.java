package com.marinshalamanov.lambda.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;

class SubtitutionTest {

	@Test
	void testFreeVariableWithCollision() {
		Variable x = new Variable('x');
		Variable y = new Variable('y');
		Variable z = new Variable('z');
		Variable a = new Variable('a');
		
		LambdaExpression expr = new Abstraction(x, new Application(y, x));
		LambdaExpression substitutedReal = expr.substitute(y, x);
		
		LambdaExpression substitutedExpected = new Abstraction(a, new Application(x, a));
		
		assertEquals(substitutedExpected, substitutedReal);
	}
	
	@Test
	void testSubstitueFreeVariable() {
		Variable x = new Variable('x');
		Variable y = new Variable('y');
		Variable z = new Variable('z');
		
		LambdaExpression expr = new Abstraction(x, new Application(y, x));
		LambdaExpression substitutedReal = expr.substitute(y, z);
		
		LambdaExpression substitutedExpected = new Abstraction(x, new Application(z, x));
		
		assertEquals(substitutedExpected, substitutedReal);
	}
	
	@Test
	void testSubsitueBoundedVar() {
		Variable x = new Variable('x');
		Variable y = new Variable('y');
		
		LambdaExpression expr = new Abstraction(x, new Application(y, x));
		LambdaExpression substitutedReal = expr.substitute(x, y);
		
		assertEquals(expr, substitutedReal);
	}

}
