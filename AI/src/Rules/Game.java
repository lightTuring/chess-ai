package Rules;

import java.util.Iterator;
import java.util.LinkedList;

public class Game {
    private Board board;
    //true = brancas; false = pretas. 
    private boolean turn;
    private int moves = 0;

    public Game(Board board) {  
        this.board = board; 
        turn = true;
    }

    public Game(Board board, boolean turn) {
        this.board = board;
        this.turn = turn;
    }

    public String getTurn() {
        return (turn ? "Brancas" : "Pretas");
    }

    public LinkedList<Coordinate>[][] legalMoves() {
        return (board.getStateBoard());
    }

    public boolean isLegalMove(int i, int j, int final_i, int final_j) {
        Coordinate c = new Coordinate(final_i, final_j);
        LinkedList<Coordinate>[][] list = legalMoves();
        Iterator<Coordinate> iterator = list[i][j].iterator();
        boolean legal = false;

        while (iterator.hasNext()) {
            if(iterator.next().equals(c)) {
                legal = true;
            }
        }
        return legal;
    }
}