package com.programming.statemachine;

import com.programming.DataContainer;

public class DFA<T> extends DataContainer<T>{
	
	State currState = null;
	State startState, endState;
	
	public State getCurrentState()
	{
		return currState;
	}
	
	public boolean hasEnded()
	{
		return currState == endState;
	}
	
	public void handlAction(Action a)
	{
		State s = currState.getNextState(a);
		if(s!=null)currState = s;
		//TODO: collect error messages if transition was invalid
	}
	
	public void addState(State state)
	{
		state.setDFA(this);
	}
	
	/**
	 * It is assumed that transitions are already configured.
	 * 
	 * @param startState
	 * @param endState
	 */
	public DFA(State startState, State endState)
	{
		this.startState = startState;
		this.endState = endState;
		startState.setDFA(this);
		endState.setDFA(this);
		currState = startState;
	}
	
	

}
