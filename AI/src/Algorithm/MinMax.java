package Algorithm;

import java.util.*;
import Rules.*;

public class MinMax extends GraphBuilder{

	//Variáveis de Min Max
	public LinkedList<Integer>[] graph;	
	private GraphBuilder gb;
	private final int inf = 1000000007;
	private final int maxn = 100000010;
	//private boolean[] mark;
	LinkedList<Integer>[] path;
	LinkedList<Integer>[] pathWeight;

	@SuppressWarnings("unchecked")
	public MinMax(GraphBuilder gb) {
		this.gb = gb;
		graph = gb.getGraph();	
		//mark = new boolean[maxn];
		path = new LinkedList[maxn];
		pathWeight = new LinkedList[maxn];

		for(int i = 0; i < maxn; i++) {
			path[i] = new LinkedList<>();
			pathWeight[i] = new LinkedList<>();
		}
	}	
	private int algorithm(int node, int depth, boolean isMaximizing) {
		if(depth == 0 || depth == gb.getDepth()){return gb.getWeight(node);}
		if(isMaximizing){
			int value = -inf;
			for (Integer child : graph[node]) {
				value = Math.max(value, algorithm(child, (--depth), false));	
			}
			gb.setWeight(node, value);
			return value;
		}else{
			int value = inf;
			for (Integer child : graph[node]) {
				value = Math.min(value, algorithm(child, (--depth), true));	
			}
			gb.setWeight(node, value);
			return value;
		}
		
	}
		//algoritmo guloso
	public Coordinate bestPlaying(int node, int depth, boolean isMaximizing){
		algorithm(node, depth, isMaximizing);

		//Zero-based
		LinkedList<Integer> son = gb.getSon(0);
		
		int ans = -inf;//resposta gulosa
		int aux = -inf;

		int u = maxn;//nó resposta

		for(int i=0; i < son.size(); i++){
			if(son.get(i) == 0) continue; //segurança
			ans = Math.max(ans, gb.getWeight(son.get(i)));
			if(ans != aux){
				u = son.get(i);
				aux = ans;
			}
		}

		return gb.getCoordinate(u);
	}
	/*
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
	}*/
}