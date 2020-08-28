package Algorithm;

import java.util.LinkedList;
import java.util.Queue;

import Rules.Game;


public class GraphBuilder {
    
    private final int maxn = 10000010;
    //private static int depth = 0;
    private int countNodes = 1; 

    @SuppressWarnings("unchecked")
    private LinkedList<Integer>[] graph = new LinkedList[maxn];//Grafo NÓSxARESTA
	private LinkedList<Game> nodesPos = new LinkedList<>();//Lista com as coordenadas das peças
	private double[] nodeWeights = new double[maxn];
	private int[] depthNode = new int[maxn];//profundidades dos nós

    public GraphBuilder(){
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
            nodeWeights[i] = 0;
        }
      //  depthNode[0] = 0;
    }
    public void createGraph(Game c){
        //graph[0].add(0);
        nodesPos.add(c);
        depthNode[0] = 0;
    }
    public void createGraph(Game father, LinkedList<Game> u){
        int nodeFather = BrutalSearch(father);
        if(nodeFather==-1) return;
        for (int i = 0; i < u.size(); i++) {
            //countNodes++;
            nodesPos.add(u.get(i));
            graph[nodeFather].add(countNodes);
            countNodes++;
            depthNode[countNodes] = getDepth();
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
        return dist[countNodes-1];
    }
	public int getDepthFromNode(int u){
		return depthNode[u];
	}
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