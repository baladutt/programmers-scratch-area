package com.programming.graph;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.PriorityQueue;


public class PriorityGraph {
	
	PriorityQueue<PriorityVertex> pQueue = new PriorityQueue<PriorityVertex>(
			10, PriorityVertex.comparator);
	
	public PriorityGraph add(PriorityVertex v)
	{
		pQueue.add(v);
		return this;
	}
	
	public void resetPriority(PriorityVertex v, int priority)
	{
		pQueue.remove(v);
		v.setPriority(priority);
		pQueue.add(v);
	}
	
	public PriorityVertex[] getVertices()
	{
		return pQueue.toArray(new PriorityVertex[pQueue.size()]);
	}
	
	public PriorityVertex remove()
	{
		return pQueue.remove();
	}
	
	public boolean contains(PriorityVertex v)
	{
		return pQueue.contains(v);
	}
	
	boolean bDirected = false;
	public PriorityGraph setDirected(boolean bDirected)
	{
		this.bDirected = bDirected;
		return this;
	}
	
	public boolean isDirected()
	{
		return this.bDirected;
	}
	

	boolean bWeighted = false;
	public PriorityGraph setWeighted(boolean bWeighted)
	{
		this.bWeighted = bWeighted;
		return this;
	}
	
	public boolean isWeighted()
	{
		return this.bWeighted;
	}
	
	public boolean isEmpty()
	{
		return pQueue.isEmpty();
	}
	
	public PriorityGraph print(PrintStream out)
	{
		
		Iterator<PriorityVertex> iter = pQueue.iterator();
		while(iter.hasNext())
		{
			iter.next().setMarked(false);
		}
			
		iter = pQueue.iterator();
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

	public void remove(PriorityVertex vTmp) {
		pQueue.remove(vTmp);
		
	}

}
