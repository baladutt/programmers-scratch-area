package com.programming.problems;

import java.net.URL;

import com.programming.graph.Edge;
import com.programming.graph.Graph;
import com.programming.graph.Vertex;
import com.programming.graph.algos.TravellingSalesman;
import com.programming.io.TSPLibFile;

public class TravellingSalesmanProblem {
	public static void main(String []args)
	{
		case1();
		case2();
		case3();
		case4();
	}
	private static void case1()
	{
		TravellingSalesmanProblem tsp = new TravellingSalesmanProblem();
		URL url = tsp.getClass().getResource("MiniDijibouti.tsp.10.tsplib");
		Graph g = (new TSPLibFile(url)).getGraph();
		g.printStats(System.out);
		(new TravellingSalesman()).findShortestPath(g);
	}
	private static void case2()
	{
		Graph g = new Graph();
		Vertex<String> va = new Vertex<String>("a");
		Vertex<String> vb = new Vertex<String>("b");
		Vertex<String> vc = new Vertex<String>("c");
		g.add(va).add(vb).add(vc);
		new Edge<String>(va,vb,1.0);
		new Edge<String>(va,vc,0.5);
		new Edge<String>(vc,vb,1.0);
		(new TravellingSalesman()).findShortestPath(g);
	}
	private static void case3()
	{
		TravellingSalesmanProblem tsp = new TravellingSalesmanProblem();
		URL url = tsp.getClass().getResource("Dijibouti.tsp.38.tsplib");
		Graph g = (new TSPLibFile(url)).getGraph();
		g.printStats(System.out);
		(new TravellingSalesman()).findShortestPathGreedyAlgo(g, System.out);
	}
	private static void case4()
	{
		TravellingSalesmanProblem tsp = new TravellingSalesmanProblem();
		URL url = tsp.getClass().getResource("MiniDijibouti.tsp.10.tsplib");
		Graph g = (new TSPLibFile(url)).getGraph();
		g.printStats(System.out);
		(new TravellingSalesman()).findShortestPathBranchAndBound(g, System.out);
	}

}
