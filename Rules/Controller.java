import java.util.ArrayList;

public class Controller {

  //A checagem de tipo de peça será realizada aqui
  public ArrayList getAllMoves(byte i, byte j, char[][] b) {

    }


  public ArrayList<Coordinate> movePawn(byte i, byte j, char[][] b) {

    ArrayList<Coordinate> moves = new ArrayList<Coordinate> ();
    Coordinate x;

    if (b[i-1][j+1] != 'o' && ((i-1)>=0 && (i-1)<8) && (j+1)<8) {
      x = new Coordinate(i, j, (byte)(i-1), (byte)(j+1);
      moves.add(x);
      }
    else if (char[i+1][j+1] != 'o') {
      
    }
  }
}
