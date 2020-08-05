package Algorithm;

import java.util.LinkedList;

import Rules.*;

public class ValidatePath {
    
    /* 
        - Faz a BT no final chama o método de decisão se devemos ou não remover o movimento
        - Complexidade.: O(n²)
    */

    private LinkedList<Coordinate> moves;
    private char[][] board;

    public ValidatePath(final LinkedList<Coordinate> moves) {
        this.moves = moves;
        board = new Board().getBoard();
    }

    private void removeMovement(final Coordinate c) {
        moves.remove(moves.indexOf(c));
    }

    public LinkedList<Coordinate> getValidatePath(){
        return moves;
    }
    
}