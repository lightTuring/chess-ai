import java.util.ArrayList;

public class Controller {

  Board b = new Board();

  public ArrayList<Coordinate> getAllMoves(char[][] b) {

    }


  public ArrayList<Coordinate> movePawn(byte i, byte j) {
    if (b.getPiece(i, j) == 'P') {
      ArrayList<Coordinate> movesP = new ArrayList<Coordinate> ();
      Coordinate x;

      if (b.getPiece(i-1, j+1) != 'o' && ((i-1)>=0 && (i-1)<8) && (j+1)<8) {
        x = new Coordinate(i, j, (i-1), (j+1));
        movesP.add(x);
        }

      if (b.getPiece(i+1, j+1) != 'o' &&  (i+1)<8 && (j+1)<8) {
        x = new Coordinate(i, j, (i+1), (j+1));
        movesP.add(x);
      }

      if (i<8 && j<8 && b.getPiece(i, j+1) == 'o') {
        x = new Coordinate(i, j, (i), (j+1));
        movesP.add(x);
      }

      if (b.hasPawnMoved(i, j) == false && b.getPiece(i, j+2) == 'o') {
        x = new Coordinate(i, j, (i), (j+2));
        movesP.add(x);
      }
    }
    if (b.getPiece(i, j) == 'p') {
        ArrayList<Coordinate> movesP = new ArrayList<Coordinate> ();
        Coordinate x;

        if (b.getPiece(i-1, j-1) != 'o' && ((i-1)>=0 && (i-1)<8) && (j-1)>=0 && (j-1)<8) {
          x = new Coordinate(i, j, (i-1), (j-1));
          movesP.add(x);
          }

        if (b.getPiece(i+1, j-1) != 'o' &&  (i+1)<8 && (j-1)>=0 && (j-1)<8) {
          x = new Coordinate(i, j, (i+1), (j-1));
          movesP.add(x);
        }

        if (i<8 && j<8 && b.getPiece(i, j-1) == 'o') {
          x = new Coordinate(i, j, (i), (j-1));
          movesP.add(x);
        }
        if (b.hasPawnMoved(i, j) == false && b.getPiece(i, j-2) == 'o') {
          x = new Coordinate(i, j, (i), (j-2));
          movesP.add(x);
        }
      }
    else {
      return null;
    }


    return movesP;
  }



}
