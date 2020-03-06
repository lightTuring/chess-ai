package Rules;

import java.util.ArrayList;

public class Controller {

  private Board b = new Board();

  public ArrayList<Coordinate> getAllMoves(char[][] b) {
	return null;

  }


  public ArrayList<Coordinate> getPawnMoves(byte i, byte j) throws Exception {
    ArrayList<Coordinate> movesP = new ArrayList<Coordinate>();
    Coordinate x;
    // Se o peão for Branco:
    if (b.getPiece(i, j) == 'P') {
	// Se a casa na diagonal esquerda tiver alguma peça:
	if (b.getPiece(i+1, j-1) != 'o') {
  	    x = new Coordinate(i, j, i+1, j-1);
  	    movesP.add(x);
  	}
        // Se a casa na diagonal direita tiver alguma peça:
  	if (b.getPiece(i+1, j+1) != 'o') {
 	    x = new Coordinate(i, j, (i+1), (j+1));
  	    movesP.add(x);
 	}
	// Se a casa a frente estiver vazia:
  	if (b.getPiece(i+1, j) == 'o') {
  	    x = new Coordinate(i, j, (i+1), (j));
  	    movesP.add(x);
 	}
	// Se no primeiro movimento do peão, a casa na frente da frente estiver vazia:
  	if (b.hasPawnMoved(i, j) == false && b.getPiece(i+2, j) == 'o') {
  	    x = new Coordinate(i, j, (i+2), (j));
  	    movesP.add(x);
  	}
     }
     if (b.getPiece(i, j) == 'p') {

	if (b.getPiece(i-1, j+1) != 'o') {
	    x = new Coordinate(i, j, (i-1), (j+1));
  	    movesP.add(x);
	}

  	if (b.getPiece(i-1, j-1) != 'o' ) {
  	    x = new Coordinate(i, j, (i-1), (j-1));
  	    movesP.add(x);
  	}
 
	if (b.getPiece(i-1, j) == 'o') {
  	    x = new Coordinate(i, j, (i-1), (j));
  	     movesP.add(x);
  	}
  	if (b.hasPawnMoved(i, j) == false && b.getPiece(i-2, j) == 'o') {
  	    x = new Coordinate(i, j, (i-2), (j));
  	    movesP.add(x);
        }
     }
     else {
         throw new IllegalArgumentException("Argumentos dados a getLegalPawn não correspondem a um peão.");
     }
     return movesP;
    }
}
