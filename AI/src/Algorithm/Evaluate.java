package Algorithm;

import java.util.LinkedList;

import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Controller;
import Rules.Coordinate;

public class Evaluate {
    double pValue = 0;
    Board board;

    public Evaluate (Board board) {
        this.board = board;
    }

    public double piece() throws BoardOutOfBoundsException {
        double white = 0;
        double black = 0;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (board.isWhite(i, j)) {
                    if (board.getPiece(i, j) == 'P') 
                        white = white + 1;
                    if (board.getPiece(i, j) == 'Q')  
                        white = white + 9;
                    if (board.getPiece(i, j) == 'T')  
                        white = white + 5;
                    if (board.getPiece(i, j) == 'B')  
                        white = white + 3.5;
                    if (board.getPiece(i, j) == 'C')  
                        white = white + 3;
                    }
                else {
                    if (board.getPiece(i, j) == 'p') 
                        black = black + 1;
                    if (board.getPiece(i, j) == 'q')  
                        black = black + 9;
                    if (board.getPiece(i, j) == 't')  
                        black = black + 5;
                    if (board.getPiece(i, j) == 'b')  
                        black = black + 3.5;
                    if (board.getPiece(i, j) == 'c')  
                        black = black + 3;
                    }
                }
            }
        return (white - black);
    }

    public double kingSafety() throws BoardOutOfBoundsException {
        double white = 0;
        double black = 0;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (board.getPiece(i, j) == 'K') {
                    LinkedList<Coordinate> safeWhite = Controller.getQueenMoves(board, i, j);
                    white = Math.sqrt(safeWhite.size());
                }
                if (board.getPiece(i, j) == 'k') {
                    LinkedList<Coordinate> safeBlack = Controller.getQueenMoves(board, i, j);
                    black = Math.sqrt(safeBlack.size());
                }
            }
        }
        return (white - black);
    }

    public double pieceSafety() throws Exception {
        double white = 0;
        double black = 0;
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (board.isBlack(i, j) && board.isSquareAttacked(new Coordinate(i, j), false)) {
                    if ((board.getPiece(i, j) == 'b') || (board.getPiece(i, j) == 't') || (board.getPiece(i, j) == 'c')) {
                        black++;
                    }
                }
                if (board.isWhite(i, j) && board.isSquareAttacked(new Coordinate(i, j), true)) {
                    if ((board.getPiece(i, j) == 'B') || (board.getPiece(i, j) == 'T') || (board.getPiece(i, j) == 'V')) {
                        white++;
                    }
                }
            }
        } 
        return (white - black);
    }
}
