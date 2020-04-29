package Rules;

import java.util.Iterator;
import java.util.LinkedList;

public class Game {
    private Board board;
    //true = brancas; false = pretas. 
    private boolean turn;
    private boolean endOfGame = false;
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

    private LinkedList<Coordinate>[][] pseudoLegalMoves() {
        return (board.getStateBoard());
    }
    
    private boolean isPseudoLegal(int i, int j, int final_i, int final_j) throws BoardOutOfBoundsException {
        Coordinate c = new Coordinate(final_i, final_j);
        LinkedList<Coordinate>[][] list = pseudoLegalMoves();
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
    public void move(int i, int j, int final_i, int final_j) throws  IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
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

    private boolean isLegal(int i, int j, Coordinate coordinate) throws BoardOutOfBoundsException, UnexpectedPieceException  {
        Board copy = board;
        boolean x = true;

        try{
            move(i, j, coordinate.getPos_i(), coordinate.getPos_j());
        }
        catch (IllegalMoveException illegal) {
            x = false;
        }
        board = copy;
        return x;
    }
    
    public void isCheckMateWhite() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        LinkedList<Coordinate>[][] list = pseudoLegalMoves();
        int legal = 0;

        if(board.isCheckInWhiteKing() == true) {
            for (int i = 0; i<8; i++) {
                for (int j = 0; j<8; j++) {
                    Board copy = board;
                    Iterator<Coordinate> x = list[i][j].iterator();
                    while (x.hasNext() && legal == 0) {
                        Coordinate c = x.next();
                        if (isLegal(i, j, c)) {
                            copy.changePos(i, j, c);
                            if (!copy.isCheckInWhiteKing()) {
                                legal++;
                            }
                        }
                    }

                    if (legal != 0) {
                        break;
                    }
                }

                if (legal != 0) {
                    break;
                }
            }

            if (legal == 0) {
                endOfGame = true;
            }
        }
    }

    public void isCheckMateBlack() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        LinkedList<Coordinate>[][] list = pseudoLegalMoves();
        int legal = 0;

        if(board.isCheckInBlackKing() == true) {
            for (int i = 0; i<8; i++) {
                for (int j = 0; j<8; j++) {
                    Board copy = board;
                    Iterator<Coordinate> x = list[i][j].iterator();
                    while (x.hasNext() && legal == 0) {
                        Coordinate c = x.next();
                        if (isLegal(i, j, c)) {
                            copy.changePos(i, j, c);
                            if (!copy.isCheckInBlackKing()) {
                                legal++;
                            }
                        }
                    }

                    if (legal != 0) {
                        break;
                    }
                }

                if (legal != 0) {
                    break;
                }
            }

            if (legal == 0) {
                endOfGame = true;
            }
        }
    }

}