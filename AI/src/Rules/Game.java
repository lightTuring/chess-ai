package Rules;

import java.util.Iterator;
import java.util.LinkedList; 

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

    public void allLegal() throws BoardOutOfBoundsException, UnexpectedPieceException, IllegalMoveException, CloneNotSupportedException {
		LinkedList<Coordinate>[][] list = Controller.uncheckedMoves(board);
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++){
				LinkedList<Coordinate> teste = list[i][j];
				LinkedList<Coordinate> letra = (LinkedList<Coordinate>)teste.clone();
				for (Coordinate c : teste) {
					Board copy = board.clone();
                    copy.changePos(i, j, c);
                    if (turn == true) {
                        if (copy.isWhiteKingInCheck()) {
                            letra.remove(c);
                        } 
                        
                    }
                    if (turn == false) {
                        if (copy.isBlackKingInCheck()) {
                            letra.remove(c);
                        } 
                        
                    }
				}
				list[i][j] = letra;
			}
		}
		board.setStateBoard(list);
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

    public void isCheckMateWhite() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException,
            CloneNotSupportedException {
        LinkedList<Coordinate>[][] list = Controller.uncheckedMoves(board);
        int legal = 0;

        if (board.isWhiteKingInCheck() == true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Iterator<Coordinate> x = list[i][j].iterator();
                    while (x.hasNext() && legal == 0) {
                        Board copy = board.clone();
                        Coordinate c = x.next();
                        copy.changePos(i, j, c);
                        if (!copy.isWhiteKingInCheck()) {
                            legal++;
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

    public void isCheckMateBlack() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException,
            CloneNotSupportedException {
        LinkedList<Coordinate>[][] list = Controller.uncheckedMoves(board);
        int legal = 0;

        if (board.isBlackKingInCheck() == true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Iterator<Coordinate> x = list[i][j].iterator();
                    while (x.hasNext() && legal == 0) {
                        Board copy = board.clone();
                        Coordinate c = x.next();
                        copy.changePos(i, j, c);
                        if (!copy.isBlackKingInCheck()) {
                            legal++;
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