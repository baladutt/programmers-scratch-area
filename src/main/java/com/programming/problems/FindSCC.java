package com.programming.problems;

import com.programming.graph.Edge;
import com.programming.graph.Graph;
import com.programming.graph.Vertex;
import com.programming.graph.algos.StrongConnectedComponents;
import com.programming.graph.algos.StrongConnectedComponents.Data;

public class FindSCC {
	public static void main(String[] args) {
		case1();
		case2();
	}

	private static void case1() {

		Vertex<Data> va = new Vertex<Data>(new Data("a"));
		Vertex<Data> vb = new Vertex<Data>(new Data("b"));
		Vertex<Data> vc = new Vertex<Data>(new Data("c"));
		Vertex<Data> vd = new Vertex<Data>(new Data("d"));

		new Edge<String>(va, vb);
		new Edge<String>(vb, va);
		new Edge<String>(vc, vd);
		new Edge<String>(vd, vc);
		new Edge<String>(va, vd);
		new Edge<String>(vd, va);

		Graph g = new Graph();
		g.setWeighted(true).setDirected(true).add(va).add(vb).add(vc).add(vd);

		g.print(System.out);
		(new StrongConnectedComponents()).printSCC(g, System.out);
	}

	private static void case2() {

		Vertex<Data> va = new Vertex<Data>(new Data("a"));
		Vertex<Data> vb = new Vertex<Data>(new Data("b"));
		Vertex<Data> vc = new Vertex<Data>(new Data("c"));
		Vertex<Data> vd = new Vertex<Data>(new Data("d"));
		new Edge<String>(va, vb);
		new Edge<String>(vb, vc);
		new Edge<String>(vc, va);

		(new StrongConnectedComponents()).printSCC(
				(new Graph()).setWeighted(true).setDirected(true).add(va)
						.add(vb).add(vc).add(vd), System.out);
	}
}
