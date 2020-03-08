package Rules;

import java.util.ArrayList;

public class Controller {

  Board b = new Board();

/*
  public ArrayList<Coordinate> getAllMoves(char[][] b) {
	return null;

  }
*/

  public ArrayList<Coordinate> getPawnMoves(byte i, byte j) throws UnexpectedPieceException, BoardOutOfBoundsException {
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
         throw new UnexpectedPieceException("Argumentos dados a Controller.getPawnMoves não correspondem a um Peão.");
     }
     return movesP;
    }

    public ArrayList<Coordinate> getRookMoves(byte i, byte j) throws UnexpectedPieceException, BoardOutOfBoundsException {
	ArrayList<Coordinate> movesP = new ArrayList<Coordinate>();
	Coordinate x;
	// A checagem de peça vai ser feito no getAllMoves
	
	// Checa se a torre não está à beira de baixo do mapa (de cima para baixo).
	if (i < 7) {
	    // Para cada casa a baixo na coluna.
	    for (int ii = i + 1; ii < 8; ii++) {
		if (b.getPiece(ii, j) == 'o') {
		    x = new Coordinate(i, j, ii, j);
		    movesP.add(x);
		}
		else if (b.isWhite(i, j) && b.isWhite(ii, j)) {
		    // Interrompe o for-loop:
		    ii = 8;
		}
		else if (b.isBlack(i, j) && b.isBlack(ii, j)) {
		    // Interrompe o for-loop:
		    ii = 8;
		}
		else if (b.isWhite(i, j) && b.isBlack(ii, j)) {
			x = new Coordinate(i, j, ii, j);
			movesP.add(x);
		}
		// Se a torre é preta e a peça é branca.
                else {
                    x = new Coordinate(i, j, ii, j);
                    movesP.add(x);
                }
	    }
        }
	// Checa se a torre não está à beira de cima do mapa (de cima para baixo).
	if (i > 0) {
	    // Para cada casa de cima na coluna.
	    for (int ii = i - 1; ii > 0; ii--) {
		if (b.getPiece(ii, j) == 'o') {
		    x = new Coordinate(i, j, ii, j);
		    movesP.add(x);
		}
                else if (b.isWhite(i, j) && b.isWhite(ii, j)) {
                    // Interrompe o for-loop:
                    ii = 0;
                }
                else if (b.isBlack(i, j) && b.isBlack(ii, j)) {
                    // Interrompe o for-loop:
                    ii = 0;
                }
                else if (b.isWhite(i, j) && b.isBlack(ii, j)) {
                        x = new Coordinate(i, j, ii, j);
                        movesP.add(x);
                }
                // Se a torre é preta e a peça é branca.
                else {
                    x = new Coordinate(i, j, ii, j);
                    movesP.add(x);
                }
	    }
	}
	//
	if (j < 7) {
            for (int jj = j + 1; jj < 7; jj++) {
                if (b.getPiece(i, jj) == 'o') {
                    x = new Coordinate(i, j, i, jj);
                    movesP.add(x);
                }
                else if (b.isWhite(i, j) && b.isWhite(i, jj)) {
                    // Interrompe o for-loop:
                    jj = 8;
                }
                else if (b.isBlack(i, j) && b.isBlack(i, jj)) {
                    // Interrompe o for-loop:
                    jj = 8;
                }
                else if (b.isWhite(i, j) && b.isBlack(i, jj)) {
                        x = new Coordinate(i, j, i, jj);
                        movesP.add(x);
                }
                // Se a torre é preta e a peça é branca.
                else {
                    x = new Coordinate(i, j, i, jj);
                    movesP.add(x);
                }
            }
	}
	if (j > 0) {
            for (int jj = j - 1; jj < 0; jj--) {
                if (b.getPiece(i, jj) == 'o') {
                    x = new Coordinate(i, j, i, jj);
                    movesP.add(x);
                }
                else if (b.isWhite(i, j) && b.isWhite(i, jj)) {
                    // Interrompe o for-loop:
                    jj = 0;
                }
                else if (b.isBlack(i, j) && b.isBlack(i, jj)) {
                    // Interrompe o for-loop:
                    jj = 0;
                }
                else if (b.isWhite(i, j) && b.isBlack(i, jj)) {
                        x = new Coordinate(i, j, i, jj);
                        movesP.add(x);
                }
                // Se a torre é preta e a peça é branca.
                else {
                    x = new Coordinate(i, j, i, jj);
                    movesP.add(x);
                }
            }
	}
	return movesP;
    }

  public ArrayList<Coordinate> getKnightMoves(byte pos_i, byte pos_j) throws Exception{
		ArrayList<Coordinate> movesK = new ArrayList<Coordinate>();
	  Coordinate x;
		byte[] addingGeneralCoordinate = {-2, -1, -2, 1, 2, -1, 2, 1, -1, -2, -1, 2, 1, -2, 1, 2};
	    if(b.getPiece(pos_i, pos_j) == 'C' || b.getPiece(pos_i, pos_j) == 'c') {//if serve como uma segurança de que a peça é um cavalo
	    	for(byte i = 0; i < addingGeneralCoordinate.length; i+=2) {
    			if(((pos_i + addingGeneralCoordinate[i]) >= 0 && (pos_i + addingGeneralCoordinate[i]) <= 7) && ((pos_j + addingGeneralCoordinate[i+1]) >= 0 && (pos_j + addingGeneralCoordinate[i+1] <= 7))) {
    				x = new Coordinate(pos_i, pos_j, (pos_i + addingGeneralCoordinate[i]), (pos_j + addingGeneralCoordinate[i+1]));
        			movesK.add(x);
    			}
    		}
	    }else {
	    	 throw new UnexpectedPieceException("Argumentos dados a Controller.getKnightMoves não correspondem a um Cavalo");
	    }

		return movesK;

	}

    public ArrayList <Coordinate> getBishopMoves(byte pos_i, byte pos_j) throws BoardOutOfBoundsException {
      ArrayList<Coordinate> movesB = new ArrayList<Coordinate>();
      Coordinate x;
      

      for (byte i = 1; (pos_i + i) < 8; i++) {
        if (pos_j + i < 8 && b.getPiece(pos_i + i, pos_j + i) == 'o') {
          x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j + i);
          movesB.add(x);
        }
        else {
          break;
        }
      }
      for (byte i = 1; (pos_i + i) < 8; i++) {
        if (pos_j - i >= 0 && b.getPiece(pos_i + i, pos_j - i) == 'o') {
          x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j - i);
          movesB.add(x);
          }
        else {
          break;
          }
      }
      for (byte i = 1; (pos_i - i) >= 0; i++) {
        if (pos_j + i < 8 && b.getPiece(pos_i - i, pos_j + i) == 'o') {
          x = new Coordinate(pos_i, pos_j, pos_i - i, pos_j + i);
          movesB.add(x);
        }
        else {
          break;
        }
      }
      for (byte i = 1; (pos_i - i) >= 0; i++) {
        if ((pos_j - i) >= 0 && b.getPiece(pos_i - i, pos_j - i) == 'o') {
          x = new Coordinate(pos_i, pos_j, pos_i - i, pos_j - i);
          movesB.add(x);
        }
        else {
          break;
        }
      }
	return movesB;
  }

  public ArrayList<Coordinate> getKingMoves(byte i, byte j) {
	ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
	Coordinate c;
	for (int x = -1; x<=1 && x>=-1; x++) {
		for (int y = -1; y<=1 && y>=-1; y++) {
			if (y != 0 || x!=0) {
				c = new Coordinate(i, j, i + x, j+y);
				moves.add(c);
			}
		}
	}
	
	return moves;
  }
  //Fiquei com preguiça entao a rainha virou Ctrl c Ctrl v do bispo. A nomenclatura das variaveis da torre me fudeu então achei mais facil reescrever
  //Lembrar de não deixar isso no codigo final
  public ArrayList<Coordinate> getQueenMoves(byte pos_i, byte pos_j) throws BoardOutOfBoundsException {
    ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
    Coordinate x;
    boolean pathBlocked = false;
    byte t = 0;
    //Parte "Bispo"
    for (byte i = 1; (pos_i + i) < 8; i++) {
      if (pos_j + i < 8 && b.getPiece(pos_i + i, pos_j + i) == 'o') {
        x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j + i);
        moves.add(x);
      }
      else {
        break;
      }
    }
    for (byte i = 1; (pos_i + i) < 8; i++) {
      if (pos_j - i >= 0 && b.getPiece(pos_i + i, pos_j - i) == 'o') {
        x = new Coordinate(pos_i, pos_j, pos_i + i, pos_j - i);
        moves.add(x);
        }
      else {
        break;
        }
    }
    for (byte i = 1; (pos_i - i) >= 0; i++) {
      if (pos_j + i < 8 && b.getPiece(pos_i - i, pos_j + i) == 'o') {
        x = new Coordinate(pos_i, pos_j, pos_i - i, pos_j + i);
        moves.add(x);
      }
      else {
        break;
      }
    }
    for (byte i = 1; (pos_i - i) >= 0; i++) {
      if ((pos_j - i) >= 0 && b.getPiece(pos_i - i, pos_j - i) == 'o') {
        x = new Coordinate(pos_i, pos_j, pos_i - i, pos_j - i);
        moves.add(x);
      }
      else {
        break;
      }
    }

    //Parte "Torre" sem captura
    
    //Para uma fileira (Versão 1 do algoritmo)
    for (byte j = 0; j < pos_j; j++) {
      if(b.getPiece(pos_i, j) != 'o') {
        pathBlocked = true;
        int q = j;
          while (q - pos_j < -1) {
            x = new Coordinate(pos_i, pos_j, pos_i, ++q);
            moves.add(x);
          }
        }
      }
    if (pathBlocked = false) {
      for (byte j = 0; j < pos_i; j++) {
        x = new Coordinate(pos_i, pos_j, pos_i, j);
        moves.add(x);
      }
    }
    else {
      pathBlocked = false;
    }
    for (byte j = (byte) (pos_j + 1); j<8; j++) {
      if (b.getPiece(pos_i, j) == 'o') {
        x = new Coordinate(pos_i, pos_j, pos_i, j);
        moves.add(x);
      }
      else {
        break;
      }
    }
    //Para uma coluna (Versão 2 do algoritmo)
    for (byte i = 0; i<pos_i; i++) {
      if(b.getPiece(i, pos_j) != 'o') {
        pathBlocked = true;
        t = i;
        }
    }
    if (pathBlocked = true) {
      for (byte i = (byte)(t+1); i < pos_i; i++) {
        x = new Coordinate(pos_i, pos_j, i, pos_j);
        moves.add(x);
      }
    }
    else {
      pathBlocked = false;
    }
    for (byte i = (byte) (pos_i + 1); i<8; i++) {
      if (b.getPiece(i, pos_j) == 'o') {
        x = new Coordinate(pos_i, pos_j, i, pos_j);
        moves.add(x);
      }
      else {
        break;
      }
    }

    //Versão anterior
    /*for (byte i = 0; i < 8; i++) {
      if (i != pos_i) {
        
        if (b.getPiece(i, pos_j) != 'o' && i - pos_i < -1) {
          int q = i;
          while (q - pos_i < -1) {
            x = new Coordinate(pos_i, pos_j, ++q, pos_j);
            moves.add(x);
          }
        }
        else if (b.getPiece(i, pos_j) != 'o' && (i - pos_i) > 1){
          int w = i;
          while ((w- pos_i) > 1) {
            x = new Coordinate(pos_i, pos_j, --w, pos_j);
            moves.add(x);
          }
          break;
        }
        else {

        }
      }
    }
*/
    return moves;
  }
}
