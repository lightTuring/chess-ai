package Algorithm;

import java.util.LinkedList;

import Rules.Game;


public class GraphBuilder {
    
    private final int maxn = 10000010;
    //private static int depth = 0;
    private static int countNodes = 1; 

    @SuppressWarnings("unchecked")
    private LinkedList<Integer>[] graph = new LinkedList[maxn];//Grafo NÓSxARESTA
	private LinkedList<Game> nodesPos = new LinkedList<>();//Lista com as coordenadas das peças
	private double[] nodeWeights = new double[maxn];
	//private int[] depthNode = new int[maxn];//profundidades dos nós
    private boolean[] mark = new boolean[maxn];

    public GraphBuilder(){
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
            nodeWeights[i] = 0;
        }
      //  depthNode[0] = 0;
    }
    public void createGraph(Game c){
        graph[0].add(0);
        nodesPos.add(c);
    }
    public void createGraph(Game father, LinkedList<Game> u){
        int nodeFather = BrutalSearch(father);
        for (int i = 0; i < u.size(); i++) {
            nodesPos.add(u.get(i));
            graph[countNodes].add(nodeFather);
            graph[countNodes].add(countNodes);
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
    private int dfsDpt(int u, int dpt){
        mark[u] = true;
        for (int v : graph[u]) {
            if(!mark[v]){
                dfsDpt(u, (dpt+1));
            }
        }
        return dpt;
    }
    public int getDepth(){
        for(int i=0;i<mark.length;i++) mark[i] = false;
        return dfsDpt(0, 0);
    }/*
	public int getDepthFromNode(int u){
		return depthNode[u];
	}*/
	public Game getGame(int node){
		return nodesPos.get(node); 
	}
	public int getNode(Game c){
		return BrutalSearch(c);
	}
    public LinkedList<Integer> getSon(int u){
        return graph[u];
    }
	public void printGraph(){
        for (int i = 0; i < countNodes; i++) {
            System.out.print("The node:" + i + " has connections with this Games-> ");
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

    private int BrutalSearch(Game c){
        
        for (int i = 0; i < nodesPos.size(); i++) {
            if(nodesPos.get(i).equals(c)){
                return i;
            }
        }

        return -1;
    }
    /*
    public static void main(String[] args) {
        
        GraphBuilder g = new GraphBuilder();
        g.createGraph(new Game(0, 0));

        LinkedList<Game> ali = new LinkedList<Game>();
		ali.add(new Game(1, 0));
        ali.add(new Game(1,1));
        
        g.createGraph(new Game(0, 0), ali);
        LinkedList<Game> aqui = new LinkedList<Game>();
		aqui.add(new Game(4, 4));
        g.createGraph(new Game(1, 0), aqui);

        System.out.println(g.getDepth());
        g.printGraph();
    

    }*/

}