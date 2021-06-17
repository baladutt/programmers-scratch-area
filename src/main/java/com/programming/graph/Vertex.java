package com.programming.graph;

import java.util.ArrayList;

public class Vertex<T> extends Container<T>{
	
	ArrayList<Edge> edges = new ArrayList<Edge>();
	ArrayList<Edge> outgoingEdges = new ArrayList<Edge>();
	Edge<String> traversedEdge = null;
	int weight = 0;
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Edge [] getEdges()
	{
		return edges.toArray(new Edge[edges.size()]);
	}
	
	public Edge [] getOutgoingEdges()
	{
		return outgoingEdges.toArray(new Edge[outgoingEdges.size()]);
	}
	
	public void addEdge(Edge edge)
	{
		edges.add(edge);
		if(edge.isSrcVertex(this))
			outgoingEdges.add(edge);
	}
	
	public Vertex()
	{
		
	}
	
	public Vertex(T data)
	{
		super(data);
	}
	
	public void setTraversedEdge(Edge<String> e) {
		traversedEdge = e;	
	}
	
	public Edge<String> getTraversedEdge() {
		return traversedEdge;
	}
}
