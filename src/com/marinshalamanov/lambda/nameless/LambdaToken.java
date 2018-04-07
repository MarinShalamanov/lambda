package com.marinshalamanov.lambda.nameless;

public class LambdaToken extends NamelessToken {
	
	private static LambdaToken instance = new LambdaToken();
	public static LambdaToken getInstance() {
		return instance;
	}
	
	private LambdaToken() {
	}
	
	@Override
	public String toString() {
		return "\\";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this == instance;
	}
}
