package Rules;

import java.util.Iterator;
import java.util.LinkedList; 

/*
OBSERVAÇÃO!!!: Talvez seja necessário considerar mover os metodos de legalidade e cheque-mate para 
a classe Controller, de forma que essa classe seja usada apenas como execução do jogo em si.
Estou com preguiça de mudar tudo agora.
*/

public class Game {
    private Board board;
    // true = brancas; false = pretas.
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

    public Board getBoard() {
        return board;
    }

    public String getTurnString() {
        return (turn ? "Brancas" : "Pretas");
    }

    public boolean getTurn() {
        return (turn);
    }

    public boolean hasEnded() {
        return endOfGame;
    }

    public int moveNumber() {
        return moves;
    } 

    public void setMoves() throws BoardOutOfBoundsException, UnexpectedPieceException,
    IllegalMoveException, CloneNotSupportedException{
        Controller.allLegal(this.board);
    }

    private LinkedList<Coordinate>[][] pseudoLegalMoves() {
        return (board.getStateBoard());
    }
    
    
    
    public void move(int i, int j, int final_i, int final_j)
            throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        Coordinate c = new Coordinate(final_i, final_j);
        if (isLegal(i, j, c) && (!board.isBlack(i,j) == turn || board.isWhite(i,j) == turn)) {
            board.changePos(i, j, c);
            turn = !turn;
            moves++;
        }
        else {
            throw new IllegalMoveException("Movimento ilegal");
        }
    }

    public boolean isLegal(int i, int j, Coordinate c) throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        LinkedList<Coordinate> list = board.getStateBoard()[i][j];
        for (Coordinate x : list) {
            if (x.equals(c)) {
                return true;
            }
        }
        return false;
    }
    

    public void isCheckMateWhite() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        LinkedList<Coordinate>[][] list = pseudoLegalMoves();
        int legal = 0;

        if (board.isWhiteKingInCheck() == true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Board copy = board;
                    Iterator<Coordinate> x = list[i][j].iterator();
                    while (x.hasNext() && legal == 0) {
                        Coordinate c = x.next();
                        if (isLegal(i, j, c)) {
                            copy.changePos(i, j, c);
                            if (!copy.isWhiteKingInCheck()) {
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
        
        if (board.isBlackKingInCheck() == true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Board copy = board;
                    Iterator<Coordinate> x = list[i][j].iterator();
                    while (x.hasNext() && legal == 0) {
                        Coordinate c = x.next();
                        if (isLegal(i, j, c)) {
                            copy.changePos(i, j, c);
                            if (!copy.isBlackKingInCheck()) {
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