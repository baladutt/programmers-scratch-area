package com.programming.graph.algos;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

import com.programming.graph.Edge;
import com.programming.graph.Graph;
import com.programming.graph.Vertex;
import com.programming.graph.algos.StrongConnectedComponents.Data;
import com.programming.permutation_combination.Combination;

/*
 * 
 * Facebook puzzle: http://www.facebook.com/careers/puzzles.php?puzzle_id=8
 */
public class DirectStrongConnectedComponents {
	
	private void debug(String msg)
	{
		//System.out.println(msg);
	}
	@SuppressWarnings("unchecked")
	public void printDSCC(Graph g, PrintStream out) {

		debug("Find DSCC");

		// Brute force
		class VertexWrapper {
			int order;
			Vertex<Data> vertex;

			public VertexWrapper(int order, Vertex<Data> vertex) {
				this.order = order;
				this.vertex = vertex;
			}
		}

		Comparator<VertexWrapper> comparator = new Comparator<VertexWrapper>() {

			@Override
			public int compare(VertexWrapper v1, VertexWrapper v2) {

				return v1.order - v2.order;
			}

		};

		Vertex<Data>[] vArr = g.getVertices();
		PriorityQueue<VertexWrapper> pQueue = new PriorityQueue<VertexWrapper>(
				vArr.length, comparator);

		int count = 0;
		for (Vertex<Data> v : vArr) {
			if (v.getEdges().length > 1)// We don't want smaller graphs
				pQueue.add(new VertexWrapper(count++, v));
			v.setMarked(false);
		}

		debug("Phase 1: vertices accepted : " + pQueue.size()
				+ "/" + vArr.length);

		VertexWrapper[] vWrapperArr = pQueue.toArray(new VertexWrapper[pQueue
				.size()]);
		pQueue.clear();

		for (VertexWrapper vWrapper : vWrapperArr) {

			debug("Vertex: "
					+ vWrapper.vertex.getData().toString());
			Edge<String>[] eArr = vWrapper.vertex.getEdges();
			int sizeOfCluster = eArr.length;

			// What is the biggest size possible?
			for (; sizeOfCluster >= 0; sizeOfCluster--) {
				count = 0;
				for (Edge<String> e : eArr) {
					Vertex<Data> v1 = e.getVertices()[1];
					if (v1 == vWrapper.vertex)
						v1 = e.getVertices()[0];
					if (v1.getEdges().length >= sizeOfCluster)
						count++;
				}
				if (count >= sizeOfCluster)
					break;
			}
			sizeOfCluster++;
			debug("Phase 2: cluster size could be : "
					+ sizeOfCluster);
			pQueue.add(new VertexWrapper(sizeOfCluster, vWrapper.vertex));
		}

		debug("Phase 2: vertices accepted : " + pQueue.size());

		vWrapperArr = pQueue.toArray(new VertexWrapper[pQueue.size()]);
		pQueue.clear();

		for (VertexWrapper vWrapper : vWrapperArr) {
			Vertex<Data> v = vWrapper.vertex;
			Vector<Vertex<Data>> vector = new Vector<Vertex<Data>>();
			vector.add(v);
			Edge<String>[] eArr = v.getEdges();
			for (Edge<String> e : eArr) {
				if(!e.getVertices()[1].isMarked() && !vector.contains(e.getVertices()[1]))
					vector.add(e.getVertices()[1]);
				if(!e.getVertices()[0].isMarked() && !vector.contains(e.getVertices()[0]))
					vector.add(e.getVertices()[0]);
			}
			
			if(vector.size() < vWrapper.order)
				continue;

			// Generate permutations
			Combination combination = new Combination(vector.size(),
					vWrapper.order);
			debug("Creating combinations : "+vector.size()+", "+vWrapper.order);
			while (true) {
				int[] indexArr = combination.getNextCombination();
				if (indexArr == null) {
					break;
				}
				Vector<Vertex<Data>> tmpVector = new Vector<Vertex<Data>>();
				for (int i = 0; i < indexArr.length; i++)
					tmpVector.add(vector.get(indexArr[i]));

				if (checkDSCC(vector)) {
					out.println("\nNew DSCC: ");
					for (Vertex<Data> v1 : vector){
						v1.setMarked(true);
						out.println(v1.getData().toString());
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private boolean checkDSCC(Vector<Vertex<Data>> vertices) {
		debug("Check DSCC : ");
		
		for (Vertex<Data> vertex : vertices) {
			debug(vertex.getData().toString()+", ");
		}
		
		Vector<Vertex<Data>> verticesClone = (Vector<Vertex<Data>>) vertices
				.clone();
		for (Vertex<Data> vertex : vertices) {

			Edge<String>[] eArr = vertex.getEdges();
			if (eArr.length < vertices.size()-1){
				debug(" : failed array length check : "+eArr.length);
				return false;
			}
			
			for (Edge<String> e : eArr) {
				Vertex<Data> dstVertex = e.getVertices()[1];
				Vertex<Data> dstVertex1 = e.getVertices()[0];

				if (verticesClone.contains(dstVertex))
					verticesClone.remove(dstVertex);

				if (verticesClone.contains(dstVertex1))
					verticesClone.remove(dstVertex1);
			}
		}
		return verticesClone.size() == 0;
	}
}
