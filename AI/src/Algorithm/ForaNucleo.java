package Algorithm;

import java.util.LinkedList;

import Rules.BoardOutOfBoundsException;
import Rules.Coordinate;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException; 

public class ForaNucleo {
    public Game board;
    public GraphBuilder gb;

    public ForaNucleo(Game board) {
        this.board = board;
        this.gb = new GraphBuilder();
        this.gb.createGraph(board);
    }

    public GraphBuilder getGraph() {
        return gb;
    }

    private void createSon(Game g) throws BoardOutOfBoundsException, UnexpectedPieceException,
            IllegalMoveException, CloneNotSupportedException {
        
        g.allLegal();
        LinkedList<Game> list = new LinkedList<Game>();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                for (Coordinate c : g.getBoard().getStateBoard()[i][j]) {
                    if(c == null) continue;
                    Game copy = (Game)g.clone();
                    if (g.getTurn() == g.getBoard().isWhite(i,j)) {
                        copy.move(i, j, c.getPos_i(), c.getPos_j()); 
                    }
                    list.add(copy);
                }
            }
        }
        gb.createGraph(g, list);
        
    }
    public void createGraph(Game g, int dpt, int depth) throws Exception {
        if (dpt < depth) {
            createSon(g);
            int x = gb.getNode(g);
            if(x!=-1){
                for (Integer h : gb.getSon(x)) {
                    g.isCheckMateBlack();
                    g.isCheckMateWhite();
                    if (g.getIsCheckMateBlack()) {
                        gb.setWeight(h, Integer.MAX_VALUE);
                    }
                    else if (g.getIsCheckMateWhite()) {
                        gb.setWeight(h, Integer.MIN_VALUE);
                    }
                    else {
                        createGraph(gb.getGame(h), (dpt+1), depth); 
                    }
                }
            }
        }
        else if(dpt==depth){
            createSon(g);
            int y = gb.getNode(g);
            if(y!=-1){
                for (int h : gb.getSon(y)) {
                    Game x = gb.getGame(h);
                    Evaluate e = new Evaluate(x.getBoard());
                    if (x.getIsCheckMateBlack()) {
                        gb.setWeight(h, Integer.MAX_VALUE);
                    }
                    else if (x.getIsCheckMateWhite()) {
                        gb.setWeight(h, Integer.MIN_VALUE);
                    }
                    else {
                        gb.setWeight(h, e.total());
                    }
                }
            }
        }else{
            return;
        }
    }

}