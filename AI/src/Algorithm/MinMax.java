package Algorithm;

import java.util.*;
import Rules.*;

public class MinMax extends GraphBuilder{

	//Variáveis de Min Max
	public LinkedList<Integer>[] graph;	
	private GraphBuilder gb;
	private final int inf = 1000000007;

	public MinMax(GraphBuilder gb) {
		this.gb = gb;
		graph = gb.getGraph();	
	}	
	private double algorithm(int node, int depth, boolean isMaximizing) {
		if(depth == 0){return gb.getWeight(node);}
		if(isMaximizing){
			double value = -inf;
			for (Integer child : graph[node]) {
				value = Math.max(value, algorithm(child, (depth-1), false));	
			}
			gb.setWeight(node, value);
			return value;
		}else{
			double value = inf;
			for (Integer child : graph[node]) {
				value = Math.min(value, algorithm(child, (depth-1), true));	
			}
			gb.setWeight(node, value);
			return value;
		}
		
	}
	//algoritmo guloso
	public Board bestPlaying(int node, int depth, boolean isMaximizing) {

		double search = algorithm(node, depth, isMaximizing);

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
	/*
	public static void main(String[] args) {
		     
        GraphBuilder g = new GraphBuilder();
        Board board = new Board();
        Board board1 = new Board();
		Board board2 = new Board();
		
		Board board3 = new Board();
		Board board4 = new Board();
		Board board5 = new Board();
		Board board6 = new Board();
		//Board board7 = new Board();
		//Board board8 = new Board();


        Game p1 = new Game(board);
        Game p2 = new Game(board1);
        Game p3 = new Game(board2);
		
		Game p4 = new Game(board3);
        Game p5 = new Game(board4);
        Game p6 = new Game(board5);
		Game p7 = new Game(board6);

		g.createGraph(p1);

        LinkedList<Game> ali = new LinkedList<Game>();
		ali.add(p2);
        ali.add(p3);
        
        g.createGraph(p1, ali);
		
		LinkedList<Game> ali1 = new LinkedList<Game>();
		ali1.add(p4);
        ali1.add(p5);
        
        g.createGraph(p2, ali1);
		
		LinkedList<Game> ali2 = new LinkedList<Game>();
		ali2.add(p6);
        ali2.add(p7);
		
		g.createGraph(p3, ali2);
		
        System.out.println(g.getDepth());
		g.printGraph();


		g.setWeight(3, 10);
		g.setWeight(4, -10);
		
		g.setWeight(5, 10);
		g.setWeight(6, -1);

		MinMax m = new MinMax(g);
		
		System.out.println(m.algorithm(0, 1, true));
	}*/
}