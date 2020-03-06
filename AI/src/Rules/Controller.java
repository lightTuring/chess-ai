package Rules;

import java.util.ArrayList;

public class Controller {

  private Board b = new Board();

  public ArrayList<Coordinate> getAllMoves(char[][] b) {
	return null;

  }


  public ArrayList<Coordinate> movePawn(byte i, byte j) throws ArrayIndexOutOfBoundsException {
	  ArrayList<Coordinate> movesP = new ArrayList<Coordinate>();
	  Coordinate x;

    if (b.getPiece(i, j) == 'P') {

  		  if (b.getPiece(i+1, j-1) != 'o') {
  		    x = new Coordinate(i, j, (i-1), (j+1));
  		    movesP.add(x);
  		    }

  		  if (b.getPiece(i+1, j+1) != 'o' ) {
  		    x = new Coordinate(i, j, (i+1), (j+1));
  		    movesP.add(x);
  		  }

  		  if (b.getPiece(i, j+1) == 'o') {
  		    x = new Coordinate(i, j, (i), (j+1));
  		    movesP.add(x);
  		  }

  		  if (b.hasPawnMoved(i, j) == false && b.getPiece(i, j+2) == 'o') {
  		    x = new Coordinate(i, j, (i), (j+2));
  		    movesP.add(x);
  		  }
  		}
  		if (b.getPiece(i, j) == 'p') {

  		if (b.getPiece(i-1, j-1) != 'o') {
  		  x = new Coordinate(i, j, (i-1), (j-1));
  		  movesP.add(x);
  		  }

  		if (b.getPiece(i+1, j-1) != 'o' ) {
  		  x = new Coordinate(i, j, (i+1), (j-1));
  		  movesP.add(x);
  		}

  		if (b.getPiece(i, j-1) == 'o') {
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
