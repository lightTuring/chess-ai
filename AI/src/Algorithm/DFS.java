package Algorithm;

import java.util.*;

public class DFS {
	
	private ArrayList<Integer>[] graph; // -> [nó][conexões]
	private boolean[] visited;
	private ArrayList<Integer> path = new ArrayList<Integer>();
	
	public DFS(ArrayList<Integer>[] graph, int nodes) {
		this.graph = graph;
		this.visited = new boolean[nodes];
        // O valor inicial de visited vai ser sempre uma array de false.
        for(int i = 0; i < nodes; i++) {
        	this.visited[i] = false;
        }
	}
	
	public void print() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				System.out.print(this.graph[i].get(j));
			}
			System.out.println();
		}
	}
	
	public Integer Search(int node, int elementForSearch) {
		visited[node] = true;
		path.add(node);
		if(node == elementForSearch) {
			return node;
		}else {
			for (int i = 0; i < graph[node].size(); i++) {
				if(visited[graph[node].get(i)] == false) {
					return Search(graph[node].get(i), elementForSearch);
				}
				else if(visited[graph[node].get(i)] == true && i == graph[node].size() - 1) {
					path.remove(path.get(path.size() - 1));	
					return Search(path.get(path.size() - 1), elementForSearch);
				}
			}
		}
		return null;
		
	}
	public Set<Integer> getPath(){
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < this.path.size(); i++) 
			set.add(path.get(i));
		
		return set;
	}
	public ArrayList<Integer> getPathProcess() {
		return path;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		int nodes = 3;
		ArrayList<Integer>[] graph = new ArrayList[nodes];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		//graph = [[1, 2],[0],[0]] -> [nó][conexões]
		graph[0].add(1);
		graph[0].add(2);
		graph[1].add(0);
		graph[2].add(0);
		
		
		DFS dfs = new DFS(graph, nodes);
		dfs.print();
		System.out.println(dfs.Search(0, 2));
		System.out.println(dfs.getPath());
		System.out.println(dfs.getPathProcess());
	}
	
}
