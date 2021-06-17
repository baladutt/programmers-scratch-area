package com.programming.interpreter.lex.lisp;

import com.programming.interpreter.lex.Token;

public class LispToken extends Token{

	public static class LispTokenType extends Token.TokenType{
		public static final TokenType  LIST_TOKEN = createTokenType("LIST_TOKEN");
		public static final TokenType  LIST_BEGIN_TOKEN = createTokenType("LIST_BEGIN_TOKEN"); //'('
		public static final TokenType  LIST_END_TOKEN = createTokenType("LIST_END_TOKEN"); //')'
		public static final TokenType  COMMENT_TOKEN = createTokenType("COMMENT_TOKEN"); //';'
		public static final TokenType  STRING_TOKEN = createTokenType("STRING_TOKEN"); //';'
		public static final TokenType  STRING_BEGIN_END_TOKEN = createTokenType("STRING_BEGIN_END_TOKEN"); //'('
		
		public LispTokenType(String name)
		{
			super(name);
		}
	}

	public LispToken(String value) {
		super(value);
		refineType();
	}
	
	public LispToken(TokenType tokenType) {
		super(tokenType);
	}
	
	public LispToken(String value, TokenType tokenType) {
		super(value, tokenType);
		refineType();
	}
	
	private void refineType()
	{
		String val = getValue();
		if(val.equals(";"))
		{
			setTokenType(LispTokenType.COMMENT_TOKEN);
		}else if(val.equals("(")){
			setTokenType(LispTokenType.LIST_BEGIN_TOKEN);
		}else if(val.equals(")")){
			setTokenType(LispTokenType.LIST_END_TOKEN);
		}else if(val.equals("\"")){
			setTokenType(LispTokenType.STRING_BEGIN_END_TOKEN);
		}
	}
	
	@Override
	public Token childrenLessClone()
	{
		Token token = new LispToken(getValue(), getTokenType());
		return token;
	}

}
