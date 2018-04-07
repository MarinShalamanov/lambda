package com.marinshalamanov.lambda;

import java.util.Stack;

import com.marinshalamanov.lambda.ast.Abstraction;
import com.marinshalamanov.lambda.ast.Application;
import com.marinshalamanov.lambda.ast.LambdaExpression;
import com.marinshalamanov.lambda.ast.Variable;

public class ASTParser {
	
	public LambdaExpression parse(String str) {
		char[] tokens = str.toCharArray();
		
		Stack<LambdaExpression> st = new Stack<>();
		for(int i = tokens.length-1; i >= 0; i--) {
			if ('a' <= tokens[i] && tokens[i] <= 'z') {
				LambdaExpression e = new Variable(tokens[i]);
				st.push(e);
			} else if (tokens[i] == '@') {
				LambdaExpression left = st.pop();
				LambdaExpression right = st.pop();
				st.push(new Application(left, right));
			} else if(tokens[i] == '\\') {
				Variable arg = (Variable)st.pop();
				LambdaExpression body = st.pop();
				st.push(new Abstraction(arg, body));
			} else if(tokens[i] == '.') {
				// ignore
			} else {
				throw new IllegalStateException("Unkown token " + tokens[i]);
			}
		}
		
		if (st.size() != 1) {
			throw new IllegalStateException("Weird stack size. Possibly illformed expression.");
		}
		
		return st.pop();
	}
	
	
}
