package com.programming.graph;

public class Container<T> {

	T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public Container()
	{
		
	}
	
	public Container(T data)
	{
		this.data = data;
	}
	
	boolean bMarked = false;

	public boolean isMarked() {
		return bMarked;
	}

	public void setMarked(boolean bMarked) {
		this.bMarked = bMarked;
	}
	
	
	
	
}
