package Algorithm;

import java.util.LinkedList;
import Rules.Coordinate;


public class GraphBuilder {
    
    private final int maxn = 1000007;
    private static int depth = 0;
    private static int countNodes = 1; 

    @SuppressWarnings("unchecked")
    private LinkedList<Integer>[] graph = new LinkedList[maxn];//Grafo NÓSxARESTA
	private LinkedList<Coordinate> nodesPos = new LinkedList<>();//Lista com as coordenadas das peças
	private int[] nodeWeights = new int[maxn];
	private int[] depthNode = new int[maxn];//profundidades dos nós

    public GraphBuilder(){
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
            nodeWeights[i] = 0;
        }
    }
    public void createGraph(Coordinate c){

        graph[0].add(0);
        nodesPos.add(c);
		depthNode[0] = depth;
        depth++;
    }
    public void createGraph(Coordinate father, LinkedList<Coordinate> u){
        int nodeFather = BrutalSearch(father);
        for (int i = 0; i < u.size(); i++) {
            nodesPos.add(u.get(i));
            graph[countNodes].add(nodeFather);
            graph[countNodes].add(countNodes);
            countNodes++;
		}
		depthNode[nodeFather] = depth;
        depth++;
    }

	public void setWeight(int u, int weight){
		nodeWeights[u] = weight;
	}
	public int getWeight(int u){
		return nodeWeights[u];
	}

	public LinkedList<Integer>[] getGraph(){
		return graph; 
	}
    public int getDepth(){
        return depth;
	}
	public int getDepthFromNode(int u){
		return depthNode[u];
	}
	public Coordinate getCoordinate(int node){
		return nodesPos.get(node); 
	}
	public int getNode(Coordinate c){
		return BrutalSearch(c);
	}
    public LinkedList<Integer> getSon(int u){
        return graph[u];
    }
	public void printGraph(){
        for (int i = 0; i < countNodes; i++) {
            System.out.print("The node:" + i + " has connections with this coordinates-> ");
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

    private int BrutalSearch(Coordinate c){
        
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
        g.createGraph(new Coordinate(0, 0));

        LinkedList<Coordinate> ali = new LinkedList<Coordinate>();
		ali.add(new Coordinate(1, 0));
        ali.add(new Coordinate(1,1));
        
        g.createGraph(new Coordinate(0, 0), ali);
        LinkedList<Coordinate> aqui = new LinkedList<Coordinate>();
		aqui.add(new Coordinate(4, 4));
        g.createGraph(new Coordinate(1, 0), aqui);

        System.out.println(g.getDepth());
        g.printGraph();
    

    }*/

}