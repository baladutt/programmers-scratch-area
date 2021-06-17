package com.programming.interpreter.parser;

import java.util.LinkedList;
import java.util.Stack;

import com.programming.interpreter.lex.Lexer;
import com.programming.interpreter.lex.Token;
import com.programming.statemachine.Action;
import com.programming.statemachine.DFA;
import com.programming.statemachine.State;

/**
 * This is a simple batch parser, where each phase occurs as a batch. Next phase
 * is not started till the earlier phase is done.
 * 
 * @author bdutt
 * 
 */
public class Parser {

	protected Lexer lexer = null;

	public Parser(String filename) {
		lexer = new Lexer(filename);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedList<Token> parse() {
		LinkedList<Token> tokens = lexer.slurp();
		for(int i=0;i<getPhasesCount();i++)
		{
			tokens = runPhase(i, tokens);
		}
		return tokens;
	}

	protected int getPhasesCount() {
		return 0;
	}

	protected LinkedList<Token> runPhase(int i, LinkedList<Token> tokens) {
		return tokens;
	}

	protected interface Handler {
		public Action getAction(Token token);
	}

	protected void runDFA(DFA dfa, LinkedList<Token> tokens, Handler handler) {
		for (Token token : tokens)
			runDFA(dfa, token, handler);
	}

	protected void runDFA(DFA dfa, Token token, Handler handler) {
		dfa.handlAction(handler.getAction(token));
		for (Token child : token.getChildren())
			runDFA(dfa, child, handler);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<Token> tokens = new Parser("data/parser/tests.scm").parse();
		System.out.println("Count of tokens : " + tokens.size());
		for (Token token : tokens) {
			token.dump("");
		}
	}

}
