package com.programming.interpreter.parser.lisp;

import java.util.LinkedList;
import java.util.Stack;

import com.programming.interpreter.lex.Token;
import com.programming.interpreter.lex.Token.TokenType;
import com.programming.interpreter.lex.lisp.LispLexer;
import com.programming.interpreter.lex.lisp.LispToken;
import com.programming.interpreter.lex.lisp.LispToken.LispTokenType;
import com.programming.interpreter.parser.Parser;
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
public class LispParser extends Parser {

	public LispParser(String filename) {
		super(filename);
		lexer = new LispLexer(filename);
	}

	protected int getPhasesCount() {
		return 3;
	}

	protected LinkedList<Token> runPhase(int i, LinkedList<Token> tokens) {
		switch (i) {
		case 0:
			return collectStrings(tokens);
		case 1:
			return removeWhitespace(tokens);
		case 2:
			return collectLists(tokens);
		}
		return super.runPhase(i, tokens);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected LinkedList<Token> collectLists(LinkedList<Token> tokens) {
		// Collect lists
		final Action lparenAction = new Action();
		final Action rparenAction = new Action();
		final Action charAction = new Action();

		State newList = new State() {

			@SuppressWarnings("unchecked")
			@Override
			protected void postTransition(Action action) {
				((Stack) getDFA().getData()).push(new LispToken(
						LispTokenType.LIST_TOKEN));

			}
		};
		State collectElements = new State() {

			@Override
			protected void postTransition(Action action) {
				((Token) ((Stack) getDFA().getData()).peek())
						.addChild((Token) action.getData());
			}
		};
		State endList = new State() {

			@SuppressWarnings("unchecked")
			@Override
			protected void postTransition(Action action) {
				Stack stack = (Stack) getDFA().getData();
				Token token = (Token) stack.pop();
				if (token.getTokenType() == LispTokenType.LIST_TOKEN
						|| token.getTokenType() == LispTokenType.COMPOUND_TOKEN) {
					((Token) stack.peek()).addChild(token);
				} else
					;// TODO add error message
			}
		};

		newList.addTransition(lparenAction, newList);
		newList.addTransition(rparenAction, endList);
		newList.addTransition(charAction, collectElements);
		collectElements.addTransition(lparenAction, newList);
		collectElements.addTransition(rparenAction, endList);
		collectElements.addTransition(charAction, collectElements);
		endList.addTransition(lparenAction, newList);
		endList.addTransition(rparenAction, endList);
		endList.addTransition(charAction, collectElements);

		DFA<Stack> listDFA = new DFA<Stack>(collectElements, new State());
		listDFA.addState(newList);
		listDFA.addState(endList);
		listDFA.setData(new Stack<Token>());
		((Stack) listDFA.getData()).push(new Token(TokenType.COMPOUND_TOKEN));

		runDFA(listDFA, tokens, new Handler() {

			@Override
			public Action getAction(Token token) {
				if (token.getTokenType() == LispTokenType.LIST_BEGIN_TOKEN)
					return lparenAction;
				else if (token.getTokenType() == LispTokenType.LIST_END_TOKEN)
					return rparenAction;
				else {
					charAction.setData(token);
					return charAction;
				}
			}
		});

		tokens = new LinkedList<Token>();
		tokens.addAll(listDFA.getData());
		return tokens;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected LinkedList<Token> collectStrings(LinkedList<Token> tokens) {
		// Collect strings
		final Action quoteAction = new Action();
		final Action charAction = new Action();
		State start = new State() {

			@SuppressWarnings("unchecked")
			@Override
			protected void postTransition(Action action) {
				// Eat up end quote
				if (action == quoteAction)
					return;

				((Stack) getDFA().getData()).push(action.getData());
			}
		};
		State newString = new State() {

			@SuppressWarnings("unchecked")
			@Override
			protected void postTransition(Action action) {
				((Stack) getDFA().getData()).push(new LispToken(
						LispTokenType.STRING_TOKEN));
			}
		};
		State collectElements = new State() {

			@Override
			protected void postTransition(Action action) {
				Stack stack = (Stack) getDFA().getData();
				Token token = (Token) stack.pop();
				String buf = "";
				if (null != token.getValue())
					buf += token.getValue();
				if (null != ((Token) action.getData()).getValue())
					buf += ((Token) action.getData()).getValue();
				// Have to collapse the children of string and since token
				// is immutable
				// we create a new one here
				token = new LispToken(buf, LispTokenType.STRING_TOKEN);
				stack.push(token);
			}
		};

		start.addTransition(quoteAction, newString);
		start.addTransition(charAction, start);
		newString.addTransition(quoteAction, start);
		newString.addTransition(charAction, collectElements);
		collectElements.addTransition(quoteAction, start);
		collectElements.addTransition(charAction, collectElements);

		DFA<Stack> listDFA = new DFA<Stack>(start, new State());
		listDFA.addState(newString);
		listDFA.addState(collectElements);
		listDFA.setData(new Stack<Token>());

		runDFA(listDFA, tokens, new Handler() {

			@Override
			public Action getAction(Token token) {
				if (token.getTokenType() == LispTokenType.STRING_BEGIN_END_TOKEN)
					return quoteAction;
				else {
					charAction.setData(token);
					return charAction;
				}
			}
		});

		tokens = new LinkedList<Token>();
		tokens.addAll(listDFA.getData());
		return tokens;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected LinkedList<Token> removeWhitespace(LinkedList<Token> tokens) {
		// Collect lists
		State collectingState = new State() {

			@SuppressWarnings("unchecked")
			@Override
			protected void postTransition(Action action) {
				Stack stack = (Stack) getDFA().getData();
				Token token = (Token) action.getData();
				stack.push(token);
			}
		};
		State rejectingState = new State() {

			@SuppressWarnings("unchecked")
			@Override
			protected void postTransition(Action action) {
				// Do nothing
			}
		};

		final Action whitespaceAction = new Action();
		final Action charAction = new Action();
		collectingState.addTransition(whitespaceAction, rejectingState);
		collectingState.addTransition(charAction, collectingState);
		rejectingState.addTransition(charAction, collectingState);
		rejectingState.addTransition(whitespaceAction, rejectingState);

		DFA<Stack> listDFA = new DFA<Stack>(collectingState, new State());
		listDFA.addState(rejectingState);
		listDFA.setData(new Stack<Token>());

		runDFA(listDFA, tokens, new Handler() {

			@Override
			public Action getAction(Token token) {
				if (token.getTokenType() == TokenType.PUNCTUATION) {
					String val = token.getValue();
					if (val == null || val.isEmpty() || val.equals(" ")
							|| val.equals("\t") || val.equals("\n")) {
						return whitespaceAction;
					}
				}
				charAction.setData(token);
				return charAction;
			}
		});

		tokens = new LinkedList<Token>();
		tokens.addAll(listDFA.getData());
		return tokens;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("-------PHASE 1 (lex)------------");
		LinkedList<Token> tokens = new LispLexer("data/parser/helloworld.scm")
				.slurp();
		System.out.println("Count of tokens : " + tokens.size());
		for (Token token : tokens) {
			token.dump("");
		}

		System.out.println("-------PHASE 2 (parse strings)------------");
		tokens = new LispParser("data/parser/helloworld.scm")
				.collectStrings(tokens);
		System.out.println("Count of tokens : " + tokens.size());
		for (Token token : tokens) {
			token.dump("");
		}

		System.out.println("-------PHASE 3 (remove whitespace)------------");
		tokens = new LispParser("data/parser/helloworld.scm")
				.removeWhitespace(tokens);
		System.out.println("Count of tokens : " + tokens.size());
		for (Token token : tokens) {
			token.dump("");
		}

		System.out.println("-------PHASE 4 (parse lists)------------");
		tokens = new LispParser("data/parser/helloworld.scm")
				.collectLists(tokens);
		System.out.println("Count of tokens : " + tokens.size());
		for (Token token : tokens) {
			token.dump("");
		}
	}

}
