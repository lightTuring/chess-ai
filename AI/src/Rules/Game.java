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
    IllegalMoveException {
        Controller.uncheckedMovesBlack(board);
        Controller.uncheckedMovesWhite(board);
    }

    private LinkedList<Coordinate>[][] pseudoLegalMoves() {
        return (board.getStateBoard());
    }
    
    private boolean isPseudoLegal(int i, int j, int final_i, int final_j) throws BoardOutOfBoundsException {
        Coordinate c = new Coordinate(final_i, final_j);
        LinkedList<Coordinate>[][] list = pseudoLegalMoves();
        boolean legal = false;

        if (list[i][j].size() != 0) {
            Iterator<Coordinate> iterator = list[i][j].iterator();

            if (turn == true && board.isWhite(i, j)) {

                while (iterator.hasNext()) {
                    if (iterator.next().equals(c)) {
                        legal = true;
                    }
                }

            } else if (board.isBlack(i, j)) {

                while (iterator.hasNext()) {
                    if (iterator.next().equals(c)) {
                        legal = true;
                    }
                }

            }
        }
        return legal;
    }
    
    public void move(int i, int j, int final_i, int final_j)
            throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        Board copy = board;

        if (isPseudoLegal(i, j, final_i, final_j) == false) {
            throw new IllegalMoveException("Movimento ilegal");
        } else {
            copy.changePos(i, j, final_i, final_j);
            if (copy.isBlackKingInCheck() || copy.isWhiteKingInCheck()) {
                throw new IllegalMoveException("Movimento ilegal");
            } else {
                //Checagem de quem é a vez.
                if ((!board.isBlack(i, j) == turn) || (board.isWhite(i, j) == turn) ) {
                    board = copy;
                    turn = !turn;
                    moves++;
                }
            }
        }
    }

    public boolean isLegal(int i, int j, Coordinate c)
            throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException {
        Board copy = board;

        if (isPseudoLegal(i, j, c.getPos_i(), c.getPos_j()) == false) {
            return false;
        } else {
            copy.changePos(i, j, c.getPos_i(), c.getPos_j());
            if (copy.isBlackKingInCheck() || copy.isWhiteKingInCheck()) {
                return false;
            } else {
                if ((!board.isBlack(i, j) == turn) || (board.isWhite(i, j) == turn) ) {
                    return true;
                }
                return false;
            }
        }
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