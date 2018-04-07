package com.marinshalamanov.lambda.nameless;

public class NamelessVar extends NamelessToken {
	private int deBrojnIndex;
	
	public NamelessVar(int deBrojnIndex) {
		this.deBrojnIndex = deBrojnIndex;
	}
	
	public int getDeBrojnIndex() {
		return deBrojnIndex;
	}
	
	public void setDeBrojnIndex(int index) {
		deBrojnIndex = index;
	}	
	
	@Override
	public String toString() {
		return String.valueOf(deBrojnIndex);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof NamelessVar) {
			NamelessVar var = (NamelessVar) obj;
			return deBrojnIndex == var.deBrojnIndex;
		} else {
			return false;
		}
	}
}
