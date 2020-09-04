package Algorithm;
import java.util.LinkedList;

import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Coordinate;
import Rules.Game;
import Rules.IllegalMoveException;
import Rules.UnexpectedPieceException; 

public class ForaNucleo {
    public Board board;
    public GraphBuilder gb;

    public ForaNucleo(Board board) {
        this.board = board;
        this.gb = new GraphBuilder();
        this.gb.createGraph(board);
    }

    public GraphBuilder getGraph() {
        return gb;
    }

    private void createSon(Board b) throws BoardOutOfBoundsException, UnexpectedPieceException,
            IllegalMoveException, CloneNotSupportedException {
        
        Game g = new Game(b);
        g.allLegal();
        LinkedList<Board> list = new LinkedList<Board>();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                for (Coordinate c : g.getStateBoard()[i][j]) {
                    if(c == null) continue;
                    Game copy = (Game)g.clone();
                    if ((b.getTurn() == b.isWhite(i,j)) && b.isAPiece(i, j)) {
                        copy.move(i, j, c.getPos_i(), c.getPos_j());
                        copy.isCheckMateBlack();
                        copy.isCheckMateWhite();
                        list.add(copy.getBoard());
                    }
                    
                }
            }
        }
        if (list.size() != 0) {
            gb.createGraph(b, list);
        }
    }
    public void createGraph(int depth) throws Exception {
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(gb.getNode(board));
        while (!q.isEmpty()) {
            int h = q.remove();
            if (gb.getBoard(h).isCheckmateBlack) {
                gb.setWeight(h, Integer.MAX_VALUE);
            }
            else if (gb.getBoard(h).isCheckmateWhite) {
                gb.setWeight(h, Integer.MIN_VALUE);
            }
            else if(gb.getDepthFromNode(h) == depth) {
                
                Evaluate e = new Evaluate(gb.getBoard(h));
                gb.setWeight(h, e.total());
                
            }
            else {
                createSon(gb.getBoard(h));
                for (int c : gb.getSon(h)) {
                    q.add(c);
                }
            }
        }
        

    }
    /*
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
    }*/

}