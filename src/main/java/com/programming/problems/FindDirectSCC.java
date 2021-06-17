package com.programming.problems;

import com.programming.graph.Edge;
import com.programming.graph.Graph;
import com.programming.graph.Vertex;
import com.programming.graph.algos.DirectStrongConnectedComponents;
import com.programming.graph.algos.StrongConnectedComponents.Data;

public class FindDirectSCC {
	public static void main(String[] args) {
		case1();
	}

	@SuppressWarnings("unchecked")
	private static void case1() {

		Vertex<Data> va = new Vertex<Data>(new Data("a"));
		Vertex<Data> vb = new Vertex<Data>(new Data("b"));
		Vertex<Data> vc = new Vertex<Data>(new Data("c"));
		Vertex<Data> vd = new Vertex<Data>(new Data("d"));
		Vertex<Data> ve = new Vertex<Data>(new Data("e"));
		Vertex<Data> vf = new Vertex<Data>(new Data("f"));
		Vertex<Data> vg = new Vertex<Data>(new Data("g"));
		Vertex<Data> vh = new Vertex<Data>(new Data("h"));
		Vertex<Data> vi = new Vertex<Data>(new Data("i"));

		new Edge<String>(va, vb);
		new Edge<String>(vc, vd);
		new Edge<String>(va, vd);
		new Edge<String>(vb, vd);
		new Edge<String>(vd, ve);
		new Edge<String>(ve, vf);
		new Edge<String>(vf, vd);
		new Edge<String>(vf, vd);
		
		new Edge<String>(vc, vh);
		new Edge<String>(vc, vi);
		new Edge<String>(vc, vg);
		new Edge<String>(vg, vh);
		new Edge<String>(vg, vi);
		new Edge<String>(vh, vi);
		

		Graph g = new Graph();
		g.setWeighted(true).setDirected(true).add(va).add(vb).add(vc).add(vd)
				.add(ve).add(vf).add(vg).add(vh).add(vi);

		g.print(System.out);

		(new DirectStrongConnectedComponents()).printDSCC(g, System.out);
	}
}
