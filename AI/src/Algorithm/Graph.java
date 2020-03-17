package Algorithm;

import java.util.ArrayList;

import Rules.Coordinate;

public class Graph {

	private ArrayList<Integer>[] graphConnections;
	private int[] graphWeights;
	private Coordinate[] graphAttributes;
	
	@SuppressWarnings("unchecked")
	public Graph(int nodes) {
		this.graphConnections = new ArrayList[nodes];
		this.graphWeights = new int[nodes];
		this.graphAttributes = new Coordinate[nodes];
		
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
	
	public void putWeight(int node, int weight) {
		this.graphWeights[node] = weight;
	}
	
	public int getWeight(int node) {
		return this.graphWeights[node];
	}
	
	public int[] getAllWeights() {
		return this.graphWeights;
	}
	
	public void putMoviment(int node, Coordinate c) {
		this.graphAttributes[node] = c;
	}
	
	public Coordinate getMoviment(int node) {
		return this.graphAttributes[node];
	}
	
	public int getDepth() {
		
		boolean[] visited = new boolean[this.graphConnections.length];
		
		for (int i = 0; i < visited.length; i++) 
			visited[i] = false;
		
		DFS dfs = new DFS(this.graphConnections, this.graphConnections.length, visited);
		dfs.Search(0, this.graphConnections.length - 1);
		
		return dfs.getPath().size();
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
