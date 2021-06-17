package com.programming.interpreter.lisp;

import java.util.HashMap;
import java.util.Map;

public class Environment { 

    Map<String, Object> bindings = new HashMap<String, Object>();

    public Environment() {}
    
    public Object getBinding(String name)
    {
    	return bindings.get(name);
    }
    
    public void setBinding(String name, Object cmd)
    {
    	bindings.put(name, cmd);
    }


}
