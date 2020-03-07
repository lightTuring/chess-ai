package Rules;

public class Board {

    private char[][] chessBoard = new char[8][8];
    private char[] posInitBlack = {'t', 'c', 'b', 'q', 'k', 'b', 'c', 't'};
    private char[] posInitWhite = {'T', 'C', 'B', 'Q', 'K', 'B', 'C', 'T'};

    private boolean hasKingMoved = false;
    private boolean hasTowerMoved = false;

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
    public boolean isWhite(int pos_i, int pos_j) {
    	return ((int)getPiece(pos_i, pos_j) < 90);
    }
    //comparação por tabela ASCII
    public boolean isBlack(int pos_i, int pos_j) {
    	return ((int)getPiece(pos_i, pos_j) > 97);
    }

    public void changePos(int begin_x, int begin_y, int final_x, int final_y){
        setChange(begin_x, begin_y, final_x, final_y);
    }

    private void setChange(int begin_x, int begin_y, int final_x, int final_y){
        if(getHasKingMoved() == false && getHasTowerMoved() == false &&
         (((chessBoard[begin_x][begin_y] == 'k' && chessBoard[final_x][final_y] == 't')||(chessBoard[begin_x][begin_y] == 't' && chessBoard[final_x][final_y] == 'k')) ^
         ((chessBoard[begin_x][begin_y] == 'K' && chessBoard[final_x][final_y] == 'T')||(chessBoard[begin_x][begin_y] == 'T' && chessBoard[final_x][final_y] == 'K')))){
            //estou usando este algoritmo pq não sei de fato qual é a peça final e inicial e tbm nn sei a cor das duas
            char a = chessBoard[begin_x][begin_y];
            char b = chessBoard[final_x][final_y];

            chessBoard[begin_x][begin_y] = b;
            chessBoard[final_x][final_y] = a;
        }

        // a verificação é com a peça final? está peça de comparação é o rei?
        else if(chessBoard[final_x][final_y] != 'K' && chessBoard[final_x][final_y] != 'k' && begin_x < 8 && begin_y < 8){
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
                System.out.print(chessBoard[i][j]+' ');
            }
            System.out.println();
        }
    }
    public boolean getHasKingMoved(){
        return this.hasKingMoved;
    }
    public void setHasKingMovedAsTrue(){
        this.hasKingMoved = true;
    }
    public boolean getHasTowerMoved(){
        return this.hasTowerMoved;
    }
    public void setHasTowerMovedAsTrue(){
        this.hasTowerMoved = true;
    }
    public boolean hasPawnMoved(int pos_i, int pos_j) throws UnexpectedPieceException {
        char piece = getPiece(pos_i, pos_j);
        if (piece != 'p' || piece != 'P') {
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
}
