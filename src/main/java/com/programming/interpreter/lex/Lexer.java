package com.programming.interpreter.lex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * A Lexer is defined as here as a tool that given a text piece returns list of
 * tokens.
 * 
 * @author bdutt
 * 
 */
public class Lexer {

	String filename;

	public Lexer(String filename) {
		this.filename = filename;
	}

	public LinkedList<Token> slurp() {
		Tokenizer tokenizer;

		LinkedList<Token> tokens = new LinkedList<Token>();

		tokenizer = getTokenizer(filename);
		if (null == tokenizer)
			return tokens; // TODO: add error log
		Token token = null;
		while ((token = tokenizer.getNextToken()).getTokenType() != Token.TokenType.EOF_TOKEN) {
			tokens.add(token);
		}
		return tokens;
	}

	protected Tokenizer getTokenizer(String filename) {
		try {
			return new Tokenizer(new FileInputStream(filename), new TokenFactory());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println( "Count of tokens : " +(new Lexer("data/parser/tests.scm")).slurp().size());
	}

}
