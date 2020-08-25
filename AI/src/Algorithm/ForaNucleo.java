package Algorithm;

import Rules.Game;

public class ForaNucleo {
    public Game board;
    public GraphBuilder gb;
    
    public ForaNucleo(Game board) {
        this.board = board;
        this.gb = new GraphBuilder();
    }

    public GraphBuilder getGraph() {
        return gb;
    }

    public void createGraph(int depth) {
        
    }

}