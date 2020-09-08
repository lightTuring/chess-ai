package Algorithm;

import java.util.LinkedList;

import Rules.Board;

public class AlphaBeta {
    public LinkedList<Integer>[] graph;	
	private GraphBuilder gb;
	private final int inf = 1000000007;

	public AlphaBeta(GraphBuilder gb) {
		this.gb = gb;
		graph = gb.getGraph();	
	}	
	private double algorithm(int node, int depth, double a, double b, boolean isMaximizing) {
		if(depth == 0){return gb.getWeight(node);}
		if(isMaximizing){
			double value = -inf;
			for (Integer child : graph[node]) {
                value = Math.max(value, algorithm(child, (depth-1), a, b, false));	
                a = Math.max(a, value);
                if(a >= b) break;
			}
			gb.setWeight(node, value);
			return value;
		}else{
			double value = inf;
			for (Integer child : graph[node]) {
                value = Math.min(value, algorithm(child, (depth-1), a, b, true));
                b = Math.min(b, value);
                if(b <= a) break;	
			}
			gb.setWeight(node, value);
			return value;
		}
		
	}
	//algoritmo guloso
	public Board bestPlaying(int node, int depth, boolean isMaximizing) {

		double search = algorithm(node, depth, (double)-inf, (double)inf, isMaximizing);

		//Zero-based
		LinkedList<Integer> son = gb.getSon(0);
		
		int x=0;

		for(int s : son){
			if(gb.getWeight(s)==search){
				x = s;
				break;
			}
		}
		return gb.getBoard(x);
	}
}
