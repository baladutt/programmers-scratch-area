package com.programming.interpreter.lex.lisp;

import com.programming.interpreter.lex.Token;
import com.programming.interpreter.lex.TokenFactory;
import com.programming.interpreter.lex.Token.TokenType;

public class LispTokenFactory extends TokenFactory{
	public Token createToken(String value, TokenType type)
	{
		return new LispToken(value, type);
	}
	
	public Token createToken(TokenType type)
	{
		return new LispToken(type);
	}


}
