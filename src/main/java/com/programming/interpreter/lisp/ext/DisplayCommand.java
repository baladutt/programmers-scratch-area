package com.programming.interpreter.lisp.ext;

import com.programming.interpreter.lisp.LispInterpreter.Command;

public class DisplayCommand implements Command {
	public Object execute(String[] args) {
		for (String arg : args){
			arg = arg.replaceAll("\\\\n", "\n");
			System.out.print(arg);
		}
		return "";
	}
}
