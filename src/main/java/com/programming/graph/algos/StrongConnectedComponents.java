package com.programming.graph.algos;

import java.io.PrintStream;
import java.util.Stack;

import com.programming.graph.Edge;
import com.programming.graph.Graph;
import com.programming.graph.Vertex;

/*
 * A directed graph is called strongly connected if there is a path from each vertex in the graph 
 * to every other vertex. In particular, this means paths in each direction; a path from a to b and 
 * also a path from b to a.
 * 
 * Tarjan's strongly connected components algorithm
 * http://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm
 * 
 * TODO: make sure that Vertices with type Data are only passed along
 */
public class StrongConnectedComponents {

	public static class Data {
		int index = -1;
		int lowlink = -1;
		String label;

		public Data(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return label.toString();
		}
	}

	Stack<Vertex<Data>> s = new Stack<Vertex<Data>>();
	int index = 0;

	public void printSCC(Graph g, PrintStream out) {
		
		Vertex [] vArr = g.getVertices();
		if(vArr.length == 0)
		{
			out.println("Empty graph!");
			return;
		}
		
		index = 0;
		s.clear();

		for (Vertex<Data> v : g.getVertices())
			if (v.getData().index == -1)
				strongConnect(v, out);

	}

	int strongConnect(Vertex<Data> v, PrintStream out) {
		// System.out.println("Strong Connect : " + v.getData().label + " : "
		// + index);
		v.getData().index = index;
		v.getData().lowlink = index;
		index++;
		s.push(v);

		for (Edge edge : v.getOutgoingEdges()) {
			if (edge.isSrcVertex(v)) {
				Vertex<Data> v1 = edge.getDestinationVertex();
				if (v1.getData().index == -1) {
					strongConnect(v1, out);
					v.getData().lowlink = Math.min(v.getData().lowlink,
							v1.getData().lowlink);
				} else if (s.contains(v1))
					v.getData().lowlink = Math.min(v.getData().lowlink,
							v1.getData().index);
			}
		}

		if (v.getData().index == v.getData().lowlink) {
			out.println("SCC : ");
			Vertex<Data> vTmp = s.pop();
			out.println(vTmp.getData().label);
			while (vTmp != v) {

				vTmp = s.pop();
				out.println(vTmp.getData().label);
			}
		}

		return index;
	}

}
