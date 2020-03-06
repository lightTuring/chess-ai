package Rules;

import java.util.ArrayList;

public class Controller {

  private Board b = new Board();

/*
  public ArrayList<Coordinate> getAllMoves(char[][] b) {
	return null;

  }
*/

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

    public ArrayList<Coordinate> getTowerMoves(byte i, byte j) {
	ArrayList<Coordinate> movesP = new ArrayList<Coordinate>();
	Coordinate x;
	// Checa se a torre não está à beira de baixo do mapa (de cima para baixo).
	if (i < 7) {
	    // Para cada casa a baixo na coluna.
	    for (int ii = i + 1; ii++; ii < 8) {
		if (b.getPiece(ii, j) == 'o') {
		    x = new Coordinate(i, j, ii, j);
		    movesP.add(x);
		} 
		else if (b.isWhite(i, j) && b.isWhite) {
		    // Interrompe o for-loop:
		    ii = 8;
		}
		else if (b.isBlack(i, j) && b.isBlack) {
		    // Interrompe o for-loop:
		    ii = 8;
		}
		else if (b.isWhite(i, j) && b.isBlack(ii, j)) {
			x = new Coordinate(i, j, ii, j);
			movesP.add(x);
		    }
		}
                else {
                    x = new Coordinate(i, j, ii, j);
                    movesP.add(x);
                    }
                }
	    }
        }
	// Checa se a torre não está à beira de cima do mapa (de cima para baixo).
	if (i > 0) {
	    // Para cada casa de cima na coluna.
	    for (int ii = i - 1; ii--; ii > 0) {
		if (b.getPiece(ii, j) == 'o') {
		    x = new Coordinate(i, j, ii, j);
		    movesP.add(x);
	    }
	}
    }
}
