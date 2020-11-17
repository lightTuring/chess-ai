package Bitboards;

//Métodos foram movidos para manipulator, deixando essa classe apenas para armazenar dados
public class Bits {
    public char[][] chessBoard = new char[8][8];
    public final char[] initPosBlack = { 't', 'c', 'b', 'q', 'k', 'b', 'c', 't' };
    public final char[] initPosWhite = { 'T', 'C', 'B', 'Q', 'K', 'B', 'C', 'T' };
    public long board = 0L;
    public long white = 0L;
    public long black = 0L;
    public long cb = 0L;
    public long cw = 0L;
    public long pb = 0L;
    public long pw = 0L;
    public long bb = 0L;
    public long bw = 0L;
    public long tb = 0L;
    public long tw = 0L;
    public long kb = 0L;
    public long kw = 0L;
    public long qb = 0L;
    public long qw = 0L;
    public boolean endOfGame = false;
    public boolean checkmateBlack = false;
    public boolean checkmateWhite = false;
    public boolean turn = true;

    public Bits() {
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


    } 

    @Override
    public Bits clone() throws CloneNotSupportedException {
        Bits b = new Bits();
        for (int i= 0; i<8; i++) {
            for (int j= 0; j<8; j++) {
                b.chessBoard[i][j] = this.chessBoard[i][j];
            }
        }
        b.board = this.board;
        b.pb = this.pb;
        b.pw = this.pw;
        b.bb = this.bb;
        b.bw = this.bw;
        b.cb = this.cb;
        b.cw = this.cw;
        b.tb = this.tb;
        b.tw = this.tw;
        b.kb = this.kb;
        b.kw = this.kw;
        b.qb = this.qb;
        b.qw = this.qw;
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