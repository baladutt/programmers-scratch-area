package com.programming.graph.algos;

import java.io.PrintStream;

import com.programming.graph.Edge;
import com.programming.graph.PriorityGraph;
import com.programming.graph.PriorityVertex;
import com.programming.graph.Vertex;

public class Dijkstra {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void findShortestPath(PriorityGraph graph, PriorityVertex startVertex,
			PriorityVertex endVertex) {

		//1. setup - mark vertices as unvisited
		PriorityVertex[] vArr = graph.getVertices();
		for (PriorityVertex v : vArr) {
			graph.resetPriority(v, Integer.MAX_VALUE);
			v.setMarked(false);
		}
		graph.resetPriority(startVertex,0);

		while (!graph.isEmpty()) {
			//2. Find smallest distance vertex
			PriorityVertex currVertex = graph.remove();
			Edge<String>[] eArr = currVertex.getOutgoingEdges();

			//3. Update neighbouring edges and mark this vertex as visited
			for (Edge<String> e : eArr) {
				PriorityVertex v = (PriorityVertex)e.getDestinationVertex();
				double weight = currVertex.getPriority() + e.getWeight();
				if (v.getPriority() > weight){
					graph.resetPriority(v, (int)weight);
					v.setTraversedEdge(e);
				}
			}
			currVertex.setMarked(true);//Actually, not needed as we are removing nodes from graph
			
			//4. Iterate till we have seen all the vertices
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	public void printShortestPath(PriorityVertex endVertex, PrintStream out)
	{
		out.println("Shortest distance is : "+endVertex.getPriority());
		
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
			Vertex [] vArr = e.getVertices();
			for(Vertex v: vArr)
			{
				if(v!=endVertex)
				{
					endVertex = (PriorityVertex) v;
					break;
				}
			}
		}
		
	}

}
