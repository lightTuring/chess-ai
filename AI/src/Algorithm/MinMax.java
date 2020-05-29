package Algorithm;

import java.util.LinkedList;

import Rules.Coordinate;

public class MinMax {

	private boolean[] visited = new boolean[1000];
	public LinkedList<Integer>[] graph = new LinkedList[10];

	public MinMax() {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new LinkedList<>();
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
