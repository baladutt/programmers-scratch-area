package com.programming.interpreter.lex;

import com.programming.interpreter.lex.Token.TokenType;

public class TokenFactory {
	public Token createToken(String value, TokenType type)
	{
		return new Token(value, type);
	}
	
	public Token createToken(TokenType type)
	{
		return new Token(type);
	}


}
