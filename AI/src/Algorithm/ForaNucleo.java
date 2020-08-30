package Algorithm;

import java.util.HashSet;
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
                    if ((g.getTurn() == g.getBoard().isWhite(i,j)) && g.getBoard().isAPiece(i, j)) {
                        copy.move(i, j, c.getPos_i(), c.getPos_j());
                        list.add(copy);
                    }
                    
                }
            }
        }
        if (list.size() != 0) {
            gb.createGraph(g, list);
        }
    }
    @SuppressWarnings("unchecked")
    public void createGraph(int depth) throws Exception {
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(gb.getNode(board));
        while (!q.isEmpty()) {
            int h = q.remove();
            if (gb.getGame(h).getIsCheckMateBlack()) {
                gb.setWeight(h, Integer.MAX_VALUE);
            }
            else if (gb.getGame(h).getIsCheckMateWhite()) {
                gb.setWeight(h, Integer.MIN_VALUE);
            }
            if(gb.getDepthFromNode(h) == depth) {
                if (gb.getGame(h).getIsCheckMateBlack()) {
                    gb.setWeight(h, Integer.MAX_VALUE);
                }
                else if (gb.getGame(h).getIsCheckMateWhite()) {
                    gb.setWeight(h, Integer.MIN_VALUE);
                }
                else {
                    Evaluate e = new Evaluate(gb.getGame(h).getBoard());
                    gb.setWeight(h, e.total());
                }
            }
            else {
                createSon(gb.getGame(h));
                for (int c : gb.getSon(h)) {
                    q.add(c);
                }
            }
        }
        

    }

    public void createGraph(Game g, Game f, int dpt, int depth) throws Exception {
        if (dpt < depth) {
            if(g.equals(f)) return;
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
                        createGraph(gb.getGame(h), g, (dpt+1), depth); 
                    }
                }
            }
        }
    }

}