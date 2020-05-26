package Algorithm;

import java.util.LinkedList;

import Rules.Coordinate;

/*
 * ESCOLHE A PEÇA, ADICIONA NO GRAFO E CONTA A PROFUNDIDADE 
 * PERCORRE UM FOR PARA TODAS AS PEÇAS JOGAREM
 * REPETE
 * 
 * Grafo do tipo Árvore
 * 
 */

public class GraphBuilder {

	private int depth = 1;
	private LinkedList<LinkedList<Coordinate>> graph = new LinkedList<LinkedList<Coordinate>>();
	private static Coordinate lastMovement = null;
	
	public void createGraph(int node, Coordinate currentPiece, LinkedList<Coordinate> plays) {
		graph.add(node, plays);
		if(lastMovement != null && !hasThisElement(lastMovement, node)) graph.get(node).add(0, lastMovement);
		else lastMovement = currentPiece;;
		depth++;
	}
	public int getDepth() {
		return depth;
	}
	public LinkedList<LinkedList<Coordinate>> getSkeleton(){
		return graph;
	}

	public void printGraph() {
		for (int i = 0; i < graph.size(); i++) {
			System.out.print("The node:" + i + " has connections with this coordinates-> ");
			for (int j = 0; j < graph.get(i).size(); j++) {
				System.out.print("("+graph.get(i).get(j).getPos_i()+", "+graph.get(i).get(j).getPos_j()+") ");
			}
			System.out.println();
		}
	}
	private boolean hasThisElement(Coordinate c, int node) {
		for (int i = 0; i < graph.get(node).size(); i++) {
			if(c.equals(graph.get(node).get(i))) {
				return true;
			}
		}
		return false;
	}
		
	public static void main(String[] args) {
							/*TESTE*/
		GraphBuilder g = new GraphBuilder();
		
		LinkedList<Coordinate> ali = new LinkedList<Coordinate>();
		ali.add(new Coordinate(1, 0));
		ali.add(new Coordinate(1,1));
		
		g.createGraph(0, new Coordinate(0, 0), ali);
		
		LinkedList<Coordinate> aqui = new LinkedList<Coordinate>();
		aqui.add(new Coordinate(4, 4));
		for (int i = 1; i <= 2; i++) {
			
			g.createGraph(i, ali.get(i-1), aqui);
		}
		System.out.println(g.getDepth());
		g.printGraph();
		
	}
}