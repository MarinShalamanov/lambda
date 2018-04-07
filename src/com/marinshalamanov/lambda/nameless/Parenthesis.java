package com.marinshalamanov.lambda.nameless;

public class Parenthesis extends NamelessToken {
	public static final Parenthesis open = new Parenthesis(true);
	public static final Parenthesis closed = new Parenthesis(false);
	
	private boolean isOpen;

	private Parenthesis (boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public boolean isClose() {
		return !isOpen;
	}
	
	@Override
	public String toString() {
		if(isOpen) return "(";
		else return ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}
}
