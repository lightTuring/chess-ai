package Rules;

//import java.util.Iterator;
import java.util.LinkedList;
//import Notation.Annotation;


public class Board implements Cloneable {

    private char[][] chessBoard = new char[8][8];
    private final char[] initPosBlack = { 't', 'c', 'b', 'q', 'k', 'b', 'c', 't' };
    private final char[] initPosWhite = { 'T', 'C', 'B', 'Q', 'K', 'B', 'C', 'T' };

    public boolean hasWhiteKingMoved = false;
    public boolean hasRightWhiteRookMoved = false;
    public boolean hasLeftWhiteRookMoved = false;
    public boolean hasBlackKingMoved = false;
    public boolean hasRightBlackRookMoved = false;
    public boolean hasLeftBlackRookMoved = false;
    public boolean endOfGame = false;
    public boolean isCheckmateWhite = false;
    public boolean isCheckmateBlack = false;
    public boolean turn = true;
	public boolean hasBlackCastled = false;
    public boolean hasWhiteCastled = false;
    //White = O peao que captura é branco
    public Coordinate[] lastMove = new Coordinate[2];

    
    @Override
    public Board clone() throws CloneNotSupportedException {
        Board b = new Board();
        for (int i= 0; i<8; i++) {
            for (int j= 0; j<8; j++) {
                b.chessBoard[i][j] = this.chessBoard[i][j];
            }
        }
        b.hasBlackKingMoved = this.hasBlackKingMoved;
        b.hasRightWhiteRookMoved = this.hasRightWhiteRookMoved;
        b.hasLeftWhiteRookMoved = this.hasLeftWhiteRookMoved;
        b.hasWhiteKingMoved = this.hasWhiteKingMoved;
        b.hasRightBlackRookMoved = this.hasRightBlackRookMoved;
        b.hasLeftBlackRookMoved = this.hasLeftBlackRookMoved;
        b.hasWhiteCastled = this.hasWhiteCastled;
        b.hasBlackCastled = this.hasBlackCastled;
        b.endOfGame = this.endOfGame;
        b.isCheckmateBlack = this.isCheckmateBlack;
        b.isCheckmateWhite = this.isCheckmateWhite;
        b.turn = this.turn;
        if (this.lastMove[0] != null) {
            b.lastMove[0] = this.lastMove[0].clone();
        }
        if (this.lastMove[1] != null) {
            b.lastMove[1] = this.lastMove[1].clone();
        }
        return b;
    }

    public void eliminate(int i, int j) {
        chessBoard[i][j] = 'o';
    }

    public boolean equals(Board b) {
        for (int i= 0; i<8; i++) {
            for (int j= 0; j<8; j++) {
                if (b.chessBoard[i][j] != this.chessBoard[i][j]) {
                    return false;
                };
            }
        }
        return (b.turn == this.turn);
    }

    public Board() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = 'o';
            }
        }
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[1][i] = 'p';
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[6][i] = 'P';
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[0][i] = initPosBlack[i];
        for (int i = 0; i < chessBoard.length; i++)
            chessBoard[7][i] = initPosWhite[i];
        this.turn = true;


    }

    public boolean isWhite(int pos_i, int pos_j) throws BoardOutOfBoundsException {
        return (Character.isUpperCase(getPiece(pos_i, pos_j)));
    }
    public boolean isWhite(Coordinate c) throws BoardOutOfBoundsException {
    	return (Character.isUpperCase(getPiece(c)));
    }

    public boolean isBlack(int pos_i, int pos_j) throws BoardOutOfBoundsException {
        return (Character.isLowerCase(getPiece(pos_i, pos_j)) && getPiece(pos_i, pos_j) != 'o');
    }
    public boolean isBlack(Coordinate c) throws BoardOutOfBoundsException {
    	return (Character.isLowerCase(getPiece(c)) && getPiece(c) != 'o');
    }

    public boolean isWhite(char piece) throws BoardOutOfBoundsException {
        return (Character.isUpperCase(piece));
    }

    public boolean isBlack(char piece) throws BoardOutOfBoundsException {
        return (Character.isUpperCase(piece) && piece != 'o');
    }

    public boolean hasSameColor(int me_i, int me_j, int that_piece_i, int that_piece_j)
            throws BoardOutOfBoundsException {
        return ((isBlack(me_i, me_j) && isBlack(that_piece_i, that_piece_j))
                || (isWhite(me_i, me_j) && isWhite(that_piece_i, that_piece_j)));
    }

    public void changePos(int begin_x, int begin_y, int final_x, int final_y) {
        setChange(begin_x, begin_y, final_x, final_y);
    }

    public void changePos(int begin_x, int begin_y, Coordinate c) {
        setChange(begin_x, begin_y, c.getPos_i(), c.getPos_j());
    }
    public void setTurn(boolean b) {
        this.turn = b;
    }
    /*DEPRECIADO
    // Método de roque para as pretas. Pede um y para definir qual das duas
    // possiblidades de roque vai ser feita.
    public void doBlacksCastling(CastlingSide side) throws IllegalCastlingException, BoardOutOfBoundsException {
        if (side == CastlingSide.Queenside && getPiece(0, 2) == 'o' && !(getHasLeftBlackRookMoved())
                && !(getHasBlackKingMoved())) {
            setChange(0, 4, 0, 2);
            setChange(0, 0, 0, 3);
        } else if (side == CastlingSide.Kingside && getPiece(0, 6) == 'o' && !(getHasRightBlackRookMoved())
                && !(getHasBlackKingMoved())) {
            setChange(0, 4, 0, 6);
            setChange(0, 7, 0, 5);
        } else {
            throw new IllegalCastlingException("doBlacksCastling chamado ilegamente");
        }
    }

    public void doWhitesCastling(CastlingSide side) throws IllegalCastlingException, BoardOutOfBoundsException {
        if (side == CastlingSide.Queenside && getPiece(7, 2) == 'o' && !(getHasLeftBlackRookMoved())
                && !(getHasBlackKingMoved())) {
            setChange(7, 4, 7, 2);
            setChange(7, 0, 7, 3);
        } else if (side == CastlingSide.Kingside && getPiece(7, 6) == 'o' && !(getHasRightBlackRookMoved())
                && !(getHasBlackKingMoved())) {
            setChange(7, 4, 7, 6);
            setChange(7, 7, 7, 5);
        } else {
            throw new IllegalCastlingException("doBlacksCastling chamado ilegamente");
        }
    }
    */
    private void setChange(int begin_x, int begin_y, int final_x, int final_y) {
        // a verificação é com a peça final? está peça de comparação é o rei?
        if (chessBoard[final_x][final_y] != 'K' && chessBoard[final_x][final_y] != 'k' && begin_x < 8 && begin_y < 8) {
            chessBoard[final_x][final_y] = chessBoard[begin_x][begin_y];
            chessBoard[begin_x][begin_y] = 'o';
        }
        /*
         * int a = (int)chessBoard[begin_x][begin_y]; int b =
         * (int)chessBoard[final_x][final_y];
         * 
         * a+=b; b = a - b; a -= b;
         * 
         * chessBoard[begin_x][begin_y] = (char)a; chessBoard[final_x][final_y] =
         * (char)b;
         * 
         */
    }

    public char getPiece(int pos_x, int pos_y) throws BoardOutOfBoundsException {
        if (pos_x >= 8 || pos_y >= 8) {
            throw new BoardOutOfBoundsException("Board.getPiece tentou acessa uma casa além dos limites do tabuleiro.");
        }
        return chessBoard[pos_x][pos_y];
    }
    public char getPiece(Coordinate c) throws BoardOutOfBoundsException {
        if (c.getPos_i() >= 8 || c.getPos_j() >= 8) {
            throw new BoardOutOfBoundsException("Board.getPiece tentou acessa uma casa além dos limites do tabuleiro.");
        }
        return chessBoard[c.getPos_i()][c.getPos_j()];
    }

    public void printImage() {
        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
        int[] numbers = { 8, 7, 6, 5, 4, 3, 2, 1 };
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j]);
            }
            System.out.print(" ");
            System.out.println(numbers[i]);
        }
        System.out.println();
        for (int i = 0; i < letters.length; i++)
            System.out.print(letters[i]);
    }
    /*DEPRECIADO

    public boolean getHasWhiteKingMoved() {
        return this.hasWhiteKingMoved;
    }

    public void setHasWhiteKingMovedAsTrue() {
        this.hasWhiteKingMoved = true;
    }

    public boolean getHasRightWhiteRookMoved() {
        return this.hasRightWhiteRookMoved;
    }

    public boolean getHasLeftWhiteRookMoved() {
        return this.hasLeftWhiteRookMoved;
    }

    public void setHasRightWhiteRookMovedAsTrue() {
        this.hasRightWhiteRookMoved = true;
    }

    public void setHasLeftWhiteRookMovedAsTrue() {
        this.hasLeftWhiteRookMoved = true;
    }

    public boolean getHasBlackKingMoved() {
        return this.hasBlackKingMoved;
    }

    public void setHasBlackKingMovedAsTrue() {
        this.hasBlackKingMoved = true;
    }

    public boolean getHasRightBlackRookMoved() {
        return this.hasRightBlackRookMoved;
    }

    public boolean getHasLeftBlackRookMoved() {
        return this.hasLeftBlackRookMoved;
    }

    public void setHasRightBlackRookMovedAsTrue() {
        this.hasRightBlackRookMoved = true;
    }

    public void setHasLeftBlackRookMovedAsTrue() {
        this.hasLeftBlackRookMoved = true;
    }

    */
    public boolean hasPawnMoved(int pos_i, int pos_j) throws UnexpectedPieceException, BoardOutOfBoundsException {
        char piece = getPiece(pos_i, pos_j);
        if (piece != 'p' && piece != 'P') {
            throw new UnexpectedPieceException("Board.hasPawnMoved foi chamado em uma casa que não contém um peão");
        } else if (piece == 'p' && pos_i == 1) {
            return false;
        } else if (piece == 'P' && pos_i == 6) {
            return false;
        } else {
            return true;
        }
    }
    public boolean isBlackKingInCheck() throws BoardOutOfBoundsException, UnexpectedPieceException,
            IllegalMoveException {
        LinkedList<Coordinate>[][] list = Controller.uncheckedMoves(this);
        Coordinate[] king = indexOfPiece('k');
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isWhite(i,j)) {
                    for (Coordinate c : list[i][j]) {
                        if (c.equals(king[0])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    public boolean isWhiteKingInCheck() throws BoardOutOfBoundsException, UnexpectedPieceException,
            IllegalMoveException {
        LinkedList<Coordinate>[][] list = Controller.uncheckedMoves(this);
        Coordinate[] king = indexOfPiece('K');
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isBlack(i,j)) {
                    for (Coordinate c : list[i][j]) {
                        if (c.equals(king[0])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    public void setBoard(char[][] board) {
        chessBoard = board;
    }

    public char[][] getBoard() {
        return chessBoard;
    }

    //10 é o número maximo de peças de um tipo (2 peças iniciais + 8 peões promovidos)
    public Coordinate[] indexOfPiece(char b) {
        Coordinate[] positions = new Coordinate[10];
        int p = 0;

        for (int i = 0; i < positions.length; i++)
            positions[i] = null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard[i][j] == b) {
                    positions[p] = new Coordinate(i, j);
                    p++;
                }
            }
        }
        return positions;
    }

    public boolean isAPiece(int pos_i, int pos_j) throws BoardOutOfBoundsException {
        return (getPiece(pos_i, pos_j) != 'o');
    }


    public Coordinate promotionWhite() throws BoardOutOfBoundsException{
        for (int j = 0; j<8; j++) {
            if (getPiece(7, j) == 'P') {
                return new Coordinate(7, j);
            }
        }
        // Caso não tenha peões brancos que possam promover.
        return new Coordinate(-1,-1);
    }

    public Coordinate promotionBlack() throws BoardOutOfBoundsException{
        for (int j = 0; j<8; j++) {
            if (getPiece(0, j) == 'P') {
                return new Coordinate(0, j);
            }
        }
        // Caso não tenha peões pretos que possam promover.
        return new Coordinate(-1,-1);
    }

	public boolean getTurn() {
		return turn;
	}

    // 0 -> borda; 1 -> tem uma peça da mesma cor; 2 -> tem uma peça oponente
    public void setLastMove(int i, int j, int a, int b) {
        lastMove[0] = new Coordinate(i, j);
        lastMove[1] = new Coordinate(a, b);
    }

    public Coordinate[] getLastMove() {
       return lastMove;
    }


    /*
     * public boolean isPiecePinned(int i, int j) throws BoardOutOfBoundsException,
     * UnexpectedPieceException { if (getPiece(i, j) != 'o' && isWhite(i, j) ==
     * true) { boolean kingPath = false; LinkedList<Coordinate> queen =
     * Controller.getQueenMoves(this, i, j); Iterator<Coordinate> it =
     * queen.iterator(); Coordinate x;
     * 
     * while (it.hasNext()) { x = it.next();
     * 
     * if (getPiece(x.getPos_i(), x.getPos_j()) == 'K') {
     * 
     * }
     * 
     * else { continue; } }
     * 
     * 
     * }
     * 
     * if (getPiece(i, j) != 'o' && isBlack(i, j) == true) {
     * 
     * 
     * return true; }
     * 
     * else {
     * 
     * throw new UnexpectedPieceException("Casa Vazia"); } }
     */
}