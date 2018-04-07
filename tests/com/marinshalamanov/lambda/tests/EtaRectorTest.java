package com.marinshalamanov.lambda.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.marinshalamanov.lambda.ASTParser;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.reductions.EtaReductor;

class EtaRectorTest {

	@Test
	void test() {
		LambdaExpression e1 = new ASTParser().parse("\\x.@fx");
		LambdaExpression expected = new ASTParser().parse("f");
		assertEquals(expected, EtaReductor.fullEtaReduce(e1));
	}

}
