package com.programming.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.CharBuffer;

import com.programming.graph.Edge;
import com.programming.graph.Graph;
import com.programming.graph.Vertex;
import com.programming.graph.algos.StrongConnectedComponents;
import com.programming.graph.algos.StrongConnectedComponents.Data;
import com.programming.permutation_combination.Combination;

class LatLongData{
	String label;
	double lat;
	double lng;
	public LatLongData(String label, double lat, double lng)
	{
		this.label = label;
		this.lat = lat;
		this.lng = lng;
	}
}
public class TSPLibFile {

	Graph g = null;
	
	public TSPLibFile(URL url) {
		try {
			g = new Graph(url.toString());
			
			//1. read the data and build graph
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String strLine;

			while ((strLine = in.readLine()) != null) {
				
				char c = strLine.charAt(0);
				if(c >= '0' && c<='9')
				{
					String [] fields = strLine.split(" ");
					Vertex<LatLongData> vertex = new Vertex<LatLongData>(new LatLongData(fields[0], Double.parseDouble(fields[1]), Double.parseDouble(fields[2])));
					g.add(vertex);
				}
			}
			in.close();
			
			
			//2. Update the distances between each vertex
			Vertex<LatLongData> []vArr = g.getVertices();
			Combination combination = new Combination(vArr.length, 2);
			int [] indexArr ;
			while((indexArr= combination.getNextCombination()) !=null)
			{
				LatLongData data= vArr[indexArr[0]].getData();
				LatLongData data1 = vArr[indexArr[1]].getData();
				 double RRR = 6378.388;

				 double q1 = Math.cos( data.lng - data1.lng ); 
				 double q2 = Math.cos( data.lat - data1.lat ); 
				 double q3 = Math.cos( data.lat + data1.lat ); 
				 double dij = (int) ( RRR * Math.acos( 0.5*((1.0+q1)*q2 - (1.0-q1)*q3) ) + 1.0);
				 new Edge<String>(vArr[indexArr[0]], vArr[indexArr[1]], dij);
				 //System.out.println("Edge for "+indexArr[0]+","+indexArr[1]+" : "+dij);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Graph getGraph()
	{
		return g;
	}

}
