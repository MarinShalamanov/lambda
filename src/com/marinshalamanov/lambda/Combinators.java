package com.marinshalamanov.lambda;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;

public class Combinators {
	public static LambdaExpression I() {
		Variable x = new Variable('x');
		return new Abstraction(x, x);
	}
	
	public static LambdaExpression omega() {
		return new ASTParser().parse("\\x.@xx");
	}
	
	public static LambdaExpression plus() {
		return new ASTParser().parse("\\m.\\n.\\f.\\x.@@mf@@nfx");
	}
	
	public static LambdaExpression mult() {
		return new ASTParser().parse("\\m.\\n.\\f.@m@nf");
	}
	
	public static LambdaExpression C(int number) {
		Variable x = new Variable('x');
		Variable f = new Variable('f');
		
		LambdaExpression body = iterate(f, x, number);
		return new Abstraction(f, new Abstraction(x, body));
	}
	
	// Returns f^n x
	private static LambdaExpression iterate(LambdaExpression f, 
			LambdaExpression x, int count) {
		
		LambdaExpression result = x;
		for(int i = 0; i < count; i++) {
			result = new Application(f, result);
		}
		
		return result;
	}
	
}
