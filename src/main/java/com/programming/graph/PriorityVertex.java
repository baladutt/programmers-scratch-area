package com.programming.graph;

import java.util.Comparator;

public class PriorityVertex<T> extends Vertex<T>{
	
	int priority = 0;
	
	public PriorityVertex()
	{
		
	}
	
	public PriorityVertex(T data)
	{
		super(data);
	}
	
	public PriorityVertex(T data, int priority)
	{
		super(data);
		this.priority = priority;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
 	void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	public static Comparator<PriorityVertex> comparator = new Comparator<PriorityVertex>() {

		@Override
		public int compare(PriorityVertex v1, PriorityVertex v2) {

			return v1.priority - v2.priority;
		}

	};
}
