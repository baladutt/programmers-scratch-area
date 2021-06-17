package com.programming.graph;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Vector;

public class Graph {
	
	Vector<Vertex> vertices = new Vector<Vertex>();
	private String label = "unnamed";
	
	public Graph()
	{
		
	}
	
	public Graph(String label)
	{
		this.label = label;
	}
	
	public Graph add(Vertex v)
	{
		vertices.add(v);
		return this;
	}
	
	public Vertex[] getVertices()
	{
		return vertices.toArray(new Vertex[vertices.size()]);
	}
	
	boolean bDirected = false;
	public Graph setDirected(boolean bDirected)
	{
		this.bDirected = bDirected;
		return this;
	}
	
	public boolean isDirected()
	{
		return this.bDirected;
	}
	

	boolean bWeighted = false;
	public Graph setWeighted(boolean bWeighted)
	{
		this.bWeighted = bWeighted;
		return this;
	}
	
	public boolean isWeighted()
	{
		return this.bWeighted;
	}
	
	public void printStats(PrintStream out)
	{
		out.println("Name: "+label+", vertices: "+vertices.size());
	}
	
	public Graph print(PrintStream out)
	{
		
		Iterator<Vertex> iter = vertices.iterator();
		while(iter.hasNext())
		{
			iter.next().setMarked(false);
		}
			
		iter = vertices.iterator();
		while(iter.hasNext())
		{
			printDFSPreOrderVertex(iter.next(), out, "");
		}
		return this;
	}
	
	private void printDFSPreOrderVertex(Vertex v, PrintStream out, String indent)
	{
		if(v.isMarked())
			return;
		v.setMarked(true);
		out.println(indent+v.toString()+": "+v.getData());
		Edge [] edges = v.getEdges();
		for(Edge edge: edges)
		{
			if(edge.isSrcVertex(v))
				printDFSPreOrderVertex(edge.dstVertex, out, indent+"    ");
		}
	}

	public boolean isEmpty() {
		
		return vertices.isEmpty();
	}

	public void remove(Vertex v) {
		vertices.remove(v);
		
	}

}
