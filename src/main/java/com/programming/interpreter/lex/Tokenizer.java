package com.programming.interpreter.lex;

import java.io.IOException;
import java.io.InputStream;


public class Tokenizer {
	
	InputStream in = null;
	TokenFactory tokenFactory = null;
	
	char [] punctuations;
	
	private Token emptyToken = new Token(Token.TokenType.EMPTY_TOKEN);
	private Token preReadToken = null;
	
	public Tokenizer(InputStream in, TokenFactory tokenFactory)
	{
		this(in, new char[]{' ', '\t', '\n'}, tokenFactory);
	}
	
	/**
	 * Assumed that field and record separators will be very few - 2-3 only
	 * 
	 * @param in
	 * @param fieldSeparators
	 * @param recordSeparators
	 */
	
	public Tokenizer(InputStream in, char[] punctuations, TokenFactory tokenFactory)
	{
		this.in = in;
		this.punctuations = punctuations ;
		this.tokenFactory = tokenFactory;
	}
	
	public Token getNextToken()
	{
		if(null!=preReadToken)
		{
			Token tmp = preReadToken;
			preReadToken = null;
			return tmp;
		}
		int c = -1;
		StringBuilder sb = new StringBuilder();
		try {
			while((c= in.read())!=-1){
				for(char punctuation: punctuations)
					if(punctuation == c)
					{
						Token tmp = tokenFactory.createToken(""+(char)c, Token.TokenType.PUNCTUATION);
						if(sb.length() == 0)
						{
							return tmp;
						}else
						{
							preReadToken = tmp;
						}
						return new Token(sb.toString());
					}
				sb.append((char)c);
			}
			if(c==-1){
				preReadToken = tokenFactory.createToken(Token.TokenType.EOF_TOKEN);
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return emptyToken;
		}
		

		return new Token(sb.toString());
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
