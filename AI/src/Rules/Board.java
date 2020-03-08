package Rules;

import java.util.ArrayList;

public class Board {

    private char[][] chessBoard = new char[8][8];
    private char[] posInitBlack = {'t', 'c', 'b', 'q', 'k', 'b', 'c', 't'};
    private char[] posInitWhite = {'T', 'C', 'B', 'Q', 'K', 'B', 'C', 'T'};

    private boolean hasWhiteKingMoved = false;
    private boolean hasRightWhiteRookMoved = false;
    private boolean hasLeftWhiteRookMoved = false;
    private boolean hasBlackKingMoved = false;
    private boolean hasRightBlackRookMoved = false;
    private boolean hasLeftBlackRookMoved = false;

    public Board() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = 'o';
            }
        }
        for (int i = 0; i < 8; i++)
            chessBoard[1][i] = 'p';
        for (int i = 0; i < 8; i++)
            chessBoard[6][i] = 'P';
        for (int i = 0; i < 8; i++)
            chessBoard[0][i] = posInitBlack[i];
        for (int i = 0; i < 8; i++)
            chessBoard[7][i] = posInitWhite[i];
    }
    //comparação por tabela ASCII
    public boolean isWhite(int pos_i, int pos_j) throws BoardOutOfBoundsException {
    	return ((int)getPiece(pos_i, pos_j) < 90);
    }
    //comparação por tabela ASCII
    public boolean isBlack(int pos_i, int pos_j) throws BoardOutOfBoundsException {
    	return ((int)getPiece(pos_i, pos_j) > 97);
    }
    public boolean hasSameColor(int me_i, int me_j, int that_piece_i, int that_piece_j) throws BoardOutOfBoundsException {
    	return ((isBlack(me_i, me_j) && isBlack(that_piece_i, that_piece_j))||(isWhite(me_i, me_j)&& isWhite(that_piece_i, that_piece_j)));
    }
    public void changePos(int begin_x, int begin_y, int final_x, int final_y){
        setChange(begin_x, begin_y, final_x, final_y);
    }
// Método de roque para as pretas. Pede um y para definir qual das duas possiblidades de roque vai ser feita.
    public void doBlacksCastling(int y) throws IllegalCastlingException, BoardOutOfBoundsException {
        if (y == 2 && getPiece(0, 2) == 'o' && !(getHasLeftBlackRookMoved()) && !(getHasBlackKingMoved())) {
            setChange(0, 4, 0, 2);
            setChange(0, 0, 0, 3);
        }
        else if (y == 6 && getPiece(0, 6) == 'o' && !(getHasRightBlackRookMoved()) && !(getHasBlackKingMoved())) {
            setChange(0, 4, 0, 6);
            setChange(0, 7, 0, 5);
        }
        else {
            throw new IllegalCastlingException("doBlacksCastling chamado ilegamente");
        }
    }
    private void setChange(int begin_x, int begin_y, int final_x, int final_y){
        // a verificação é com a peça final? está peça de comparação é o rei?
       if(chessBoard[final_x][final_y] != 'K' && chessBoard[final_x][final_y] != 'k' && begin_x < 8 && begin_y < 8){
            chessBoard[final_x][final_y] = chessBoard[begin_x][begin_y];
            chessBoard[begin_x][begin_y] = 'o';
        }
        /*
        int a = (int)chessBoard[begin_x][begin_y];
        int b = (int)chessBoard[final_x][final_y];

        a+=b;
        b = a - b;
        a -= b;

        chessBoard[begin_x][begin_y] = (char)a;
        chessBoard[final_x][final_y] = (char)b;

        */
    }
    public char getPiece(int pos_x, int pos_y) throws BoardOutOfBoundsException {
        if (pos_x >= 8 || pos_y >= 8) {
            throw new BoardOutOfBoundsException("Board.getPiece tentou acessa uma casa além dos limites do tabuleiro.");
        }
        return chessBoard[pos_x][pos_y];
    }
    public void printImage(){
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j]);
            }
            System.out.println();
        }
    }
    public boolean getHasWhiteKingMoved(){
        return this.hasWhiteKingMoved;
    }
    public void setHasWhiteKingMovedAsTrue(){
        this.hasWhiteKingMoved = true;
    }
    public boolean getHasRightWhiteRookMoved(){
        return this.hasRightWhiteRookMoved;
    }
    public boolean getHasLeftWhiteRookMoved(){
        return this.hasLeftWhiteRookMoved;
    }
    public void setHasRightWhiteRookMovedAsTrue(){
        this.hasRightWhiteRookMoved = true;
    }
    public void setHasLeftWhiteRookMovedAsTrue(){
        this.hasLeftWhiteRookMoved = true;
    }
    public boolean getHasBlackKingMoved(){
        return this.hasBlackKingMoved;
    }
    public void setHasBlackKingMovedAsTrue(){
        this.hasBlackKingMoved = true;
    }
    public boolean getHasRightBlackRookMoved(){
        return this.hasRightBlackRookMoved;
    }
    public boolean getHasLeftBlackRookMoved(){
        return this.hasLeftBlackRookMoved;
    }
    public void setHasRightBlackRookMovedAsTrue(){
        this.hasRightBlackRookMoved = true;
    }
    public void setHasLeftBlackRookMovedAsTrue(){
        this.hasLeftBlackRookMoved = true;
    }

    public boolean hasPawnMoved(int pos_i, int pos_j) throws UnexpectedPieceException, BoardOutOfBoundsException {
        char piece = getPiece(pos_i, pos_j);
        if (piece != 'p' && piece != 'P') {
            throw new UnexpectedPieceException("Board.hasPawnMoved foi chamado em uma casa que não contém um peão");
        }
        else if (piece == 'p' && pos_i == 1) {
            return false;
        }
        else if (piece == 'P' && pos_i == 6) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<Coordinate> indexOfPiece(char b) {
        ArrayList<Coordinate> list = new ArrayList <Coordinate> ();
        Coordinate x;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (chessBoard[i][j] == b) {
                    x = new Coordinate (-1,-1, i, j);
                    list.add(x);
                }
            }
        }
        return list;
    }
}
