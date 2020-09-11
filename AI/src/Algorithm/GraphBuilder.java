package Algorithm;

import java.util.LinkedList;
import java.util.Queue;

import Rules.Board;

public class GraphBuilder {
    
    private final int maxn = 10000010;
    //private static int depth = 0;
    private int countNodes = 1; 

    @SuppressWarnings("unchecked")
    private LinkedList<Integer>[] graph = new LinkedList[maxn];//Grafo NÓSxARESTA
	private LinkedList<Board> nodesPos = new LinkedList<>();//Lista com as coordenadas das peças
	private double[] nodeWeights = new double[maxn];
	private int[] depthNode = new int[maxn];//profundidades dos nós

    public GraphBuilder(){
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
            nodeWeights[i] = 0;
        }
      //  depthNode[0] = 0;
    }
    public void createGraph(Board c){
        //graph[0].add(0);
        nodesPos.add(c);
        depthNode[0] = 0;
    }
    public void createGraph(int nodeFather, LinkedList<Board> u){
        if(nodeFather==-1) return;
        for (int i = 0; i < u.size(); i++) {
            //countNodes++;
            nodesPos.add(u.get(i));
            graph[nodeFather].add(countNodes);
            depthNode[countNodes] = depthNode[nodeFather] + 1;
            countNodes++;
		}
    }
	public void setWeight(int u, double weight){
		nodeWeights[u] = weight;
	}
	public double getWeight(int u){
		return nodeWeights[u];
	}

	public LinkedList<Integer>[] getGraph(){
		return graph; 
    }
    public int HowManyNodes(){
        return countNodes;
    }
    
    public int getDepth(){
        boolean[] mark = new boolean[maxn];
        for(int i=0;i<mark.length;i++) mark[i] = false;
        Queue<Integer> fila = new LinkedList<>();
        int[] dist = new int[maxn];
        for(int i=0;i<dist.length;i++) dist[i]=0;
        fila.add(0);
        mark[0] = true;
        while(!fila.isEmpty()){
            Integer u = fila.poll();
            for (Integer v : graph[u]) {
                if(!mark[v]){
                    dist[v] =dist[u]+1;
                    fila.add(v);
                    mark[v] = true;
                }  
            }
        }  
        return dist[countNodes];
    }
	public int getDepthFromNode(int u){
		return depthNode[u];
	}
	public Board getBoard(int node){
		return nodesPos.get(node); 
	}
    public LinkedList<Integer> getSon(int u){
        return graph[u];
    }
	public void printGraph(){
        for (int i = 0; i < countNodes; i++) {
            System.out.print("The node:" + i + " has connections with this Boards-> ");
            for (int j = 0; j < graph[i].size(); j++) {
                System.out.print(graph[i].get(j) + " ");
            }
            System.out.println();
        }
    }
    public void printWeightGraph(){
        for (int i = 0; i < countNodes; i++) {
            System.out.print("The node:" + i + " has this weight-> ");
            System.out.println(nodeWeights[i]);
        }
	}


    /*EXEMPLOS
    public static void main(String[] args) {
        
        GraphBuilder g = new GraphBuilder();
        Board board = new Board();
        Board board1 = new Board();
        Board board2 = new Board();
        
        Board p1 = new Board(board);
        Board p2 = new Board(board1);
        Board p3 = new Board(board2);
        g.createGraph(p1);

        LinkedList<Board> ali = new LinkedList<Board>();
		ali.add(p2);
        ali.add(p3);
        
        g.createGraph(p1, ali);
     
        System.out.println(g.getDepth());
        g.printGraph();
    

    }
    public static void main(String[] args) {
        GraphBuilder gb = new GraphBuilder();

        gb.createGraph(new Board(new Board()));

        LinkedList<Board> ali = new LinkedList<Board>();
		ali.add(new Board(new Board()));
        ali.add(new Board(new Board()));

        gb.createGraph(new Board(new Board()), ali);
        System.out.println(gb.getDepth());
        gb.printGraph();
    
    }*/
}