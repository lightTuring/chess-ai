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

    public String getTurnString() {
        return (turn ? "Brancas" : "Pretas");
    }

    public boolean getTurn() {
        return (turn);
    }

    public LinkedList<Coordinate>[][] legalMoves() {
        return (board.getStateBoard());
    }
    
    private boolean isPseudoLegal(int i, int j, int final_i, int final_j) throws Exception {
        Coordinate c = new Coordinate(final_i, final_j);
        LinkedList<Coordinate>[][] list = legalMoves();
        boolean legal = false;

        if (list[i][j] != null) {
            Iterator<Coordinate> iterator = list[i][j].iterator();
        
            if (turn == true && board.isWhite(i, j)) {
                
                while (iterator.hasNext()) {
                    if(iterator.next().equals(c)) {
                    legal = true;
                    }
                }
                   
            }
            else if (board.isBlack(i, j)) {
                
                while (iterator.hasNext()) {
                    if(iterator.next().equals(c)) {
                        legal = true;
                    }
                }
                 
            }
        }
        return legal;
    }
    public void move(int i, int j, int final_i, int final_j) throws Exception, IllegalMoveException {
        Board copy = board;
        
        if (isPseudoLegal(i, j, final_i, final_j) == false) {
            throw new IllegalMoveException("Movimento ilegal");
        }
        else {
            copy.changePos(i, j, final_i, final_j);
            if (copy.isCheckInBlackKing() || copy.isCheckInWhiteKing()) {
                throw new IllegalMoveException("Movimento ilegal");
            }
            else {
                board = copy;
            }
        }
    }
}