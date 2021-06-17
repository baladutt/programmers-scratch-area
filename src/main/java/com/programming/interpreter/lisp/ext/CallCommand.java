package com.programming.interpreter.lisp.ext;

import com.programming.interpreter.lisp.LispInterpreter.Command;

public class CallCommand implements Command {
	public Object execute(String[] args) {
		for (String arg : args)
			System.out.print(arg);
		return "";
	}
}
