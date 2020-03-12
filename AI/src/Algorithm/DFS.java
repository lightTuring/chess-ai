package Algorithm;

import java.util.ArrayList;

public class DFS {

	private int nodes;
	
	private ArrayList<Integer>[] graph = new ArrayList[nodes];
	
	//private int[][] graph = new int[nodes][branches];
	private int[] visited = new int[graph.length];
	
	public DFS(ArrayList<Integer>[] graph, int nodes) {
		this.nodes = nodes;
		this.graph = graph;
	}
	
	public void print() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				System.out.print(this.graph[i].get(j));
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int nodes = 2;
		ArrayList<Integer>[] graph = new ArrayList[nodes];
		
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		graph[0].add(1);
		graph[1].add(2);
		graph[1].add(2);
		
		
		DFS dfs = new DFS(graph, nodes);
		
		dfs.print();
	}
	
}
