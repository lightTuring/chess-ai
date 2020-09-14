package Algorithm;

import java.util.LinkedList;

import Rules.Board;
import Rules.BoardOutOfBoundsException;
import Rules.Controller;
import Rules.Coordinate;
import Rules.UnexpectedPieceException;

public class Evaluate {
    Board board;

    public Evaluate(Board board) {
        this.board = board;
    }

    private double piece() throws BoardOutOfBoundsException {
        double white = 0;
        double black = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
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
                } else {
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

    private double kingSafety() throws BoardOutOfBoundsException {
        double white = 0;
        double black = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) == 'K') {
                    LinkedList<Coordinate> safeWhite = Controller.getQueenMoves(board, i, j);
                    white = -Math.sqrt((double)safeWhite.size());
                }
                if (board.getPiece(i, j) == 'k') {
                    LinkedList<Coordinate> safeBlack = Controller.getQueenMoves(board, i, j);
                    black = -Math.sqrt((double)safeBlack.size());
                }
            }
        }
        return (white - black);
    }

    private double pieceSafety() throws Exception {
        double white = 0;
        double black = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.isBlack(i, j) && Controller.isSquareDefended(new Coordinate(i, j), board)) {
                    if ((board.getPiece(i, j) == 'b') || (board.getPiece(i, j) == 't')
                            || (board.getPiece(i, j) == 'c')) {
                        black++;
                    }
                    if ((board.getPiece(i, j) == 'p')) {
                        black += 0.3;
                    }
                }
                if (board.isWhite(i, j) && Controller.isSquareDefended(new Coordinate(i, j), board)) {
                    if ((board.getPiece(i, j) == 'B') || (board.getPiece(i, j) == 'T')
                            || (board.getPiece(i, j) == 'C')) {
                        white++;
                    }
                    if ((board.getPiece(i, j) == 'P')) {
                        white += 0.3;
                    }
                }
            }
        }
        return (white - black);
    }

    private double kingMobility() throws BoardOutOfBoundsException {
        double white = 0;
        double black = 0;
        Coordinate[] whiteKing = board.indexOfPiece('K');
        Coordinate[] blackKing = board.indexOfPiece('k');
        LinkedList<Coordinate> listWhite = Controller.getKingMoves(board, whiteKing[0].getPos_i(), whiteKing[0].getPos_j());
        LinkedList<Coordinate> listBlack = Controller.getKingMoves(board, blackKing[0].getPos_i(), blackKing[0].getPos_j());
        white = Math.sqrt((double) listWhite.size());
        black = Math.sqrt((double) listBlack.size());
        return (white - black);
    }

    private double pawnAdvancement() {
        double white = 0;
        double black = 0;
        Coordinate[] listWhite = board.indexOfPiece('P');
        Coordinate[] listBlack = board.indexOfPiece('p');
        for (Coordinate c : listWhite) {
            if (c != null) {
                int i = c.getPos_i();
                if (i != 7) {
                    white += (7 - (i + 1)) * 0.2;
                }
            }
        }
        for (Coordinate c : listBlack) {
            if (c != null) {
                int i = c.getPos_i();
                black += (i - 1) * 0.2;
            }
        }
        return (white - black);

    }

    private double pieceMobility() throws BoardOutOfBoundsException, UnexpectedPieceException {
        double white = 0;
        double black = 0;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (board.isWhite(i, j)) {
                    if (board.getPiece(i, j) == 'T')  
                        white = white + Math.sqrt((double)Controller.getRookMoves(board, i, j).size())/2;
                    if (board.getPiece(i, j) == 'B')  
                        white = white + Math.sqrt((double)Controller.getBishopMoves(board, i, j).size())/2;
                    if (board.getPiece(i, j) == 'C')  
                        white = white + Math.sqrt((double)Controller.getKnightMoves(board, i, j).size())/2;
                    }
                else {
                    if (board.getPiece(i, j) == 't')  
                        black += Math.sqrt((double)Controller.getRookMoves(board, i, j).size())/2;
                    if (board.getPiece(i, j) == 'b')  
                        black += Math.sqrt((double)Controller.getBishopMoves(board, i, j).size())/2;
                    if (board.getPiece(i, j) == 'c')  
                        black += Math.sqrt((double)Controller.getKnightMoves(board, i, j).size())/2;
                    }
                }
            }

        return (white-black);
    }

    private double castlePoints() {
        double white= 0;
        double black= 0;
        if (board.hasWhiteCastled) {
            white+=1.5;
        }
        if (board.hasBlackCastled) {
            black+=1.5;
        }
        return (white-black);
    }

    public double total () throws BoardOutOfBoundsException, Exception {
        return (kingMobility() + pieceSafety() + piece() + kingSafety() + pawnAdvancement() + pieceMobility() + castlePoints());
    }

}
