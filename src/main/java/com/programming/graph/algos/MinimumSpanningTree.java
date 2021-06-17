package com.programming.graph.algos;

import java.io.PrintStream;

import com.programming.graph.Edge;
import com.programming.graph.PriorityGraph;
import com.programming.graph.PriorityVertex;
import com.programming.graph.Vertex;

public class MinimumSpanningTree {
	
	PriorityVertex endVertex = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void findMSTPrims(PriorityGraph graph) {

		//1. setup - mark vertices as unvisited
		for (PriorityVertex v : graph.getVertices()) {
			graph.resetPriority(v, Integer.MAX_VALUE);
			v.setMarked(false);
		}

		while (!graph.isEmpty()) {
			//2. Find smallest distance vertex
			endVertex = graph.remove();
			endVertex.setMarked(true);

			//3. Update neighbouring edges and mark this vertex as visited
			for (Edge<String> e : endVertex.getEdges()) {
				PriorityVertex v = (PriorityVertex)e.getOtherVertex(endVertex);
				if(!v.isMarked() && v.getPriority() > e.getWeight()){
					graph.resetPriority(v, (int)e.getWeight());
					v.setTraversedEdge(e);
				}
			}
			
			//4. Iterate till we have seen all the vertices
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public void printMST(PrintStream out)
	{	
		while(null != endVertex)
		{
			System.out.println(endVertex.getData().toString()+" : "+endVertex.getPriority());
			Edge e = endVertex.getTraversedEdge();
			if(e==null)
			{
				endVertex = null;
				break;
			}
			System.out.println("Edge : "+e.getWeight());
			endVertex = (PriorityVertex)e.getOtherVertex(endVertex);
		}
	}

}
