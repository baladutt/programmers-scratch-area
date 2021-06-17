package com.programming.statemachine;

import java.util.HashMap;

import com.programming.DataContainer;

public class State<T> extends DataContainer<T>{
	
	HashMap<Action, State> transitionMap = new HashMap<Action, State>();
	DFA dfa = null;
	
	public void addTransition(Action a, State s)
	{
		transitionMap.put(a, s);
	}
	
	public State getNextState(Action action){
		State s = transitionMap.get(action);
		if(null!=s)
			s.postTransition(action);
		return s;
	}
	
	protected void postTransition(Action action)
	{
	}
	
	protected void preTransition(Action action)
	{
	}
	
	protected void setDFA(DFA dfa) {
		this.dfa = dfa;
	}
	
	protected DFA getDFA() {
		return dfa;
	}

}
