package Rules;

import java.util.Iterator;
import java.util.LinkedList; 

public class Game {
    private Board board;
    // true = brancas; false = pretas.
    private boolean turn;
    private boolean endOfGame = false;
    public boolean isCheckMateBlack = false;
    public boolean isCheckMateWhite = false;
    private int moves = 0;

    public Game(Board board) {
        this.board = board;
        turn = true;
    }

    public Game(Board board, boolean turn) {
        this.board = board;
        this.turn = turn;
    }

    public Game clone() throws CloneNotSupportedException {
        Board b = this.board.clone();
        Game game = new Game(b);
        game.endOfGame = this.endOfGame;
        game.turn = this.turn;
        game.isCheckMateBlack = this.isCheckMateBlack;
        game.isCheckMateWhite = this.isCheckMateWhite;
        game.moves = this.moves;
        return game;
    }

    public boolean equals (Game g) {
        if (this.board.equals(g.board) && (turn == g.turn)) {
            return true;
        }
        return false;
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
                        if (board.hasSameColor(i, j, c.getPos_i(), c.getPos_j())) {
                            letra.remove(c);
                        }
                        else if (copy.isWhiteKingInCheck()) {
                            letra.remove(c);
                        } 
                        
                    }
                    if (turn == false) {
                        if (board.hasSameColor(i, j, c.getPos_i(), c.getPos_j())) {
                            letra.remove(c);
                        }
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
        int legal = 0;
        Coordinate z = board.indexOfPiece('K')[0];
        if ((board.getStateBoard()[z.getPos_i()][z.getPos_j()].size() == 0) && board.isWhiteKingInCheck()) {
            endOfGame = true;
        }
        
        else if (board.isWhiteKingInCheck()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Iterator<Coordinate> x = board.getStateBoard()[i][j].iterator();
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
                isCheckMateWhite = true;
            }
        }
        
    }

    public void isCheckMateBlack() throws IllegalMoveException, BoardOutOfBoundsException, UnexpectedPieceException, CloneNotSupportedException {
        int legal = 0;
        Coordinate z = board.indexOfPiece('k')[0];
        if ((board.getStateBoard()[z.getPos_i()][z.getPos_j()].size() == 0) && board.isBlackKingInCheck()) {
            System.out.println((board.getStateBoard()[z.getPos_i()][z.getPos_j()].size() == 0) + "<- OLHA O GOL");
            endOfGame = true;
        }
        
        else if (board.isBlackKingInCheck()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Iterator<Coordinate> x = board.getStateBoard()[i][j].iterator();
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
                isCheckMateBlack = true;
            }
        }
    }

}