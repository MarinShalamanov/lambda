package com.marinshalamanov.lambda.ast;

import java.util.HashSet;
import java.util.Set;

public class VariableSet {
	
	private static VariableSet instance = null;
	
	public static VariableSet getInstance() {
		if (instance == null) {
			HashSet<Variable> set = new HashSet<>();
			for(char c = 'a'; c <= 'z'; c++) {
				set.add(new Variable(c));
			}
			instance = new VariableSet(set);
		} 

		return instance;
	}
	
	private Set<Variable> set;
	
	private VariableSet(Set<Variable> set) {
		this.set = new HashSet<Variable>(set);
	}
	
	public Set<Variable> getSet() {
		return set;
	}
	
	public Variable getNewVariable(Set<Variable> existing) {
		for (Variable v : set) {
			if (!existing.contains(v)) {
				return v;
			}
		}
		
		return null;
	}
}
