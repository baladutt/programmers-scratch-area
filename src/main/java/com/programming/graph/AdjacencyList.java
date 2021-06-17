package com.programming.graph;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import com.programming.graph.algos.StrongConnectedComponents.Data;

public class AdjacencyList {
	
	Hashtable<Vertex, ArrayList<Vertex>> adjList = new Hashtable<Vertex, ArrayList<Vertex>>();
	
	public AdjacencyList(Graph g){
	
		for(Vertex v: g.getVertices()){
			ArrayList<Vertex> adjacentVertices = new ArrayList<Vertex>();
			for(Edge e: v.getEdges()){
				if(g.isDirected()){
					Vertex u = e.getDestinationVertex();
					if(!u.equals(v)){
						adjacentVertices.add(e.getOtherVertex(v));
					}
				}else{
					adjacentVertices.add(e.getOtherVertex(v));
				}
			}
			adjList.put(v,adjacentVertices);
			
		}
	}
	
	public void print(PrintStream out){
		
		for(Vertex v: adjList.keySet()){
			out.print(""+v+" : ");
			for(Vertex u : adjList.get(v)){
				out.print(" "+u);
			}
			out.println();
		}
	}
	
	public static void main(String []args){
		Vertex<Data> vRed = new Vertex<Data>(new Data("Redville"));
		Vertex<Data> vBlue = new Vertex<Data>(new Data("Blueville"));
		Vertex<Data> vGreen = new Vertex<Data>(new Data("Greenville"));
		Vertex<Data> vOrange = new Vertex<Data>(new Data("Orangeville"));
		Vertex<Data> vPurple = new Vertex<Data>(new Data("Purpleville"));

		new Edge<String>(vRed, vGreen, 10);
		new Edge<String>(vRed, vBlue, 5);
		new Edge<String>(vRed, vOrange, 8);
		new Edge<String>(vBlue, vPurple, 7);
		new Edge<String>(vOrange, vPurple, 2);

		Graph g = new Graph();
		g.setWeighted(true).add(vRed).add(vBlue).add(vGreen).add(vOrange).add(vPurple);

		g.print(System.out);
		
		AdjacencyList al = new AdjacencyList(g);
		al.print(System.out);
		
	}
}
