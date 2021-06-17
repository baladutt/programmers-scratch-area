package com.programming.problems;

import com.programming.graph.Edge;
import com.programming.graph.PriorityGraph;
import com.programming.graph.PriorityVertex;
import com.programming.graph.algos.Dijkstra;
import com.programming.graph.algos.StrongConnectedComponents.Data;

public class FindShortestDistance {
	public static void main(String[] args) {
		case1();
	}

	/**
	 * Graph taken from: http://www.algolist.com/Dijkstra's_algorithm
	 */
	private static void case1() {

		PriorityVertex<Data> vRed = new PriorityVertex<Data>(new Data("Redville"));
		PriorityVertex<Data> vBlue = new PriorityVertex<Data>(new Data("Blueville"));
		PriorityVertex<Data> vGreen = new PriorityVertex<Data>(new Data("Greenville"));
		PriorityVertex<Data> vOrange = new PriorityVertex<Data>(new Data("Orangeville"));
		PriorityVertex<Data> vPurple = new PriorityVertex<Data>(new Data("Purpleville"));

		new Edge<String>(vRed, vGreen, 10);
		new Edge<String>(vRed, vBlue, 5);
		new Edge<String>(vRed, vOrange, 8);
		new Edge<String>(vBlue, vPurple, 7);
		new Edge<String>(vOrange, vPurple, 2);

		PriorityGraph g = new PriorityGraph();
		g.setWeighted(true).setDirected(true).add(vRed).add(vBlue).add(vGreen).add(vOrange).add(vPurple);

		g.print(System.out);
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.findShortestPath(g, vRed, vPurple);
		dijkstra.printShortestPath(vPurple, System.out);
	}

}
