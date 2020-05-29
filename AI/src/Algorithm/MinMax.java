package Algorithm;

import java.util.LinkedList;
import Rules.Coordinate;

public class MinMax extends GraphBuilder{

	private final int maxn = 1000007;
	private boolean[] visited = new boolean[maxn];
	public LinkedList<Integer>[] graph;	

	public MinMax(LinkedList<Integer>[] graph) {
		this.graph = graph;
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
	}
	public void algorithm() {
		/*
		 * 1o -> Busca o último nó por DFS 
		 * 2o -> Seta os pesos fazendo o caminho contrário(ou seja, retira cada elemento da Stack)
		 * 3o -> Aplica DFS nos nós de profundidade e realiza as somatórias e retorna o melhor caminho 
		 * 
		 */
	}
	private void dfs(int u){

		visited[u] = true;

		if(!visited[u]){
			for (Integer v : graph[u]) {
				if(!visited[v]){
					dfs(v);
				}
			}
		}

	}
	public int min() {
		return -1;
	}
	public int max() {
		return 1;
	}
	
}