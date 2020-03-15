package Rules;

import java.util.ArrayList;
import java.util.Iterator;

public class Controller {

  Board b = new Board();

/*
  public ArrayList<Coordinate> getAllMoves(char[][] b) {
	return null;

  }
*/

  public ArrayList<Coordinate> getPawnMoves(int i, int j) throws UnexpectedPieceException, BoardOutOfBoundsException {
    ArrayList<Coordinate> movesP = new ArrayList<Coordinate>();
    Coordinate x;
    // Se o peão for Branco:
    if (b.getPiece(i, j) == 'P') {
	// Se a casa na diagonal esquerda tiver alguma peça:
	if (b.getPiece(i-1, j-1) != 'o') {
		 x = new Coordinate(i-1, j-1);
		movesP.add(x);
  	}
        // Se a casa na diagonal direita tiver alguma peça:
  	if (b.getPiece(i-1, j+1) != 'o') {
		x = new Coordinate((i-1), (j+1));
		movesP.add(x);
 	}
	// Se a casa a frente estiver vazia:
  	if (b.getPiece(i-1, j) == 'o') {
  	    x = new Coordinate((i-1), (j));
  	    movesP.add(x);
 	}
	// Se no primeiro movimento do peão, a casa na frente da frente estiver vazia:
  	if (b.hasPawnMoved(i, j) == false && b.getPiece(i-2, j) == 'o') {
  	    x = new Coordinate((i-2), (j));
  	    movesP.add(x);
  	}
     }
     if (b.getPiece(i, j) == 'p') {

	if (b.getPiece(i+1, j+1) != 'o') {
	    x = new Coordinate((i+1), (j+1));
  	    movesP.add(x);
	}

  	if (b.getPiece(i+1, j-1) != 'o' ) {
  	    x = new Coordinate((i+1), (j-1));
  	    movesP.add(x);
  	}

	if (b.getPiece(i+1, j) == 'o') {
  	    x = new Coordinate((i+1), (j));
  	    movesP.add(x);
  	}
  	if (b.hasPawnMoved(i, j) == false && b.getPiece(i+2, j) == 'o') {
  	    x = new Coordinate((i+2), (j));
  	    movesP.add(x);
        }
     }
     
     return movesP;
    }

    public ArrayList<Coordinate> getRookMoves(int i, int j) throws BoardOutOfBoundsException {
	    ArrayList<Coordinate> movesP = GenericMove.rookGen(i, j, b);
	    return movesP;
    }

  public ArrayList<Coordinate> getKnightMoves(int pos_i, int pos_j) throws Exception{
		ArrayList<Coordinate> movesK = new ArrayList<Coordinate>();
	  Coordinate x;
		byte[] addingGeneralCoordinate = {-2, -1, -2, 1, 2, -1, 2, 1, -1, -2, -1, 2, 1, -2, 1, 2};
	    if(b.getPiece(pos_i, pos_j) == 'C' || b.getPiece(pos_i, pos_j) == 'c') {//if serve como uma segurança de que a peça é um cavalo
	    	for(byte i = 0; i < addingGeneralCoordinate.length; i+=2) {
    			if(((pos_i + addingGeneralCoordinate[i]) >= 0 && (pos_i + addingGeneralCoordinate[i]) <= 7) && ((pos_j + addingGeneralCoordinate[i+1]) >= 0 && (pos_j + addingGeneralCoordinate[i+1] <= 7))) {
    				x = new Coordinate((pos_i + addingGeneralCoordinate[i]), (pos_j + addingGeneralCoordinate[i+1]));
        			movesK.add(x);
    			}
    		}
	    }else {
	    	 throw new UnexpectedPieceException("Argumentos dados a Controller.getKnightMoves não correspondem a um Cavalo");
	    }

		return movesK;

	}

    public ArrayList <Coordinate> getBishopMoves(int pos_i, int pos_j) throws BoardOutOfBoundsException {
      
      return GenericMove.bishopGen(pos_i, pos_j, b);
    }

  public ArrayList<Coordinate> getKingMoves(int i, int j) {
	ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
	Coordinate c;
	for (int x = -1; x<=1 && x>=-1; x++) {
		for (int y = -1; y<=1 && y>=-1; y++) {
			if (y != 0 || x!=0) {
				c = new Coordinate(i + x, j+y);
				moves.add(c);
			}
		}
	}
	
	return moves;
  }

  public ArrayList<Coordinate> getQueenMoves(int pos_i, int pos_j) throws BoardOutOfBoundsException {
    ArrayList<Coordinate> moves = GenericMove.bishopGen(pos_i, pos_j, b);
    ArrayList<Coordinate> list = GenericMove.rookGen(pos_i, pos_j, b);
    Iterator<Coordinate> i = list.iterator();

    while (i.hasNext()){
      moves.add(i.next());
    }
    return moves;
  }
    
    
  
}
