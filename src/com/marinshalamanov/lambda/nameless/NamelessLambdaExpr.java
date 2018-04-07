package com.marinshalamanov.lambda.nameless;

import java.util.ArrayList;
import java.util.List;

public class NamelessLambdaExpr {
	private List<NamelessToken> tokens;
	
	public NamelessLambdaExpr(List<NamelessToken> tokens) {
		this.tokens = tokens;
	}
	
	public NamelessLambdaExpr(NamelessToken token) {
		this.tokens = new ArrayList<>();
		this.tokens.add(token);
	}
	
	public List<NamelessToken> getTokens() {
		return tokens;
	}
	
	@Override
	public String toString() {
		String str = new String();
		for (NamelessToken token : tokens) {
			str += token;
		}
		return str;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NamelessLambdaExpr) {
			NamelessLambdaExpr o = (NamelessLambdaExpr) obj;
			if(o.tokens.size() != this.tokens.size()) return false;
			
			for(int i = 0; i < tokens.size(); i++) {
				if(!tokens.get(i).equals(o.tokens.get(i))) {
					return false;
				}
			}
			
			return true;
		} else {
			return false;
		}
	}
}
