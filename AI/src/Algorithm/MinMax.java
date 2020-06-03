package Algorithm;

import java.util.*;
import Rules.*;

public class MinMax extends GraphBuilder{

	//Variáveis de Min Max
	public LinkedList<Integer>[] graph;	
	private GraphBuilder gb;
	private final int inf = 1000000007;
	private final int maxn = 100000010;
	private int value;
	private boolean[] mark;
	LinkedList<Integer>[] path;
	LinkedList<Integer>[] pathWeight;

	@SuppressWarnings("unchecked")
	public MinMax(GraphBuilder gb) {
		this.gb = gb;
		graph = gb.getGraph();	
		mark = new boolean[maxn];
		path = new LinkedList[maxn];
		pathWeight = new LinkedList[maxn];

		for(int i = 0; i < maxn; i++) {
			path[i] = new LinkedList<>();
			pathWeight[i] = new LinkedList<>();
		}
	}	
	public int algorithm(int node, int depth, boolean isMaximizing) {
		if(depth == gb.getDepth()){return gb.getWeight(node);}
		if(isMaximizing){
			value = -inf;
			for (Integer child : graph[node]) {
				value = Math.max(value, algorithm(child, (depth+1), false));	
			}
		}else{
			value = inf;
			for (Integer child : graph[node]) {
				value = Math.min(value, algorithm(child, (depth+1), true));	
			}
		}
		gb.setWeight(node, value);
		return value;
	}

	private void dfs(int u, int dpt, int dm){
		mark[u] = true;
		path[dm].add(u);
		pathWeight[dm].add(gb.getWeight(u)); //atribui o peso de u numa list set de set. Quando dpt = depth, atualiza a dimensão do array
		if(dpt == gb.getDepth()) dm++;

		for (Integer v : graph[u]) {
			if(!mark[v]){
				dfs(v, ++dpt, dm);
			}
		}
	}

	public LinkedList<Coordinate> betterPath(){
		dfs(0, 0, 0);
		LinkedList<Coordinate> better_path = new LinkedList<>();
		//Soma os pesos, busca o melhor caminho e retorna.

		//Soma os pesos e busca a melhor resposta
		int max = -inf;
		for (int i = 0; i < pathWeight.length; i++) {
			int sum = 0;
			for (int j = 0; j < pathWeight[i].size(); j++) {
				sum+=pathWeight[i].get(j);
			}
			max = Math.max(max, sum);
		}
		//Busca o índice da melhor resposta
		int ii = maxn - 1;
		for (int i = 0; i < pathWeight.length; i++) {
			int sum = 0;
			for (int j = 0; j < pathWeight[i].size(); j++) {
				sum+=pathWeight[i].get(j);
			}
			if(sum == max){
				ii = i;
				break;
			}
		}
		//Converte para uma lista com as coordenadas dos movimentos.
		for (int i = 0; i < path[ii].size(); i++) {
			better_path.add(gb.getCoordinate(path[ii].get(i)));
		}
		return better_path;
	}
}