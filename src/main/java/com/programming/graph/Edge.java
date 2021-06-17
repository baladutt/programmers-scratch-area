package com.programming.graph;

public class Edge<T> extends Container<T>{
	
	Vertex srcVertex;
	Vertex dstVertex;
	
	double weight = 0;
	
	public Edge(Vertex srcVertex, Vertex dstVertex)
	{
		this.srcVertex = srcVertex;
		this.dstVertex = dstVertex;
		srcVertex.addEdge(this);
		dstVertex.addEdge(this);
	}
	
	public Edge(Vertex srcVertex, Vertex dstVertex, double weight)
	{
		this(srcVertex, dstVertex);
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public Vertex [] getVertices()
	{
		return new Vertex[]{srcVertex, dstVertex};
	}
	
	public boolean isSrcVertex(Vertex v)
	{
		return v == srcVertex;
	}
	
	public Vertex getDestinationVertex()
	{
		return this.dstVertex;
	}
	public Vertex getSourceVertex()
	{
		return this.srcVertex;
	}

	public Vertex getOtherVertex(Vertex v) {
		//Assumed that input will be a valid vertex of the edge
		return srcVertex == v? dstVertex : srcVertex;
	}

}
