import java.util.ArrayList;

public class Controller {

  Board b = new Board();

  public ArrayList<Coordinate> getAllMoves(char[][] b) {

    }


  public ArrayList<Coordinate> movePawn(byte i, byte j) {
    if (b.chessBoard[i][j] == 'P') {
      ArrayList<Coordinate> movesP = new ArrayList<Coordinate> ();
      Coordinate x;

      if (b.chessBoard[i-1][j+1] != 'o' && ((i-1)>=0 && (i-1)<8) && (j+1)<8) {
        x = new Coordinate(i, j, (byte)(i-1), (byte)(j+1));
        movesP.add(x);
        }

      if (b.chessBoard[i+1][j+1] != 'o' &&  (i+1)<8 && (j+1)<8) {
        x = new Coordinate(i, j, (byte)(i+1), (byte)(j+1));
        movesP.add(x);
      }

      if (i<8 && j<8) {
        x = new Coordinate(i, j, (byte)(i), (byte)(j+1));
        movesP.add(x);
      }

      if (b.hasPawnMoved(i, j) == false) {
        x = new Coordinate(i, j, (byte)(i), (byte)(j+2));
        movesP.add(x);
      }
    }
    if (b.chessBoard[i][j] == 'p') {
        ArrayList<Coordinate> movesP = new ArrayList<Coordinate> ();
        Coordinate x;

        if (b.chessBoard[i-1][j-1] != 'o' && ((i-1)>=0 && (i-1)<8) && (j-1)>=0 && (j-1)<8) {
          x = new Coordinate(i, j, (byte)(i-1), (byte)(j-1));
          movesP.add(x);
          }

        if (b.chessBoard[i+1][j-1] != 'o' &&  (i+1)<8 && (j-1)>=0 && (j-1)<8) {
          x = new Coordinate(i, j, (byte)(i+1), (byte)(j-1));
          movesP.add(x);
        }

        if (i<8 && j<8) {
          x = new Coordinate(i, j, (byte)(i), (byte)(j-1));
          movesP.add(x);
        }
        if (b.hasPawnMoved(i, j) == false) {
          x = new Coordinate(i, j, (byte)(i), (byte)(j-2));
          movesP.add(x);
        }
      }
    else {
      return null;
    }


    return movesP;
  }



}
