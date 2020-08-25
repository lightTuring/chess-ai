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
    }

    public GraphBuilder getGraph() {
        return gb;
    }

    public void createGraph(Game g, int depth) throws BoardOutOfBoundsException, UnexpectedPieceException,
            IllegalMoveException, CloneNotSupportedException {
        if (depth == 0) {
            g.allLegal();
            LinkedList<Game> list = new LinkedList<Game>();
            for (int i = 0; i<8; i++) {
                for (int j = 0; j<8; j++) {
                    for (Coordinate c : g.getBoard().getStateBoard()[i][j]) {
                        Game copy = g.clone();
                        copy.move(i, j, c.getPos_i(), c.getPos_j());
                        list.add(copy);
                    }
                }
            }
            gb.createGraph(g, list);
        }
        else {
            

        }
    }

}