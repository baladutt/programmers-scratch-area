package com.programming.interpreter.lisp;

import java.util.ArrayList;
import java.util.LinkedList;

import com.programming.interpreter.lex.Token;
import com.programming.interpreter.lex.lisp.LispToken.LispTokenType;
import com.programming.interpreter.parser.Parser;
import com.programming.interpreter.parser.lisp.LispParser;

public class LispInterpreter {

	public static interface Command {
		public Object execute(String[] args);
	}

	public static class JavaCallCommand implements Command {

		public Object execute(String[] args) {
			try {
				// TODO: cache commands
				Command cmd = (Command) Class.forName(args[0]).newInstance();
				String[] args1 = new String[args.length - 1];
				System.arraycopy(args, 1, args1, 0, args1.length);
				return cmd.execute(args1);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public static class LetCommand implements Command {
		private Environment env;

		public LetCommand(Environment env) {
			this.env = env;
		}

		public Object execute(String[] args) {
			String[] args1 = new String[args.length - 1];
			System.arraycopy(args, 1, args1, 0, args1.length);
			env.setBinding(args[0], args1);
			return null;
		}
	}

	public static class GetCommand implements Command {
		private Environment env;

		public GetCommand(Environment env) {
			this.env = env;
		}

		public Object execute(String[] args) {
			return env.getBinding(args[0]);
		}
	}

	Parser parser = null;

	public LispInterpreter(String filename) {
		parser = new LispParser(filename);
	}

	public String interpret() {
		Environment env = new Environment();
		LinkedList<Token> tokens = parser.parse();

		env.setBinding("call-java", new JavaCallCommand());
		env.setBinding("let", new LetCommand(env));
		env.setBinding("get", new GetCommand(env));

		// Execute this
		execute(tokens, env);

		return "";// TODO: return some valid value
	}

	protected void execute(LinkedList<Token> tokens, Environment env) {
		for (Token token : tokens)
			execute(token, env);
	}

	protected Object execute(Token token, Environment env) {

		//Evaluate children first so that arguments are ready by the time we need them
		ArrayList<String> children = new ArrayList<String>();
		for (Token child : token.getChildren()) {
			Object ret = execute(child, env);
			if (ret instanceof String)
				children.add((String) ret);
			else if (ret instanceof String[]) {
				for (String s : (String[]) ret)
					children.add(s);
			}
			else
				;//TODO: handle this
		}

		//Now evaluate this token
		if (token.getTokenType() == LispTokenType.LIST_TOKEN) {
			String form = null;
			ArrayList<String> args = new ArrayList<String>();
			for (String child : children) {
				if (null == form)
					form = child;
				else
					args.add(child);
			}

			Object cmd = env.getBinding(form);
			if (null != cmd && cmd instanceof Command) {
				Object obj = ((Command) cmd).execute(args
						.toArray(new String[args.size()]));
				if (obj instanceof String) {
					return (String) obj;
				} else if (obj instanceof String[]) {
					return (String[]) obj;
				}else{
					//TODO: add error log
					return obj;
				}
			} else {
				// TODO: implement
				return form;
			}
		} else {
			// TODO: implement
			return token.getValue();
		}
	}

	public static void main(String[] args) {
		String result = new LispInterpreter("data/parser/helloworld.scm")
				.interpret();

		System.out.println(result);

	}
}
