package com.programming.graph.algos;

import java.io.PrintStream;

import com.programming.graph.Edge;
import com.programming.graph.Graph;
import com.programming.graph.Vertex;
import com.programming.permutation_combination.Permutation;
import com.programming.permutation_combination.PermutationTree;

/**
 * For usage look at com.ps.problems.TSP
 * 
 * @author bdutt
 *
 */
public class TravellingSalesman {

	private boolean bShowProgress = false;


	/**
	 * Brute force algorithm
	 * 
	 * @param graph
	 */
	public void findShortestPath(Graph graph) {

		Vertex[] vArr = graph.getVertices();

		Permutation permutation = new Permutation(vArr.length);

		double minDistance = Double.MAX_VALUE;
		int[] iArr;
		int nPermutations = 0;
		while ((iArr = permutation.getNextPermutation()) != null) {
			nPermutations++;
			// Compute distance
			double distance = 0;
			for (int i = 0; i < iArr.length - 1; i++) {
				Edge[] eArr = vArr[iArr[i]].getEdges();
				boolean bFoundEdge = false;
				for (Edge e : eArr) {
					if (e.getDestinationVertex() == vArr[iArr[i + 1]]
							|| e.getSourceVertex() == vArr[iArr[i + 1]]) {
						distance += e.getWeight();
						bFoundEdge = true;
						break;
					}

				}
				if (!bFoundEdge)
					System.out.println("Could not find an edge");
			}
			// System.out.print(" "+distance);
			if (distance < minDistance)
				minDistance = distance;
		}
		System.out.println("Checked permutations : " + nPermutations);
		System.out.println("Min distance  is : " + minDistance);
	}

	/**
	 * Be greedy - go to nearest neighbour. Supposed to give 25% lengthier distance than exact solution
	 * @param graph
	 * @param out
	 */
	@SuppressWarnings("rawtypes")
	public double findShortestPathGreedyAlgo(Graph graph, PrintStream out) {

		if (graph.isEmpty()) {
			out.println("Empty graph!");
			return Double.MAX_VALUE;// No path
		}

		Vertex[] vArr = graph.getVertices();

		for (Vertex v : vArr)
			v.setMarked(false);

		Vertex v = vArr[0];
		double distance = 0;

		while (!graph.isEmpty()) {
			Edge[] eArr = v.getEdges();
			Edge smallestEdge = null;
			double smallestDistance = Double.MAX_VALUE;

			for (Edge e : eArr) {
				if (e.getWeight() < smallestDistance) {
					Vertex v1 = e.getOtherVertex(v);
					if (!v1.isMarked()) {
						smallestDistance = e.getWeight();
						smallestEdge = e;
					}
				}
			}

			graph.remove(v);
			v.setMarked(true);

			if (null == smallestEdge) {
				if (!graph.isEmpty()) {
					out.println("Error : found a vertex from where nowhere to go! A strongly connected graph was assumed");
					out.println("Remaining graph : ");
					graph.printStats(out);
					return Double.MAX_VALUE;
				}
			} else {
				distance += smallestDistance;

				v = smallestEdge.getOtherVertex(v);
			}
		}
		System.out.println("Min distance  is : " + distance);
		return distance;
	}
	

	/**
	 * http://en.wikipedia.org/wiki/Branch_and_bound
	 * Uses greedy algo to get an initial bound
	 * @param graph
	 * @param out
	 */
	@SuppressWarnings("rawtypes")
	public void findShortestPathBranchAndBound(Graph graph, PrintStream out) {

		if (graph.isEmpty()) {
			out.println("Empty graph!");
			return;// No path
		}

		Vertex[] vArr = graph.getVertices();

		double minDistance = findShortestPathGreedyAlgo(graph, out);
		double greedyMinDistance = minDistance;
		
		PermutationTree permutationTree = new PermutationTree(vArr.length, vArr.length);
		int iPermArr [] = null;
		int nPermutations = 0;

		while ((iPermArr = permutationTree.getNextPermutation()) !=null) {
			
			nPermutations++;
			// Compute distance
			double distance = 0;
			for (int i = 0; i < iPermArr.length - 1; i++) {
				Edge[] eArr = vArr[iPermArr[i]].getEdges();
				boolean bFoundEdge = false;
				for (Edge e : eArr) {
					if (e.getDestinationVertex() == vArr[iPermArr[i + 1]]
							|| e.getSourceVertex() == vArr[iPermArr[i + 1]]) {
						distance += e.getWeight();
						bFoundEdge = true;
						break;
					}
					if(-1!=minDistance && distance>minDistance)
					{
						//Bound
						permutationTree.abandonSubTree(i);
						if(bShowProgress )
						System.out.println("X@"+i);
						i=iPermArr.length;//Break outer loop too
						bFoundEdge = true;
						break;
						
					}
				}
				if (!bFoundEdge)
					System.out.println("Could not find an edge");
			}
			// System.out.print(" "+distance);
			if (distance < minDistance)
			{
				minDistance = distance;
				System.out.println("minDistance updated : "+minDistance);
			}
			if(bShowProgress && nPermutations%100 == 0)
				System.out.print("+");
		}
		System.out.println("Checked permutations : " + nPermutations);
		System.out.println("Min distance  is : " + minDistance+", greedy was : "+greedyMinDistance);
	}
}
