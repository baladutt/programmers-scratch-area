package com.programming.interpreter.lex.lisp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.programming.interpreter.lex.Lexer;
import com.programming.interpreter.lex.Tokenizer;

public class LispLexer extends Lexer{

	public LispLexer(String filename)
	{
		super(filename);
	}
		
	protected Tokenizer getTokenizer(String filename)
	{
		try {
			return new Tokenizer(
					new FileInputStream(filename),  
					new char[]{' ','\t','(',')',';', '"', '\'','\n'},
					new LispTokenFactory());
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
