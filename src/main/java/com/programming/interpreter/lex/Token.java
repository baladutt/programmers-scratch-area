package com.programming.interpreter.lex;

import java.util.ArrayList;

/**
 * Tokens are defined here to be immutable
 * 
 * @author bdutt
 *
 */
public class Token {
	public static class TokenType{	
		String name;
		protected static final TokenType createTokenType(String name)
		{
			return new TokenType(name);
		}
		
		protected TokenType(String name){this.name = name;}
		public static final TokenType  EMPTY_TOKEN = createTokenType("EMPTY_TOKEN");
		public static final TokenType  EOF_TOKEN = createTokenType("EOF_TOKEN");
		public static final TokenType  PUNCTUATION = createTokenType("PUNCTUATION");
		public static final TokenType COMPOUND_TOKEN = createTokenType("COMPOUND_TOKEN");
		public static final TokenType GENERAL_TOKEN = createTokenType("GENERAL_TOKEN");
		
		@Override
		public String toString()
		{
			return name;
		}
	}
	
	String value;
	TokenType tokenType = TokenType.GENERAL_TOKEN;
	ArrayList<Token> children = new ArrayList<Token>();
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the tokenType
	 */
	public TokenType getTokenType() {
		return tokenType;
	}
	
	protected void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	
	public void addChild(Token t){ children.add(t);}
	public ArrayList<Token> getChildren(){return children;}

	public Token(String value) {
		this.value = value;
	}
	
	public Token(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	
	public Token(String value, TokenType tokenType) {
		this.value = value;
		this.tokenType = tokenType;
	}
	
	public void dump(String indent)
	{
		System.out.println(indent+value+", "+tokenType.toString());
		for(Token token: children)
		{
			token.dump(indent+"    ");
		}
	}
	
	public Token childrenLessClone()
	{
		Token token = new Token(value, tokenType);
		return token;
	}

}
