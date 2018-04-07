package com.marinshalamanov.lambda;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;
import com.marinshalamanov.lambda.nameless.Converter;

public class Main {
	public static void main(String[] args) {
		Variable x = new Variable('x');
		Variable y = new Variable('y');

		Variable z = new Variable('z');
		
		LambdaExpression e = new Application(new Application(y, new Abstraction(x, new Application(new Application(x, y), z))), z);
		System.out.println(e);
		
		System.out.println(new Converter().flat(e));
	}
}
