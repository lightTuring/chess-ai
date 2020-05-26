package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import Rules.Coordinate;

public class GraphBuilder {

	private int depth = 0;
	private LinkedList<LinkedList<Coordinate>> graph = new LinkedList<LinkedList<Coordinate>>();
	private static Coordinate lastMovement = null;
	
	/*
	 * ESCOLHE A PEÇA, ADICIONA NO GRAFO E CONTA A PROFUNDIDADE 
	 * PERCORRE UM FOR PARA TODAS AS PEÇAS JOGAREM
	 * REPETE
	 * 
	 */
	
	public GraphBuilder() {
		
	}
	public void createGraph(int node, Coordinate currentPiece, LinkedList<Coordinate> plays) {
		graph.add(node, plays);
		if(lastMovement != null) graph.get(node).add(0, lastMovement);
		else lastMovement = currentPiece;;
		depth++;
	}
	public int getDepth() {
		return depth;
	}
	/*
	public void addElements(int node, int ... elements) {
		for (int i : elements) {
			this.graphConnections[node].add(i);
		}
	}*/
		
	public static void main(String[] args) {
		
	}
}