package Algorithm;

import java.util.ArrayList;

public class Graph {

	private ArrayList<Integer>[] graph;
	
	public Graph(int depth) {
		this.graph = new ArrayList[depth];
		for (int i = 0; i < graph.length; i++) {
			this.graph[i] = new ArrayList<Integer>();
		}
	}
	
	public void addElements(int node, int ... elements) {
		for (int i : elements) {
			this.graph[node].add(i);
		}
	}
	
	public ArrayList<Integer>[] graph(){
		return this.graph;
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
