package Rules;

import java.util.Arrays;

//Métodos foram movidos para manipulator, deixando essa classe apenas para armazenar dados
public class Bits {
    public char[][] chessBoard = new char[8][8];
    public final char[] initPosBlack = { 't', 'c', 'b', 'q', 'k', 'b', 'c', 't' };
    public final char[] initPosWhite = { 'T', 'C', 'B', 'Q', 'K', 'B', 'C', 'T' };
    public long board = 0L;
    public long white = 0L;
    public long black = 0L;
    //pretas pares; brancas ímpares.
    // peão 0 e 1; cavalo: 2 e 3; bispo: 4 e 5; torre: 6 e 7; rainha: 8 e 9; rei: 10 e 11
    public long[] pieceBoard = new long[12];
    public boolean endOfGame = false;
    public boolean checkmateBlack = false;
    public boolean checkmateWhite = false;
    public boolean turn = true;
    public long lastMove[] = new long[2];

    public Bits() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = 'o';
            }
        }
        
        // Ao invés de usar for-loop, java.util já tem um método otimizado pra isso:
        Arrays.fill(chessBoard[1], 'p');
        Arrays.fill(chessBoard[6], 'P');
        // initPosBlack e White são "final", então não tem problema dar assignment direto assim:
        chessBoard[0] = initPosBlack;
        chessBoard[7] = initPosWhite;

        Manipulator.makeBoards(this);
    } 

    public Bits(char[][] board) {
        chessBoard = board;
        Manipulator.makeBoards(this);
    } 

    @Override
    public Bits clone() {
        Bits b = new Bits();
        for (int i= 0; i<8; i++) {
            for (int j= 0; j<8; j++) {
                b.chessBoard[i][j] = this.chessBoard[i][j];
            }
        }
        b.board = this.board;
        b.pieceBoard = this.pieceBoard.clone();
        b.white = this.white;
        b.black = this.black;
        b.checkmateBlack = this.checkmateBlack;
        b.checkmateWhite = this.checkmateWhite;
        b.turn = this.turn;
        /*
        if (this.lastMove[0] != null) {
            b.lastMove[0] = this.lastMove[0].clone();
        }
        if (this.lastMove[1] != null) {
            b.lastMove[1] = this.lastMove[1].clone();
        }*/
        return b;
    }
    
}