package Algorithm;

import java.util.*;

public class MinMax extends GraphBuilder{

	//Variáveis de Min Max
	public LinkedList<Integer>[] graph;	
	private GraphBuilder gb;
	private final int inf = 1000000007;
	int lim = 5;//aleatório 
	private int value;

	public MinMax(GraphBuilder gb) {
		this.gb = gb;
		graph = gb.getGraph();		
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
				value = Math.min(value, algorithm(child, (depth+1), false));	
			}
		}
		return value;
	}
	
}