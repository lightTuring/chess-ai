package Algorithm;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Integer>[] graphConnections;
	
	public Graph(int nodes) {
		this.graphConnections = new ArrayList[nodes];
		for (int i = 0; i < graphConnections.length; i++) {
			this.graphConnections[i] = new ArrayList<Integer>();
		}
	}
	
	public void addElements(int node, int ... elements) {
		for (int i : elements) {
			this.graphConnections[node].add(i);
		}
	}
	
	public ArrayList<Integer>[] graphConnections(){
		return this.graphConnections;
	}
	/*
	public static void main(String[] args) {
		Graph g = new Graph(2);
		g.addElements(0, 1,2,2,3,4,5);
		g.addElements(1, 2,3,4,5);
		
		ArrayList<Integer>[] graph = g.graph();
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				System.out.print(graph[i].get(j));
			}
			System.out.println();
		}
	}*/
}
