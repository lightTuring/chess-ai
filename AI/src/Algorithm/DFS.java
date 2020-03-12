package Algorithm;

public class DFS {

	private int nodes;
	private int branches;
	
	private int[][] graph = new int[nodes][branches];
	private int[] visited = new int[graph.length];
	
	public DFS(int[][] graph, int nodes, int branches) {
		this.nodes = nodes;
		this.branches = branches;
		
		this.graph = graph;
	}
	
}
