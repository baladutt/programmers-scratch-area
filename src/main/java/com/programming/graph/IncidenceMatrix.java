package com.programming.graph;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import com.programming.graph.algos.StrongConnectedComponents.Data;

public class IncidenceMatrix {
	
	ArrayList<Vertex> verticesList = new ArrayList<Vertex>();
	boolean matrix[];
	
	public IncidenceMatrix(Graph g){
	
		int size = g.getVertices().length;
		matrix = new boolean[size*size];
		for(int i=0;i<matrix.length;i++){
			matrix[i]=false;
		}
		
		for(Vertex v: g.getVertices()){
			verticesList.add(v);
		}
		
		for(Vertex v: g.getVertices()){
			
			for(Edge e: v.getEdges()){
				if(g.isDirected()){
					Vertex u = e.getDestinationVertex();
					if(!u.equals(v)){
						matrix[verticesList.indexOf(u)*size+verticesList.indexOf(v)]=true;
					}
				}else{
					matrix[verticesList.indexOf(v)*size+verticesList.indexOf(e.getOtherVertex(v))]=true;
				}
			}
			
		}
	}
	
	public void print(PrintStream out){
		
		int size = (int)Math.sqrt(matrix.length);
		for(Vertex v: verticesList){
			out.print(" "+v);
		}
		out.println();
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				out.print(" "+ (matrix[i*size+j]?1:0) );
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
		
		IncidenceMatrix im = new IncidenceMatrix(g);
		im.print(System.out);
		
	}
}
