package com.marinshalamanov.lambda.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.lambda.ASTParser;
import com.marinshalamanov.lambda.Combinators;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.sun.javafx.css.Combinator;

class ASTParserTest {

	@Test
	void testIdentity() {
		LambdaExpression real = new ASTParser().parse("\\x.x");
		assertTrue(Combinators.I().alphaEquivalent(real));
	}
	
	@Test
	void testC1() {
		LambdaExpression real = new ASTParser().parse("\\f.\\x.@fx");
		assertTrue(Combinators.C(1).alphaEquivalent(real));
	}
	
	@Test
	void testC0() {
		LambdaExpression real = new ASTParser().parse("\\f.\\x.x");
		assertTrue(Combinators.C(0).alphaEquivalent(real));
	}

}
