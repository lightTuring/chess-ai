package Algorithm;

import java.util.*;
import Rules.*;

public class MinMax extends GraphBuilder{

	//Variáveis de Min Max
	public LinkedList<Integer>[] graph;	
	private GraphBuilder gb;
	private final int inf = 1000000007;
	private final int maxn = 2147483647;

	public MinMax(GraphBuilder gb) {
		this.gb = gb;
		graph = gb.getGraph();	
	}	
	public double algorithm(int node, int depth, boolean isMaximizing) {
		//if(depth==0) return 0;
		if(depth == gb.getDepth()){return gb.getWeight(node);}
		if(isMaximizing){
			double value = -inf;
			for (Integer child : graph[node]) {
				value = Math.max(value, algorithm(child, (depth+1), false));	
			}
			gb.setWeight(node, value);
			return value;
		}else{
			double value = inf;
			for (Integer child : graph[node]) {
				value = Math.min(value, algorithm(child, (depth+1), true));	
			}
			gb.setWeight(node, value);
			return value;
		}
		
	}
		//algoritmo guloso
	public Game bestPlaying(int node, int depth, boolean isMaximizing) {

		double search = algorithm(node, depth, isMaximizing);

		/*for(int i=0;i<gb.HowManyNodes();i++){
			System.out.println("*"+i+"->"+gb.getWeight(i));
		}*/

		//Zero-based
		LinkedList<Integer> son = gb.getSon(0);
		
		int x=0;

		for(int s : son){
			if(gb.getWeight(s)==search){
				x = s;
				break;
			}
		}
		/*
		double ans = -inf;//resposta gulosa
		double aux = -inf;

		int u = -1;//nó resposta

		for(int i=0; i < son.size(); i++){
			if(son.get(i) == 0) continue; //segurança
			ans = Math.max(ans, gb.getWeight(son.get(i)));
			if(ans != aux){
				u = son.get(i);
				aux = ans;
			}
		}
		System.out.println("DEU CERTOO");*/
		return gb.getGame(x);
	}
}