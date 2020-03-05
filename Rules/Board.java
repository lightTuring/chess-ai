import java.util.ArrayList;

public class Board {

    private char[][] chessBoard = new char[8][8];
    private char[] posInitBlack = {'t', 'c', 'b', 'k', 'q', 'b', 'c', 't'};
    private char[] posInitWhite = {'T', 'C', 'B', 'K', 'Q', 'B', 'C', 'T'};

    public Board(){
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = 'o';
            }
        }
        for (int i = 0; i < chessBoard[2].length; i++)
            chessBoard[i] = 'p';
        for (int i = 0; i < chessBoard[5].length; i++)
            chessBoard[i] = 'P';
        for (int i = 0; i < chessBoard[2].length; i++)
            chessBoard[i] = posInitBlack[i];
        for (int i = 0; i < chessBoard[5].length; i++)
            chessBoard[i] = posInitWhite[i];
    }

    public void getChange(int begin_x, int begin_y, int final_x, int final_y){
        change(begin_x, begin_y, final_x, final_y);
    }

    private void change(int begin_x, int begin_y, int final_x, int final_y){
        // a verificação é com a peça final? está peça de comparação é o rei?
        if((chessBoard[begin_x][begin_y] != 'K'||chessBoard[begin_x][begin_y] != 'k') && begin_x < 8 && begin_y < 8){
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

}
