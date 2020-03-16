package Algorithm;

import java.util.ArrayList;

public class Graph {

	private int depth;
	private ArrayList<Integer>[] graph;
	
	public Graph(int depth) {
		this.depth = depth;
		this.graph = new ArrayList[depth];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<Integer>();
		}
	}
	
	public void addElements(int node, int ... elements) {
		for (int i : elements) {
			graph[node].add(i);
		}
	}
	
	public ArrayList<Integer>[] graph(){
		return this.graph;
		
	}
}
