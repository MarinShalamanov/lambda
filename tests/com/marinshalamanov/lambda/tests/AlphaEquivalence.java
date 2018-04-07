package com.marinshalamanov.lambda.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;

class AlphaEquivalence {

	@Test
	void testEq() {
		Variable x = new Variable('x');
		Variable y = new Variable('y');
		Variable z = new Variable('z');
		Variable a = new Variable('a');
		
		// (y(\x.xyz))z
		LambdaExpression e1 = new Application(new Application(y, new Abstraction(x, new Application(new Application(x, y), z))), z);
		
		// (y(\a.ayz))z
		LambdaExpression e2 = new Application(new Application(y, new Abstraction(a, new Application(new Application(a, y), z))), z);
		
		assertTrue(e1.alphaEquivalent(e2));
	}
	
	@Test
	void testNotEq() {
		Variable x = new Variable('x');
		Variable y = new Variable('y');
		Variable z = new Variable('z');
		Variable a = new Variable('a');
		
		// (y(\x.xyz))z
		LambdaExpression e1 = new Application(new Application(y, new Abstraction(x, new Application(new Application(x, y), z))), z);
		
		// (z(\x.xyz))y
		LambdaExpression e2 = new Application(new Application(z, new Abstraction(x, new Application(new Application(x, y), z))), y);
		
		assertFalse(e1.alphaEquivalent(e2));
	}
}
